package com.zxsd.dao.interfaces;

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
import com.zxsd.dao.bean.MemEntity;
import com.zxsd.dao.bean.MemberConEntity;
import com.zxsd.dao.bean.MemberContent;
import com.zxsd.dao.bean.SubjectEntity;
import com.zxsd.dao.bean.SubjectTemplateEntity;
import com.zxsd.dao.bean.SurveyQuestEntity;
import com.zxsd.dao.bean.UserEntity;
import com.zxsd.dao.bean.WebsiteLink;
import com.zxsd.dao.bean.Word;
import com.zxsd.dao.bean.templetAffirmApp;

/**
 * 
 * @ClassName IContTemplate   
 * @Description 
 * @author  zhy
 * @date 2016-10-25 上午9:48:27  
 * @version v1.0 2016-10-25 上午9:48:27
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2016-10-25 上午9:48:27　　V1.0　　 build此模块
 */
public interface WapTemplateDao {

	/**
	 * 获取频道名称
	 * @Title queryChanname 
	 * @Description TODO
	 * @param channelId
	 * @return
	 * @throws 
	 * @author  zhy
	 * @date 2016-10-26 下午5:15:50 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	ChannelEntity queryChanname(String channelId);
    /**
     * 查询首页及频道页轮播图
     * @Title findImageList 
     * @Description TODO
     * @param projectId
     * @return
     * @throws 
     * @author  gw
     * @date 2017年10月11日 下午2:50:29 
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
	
	List<ColumnAppEntity> getColumnlByChannelId(String projectId);
	
	List<templetAffirmApp> getTempletListByColumnId(String columnId,String pageSize);
	
	Article getArticleById(String article_id);
	
	UserEntity getUser(String userId);
	
	MemberConEntity getMemberContentById(String projectId1);
	
	MemEntity getMemberId(String mId);
	
    List<Article> getArticleList(String searchTitle,String column_id,
			String goods_class_id, String code,String brandId, String pageNo,String pagesize,String order,String channel_id);
   
    List<templetAffirmApp> getArticleLists(String searchTitle,String column_id,
    		String goods_class_id, String code,String brandId, String pageNo,String pagesize,String order,String channel_id,String isStick);
	
	List<templetAffirmApp> getBrandList(String searchTitle,String brandType,String goodsClassId,String pageNum,String pageSize);
	
	List<templetAffirmApp> getBrandForNew(String searchTitle, String goodsClassId, String pageNum, String pageSize);

	List<templetAffirmApp> getBrandTenForWorld(String searchTitle, String goodsClassId, String pageNum,
			String pageSize);
	
	List<MemberConEntity> getMemberContent(String searchKey, String columnId, String goodsClassId, String memberId,
			String pageNum, String pageSize, String order);
	
	List<LogExp> getMemberContent(String pageNum, int pageSize);
	
	List<MemberConEntity> getMemberImageAndContent(String columnId, String goodsClassId, String pageNum, String pageSize,
			String order);
	
	List<AfterSale> getAfterSaleLists(String address, String pAddress, String brand_name_c, String brand_name_e,
			String goodsClassId, String pageNum, Integer pageSize, String typeVal);
	
	List<ColumnAppEntity> getColumnlByGoodsId(String goodsClassId);
	
	List<templetAffirmApp> getArticles(String searchTitle,String column_id,String goods_class_id,String brandId,String pageNo,String pageSize,String order);
	
	List<Exhibition> getBrandByType(String goodsClassId, String type);
	
	List<Exhibition> getBrandByGoodsId(String goodsClassId, String type);
	
	Word getWordById(String wordId);
	
	List<ColumnAppEntity> queryByColumnId(String channelId, String columnId);
	
	List<templetAffirmApp> getMemberHotContent(String columnId);

}