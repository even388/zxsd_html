package com.zxsd.dao.impl;

import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.CodeEntity;
import com.zxsd.dao.interfaces.ICodeLibraryDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CodeLibraryDaoImpl extends BaseDao implements ICodeLibraryDao {
	
	@Override
	public boolean updateCodeLibaryWord(int wordId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into tb_code_library (id,coded_attr_id,type) ");
		sql.append(" select word_id,word_id,2 from tb_word where status=3 and is_publish=0 and word_id>=? limit 0,100");
		return this.update(sql.toString(), new Object[]{wordId});
	}

	@Override
	public CodeEntity getMaxCodeId(String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select max(id) id,update_time from tb_code_library");
		sql.append(" where type=?");
		return (CodeEntity) this.queryObject(sql.toString(), new Object[]{type}, new BeanPropertyRowMapper(CodeEntity.class));
	} 

	@Override
	public boolean deleteCodeLibary(String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from tb_code_library");
		sql.append(" where type=?");
		return this.update(sql.toString(), new Object[]{type});
	}

	@Override
	public int updateCodeUid(String suid) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().update("update tb_code_library set suid=? where suid is null", new Object[]{suid});
	}

	@Override
	public boolean clearCodeUid() {
		// TODO Auto-generated method stub
		return this.update("update tb_code_library set suid=? where suid is null", null);
	}

	@Override
	public int getMaxWordId() {
		return this.getJdbcTemplate().queryForInt("select max(word_id) from tb_word where status=3 and is_publish=0");
	}

	@Override
	public int getMinWordId() {
		return this.getJdbcTemplate().queryForInt("select min(word_id) from tb_word where status=3 and is_publish=0");
	}

	@Override
	public boolean insertIntoCodeLibrary(List<Map<String, Object>> list,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into tb_code_library (id,coded_attr_id,type) ");
		sql.append(" values(?,?,?)");
		List<Object[]> list1 = new ArrayList<>();
		for(Map<String, Object> map :list){
			list1.add(new Object[]{map.get("id"),map.get("barcode"),type});
		}
		this.getJdbcTemplate().batchUpdate(sql.toString(),list1);
		return true;
	}

	@Override
	public List<Map<String, Object>> queryCatetoryByweek() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select type_id from tb_category where status =5 and ");
		sql.append(" DATE_SUB(CURDATE(), INTERVAL 6 DAY) <= date(publish_time)");
		return this.getJdbcTemplate().queryForList(sql.toString());
	}


}
