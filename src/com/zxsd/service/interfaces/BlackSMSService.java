package com.zxsd.service.interfaces;

import java.util.List;

import com.zxsd.dao.bean.BlackSMS;
import com.zxsd.dao.interfaces.BlackSMSDao;

/**
 * 短信黑名单
 * @ClassName BlackSMSService   
 * @Description 
 * @author  zhy
 * @date 2018年9月3日 上午9:02:47  
 * @version v1.0 2018年9月3日 上午9:02:47
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2018年9月3日 上午9:02:47　　V1.0　　 build此模块
 */
public interface BlackSMSService {
	
	public List<BlackSMS> queryBlackSMSList();
	
}
