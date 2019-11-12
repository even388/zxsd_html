package com.zxsd.service.interfaces;

import java.util.List;
import java.util.Map;

import com.zxsd.dao.bean.AfterBrandGclass;
import com.zxsd.dao.bean.AfterSale;
import com.zxsd.dao.bean.Article;
import com.zxsd.dao.bean.ArticleEntity;
import com.zxsd.dao.bean.ChannelEntity;
import com.zxsd.dao.bean.ColumnAppEntity;
import com.zxsd.dao.bean.ContTemplate;
import com.zxsd.dao.bean.ContTempletNew;
import com.zxsd.dao.bean.Exhibition;
import com.zxsd.dao.bean.FocusImageEntity;
import com.zxsd.dao.bean.FocusImageEntity2;
import com.zxsd.dao.bean.GoodsClass;
import com.zxsd.dao.bean.GoodsClassEntity;
import com.zxsd.dao.bean.LogExp;
import com.zxsd.dao.bean.MebContentNum;
import com.zxsd.dao.bean.MemberConEntity;
import com.zxsd.dao.bean.MemberContent;
import com.zxsd.dao.bean.SubjectEntity;
import com.zxsd.dao.bean.SubjectTemplateEntity;
import com.zxsd.dao.bean.SurveyQuestEntity;
import com.zxsd.dao.bean.UserEntity;
import com.zxsd.dao.bean.WebsiteLink;
import com.zxsd.dao.bean.Word;
import com.zxsd.dao.bean.templetAffirmApp;

public interface WapTemplateService {
	
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
     * 查询首页及频道页轮播图
     * @Title findImageList 
     * @Description TODO
     * @param projectId
     * @return
     * @throws 
     * @author  gw
     * @date 2017年10月11日 下午2:36:26 
     * @version v1.0
     * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
     */
	List<FocusImageEntity2> findImageList(String projectId);
	/**
	 * 首页查找频道
	 * @Title findChannelList 
	 * @Description TODO
	 * @return
	 * @throws 
	 * @author  gw
	 * @date 2017年10月11日 下午3:16:43 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */

	List<ChannelEntity> findChannelList();
	/**
	 * 根据栏目查找数据
	 */

	List<ColumnAppEntity> getColumnlByChannelId(String projectId);

	List<templetAffirmApp> getTempletListByColumnId(String columnId,String pageSize);

	Article getArticleById(String string);

	String getUser(String userId);

	MemberConEntity getMemberContentById(String projectId1);

	String getMemberId(String mId);

	List<Article>	getArticlesList(String searchTitle,String column_id,String goods_class_id,String code,String brandId,String pageNo,String pageSize,String order,String channel_id);
	
	List<templetAffirmApp>	getArticlesLists(String searchTitle,String column_id,String goods_class_id,String code,String brandId,String pageNo,String pageSize,String order,String channel_id,String isStick);

	List<templetAffirmApp> getBrandList(String searchTitle,String brandType,String goodsClassId,String pageNo,String pageSize);
 
    List<MemberConEntity> getMemberContent(String searchKey,String columnId, String goodsClassId,String memberId,
			String pageNo, String pageSize,String order);

	List<LogExp> getlogExpShort(int pageNo, int pageSize);


	List<MemberConEntity> getMemberImageAndContent(String columnId, String goodsClassId,
			String pageNo, String pageSize,String order);


	//wap端售后分类增加
	List<AfterSale> getAfterSaleLists(String address,String pAddrCode,String brandName,
			String goodsClassId, Integer pageNo, Integer pageSize , String typeVal);


	List<ColumnAppEntity> getColumnlByGoodsId(String goodsClassId,String channelId);


	List<Exhibition> getBrandByType(String goodsClassId,String type);


	List<Exhibition> getBrandByGoodsId(String goodsClassId,String type);


	Word getWordById(String wordId);

	List<ColumnAppEntity> getColumnlByColumnId(String channelId,String... columnIdList);


	List<templetAffirmApp> getMemberHotContent(String columnId);


	
}
