package com.zxsd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxsd.common.DataType;
import com.zxsd.common.StringUtil;
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
import com.zxsd.dao.bean.SurveyAnswEntity;
import com.zxsd.dao.bean.SurveyQuestEntity;
import com.zxsd.dao.bean.UserEntity;
import com.zxsd.dao.bean.WebsiteLink;
import com.zxsd.dao.bean.Word;
import com.zxsd.dao.bean.templetAffirmApp;
import com.zxsd.dao.interfaces.IContTemplate;
import com.zxsd.dao.interfaces.WapTemplateDao;
import com.zxsd.service.interfaces.IContTemplateService;
import com.zxsd.service.interfaces.WapTemplateService;

@Service
public class WapTemplateServiceImpl implements WapTemplateService{
	@Autowired
	private WapTemplateDao wapTemplateDao;
	
	@Override
	public String queryChanname(String channelId) {
		String channelName = "";
		ChannelEntity entity = wapTemplateDao.queryChanname(channelId);
		if(entity != null)
		{
			channelName = entity.getChannelName();
		}
		return channelName;
	}

	@Override
	public List<FocusImageEntity2> findImageList(String projectId) {
		List<FocusImageEntity2> imgList=wapTemplateDao.findImageList(projectId);
		for(FocusImageEntity2 focusEntity:imgList){
			Object channelPath=focusEntity.getChannelPath();
			if(channelPath!=null&&!channelPath.toString().equals("")){
				focusEntity.setProjectId(channelPath.toString());
				focusEntity.setProjectType("7");
			}
		}
		return imgList;
	}

	@Override
	public List<ChannelEntity> findChannelList() {
		// TODO Auto-generated method stub
		return wapTemplateDao.findChannelList();
	}

	@Override
	public List<ColumnAppEntity> getColumnlByChannelId(String projectId) {
		// TODO Auto-generated method stub
		return wapTemplateDao.getColumnlByChannelId(projectId);
	}

	@Override
	public List<templetAffirmApp> getTempletListByColumnId(String columnId,String pageSize) {
		// TODO Auto-generated method stub
		return wapTemplateDao.getTempletListByColumnId(columnId,pageSize);
	}

	@Override
	public Article getArticleById(String article_id) {
		String columnId="101,102,103";//国家政策，地方法规，政策法规日期展示部一样 
		String colunmOwerId="130,131,132,133,134,135";
		Article article=wapTemplateDao.getArticleById(article_id);
		if(columnId.indexOf(article.getColumnId().toString())>-1){
			if(article.getFilePublishDate()!=null)
			  article.setPublishDate(article.getFilePublishDate().toString());
		}
		String source=article.getSource()==null?"":article.getSource().toString().trim();
		String userTrueName=article.getUserTrueName()==null?"":article.getUserTrueName().toString().trim();
		if(colunmOwerId.indexOf(article.getColumnId().toString())>-1){
			article.setSource("");
			article.setUserTrueName(userTrueName);
		}else if(colunmOwerId.indexOf(article.getColumnId().toString())>-1){
			article.setSource(source);
			article.setUserTrueName("");
		}
		return article;
	}

	@Override
	public String getUser(String userId) {
		String user_icon = "";
		UserEntity entity = wapTemplateDao.getUser(userId);
		if(entity != null)
		{
			user_icon = entity.getUser_icon();
		}
		return user_icon;
		
	}

	@Override
	public MemberConEntity getMemberContentById(String projectId1) {
		
		return wapTemplateDao.getMemberContentById(projectId1);
	}

	@Override
	public String getMemberId(String mId) {
		String memberIcon = "";
		MemEntity entity = wapTemplateDao.getMemberId(mId);
		if(entity != null)
		{
			memberIcon = entity.getMemberIcon();
		}
		return memberIcon;
	}

	@Override
	public List<Article> getArticlesList(String searchTitle, String column_id, String goods_class_id, String code,
			String brandId, String pageNo, String pageSize, String order,String channel_id) {
		List<Article> allArticleList=new ArrayList<Article>(); 
		if(!StringUtil.isNOrS(column_id)){
       	  String columnId[]=column_id.split(",");
       	  for(int i=0;i<columnId.length;i++){
       		  List<Article> list=wapTemplateDao.getArticleList(searchTitle,columnId[i],goods_class_id,code,brandId,pageNo,pageSize,order,channel_id);
       		  allArticleList.addAll(list);
       	  }
         }else{
        	 return wapTemplateDao.getArticleList(searchTitle,column_id,goods_class_id,code,brandId,pageNo,pageSize,order,channel_id);
         }
		return allArticleList;
	}
	@Override
	public List<templetAffirmApp> getArticlesLists(String searchTitle, String column_id, String goods_class_id, String code,
			String brandId, String pageNo, String pageSize, String order,String channel_id,String isStick) {
		List<templetAffirmApp> allArticleList=new ArrayList<templetAffirmApp>(); 
		if(!StringUtil.isNOrS(column_id)){
			String columnId[]=column_id.split(",");
			for(int i=0;i<columnId.length;i++){
				List<templetAffirmApp> list=wapTemplateDao.getArticleLists(searchTitle,columnId[i],goods_class_id,code,brandId,pageNo,pageSize,order,channel_id,isStick);
				allArticleList.addAll(list);
			}
		}else{
			return wapTemplateDao.getArticleLists(searchTitle,column_id,goods_class_id,code,brandId,pageNo,pageSize,order,channel_id,isStick);
		}
		return allArticleList;
	}
	@Override
	public List<templetAffirmApp> getBrandList(String searchTitle, String brandType, String goodsClassId, String pageNo,
			String pageSize) {
		Integer pageNum1=Integer.parseInt(pageNo)*Integer.parseInt(pageSize);
		String pageNum=pageNum1.toString();
		if(brandType.equals("1")){
			return wapTemplateDao.getBrandList(searchTitle,"1", goodsClassId, pageNum, pageSize);
		}else if(brandType.equals("2")){
			return wapTemplateDao.getBrandList(searchTitle,"2", goodsClassId, pageNum, pageSize);
		}else if(brandType.equals("3")){
			return wapTemplateDao.getBrandList(searchTitle,"3",goodsClassId, pageNum, pageSize);
		}else if(brandType.equals("4")){
			return wapTemplateDao.getBrandTenForWorld(searchTitle,goodsClassId, pageNum, pageSize);
		}
		return null;
	}

