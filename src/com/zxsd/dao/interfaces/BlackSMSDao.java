package com.zxsd.dao.interfaces;

import java.util.List;

import com.zxsd.dao.bean.BlackSMS;

/**
 * 短信黑名单
 * @ClassName BlackSMSDao   
 * @Description 
 * @author  zhy
 * @date 2018年9月3日 上午9:04:47  
 * @version v1.0 2018年9月3日 上午9:04:47
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2018年9月3日 上午9:04:47　　V1.0　　 build此模块
 */
public interface BlackSMSDao {
	
	
	/**
	 * 查询黑名单列表
	 * @Title queryBlackSMSList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2018年9月3日 上午9:07:46 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	public List<BlackSMS> queryBlackSMSList();
	
	
}
