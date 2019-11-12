package com.zxsd.dao.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.AfterCustomService;
import com.zxsd.dao.bean.AfterExhibition;
import com.zxsd.dao.bean.ArticleEntity;
import com.zxsd.dao.bean.CityAddr;
import com.zxsd.dao.interfaces.IAfterSaleDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AfterSaleDaoImpl extends BaseDao implements IAfterSaleDao {

	@Override
	public List<AfterExhibition> queryAfterExhiList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_after_exhibition order by block_num,seq_num asc");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(AfterExhibition.class));
	}

	@Override
	public List<ArticleEntity> queryAfterChannelArticleList(String goods_class_id,String column_id) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		sql.append(" select article_id,user_true_name,title,title_image,channel_id,column_id,");
		sql.append(" source,content_intro,publish_date,page_url");
		sql.append(" from tb_article  where is_publish = 0 and status =3 and channel_id=112 ");
		if(!StringUtil.isNOrS(column_id)){
			sql.append(" and column_id = ?");
			list.add(column_id);
		}
		sql.append(" order by publish_date desc limit 0,5");
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<AfterCustomService> queryAfterServiceList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_custom_service where status=0 order by custom_initial");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(AfterCustomService.class));
	}

	@Override
	public List<CityAddr> findCity() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from tb_addr where is_enabled=0 and addr_level=1");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(CityAddr.class));
	}

}
