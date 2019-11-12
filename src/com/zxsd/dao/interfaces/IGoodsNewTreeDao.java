package com.zxsd.dao.interfaces;

import java.util.List;

import com.zxsd.dao.bean.GoodsNewTreeEntity;


public interface IGoodsNewTreeDao {
	
	List<GoodsNewTreeEntity> getGoodsNewTreeList(String good_class_id,String parent_good_class_id,boolean isShow);
}
