package com.zxsd.service.interfaces;

import com.zxsd.dao.bean.*;

import java.util.List;

public interface IContTemplateService {
	 /** 获取模板数据
	 * @Title queryIndexListForManager 
	 * @Description TODO
	 * @param channelName
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-25 上午9:48:36 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ContTemplate> queryIndexList(String channelName,String columnName);
	
	/** 获取新模板数据
	 * @Title queryIndexListForManager 
	 * @Description TODO
	 * @param channelName
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-25 上午9:48:36 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ContTempletNew> queryNewIndexList(String channelName);
	/**
	 * 获取友情链接
	 * @Title queryWebLinkList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-25 上午9:48:50 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<WebsiteLink> queryWebLinkList(String channel_id);
	/**
	 * 获取焦点图
	 * @Title getImagesByChalId 
	 * @Description TODO
	 * @param channelId
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-25 上午9:49:16 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<FocusImageEntity> getImagesByChalId(String channelId);
	/**
	 * 商品分类
	 * @Title getAllGoodsResources 
	 * @Description TODO
	 * @param goods_class_type
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-25 下午4:46:40 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<GoodsClassEntity> getAllGoodsResources(String goods_class_type);
	
	/**
	 * 曝光台
	 * @Title getArticlesByColid 
	 * @Description TODO
	 * @param
	 * @param
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-26 下午4:38:31 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getArticlesByColid(String channelId,String columnId,String columnName,String searchType,String limits,boolean isStick,boolean isColid);
	
	/**
	 * 获取频道名称
	 * @Title queryChanname 
	 * @Description TODO
	 * @param channelId
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-26 下午5:16:54 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	String queryChanname(String channelId);
	
	/**
	 * 最新曝光线索
	 * @Title getNewMemContents 
	 * @Description TODO
	 * @param channelId
	 * @param columnId
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-26 下午5:38:01 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<MemberContent> getNewMemContents(String channelId,String columnId,
			String limits,String isStick,boolean isCloid);
	/**
	 * 热门经验分享
	 * @Title getMemContentsByPraise 
	 * @Description TODO
	 * @param channelId
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-27 上午11:04:54 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<MemberContent> getMemContentsByPraise(String channelId,String limits);
	
	/**
	 * 查询经验分享
	 * @Title getMemcontInfoByParam 
	 * @Description TODO
	 * @param page_url
	 * @param content_id
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2018-9-12 上午10:31:00 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	MemberContent getMemcontInfoByParam(String page_url,String content_id);
	
	/**
	 * 最新经验分享
	 * @Title getMemContentsByDate 
	 * @Description TODO
	 * @param channelId
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-27 上午11:05:16 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<MemberContent> getMemContentsByDate(String channelId,String limits);
	
	/**
	 * 经验分享数量
	 * @Title getMemberNums 
	 * @Description TODO
	 * @param channelId
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-27 上午11:05:31 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	MebContentNum getMemberNums(String channelId);
	
	/**
	 * 经验分享周排行
	 * @Title getMemContentsByweek 
	 * @Description TODO
	 * @param channelId
	 * @param limit
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-27 上午11:06:07 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<MebContentNum> getMemContentsByweek(String channelId,String limit);
	
	/**
	 * 品牌故事、人物
	 * @Title getBrandStorys 
	 * @Description TODO
	 * @param column_id
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-27 上午11:06:21 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getBrandStorys(String column_id, String limits);
	
	/**
	 * 获取商品分类
	 * @Title findGoodsClassByParent 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-28 上午11:15:13 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<GoodsClass> findGoodsClassByParent();
	
	/**
	 * 根据商品ID获取商品分类名称
	 * @Title findGoodsClassById 
	 * @Description TODO
	 * @param goodsid
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-28 上午11:22:41 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	GoodsClass findGoodsClassById(String goodsid);
	
	/**
	 * 获取推荐专题
	 * @Title queryPubSubList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-10-17 上午9:58:39 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<SubjectEntity> queryPubSubList(String subject_id);
	
	/**
	 * 获取首页发布的专题页面
	 * @Title getSubjectList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-1-11 下午4:04:52 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<SubjectEntity> getSubjectList();
	
	/**
	 * 根据专题ID获取专题信息
	 * @Title getSubjectInfo 
	 * @Description TODO
	 * @param subject_id
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-1-11 下午5:10:09 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	SubjectEntity getSubjectInfo(String subject_id);
	
	/**
	 * 获取专题页面数据
	 * @Title getSubjectTemLists 
	 * @Description TODO
	 * @param subject_id
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-1-11 下午4:09:57 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<SubjectTemplateEntity> getSubjectTemLists(String subject_id);
	
	/**
	 * 获取问卷题目
	 * @Title getSurveyLists 
	 * @Description TODO
	 * @param survey_id
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-4-11 下午5:24:02 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
    List<SurveyQuestEntity> getSurveyLists(String survey_id);
    
    /**
	 * 获取全部词条
	 * @Title getAllWordList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-7-12 下午4:14:09 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getAllWordList();
	
	/**
	 * 获取全部文章
	 * @Title getAllArticleList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-7-12 下午4:14:25 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getAllArticleList();
	
	/**
	 * 获取全部UGC
	 * @Title getAllArticleList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-7-12 下午4:14:25 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getAllUGCList();
	
	/**
	 * 获取商品服务一级二级分类
	 * @Title getFirGoddsList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2017-7-12 下午4:30:22 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<GoodsClassEntity> getFirGoodsList();	
	
	/**
	 * 查询所有的售后服务品牌
	 * @Title findAfterBrandList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  txw
	 * @date 2017年9月15日 下午4:57:25 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<AfterBrandGclass> findAfterBrandList();
	
	/**
	 * 根据栏目、商品分类获取置顶文章列表
	 * @Title getArticlesByColid 
	 * @Description TODO
	 * @param goods_class_id
	 * @param column_id
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-12-1 上午9:40:28 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> getStickArticlesByColid(String goods_class_id,String column_id,int article_num,boolean isStick);

	/**
	 * 查询搜索框文字
	 * @Title getFindSearchList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2018年9月5日 上午9:35:07 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<HotWordEntity> getFindSearchList();
	
	/**
	 * 查询栏目
	 * @Title getSubjectTemplateSection 
	 * @Description TODO
	 * @param subjectid
	 * @return
	 * @throws 
	 * @author  wyj
	 * @date 2018年9月28日 下午3:55:16 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<Tb_subject_section> getSubjectTemplateSection(String subjectid);
	
	/**
	 * 获取前台行业分类管理词条
	 * @Title getWordList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-1-17 下午2:22:42 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<WordEntity> getWordsListByNewGoods(String goods_class_id,String limit,String channel_id);
	
	/**
	 * 查询前台行业分类关联文章列表
	 * @Title queryArticleListByNewGoods 
	 * @Description TODO
	 * @param goods_class_id
	 * @param offset
	 * @param limit
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-1-16 下午2:29:35 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<ArticleEntity> queryArticleListByNewGoods(String goods_class_id,String column_id,String limit);
	
	/**
	 * 获取词条库
	 * @Title getWordList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-1-17 下午2:22:42 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<WordEntity> getWordList();
	
	/**
	 * 获取产品库
	 * @Title getCodeShowList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-1-17 下午2:54:23 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<CodeEntity> getCodeShowList();
	
	/**
	 * UGC加精、自动数据
	 * @Title queryMemcontListForIndex 
	 * @Description TODO
	 * @param channelId
	 * @param columnId
	 * @param columnName
	 * @param limits
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-2-26 下午5:04:34 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<MemberContent> queryMemcontListForIndex(String channelId,String columnId,String columnName,String limits,String is_stick);
	
	/**
	 * 获取图集集合
	 * @Title getPiclibList 
	 * @Description TODO
	 * @param linkGoodsClassId
	 * @param limit
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-3-18 下午5:37:31 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<PiclibEntity> getPiclibList(String linkGoodsClassId,String limit);
	
	/**
	 * 获取前台行业类目
	 * @Title getWordList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2019-1-17 下午2:22:42 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<WordEntity> getCategorysListByNewGoods(String goods_class_id,String limit);

	/**
	 * 获取品牌排行榜
	 * @Title getPiclibList
	 * @Description TODO
	 * @param
	 * @param
	 * @return
	 * @throws
	 * @author  zhy
	 * @date 2019-3-18 下午5:37:31
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<CodeEntity> getBrandClassifyList();

	/**
	 * 查询新树关联UGC列表
	 * @param goodsClassId
	 * @param columnId
	 * @param limit
	 * @return
	 */
	List<MemberContent> queryMemcontByListNewGoods(String goodsClassId,String columnId,String isStick,String limit);

	/**
	 * 查询十大品牌
	 * @Title getIsTenBrandList
	 * @Description TODO
	 * @param brandType
	 * @param goodsClassId
	 * @return
	 * @throws
	 * @author  zhy
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	List<BrandEntity> getIsTenBrandList(String brandType, String goodsClassId, String brandName, String limit);
}
