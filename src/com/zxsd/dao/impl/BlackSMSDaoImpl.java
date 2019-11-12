package com.zxsd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.BlackSMS;
import com.zxsd.dao.interfaces.BlackSMSDao;

@Repository
public class BlackSMSDaoImpl extends BaseDao<BlackSMS> implements BlackSMSDao {

	@Override
	public List<BlackSMS> queryBlackSMSList() {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		sql.append("SELECT * FROM tb_member_short_back");
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(BlackSMS.class));
	}
	
}
