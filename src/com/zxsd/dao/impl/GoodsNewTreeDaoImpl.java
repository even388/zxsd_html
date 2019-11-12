package com.zxsd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.GoodsNewTreeEntity;
import com.zxsd.dao.interfaces.IGoodsNewTreeDao;


@Repository
public class GoodsNewTreeDaoImpl extends BaseDao<GoodsNewTreeEntity> implements IGoodsNewTreeDao {

	@Override
	public List<GoodsNewTreeEntity> getGoodsNewTreeList(String good_class_id,
			String parent_good_class_id,boolean isShow) {
		StringBuffer sql = new StringBuffer();
		List<String> paramlist = new ArrayList<>();
		sql.append(" select t1.*,t2.label_name,t2.label_status,t2.hot_spot from tb_good_tree t1");
		sql.append(" left join tb_good_label t2 on t1.label_id=t2.id where 1=1");
		if(isShow){
			sql.append(" and t2.label_status=1");
		}
		if(!StringUtil.isNOrS(good_class_id)){
			sql.append(" and t1.good_class_id=?");
			paramlist.add(good_class_id);
		}
		if(!StringUtil.isNOrS(parent_good_class_id)){
			sql.append(" and t1.parent_good_class_id=?");
			paramlist.add(parent_good_class_id);
		}
		sql.append(" order by t1.sort");
		return this.queryList(sql.toString(), paramlist.toArray(), new BeanPropertyRowMapper(GoodsNewTreeEntity.class));
	}

}
