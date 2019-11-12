package com.zxsd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.SmsMessageEntity;
import com.zxsd.dao.interfaces.ISmsSendDao;

@Repository
public class SmsSendDaoImpl extends BaseDao<SmsMessageEntity> implements
		ISmsSendDao {

	@Override
	public List<SmsMessageEntity> retrieveTask(String sender_uuid) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" SELECT * FROM tb_member_short_message WHERE sender_uuid = ? AND state=0");
		list.add(sender_uuid);
		return this.queryList(sql.toString(), list.toArray(),new BeanPropertyRowMapper(SmsMessageEntity.class));
	}
	
	@Override
	public boolean occupyTask(String sender_uuid) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(""
				+ " UPDATE  tb_member_short_message "
				+ " SET sender_uuid=? "
				+ " WHERE state=0 "
				+ "     AND sender_uuid IS NULL "
				+ "     AND  create_time >= DATE_SUB(NOW(), INTERVAL 30 MINUTE) "
				+ " LIMIT 50");
		list.add(sender_uuid);
		return this.update(sql.toString(), list.toArray());
	}

	@Override
	public boolean finishTask(String id, String uuid, String state) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" UPDATE tb_member_short_message SET state=?,sender_time=now(), sender_uuid=null WHERE state=0 AND message_id=?  AND sender_uuid = ? ");
		list.add(state);
		list.add(id);
		list.add(uuid);
		return this.update(sql.toString(), list.toArray());
	}

	@Override
	public boolean insertMessageShort(SmsMessageEntity smsEntity) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into tb_member_short_message (message,mobile_no,state,create_time,type,terminal) values (?,?,0,now(),9,1)");
		return this.update(sql.toString(), new Object[]{smsEntity.getMessage(),smsEntity.getMobile_no()});
	}

}
