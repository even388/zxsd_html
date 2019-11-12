package com.zxsd.service.interfaces;

import java.util.List;

import com.zxsd.dao.bean.AfterCustomService;
import com.zxsd.dao.bean.AfterExhibition;
import com.zxsd.dao.bean.ArticleEntity;
import com.zxsd.dao.bean.CityAddr;

public interface IAfterSaleService {
	/**
	  * 获取售后服务行业数据
	  * @Title queryAfterExhiList 
	  * @Description TODO
	  * @return
	  * @throws 
	  * @author  zhy
	  * @date 2017-10-10 下午3:48:06 
	  * @version v1.0
	  * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	  */
	 List<AfterExhibition> queryAfterExhiList();
	 
	 /**
	  * 获取售后服务频道栏目数据
	  * @Title queryAfterChannelArticleList 
	  * @Description TODO
	  * @return
	  * @throws 
	  * @author  zhy
	  * @date 2017-10-10 下午3:48:36 
	  * @version v1.0
	  * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	  */
	 List<ArticleEntity> queryAfterChannelArticleList(String goods_class_id,String column_id);
	 
	 /**
	  * 获取售后服务快捷网点
	  * @Title queryAfterServiceList
	  * @Description TODO
	  * @return
	  * @throws 
	  * @author  zhy
	  * @date 2017-10-10 下午3:49:01 
	  * @version v1.0
	  * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	  */
	 List<AfterCustomService> queryAfterServiceList();
	 
	 /**
	  * 获取省份列表
	  * @Title findCity 
	  * @Description TODO
	  * @return
	  * @throws 
	  * @author  zhy
	  * @date 2017-10-16 下午4:16:24 
	  * @version v1.0
	  * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	  */
	 List<CityAddr> findCity();
}
