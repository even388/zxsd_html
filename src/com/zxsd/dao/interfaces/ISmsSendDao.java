package com.zxsd.dao.interfaces;

import java.util.List;

import com.zxsd.dao.bean.SmsMessageEntity;

public interface ISmsSendDao {

	/**
	 * 查询短信集合
	 * 
	 * @Title selectSmsMessageList
	 * @Description TODO
	 * @param sender_uuid
	 * @return
	 * @throws
	 * @author zhy
	 * @date 2018-5-4 上午10:01:04
	 * @version v1.0 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<SmsMessageEntity> retrieveTask(String sender_uuid);

	/**
	 * 修改短信状态
	 * 
	 * @Title updateSmsState
	 * @Description TODO
	 * @param id
	 * @param state
	 * @return
	 * @throws
	 * @author zhy
	 * @date 2018-5-4 上午10:07:44
	 * @version v1.0 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	boolean finishTask(String id, String uuid, String state);

	
	boolean insertMessageShort(SmsMessageEntity smsEntity);

	/**
	 * @Title occupyTask 
	 * @Description 抢占任务（指定时间内的任务）
	 * @param sender_uuid 
	 * @return
	 * @throws 
	 * @author  乔文博
	 * @date 2018年9月29日 下午4:52:05 
	 * @version v1.0
	 */
	boolean occupyTask(String sender_uuid);
}
