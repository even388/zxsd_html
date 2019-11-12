package com.zxsd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxsd.dao.bean.BlackSMS;
import com.zxsd.dao.interfaces.BlackSMSDao;
import com.zxsd.service.interfaces.BlackSMSService;
/**
 * 短信黑名单
 * @ClassName BlackSMSImpl   
 * @Description 
 * @author  zhy
 * @date 2018年9月3日 上午9:03:25  
 * @version v1.0 2018年9月3日 上午9:03:25
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2018年9月3日 上午9:03:25　　V1.0　　 build此模块
 */
@Service
public class BlackSMSImpl implements BlackSMSService {
	
	@Autowired
	private BlackSMSDao blackSMSDao;
	public List<BlackSMS> queryBlackSMSList(){
		return blackSMSDao.queryBlackSMSList();
	}
	
}
