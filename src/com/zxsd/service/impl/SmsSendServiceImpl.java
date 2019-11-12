package com.zxsd.service.impl;

import com.zxsd.common.HttpSenderNew;
import com.zxsd.common.RedisClient;
import com.zxsd.common.StringUtil;
import com.zxsd.dao.bean.BlackSMS;
import com.zxsd.dao.bean.SmsMessageEntity;
import com.zxsd.dao.interfaces.ISmsSendDao;
import com.zxsd.service.interfaces.BlackSMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 短信发送 定时类
 * 
 * @ClassName SmsSendServiceImpl
 * @Description
 * @author zhy
 * @date 2018-5-4 上午9:53:53
 * @version v1.0 2018-5-4 上午9:53:53
 * @history 历史修改记录 <作者>　 <日期>　　 <版本>　 <描述> zhy　　　2018-5-4 上午9:53:53　　V1.0　　
 *          build此模块
 */

@Component
@EnableScheduling
public class SmsSendServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(SmsSendServiceImpl.class);
	private static final String SERVER_NAME;;
	static {
		String hn;
		try {
			hn = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error("无法获取主机名。",e);
			hn = System.currentTimeMillis()+"";
		}
		SERVER_NAME = hn;
	}
	@Autowired
	private ISmsSendDao smsSendDao;
	@Autowired
	private BlackSMSService blackSMSService;

	// 定时任务 每隔1秒执行一次 单线程执行，上次执行完下次在执行

	public void smsSend() {
		
		Date date = new Date();
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(Calendar.MINUTE , -30);
//		String sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//				.format(date);
		//30分钟以前不发送
//		String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(cal.getTime());
		// 当前时间30以内未发送短信
		/*boolean existTask =*/ smsSendDao.occupyTask(SERVER_NAME); // 抢占任务（在 SQL 中实现 30分钟以前不发送）
//		if(!existTask) { // 没有抢占到任务
//			continue;
//		} //没有抢到任务也要继续执行，因为会有因宕机等情况造成的没有发送成功的短信。
	
		List<SmsMessageEntity> smsList = smsSendDao.retrieveTask(SERVER_NAME);
		if (smsList != null && smsList.size() > 0) {
			//短信黑名单
			List <BlackSMS> bList = blackSMSService.queryBlackSMSList();
			Set<String> bSet = new LinkedHashSet<>();
			for(BlackSMS sms : bList){
				bSet.add(sms.getMobile_no());
			}
			List<SmsMessageEntity> invalidMessage = new ArrayList<>();
			Iterator<SmsMessageEntity> it = smsList.iterator();
			//去除掉短信黑名单号码
			while (it.hasNext()){
				SmsMessageEntity mes = it.next();
				String mobile_no = mes.getMobile_no();
				if(mobile_no.indexOf(",") != -1){
					//多个
					String [] mobile = mobile_no.split(",");
					for(String str : mobile){
						if(bSet.contains(str)){
							mobile_no = mobile_no.replace(str, "");
						};
					}
					//若该条记录中的号码都是黑名单的，则移除该条记录
					if(mobile_no.replaceAll(",", "").trim().equals("")){
						invalidMessage.add(mes);
						it.remove();
					}else{
					    mobile_no = mobile_no.replaceAll(",{2,}", ",");
					    if(mobile_no.startsWith(",")){
					    	mobile_no = mobile_no.substring(1, mobile_no.length());
					    }
					    if(mobile_no.endsWith(",")){
					    	mobile_no = mobile_no.substring(0, mobile_no.length()-1);
					    }
						mes.setMobile_no(mobile_no);
					}
				}else{
					if(bSet.contains(mobile_no)){
						invalidMessage.add(mes);
						 it.remove();
					}
				}
			}
			for (SmsMessageEntity sms : smsList) {
				try {
					// 发送短信
					
//					/*boolean updateSmsUUid = */smsSendDao.updateSmsUUid(sendTime,sms.getMessage_id(), SERVER_NAME);
					
//					SmsMessageEntity smmessage = smsSendDao.selectSmsByUuid(SERVER_NAME);
					
					//HttpSender.batchSend(sms.getMobile_no(), sms.getMessage(),null, false);
					logger.info(sms.getMobile_no()+"--"+sms.getMessage());
			  		HttpSenderNew.sendSms(sms.getMobile_no(),sms.getMessage());
			  		
					// 正常完成发送。更新短信状态
					smsSendDao.finishTask(sms.getMessage_id(), SERVER_NAME, "1"); 
					//统计1小时内短信数量
					if(!sms.getType().equals("5")){
						String rediskey = "SMS_"+new SimpleDateFormat("yyyy-MM-dd-HH").format(date);
						String smsNum = RedisClient.getInstance().get(rediskey);
						if(smsNum == null || smsNum == ""){
							RedisClient.getInstance().add(rediskey,1, 60*60);
						}else{
							RedisClient.getInstance().add(rediskey, Integer.parseInt(smsNum)+1, 60*60);
							if(Integer.parseInt(smsNum) > 200 && StringUtil.isNOrS(RedisClient.getInstance().get(rediskey+"_ISSEND"))){
								SmsMessageEntity smsEntity = new SmsMessageEntity();
								smsEntity.setMessage(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+",本小时短信已发送200条");
								smsEntity.setMobile_no("13661316114,18610322158,13264583604");
								smsSendDao.insertMessageShort(smsEntity);
								RedisClient.getInstance().add(rediskey+"_ISSEND","true",60*60);
							}
						};
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			
			for(SmsMessageEntity sms : invalidMessage) {
				// 对于无效的短信也要更改状态
				smsSendDao.finishTask(sms.getMessage_id(), SERVER_NAME, "2");
			}
		}
	}
	
}