	@Override
	public List<MemberConEntity> getMemberContent(String searchKey, String columnId, String goodsClassId,
			String memberId, String pageNo, String pageSize, String order) {
		Integer pageNum1=Integer.parseInt(pageNo)*Integer.parseInt(pageSize);
		String pageNum=pageNum1.toString();
		return wapTemplateDao.getMemberContent(searchKey,columnId,goodsClassId,memberId,pageNum,pageSize,order);
	}

	@Override
	public List<LogExp> getlogExpShort(int pageNo, int pageSize) {
		Integer pageNum1=pageNo*pageSize;
		String pageNum=pageNum1.toString();
		return wapTemplateDao.getMemberContent(pageNum,pageSize);
	}

	@Override
	public List<MemberConEntity> getMemberImageAndContent(String columnId, String goodsClassId, String pageNo,
			String pageSize, String order) {
		Integer pageNum1=Integer.parseInt(pageNo)*Integer.parseInt(pageSize);
		String pageNum=pageNum1.toString();
		
		return wapTemplateDao.getMemberImageAndContent(columnId,
				goodsClassId, pageNum, pageSize, order);
	}

	@Override
	public List<AfterSale> getAfterSaleLists(String address,String pAddress, String brandName, String goodsClassId, Integer pageNo,
			Integer pageSize , String typeVal) {
		String brand_name_c=null;//中文名
		String brand_name_e=null;//英文名 
		if(!StringUtil.isNullOrStr(brandName)){
			Matcher m=Pattern.compile("[A-Za-z].*").matcher(brandName);

		if(m.find()){	
				brand_name_e=brandName;	
		}else{
			brand_name_c=brandName;
		}
	}
		String pageNum="";
		if(pageNo!=null){
			Integer pageNum1=pageNo*pageSize;
		    pageNum=pageNum1.toString();
		}
		return wapTemplateDao.getAfterSaleLists( address,pAddress,
				brand_name_c,brand_name_e,goodsClassId, pageNum,  pageSize , typeVal);
	}

	@Override
	public List<ColumnAppEntity> getColumnlByGoodsId(String goodsClassId, String channelId) {
	
		List<ColumnAppEntity> columnList=wapTemplateDao.getColumnlByGoodsId(goodsClassId);
		for (ColumnAppEntity columnEntity : columnList) 
		{
			columnEntity.setColumnType("2");
			String columnId =columnEntity.getColumnId().toString();
			List<templetAffirmApp>	columnInfoList=wapTemplateDao.getArticles(null,columnId,goodsClassId ,null, "0", "5","publish_date");
			for(templetAffirmApp article:columnInfoList)
			{
				String source=article.getSource()==null?"":article.getSource().toString().trim();
				String userTrueName=article.getUserTrueName()==null?"":article.getUserTrueName().toString().trim();
				
				if(source.equals(userTrueName)&&!source.equals(""))
				{
					article.setSource(source);
				}else
				{
					article.setSource("");
				}
				if(channelId.equals("112")){
					article.setSource(source);
				}
			
				article.setUserTrueName("");
			}
			columnEntity.setColumnInfoList(columnInfoList);
		}
		
		return columnList;
	}

	@Override
	public List<Exhibition> getBrandByType(String goodsClassId,
			String type) {
		return wapTemplateDao.getBrandByType(goodsClassId,type);
	}
	@Override
	public List<Exhibition> getBrandByGoodsId(String goodsClassId,String type) {
		// TODO Auto-generated method stub
		return wapTemplateDao.getBrandByGoodsId(goodsClassId,type);
	}

	@Override
	public Word getWordById(String wordId) {
		// TODO Auto-generated method stub
		return wapTemplateDao.getWordById(wordId);
	}

	@Override
	public List<ColumnAppEntity> getColumnlByColumnId(String channelId, String... columnIdList) {
		List<ColumnAppEntity> columnList=new ArrayList();
		for(String columnId:columnIdList){
			List<ColumnAppEntity> list=getColumnlByColumnIds(channelId,columnId);
			columnList.addAll(list);
		}
		return columnList; 
		
	}
	
	public List<ColumnAppEntity> getColumnlByColumnIds(String channelId,String columnId) {
		// TODO Auto-generated method stub
		return wapTemplateDao.queryByColumnId(channelId,columnId);
	}

	@Override
	public List<templetAffirmApp> getMemberHotContent(String columnId) {
		// TODO Auto-generated method stub
		return wapTemplateDao.getMemberHotContent(columnId);
	}
	

}
