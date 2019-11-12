package com.zxsd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxsd.dao.bean.GoodsNewTreeEntity;
import com.zxsd.dao.interfaces.IGoodsNewTreeDao;
import com.zxsd.service.interfaces.IGoodsNewTreeService;

@Service
public class GoodsNewTreeServiceImpl implements IGoodsNewTreeService {
	
	@Autowired
	private IGoodsNewTreeDao goodsNewTreeDao;
	
	@Override
	public List<GoodsNewTreeEntity> getGoodsNewTreeList(String good_class_id,
			String parent_good_class_id,boolean isShow) {
		// TODO Auto-generated method stub
		return goodsNewTreeDao.getGoodsNewTreeList(good_class_id, parent_good_class_id,isShow);
	}

}
