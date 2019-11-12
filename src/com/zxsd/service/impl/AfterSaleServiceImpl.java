package com.zxsd.service.impl;

import com.zxsd.dao.bean.*;
import com.zxsd.dao.interfaces.IAfterSaleDao;
import com.zxsd.dao.interfaces.IContTemplate;
import com.zxsd.service.interfaces.IAfterSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AfterSaleServiceImpl implements IAfterSaleService {
	@Autowired
	private IAfterSaleDao afterSaleDao;
	@Autowired
	private IContTemplate contTemplate;
	@Override
	public List<AfterExhibition> queryAfterExhiList() {
		// TODO Auto-generated method stub
		List<AfterExhibition> newList = new ArrayList<AfterExhibition>();
		List<AfterExhibition> list = afterSaleDao.queryAfterExhiList();
		for(AfterExhibition af:list){
			//品牌
			if(af.getExhibition_type().equals("2")){
				BrandEntity brand = contTemplate.getBrandInfo(af.getMap_id());
				if(brand != null){
					af.setWord_name_show(brand.getBrand_name_s());
					newList.add(af);
				}
			}else{
				newList.add(af);
			}
		}
		return newList;
	}

	@Override
	public List<ArticleEntity> queryAfterChannelArticleList(String goods_class_id,String column_id) {
		// TODO Auto-generated method stub
		List<ArticleEntity> list = afterSaleDao.queryAfterChannelArticleList(goods_class_id,column_id);
		return list;
	}

	@Override
	public List<AfterCustomService> queryAfterServiceList() {
		// TODO Auto-generated method stub
		return afterSaleDao.queryAfterServiceList();
	}

	@Override
	public List<CityAddr> findCity() {
		// TODO Auto-generated method stub
		return afterSaleDao.findCity();
	}

}
