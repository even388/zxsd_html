package com.zxsd.service.interfaces;

import java.util.List;

import com.zxsd.dao.bean.GoodsNewTreeEntity;


public interface IGoodsNewTreeService {
	
	List<GoodsNewTreeEntity> getGoodsNewTreeList(String good_class_id,String parent_good_class_id,boolean isShow);
}
