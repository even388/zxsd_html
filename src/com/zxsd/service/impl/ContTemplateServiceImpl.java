package com.zxsd.service.impl;

import com.zxsd.common.Constants;
import com.zxsd.common.StringUtil;
import com.zxsd.dao.bean.*;
import com.zxsd.dao.interfaces.IBarcodeDao;
import com.zxsd.dao.interfaces.IContTemplate;
import com.zxsd.service.interfaces.IContTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContTemplateServiceImpl implements IContTemplateService{
	@Autowired
	private IContTemplate templateDao;
	@Autowired
	private IBarcodeDao barcodeDao;

	@Override
	public List<ContTemplate> queryIndexList(String channelName,String columnName) {
		// TODO Auto-generated method stub
		return templateDao.queryIndexList(channelName,columnName);
	}
	@Override
	public List<WebsiteLink> queryWebLinkList(String channel_id) {
		// TODO Auto-generated method stub
		return templateDao.queryWebLinkList(channel_id);
	}
	@Override
	public List<FocusImageEntity> getImagesByChalId(String channelId) {
		// TODO Auto-generated method stub
		return templateDao.getImagesByChalId(channelId);
	}
	@Override
	public List<GoodsClassEntity> getAllGoodsResources(String goods_class_type) {
		List<GoodsClassEntity> menuList = templateDao.getAllGoodsResources(goods_class_type);
	    List<GoodsClassEntity> nodeList = new ArrayList<GoodsClassEntity>();
	        for (GoodsClassEntity menu1 : menuList) {
	            boolean mark = false;
	            for (GoodsClassEntity menu2 : menuList) {
	            	if(!StringUtil.isNOrS(menu1.getP_goods_class_id()))
	            	{
	            		if (menu1.getP_goods_class_id().equals(menu2.getGoods_class_id())) {
	            			mark = true;
	            			if (menu2.getChildren() == null) {
	            				menu2.setChildren(new ArrayList<GoodsClassEntity>());
	            			}
	            			menu2.getChildren().add(menu1);
	            			break;
	            		}
	            	}
	            }
	            if (!mark) {
	                nodeList.add(menu1);
	            }
	        }
        return nodeList;
	}
	@Override
	public List<ArticleEntity> getArticlesByColid(String channelId,String columnId,String columnName,String searchType,String limits,boolean isStick,boolean isColid) {
		int contNum = Integer.parseInt(limits);
		List<ArticleEntity> newlist = new ArrayList<>(contNum);
		//模板配置数据
		List<ContTemplate> templist = new ArrayList<>();
		if(!StringUtil.isNOrS(columnName)){
			templist = templateDao.queryIndexList(null,columnName);
		}
		if(templist != null && templist.size() > 0 ){
			//加精、自动数据
			List<ArticleEntity> ardlist = templateDao.getArticlesByColid(channelId, columnId,searchType, limits,isStick,isColid);
			List<ArticleEntity> list = new ArrayList<>();
			list.addAll(ardlist);
			for(ArticleEntity article:ardlist){
				for(ContTemplate temp:templist){
					if(temp.getLink().equals(article.getPage_url())){
						list.remove(article);
					}
				}
			}
			for(int i=0;i<contNum;i++){
				boolean isTemp = false;
				for(ContTemplate temp:templist){
					if(temp.getSeq_num().equals(String.valueOf(i+1))){
						isTemp = true;
						ArticleEntity articletmp = new ArticleEntity();
						//今日焦点 时间
						if(!StringUtil.isNOrS(columnName) && columnName.equals(Constants.INDEX_JD_NAME)){
							articletmp = templateDao.quertArticleInfo(null,temp.getLink(), null);
							if(articletmp==null){
								articletmp = new ArticleEntity();
								articletmp.setFirst_publish_date (temp.getCreate_time());
								articletmp.setContent_intro(temp.getIntro());
							}
						}
						articletmp.setIs_stick("1");
						articletmp.setTitle(temp.getTitle());
						articletmp.setPage_url(temp.getLink());
						articletmp.setTitle_image(temp.getImage());
						articletmp.setLink_type(temp.getLink_type());
						newlist.add(i, articletmp);
					}
				}
				//自动数据 补充占位
				if(!isTemp && list.size() > 0){
					newlist.add(i,list.get(0));
					list.remove(0);
				}
			}
			return newlist;
		}else{
			return templateDao.getArticlesByColid(channelId, columnId, searchType,limits,isStick,isColid);
		}
	}
	@Override
	public String queryChanname(String channelId) {
		String channelName = "";
		ChannelEntity entity = templateDao.queryChanname(channelId);
		if(entity != null)
		{
			channelName = entity.getChannelName();
		}
		return channelName;
	}
	@Override
	public List<MemberContent> getNewMemContents(String channelId,
			String columnId, String limits,String isStick,boolean isCloid) {
		// TODO Auto-generated method stub
		return templateDao.getNewMemContents(channelId, columnId, limits,isStick,isCloid);
	}
	@Override
	public List<MemberContent> getMemContentsByPraise(String channelId,
			String limits) {
		// TODO Auto-generated method stub
		return templateDao.getMemContentsByPraise(channelId, limits);
	}
	@Override
	public List<MemberContent> getMemContentsByDate(String channelId,
			String limits) {
		// TODO Auto-generated method stub
		return templateDao.getMemContentsByDate(channelId, limits);
	}
	@Override
	public MebContentNum getMemberNums(String channelId) {
		// TODO Auto-generated method stub
		return templateDao.getMemberNums(channelId);
	}
	@Override
	public List<MebContentNum> getMemContentsByweek(String channelId,
			String limit) {
		// TODO Auto-generated method stub
		return templateDao.getMemContentsByweek(channelId, limit);
	}
	@Override
	public List<ArticleEntity> getBrandStorys(String column_id, String limits) {
		// TODO Auto-generated method stub
		return templateDao.getBrandStorys(column_id, limits);
	}
	@Override
	public List<GoodsClass> findGoodsClassByParent() {
		// TODO Auto-generated method stub
		return templateDao.findGoodsClassByParent();
	}
	@Override
	public GoodsClass findGoodsClassById(String goodsid) {
		// TODO Auto-generated method stub
		return templateDao.findGoodsClassById(goodsid);
	}
	@Override
	public List<SubjectEntity> getSubjectList() {
		// TODO Auto-generated method stub
		return templateDao.getSubjectList();
	}
	@Override
	public List<SubjectTemplateEntity> getSubjectTemLists(String subject_id) {
		// TODO Auto-generated method stub
		return templateDao.getSubjectTemLists(subject_id);
	}
	@Override
	public SubjectEntity getSubjectInfo(String subject_id) {
		// TODO Auto-generated method stub
		return templateDao.getSubjectInfo(subject_id);
	}
	@Override
	public List<ContTempletNew> queryNewIndexList(String channelName) {
		// TODO Auto-generated method stub
		return templateDao.queryNewIndexList(channelName);
	}
	@Override
	public List<SurveyQuestEntity> getSurveyLists(String survey_id) {
		// TODO Auto-generated method stub
		List<SurveyQuestEntity> new_list = new ArrayList<SurveyQuestEntity>();
		List<SurveyQuestEntity> list = templateDao.getSurveyLists(survey_id);
		for(SurveyQuestEntity entity:list)
		{
			if(entity != null)
			{	//选择题
				if(entity.getType().endsWith("1") || entity.getType().endsWith("2") )
				{
					List<SurveyAnswEntity> answlist = new ArrayList<>();
					if(!StringUtil.isNOrS(entity.getAnsws()) && !StringUtil.isNOrS(entity.getIsVals()))
					{
						//题目 选项
						String [] answs = entity.getAnsws().split("\\|");
						String [] isVals = entity.getIsVals().split("\\|");
						for(int i=0;i<answs.length;i++)
						{
							SurveyAnswEntity answEn = new SurveyAnswEntity();
							answEn.setQuest_id(entity.getQuest_id());
							answEn.setAnsw_val(answs[i]);
							answEn.setIs_val(isVals[i]);
							answlist.add(answEn);
						}
						entity.setAnswlist(answlist);
					}
				}else if(entity.getType().endsWith("4"))//矩阵单选
				{
					List<SurveyAnswEntity> answlist = new ArrayList<>();
					List<SurveyAnswEntity> addanswlist = new ArrayList<>();
					if(!StringUtil.isNOrS(entity.getAnsws()))
					{
						//题目 选项
						String [] answs = entity.getAnsws().split("\\|");
						for(int i=0;i<answs.length;i++)
						{
							SurveyAnswEntity answEn = new SurveyAnswEntity();
							answEn.setQuest_id(entity.getQuest_id());
							answEn.setAnsw_val(answs[i]);
							answlist.add(answEn);
						}
						entity.setAnswlist(answlist);
					}
					if(!StringUtil.isNOrS(entity.getAddAnsws()))
					{
						//题目附加选项
						String [] addansws = entity.getAddAnsws().split("\\|");
						for(int i=0;i<addansws.length;i++)
						{
							SurveyAnswEntity answEn = new SurveyAnswEntity();
							answEn.setQuest_id(entity.getQuest_id());
							answEn.setAdd_answ_val(addansws[i]);
							addanswlist.add(answEn);
						}
						entity.setAddanswlist(addanswlist);
					}
				}
				new_list.add(entity);
			}
		}
		return new_list;
	}
	@Override
	public List<ArticleEntity> getAllWordList() {
		// TODO Auto-generated method stub
		return templateDao.getAllWordList();
	}
	@Override
	public List<ArticleEntity> getAllArticleList() {
		// TODO Auto-generated method stub
		return templateDao.getAllArticleList();
	}
	@Override
	public List<GoodsClassEntity> getFirGoodsList() {
		List<GoodsClassEntity> menuList = templateDao.getFirGoodsList();
	    List<GoodsClassEntity> nodeList = new ArrayList<GoodsClassEntity>();
	        for (GoodsClassEntity menu1 : menuList) {
	            boolean mark = false;
	            for (GoodsClassEntity menu2 : menuList) {
	            	if(!StringUtil.isNOrS(menu1.getP_goods_class_id()))
	            	{
	            		if (menu1.getP_goods_class_id().equals(menu2.getGoods_class_id())) {
	            			mark = true;
	            			if (menu2.getChildren() == null) {
	            				menu2.setChildren(new ArrayList<GoodsClassEntity>());
	            			}
	            			menu2.getChildren().add(menu1);
	            			break;
	            		}
	            	}
	            }
	            if (!mark) {
	                nodeList.add(menu1);
	            }
	        }
        return nodeList;	
	}
	@Override
	public List<ArticleEntity> getAllUGCList() {
		// TODO Auto-generated method stub
		return templateDao.getAllUGCList();
	}
	@Override
	public List<AfterBrandGclass> findAfterBrandList() {
		// TODO Auto-generated method stub
		return templateDao.findAfterBrandList();
	}
	@Override
	public List<SubjectEntity> queryPubSubList(String subject_id) {
		// TODO Auto-generated method stub
		return templateDao.queryPubSubList(subject_id);
	}
	@Override
	public List<ArticleEntity> getStickArticlesByColid(String goods_class_id,
			String column_id,int article_num,boolean isStick) {
		List<ArticleEntity> stickList = new ArrayList<>();
		if(isStick){
			//置顶文章
			stickList = templateDao.getStickArticlesByColid(goods_class_id, column_id);
			if(stickList.size() >= article_num){
				return stickList.subList(0, article_num);
			}else{
				article_num = article_num-stickList.size();
			}
		}
		//自动获取
		List<ArticleEntity> list = templateDao.getArticlesByColid(goods_class_id, column_id,null, String.valueOf(article_num),false,false);
		stickList.addAll(list);
		return stickList;
	}
	@Override
	public List<HotWordEntity> getFindSearchList() {
		// TODO Auto-generated method stub
		return templateDao.getFindSearchList();
	}
	@Override
	public MemberContent getMemcontInfoByParam(String page_url,
			String content_id) {
		// TODO Auto-generated method stub
		return templateDao.getMemcontInfoByParam(page_url, content_id);
	}
	@Override
	public List<Tb_subject_section> getSubjectTemplateSection(String subjectid) {
		
		return templateDao.getSubjectTemplateSection(subjectid);
	}
	@Override
	public List<WordEntity> getWordsListByNewGoods(String goods_class_id,
			String limit,String channel_id) {
		// TODO Auto-generated method stub
		return templateDao.getWordsListByNewGoods(goods_class_id, limit,channel_id);
	}
	@Override
	public List<WordEntity> getWordList() {
		// TODO Auto-generated method stub
		return templateDao.getWordList();
	}
	@Override
	public List<CodeEntity> getCodeShowList(){
		List<CodeEntity> list = templateDao.getCodeShowList();
		for(CodeEntity code:list){
			Map<String, Object> codemap = barcodeDao.getCodedAttrCode(code.getGoodsCode());
			if(codemap != null){
				code.setShowName(codemap.get("showName").toString());
			}
		}
		return list;
	}
	@Override
	public List<ArticleEntity> queryArticleListByNewGoods(
			String goods_class_id, String column_id, String limit) {
		// TODO Auto-generated method stub
		return templateDao.queryArticleListByNewGoods(goods_class_id, column_id, limit);
	}
	@Override
	public List<MemberContent> queryMemcontListForIndex(String channelId,
			String columnId, String columnName,String limits, String isStick) {
		int contNum = Integer.parseInt(limits);
		List<MemberContent> newlist = new ArrayList<MemberContent>(contNum);
		//模板配置数据
		List<ContTemplate> templist = templateDao.queryIndexList(null,columnName);
		if(templist != null && templist.size() > 0){
			//加精、自动数据
			List<MemberContent> list = templateDao.getNewMemContents(channelId,columnId,limits,isStick,true);
			for(int i=0;i<contNum;i++){
				boolean isTemp = false;
				for(ContTemplate temp:templist){
					//曝光台  多块数据
					if(columnName.equals(Constants.INDEX_BG_NAME)  && (temp.getBlock_num().equals("2") || temp.getBlock_num().equals("3"))){
						continue;
					}
					//模板数据  补充占位
					if(temp.getSeq_num().equals(String.valueOf(i+1))){
						isTemp = true;
						MemberContent memobj = new MemberContent();
						//经验分享 作者 阅读数
						if(columnName.equals(Constants.INDEX_FX_NAME)){
							MemberContent memcont = templateDao.getMemcontInfoByParam(temp.getLink(), null);
							if(memcont != null){
								memobj.setMember_nickname(memcont.getMember_nickname());
								memobj.setBrowse_sum_show(memcont.getBrowse_sum_show());
							}else{
								memobj = new MemberContent();
								memobj.setBrowse_sum_show("0");
								memobj.setMember_nickname("");
							}
						}
						memobj.setTitle(temp.getTitle());
						memobj.setPage_url(temp.getLink());
						memobj.setTitle_image(temp.getImage());
						memobj.setLink_type(temp.getLink_type());
						newlist.add(i, memobj);
					}
				}
				//自动数据 补充占位
				if(!isTemp && list.size() > 0){
					newlist.add(i,list.get(0));
					list.remove(0);
				}
			}
			return newlist;
		}else{
			//加精、自动数据
			List<MemberContent> list = templateDao.getNewMemContents(channelId,columnId,limits,isStick,true);
			return list;
		}
	}
	@Override
	public List<PiclibEntity> getPiclibList(String linkGoodsClassId,
			String limit) {
		// TODO Auto-generated method stub
		return templateDao.getPiclibList(linkGoodsClassId, limit);
	}
	@Override
	public List<WordEntity> getCategorysListByNewGoods(String goods_class_id,
			String limit) {
		// TODO Auto-generated method stub
		return templateDao.getCategorysListByNewGoods(goods_class_id, limit);
	}

	@Override
	public List<CodeEntity> getBrandClassifyList() {
		return templateDao.getBrandClassifyList();
	}

	@Override
	public List<MemberContent> queryMemcontByListNewGoods(String goodsClassId, String columnId, String isStick, String limit) {
		return templateDao.queryMemcontByListNewGoods(goodsClassId, columnId, isStick, limit);
	}

	@Override
	public List<BrandEntity> getIsTenBrandList(String brandType, String goodsClassId, String brandName, String limit) {
		return templateDao.getIsTenBrandList(brandType, goodsClassId, brandName, limit);
	}

}
