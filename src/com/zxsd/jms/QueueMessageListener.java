package com.zxsd.jms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zxsd.common.*;
import com.zxsd.dao.base.MqMesEntity;
import com.zxsd.dao.bean.*;
import com.zxsd.service.interfaces.*;
import com.zxsd.templet.JYFXTemplet;
import com.zxsd.templet.SHFWTemplet;
import com.zxsd.templet.YZPPTemplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class QueueMessageListener  implements MessageListener {
	private static final  Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);
	private final static Map<String, String> templateMap = new HashMap<String, String>();
	private final static Map<String, String> waptemplateMap = new HashMap<String, String>();
	static{
		templateMap.put("100", "zxsd.html");//ProjectType=1
		templateMap.put("101", "bg.html");//ProjectType=1
		templateMap.put("102", "fx.html");//ProjectType=1
		templateMap.put("103", "yj.html");//ProjectType=1
		templateMap.put("104", "jq.html");//ProjectType=1
		templateMap.put("105", "ts.html");//ProjectType=1
		templateMap.put("106", "wq.html");//ProjectType=1
		templateMap.put("107", "xj.html");//ProjectType=1
		templateMap.put("108", "lc.html");//ProjectType=1
		templateMap.put("109", "bz.html");//ProjectType=1
		templateMap.put("110", "yp.html");//ProjectType=1
		templateMap.put("111", "hl.html");//ProjectType=1
		templateMap.put("112", "fw.html");//ProjectType=1
		templateMap.put("1", "tops.html,foots.html");//ProjectType=2
		//templateMap.put("2", "foots.html");//ProjectType=3
		templateMap.put("1001", "zt1.html");//ProjectType=9
		templateMap.put("1002", "zt2.html");//ProjectType=9
		templateMap.put("1003", "zt3.html");//ProjectType=9
		templateMap.put("1004", "zt4.html");//ProjectType=9
		templateMap.put("1005", "zt5.html");//ProjectType=9
		templateMap.put("1006", "zt6.html");//ProjectType=9
		templateMap.put("1007", "zt7.html");//ProjectType=9
		templateMap.put("1008", "zt8.html");//ProjectType=9
		templateMap.put("1009", "zt9.html");//ProjectType=9
		templateMap.put("1010", "zt10.html");//ProjectType=9
		templateMap.put("1011", "zt11.html");//ProjectType=9
		templateMap.put("1012", "zt12.html");//ProjectType=9
		templateMap.put("1013", "zt13.html");//ProjectType=9
		templateMap.put("1014", "zt14.html");//ProjectType=9
		templateMap.put("11", "survey.html");//ProjectType=8
		templateMap.put("10", "talk.html,mtalk.html");//ProjectType=10
		templateMap.put("3", "jiadian.html,jiaju.html,yundong.html,shipin.html,yiyao.html,fuwu.html,fuwu1.html,nonglin.html");//ProjectType=7
		templateMap.put("4", "jiaodian.html,jiqiao.html,biaozhun.html,baoguangtai.html,pinpai.html");//ProjectType=7
		
	  }
	static{
		waptemplateMap.put("100", "index.html");//ProjectType=10
		waptemplateMap.put("103", "channel.html");//ProjectType=11
		waptemplateMap.put("104", "channel.html");//ProjectType=11
		waptemplateMap.put("105", "channel.html");//ProjectType=11
		waptemplateMap.put("106", "channel.html");//ProjectType=11
		waptemplateMap.put("107", "channel.html");//ProjectType=11
		waptemplateMap.put("108", "channel.html");//ProjectType=11
		waptemplateMap.put("109", "channel.html");//ProjectType=11
		waptemplateMap.put("111", "channel.html");//ProjectType=11
		waptemplateMap.put("101", "bgt.html");//ProjectType=11
		waptemplateMap.put("110", "yzpp.html");//ProjectType=11
		waptemplateMap.put("112", "shfw.html");//ProjectType=11
		waptemplateMap.put("102", "jyfx.html");//ProjectType=11
	}
	
	
	
	
	@Autowired
	private IContTemplateService templateService;
	@Autowired
	private WapTemplateService wapTemplateService;
	@Autowired
	private IAfterSaleService afterSaleService;
	@Autowired
	private IGoodsNewTreeService goodsNewTreeService;
	@Autowired
	private ITalkService talkService;

    //当收到消息时，自动调用该方法。
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message; 
		LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		try {
			
			MqMesEntity mes = (MqMesEntity) JsonFormatUtil.getObject4JsonString(tm.getText(), MqMesEntity.class);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(mes != null)
			{
				/*v
				 * 日志记录
				 */
				StringBuffer logParams=new StringBuffer();
				logParams.append("projectId="+mes.getProjectId()+";");
				logParams.append("projectType="+mes.getProjectType()+";");
				logParams.append("contId="+mes.getContId()+";");
				loghead.setParames(logParams.toString());
				logger.info(JsonFormatUtil.getLogJsonString(loghead));
				
				String templateName = templateMap.get(mes.getProjectId());
				if(mes.getProjectType().equals(Constants.HTML_INDEX_TYPE))//频道首页
				{
					paramMap = createHtmlIndex(mes.getProjectId());
					FreemakerTemplate.getInstance().createHTML(paramMap, templateName);
				}else if(mes.getProjectType().equals(Constants.HTML_TOPS_TYPE))//头部、商品页、服务页
				{
					paramMap = createHtmlTop();
					String [] templateNames = templateMap.get(mes.getProjectId()).split(",");
					for(int i=0;i<templateNames.length;i++)
					{
						FreemakerTemplate.getInstance().createHTML(paramMap, templateNames[i]);
					}
				}else if(mes.getProjectType().equals(Constants.HTML_FOOTS_TYPE))//底部
				{
					paramMap = createHtmlFoot();
					FreemakerTemplate.getInstance().createHTML(paramMap, templateName);
				}else if(mes.getProjectType().equals(Constants.HTML_WORD_TYPE))//词条
				{
					paramMap = createHtmlWord();
					FreemakerTemplate.getInstance().createHTML(paramMap, templateName);
				}else if(mes.getProjectType().equals(Constants.HTML_WORDATTR_TYPE))//主词条
				{
					paramMap = createHtmlWordAttr();
					FreemakerTemplate.getInstance().createHTML(paramMap, templateName);
				}else if(mes.getProjectType().equals(Constants.HTML_ARTICLE_TYPE))//文章
				{
					paramMap = createHtmlArticle();
					FreemakerTemplate.getInstance().createHTML(paramMap, templateName);
				}else if(mes.getProjectType().equals(Constants.HTML_HUATI_TYPE))//话题
				{
					templateName = templateMap.get("10");//话题
					paramMap = createHtmlHUATI(mes.getContId());
					String [] templateNames = templateName.split(",");
					for(int i=0;i<templateNames.length;i++) {
						FreemakerTemplate.getInstance().createHUATIHTML(paramMap, templateNames[i], mes.getProjectId() + ".html");
					}
				}else if(mes.getProjectType().equals(Constants.HTML_THEME_TYPE))//专题
				{
					paramMap = createHtmlTHEME(mes.getContId());
					FreemakerTemplate.getInstance().createTHEMEHTML(paramMap, templateName,"theme"+mes.getContId()+".html");
				}else if(mes.getProjectType().equals(Constants.HTML_SURVEY_TYPE))//问卷
				{
					paramMap = createHtmlSURVEY(mes.getContId());
					FreemakerTemplate.getInstance().createTHEMEHTML(paramMap, templateName,"survey"+mes.getContId()+".html");
				}else if(mes.getProjectType().equals(Constants.HTML_CONTENTMAP_TYPE))//词条文章地图
				{
					paramMap = createHtmlContMap(mes.getProjectId());
					String [] templateNames = templateMap.get(mes.getProjectId()).split(",");
					for(int i=0;i<templateNames.length;i++)
					{
						FreemakerTemplate.getInstance().createContMapHTML(paramMap, templateNames[i]);
					}
				}else if(mes.getProjectType().equals(Constants.HTML_WAPINDEX_TYPE))//wap端首页
				{
					templateName = waptemplateMap.get(mes.getProjectId());
					paramMap = createHtmlWapIndex(mes.getProjectId());
					FreemakerTemplate.getInstance().createWapHTML(paramMap, templateName);
				
				}else if(mes.getProjectType().equals(Constants.HTML_WAPCHANNEL_TYPE))//wap端频道页
			     {
					templateName = waptemplateMap.get(mes.getProjectId());
					String templateName1="";
						if("103".equals(mes.getProjectId())){
							templateName1="yj.html";
						}
						if("104".equals(mes.getProjectId())){
							templateName1="jq.html";
						}
						if("105".equals(mes.getProjectId())){
							templateName1="ts.html";
						}
						if("106".equals(mes.getProjectId())){
							templateName1="wq.html";
						}
						if("107".equals(mes.getProjectId())){
							templateName1="xj.html";
						}
						if("108".equals(mes.getProjectId())){
							templateName1="lc.html";
						}
						if("109".equals(mes.getProjectId())){
							templateName1="bz.html";
						}
						if("111".equals(mes.getProjectId())){
							templateName1="hl.html";
						}
						if("101".equals(mes.getProjectId())){
							templateName1="bg.html";
						}
						if("102".equals(mes.getProjectId())){
							templateName1="fx.html";
						}
						if("110".equals(mes.getProjectId())){
							templateName1="yp.html";
						}
						if("112".equals(mes.getProjectId())){
							templateName1="fw.html";
						}
						if("112".equals(mes.getProjectId())){
							paramMap = createHtmlWapShfwChannel(mes.getProjectId());
						}else{
							paramMap = createHtmlWapChannel(mes.getProjectId());
						}
						
						FreemakerTemplate.getInstance().createChannelHTML(paramMap, templateName,templateName1);
			     }
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//访问路径
			loghead.setResults(ExceptionUtil.getStackMsg(e)+"CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
		}
	}
	//生成频道首页
	private Map<String, Object> createHtmlIndex(String projectId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String channleName = templateService.queryChanname(projectId);
		List<ContTemplate> templist = templateService.queryIndexList(channleName, null);
		paramMap.put("list", templist);
		//新模板数据
		List<ContTempletNew> templateList_new = templateService.queryNewIndexList(channleName);
		//焦点图
		List<FocusImageEntity> imagelist = templateService.getImagesByChalId(projectId);
		paramMap.put("imagelist", imagelist);
		//友情链接
		List<WebsiteLink> linklist = templateService.queryWebLinkList(projectId);
		paramMap.put("linklist", linklist);
		if(projectId.equals("100")){
			List<GoodsNewTreeEntity> newList = new ArrayList<>();
			//一级行业
			List<GoodsNewTreeEntity> firList = goodsNewTreeService.getGoodsNewTreeList(null, "0",true);
			if(firList != null && firList.size() > 15){
				firList = firList.subList(0, 15);
			}
			newList.addAll(firList);
			for(GoodsNewTreeEntity firobj:firList){
				//二级行业
				List<GoodsNewTreeEntity> secList = goodsNewTreeService.getGoodsNewTreeList(null,firobj.getGood_class_id(),true);
				newList.addAll(secList);
				for(GoodsNewTreeEntity secobj:secList){
					//三级级行业
					List<GoodsNewTreeEntity> threeList = goodsNewTreeService.getGoodsNewTreeList(null,secobj.getGood_class_id(),true);
					newList.addAll(threeList);
				}
			}
			List<GoodsNewTreeEntity> nodeList = new ArrayList<GoodsNewTreeEntity>();
	        for (GoodsNewTreeEntity menu1 : newList) {
	            boolean mark = false;
	            for (GoodsNewTreeEntity menu2 : newList) {
	            	if(!StringUtil.isNOrS(menu1.getParent_good_class_id()))
	            	{
	                if (menu1.getParent_good_class_id().equals(menu2.getGood_class_id())) {
	                    mark = true;
	                    if (menu2.getChildlist() == null) {
	                        menu2.setChildlist(new ArrayList<GoodsNewTreeEntity>());
	                    }
	                    menu2.getChildlist().add(menu1);
	                    break;
	                }
	            	}
	            }
	            if (!mark) {
	                nodeList.add(menu1);
	            }
	        }
	        paramMap.put("goodsList", nodeList);
			//今日焦点
			List<ArticleEntity> jdlist = templateService.getArticlesByColid(null,Constants.INDEX_JRJD, Constants.INDEX_JD_NAME,null, "4",true,true);
			//首页摘要
			List<ArticleEntity> zylist = templateService.getArticlesByColid(null, Constants.INDEX_SYZY, Constants.INDEX_ZY_NAME, "1","12",true,true);
			//惊天曝料
			List<ArticleEntity> bllist = templateService.getArticlesByColid(null,Constants.INDEX_JTBL, Constants.INDEX_BL_NAME, null, "6",true,true);
			//购买技巧
			List<ArticleEntity> jqlist = templateService.getArticlesByColid(Constants.ZXSD_JQINDEX_ID,null, Constants.INDEX_JQ_NAME, null, "14",false,false);
			//购买依据
			List<ArticleEntity> yjlist = templateService.getArticlesByColid(Constants.ZXSD_YJINDEX_ID,null, Constants.INDEX_YJ_NAME, null, "12",false,false);
			//购买提示
			List<ArticleEntity> tslist = templateService.getArticlesByColid(Constants.ZXSD_TSINDEX_ID,null, Constants.INDEX_TS_NAME, null, "14",false,false);
			//购买误区
			List<ArticleEntity> wqlist = templateService.getArticlesByColid(Constants.ZXSD_WQINDEX_ID,null, Constants.INDEX_WQ_NAME, null, "7",false,false);
			//购买陷阱
			List<ArticleEntity> xjlist = templateService.getArticlesByColid(Constants.ZXSD_XJINDEX_ID,null, Constants.INDEX_XJ_NAME, null, "14",false,false);
			//购买流程
			List<ArticleEntity> lclist = templateService.getArticlesByColid(Constants.ZXSD_LCINDEX_ID,null, Constants.INDEX_LC_NAME, null, "7",false,false);
			//曝光台
			List<MemberContent> bglist = templateService.queryMemcontListForIndex(Constants.ZXSD_BGTINDEX_ID,null, Constants.INDEX_BG_NAME, "11",null);
			//曝光企业、品牌
			List<ContTemplate> bgtemplist = templateService.queryIndexList(null, Constants.INDEX_BG_NAME);
			//国家标准
			List<ArticleEntity> bzlist = templateService.getArticlesByColid(Constants.ZXSD_BZINDEX_ID,null, Constants.INDEX_BZ_NAME, null, "9",false,false);
			//优质品牌
			List<ContTemplate> yplist = templateService.queryIndexList(null, Constants.INDEX_YP_NAME);
			//保养护理
			List<ArticleEntity> hllist = templateService.getArticlesByColid(Constants.ZXSD_HLINDEX_ID,null, Constants.INDEX_HL_NAME,null,  "14",false,false);
			//售后服务
			List<ArticleEntity> fwlist = templateService.getArticlesByColid(Constants.ZXSD_SHINDEX_ID,null, Constants.INDEX_FW_NAME,null,  "9",false,false);
			//经验分享
			List<MemberContent> fxlist = templateService.queryMemcontListForIndex(Constants.ZXSD_FXINDEX_ID,null, Constants.INDEX_FX_NAME, "20","1");
			//产品库
			List<CodeEntity> codelist = templateService.getCodeShowList();
			//词条库
			List<WordEntity> wordlist = templateService.getWordList();
			//图集
			List<PiclibEntity> piclist = templateService.getPiclibList(null,"15");
			//品牌排行榜
			List<CodeEntity> brandlist = templateService.getBrandClassifyList();
			//搜索词设置
			List<HotWordEntity> hotwords = templateService.getFindSearchList();
			if(hotwords != null && hotwords.size() > 0){
				paramMap.put("hotwords", hotwords.get(0).getsHotWord().split("\\|"));
				paramMap.put("hotmessage", hotwords.get(0).getpMessage());
			}
			paramMap.put("brandlist", brandlist);
			paramMap.put("piclist", piclist);
			paramMap.put("codeS", codelist);
			paramMap.put("wordS", wordlist);
			paramMap.put("jdlist", jdlist);
			paramMap.put("zylist", zylist);
			paramMap.put("bllist", bllist);
			paramMap.put("jqlist", jqlist);
			paramMap.put("yjlist", yjlist);
			paramMap.put("tslist", tslist);
			paramMap.put("wqlist", wqlist);
			paramMap.put("xjlist", xjlist);
			paramMap.put("lclist", lclist);
			paramMap.put("bglist", bglist);
			paramMap.put("bgtemplist", bgtemplist);
			paramMap.put("bzlist", bzlist);
			paramMap.put("yplist", yplist);
			paramMap.put("hllist", hllist);
			paramMap.put("fwlist", fwlist);
			paramMap.put("fxlist", fxlist);
		}else if(projectId.equals("101")){//曝光台
			//最新曝光线索
			List<MemberContent> bgtnewlist = templateService.getNewMemContents(projectId,Constants.BGT_MEMXS, "15",null,false);
			//置顶文章及自动获取文章
			List<ArticleEntity> sticklist1 = templateService.getStickArticlesByColid(null,Constants.BGT_XWZX,14,false);
			List<ArticleEntity> sticklist2 = templateService.getStickArticlesByColid(null,Constants.BGT_QWFB,12,false);
			List<ArticleEntity> sticklist3 = templateService.getStickArticlesByColid(null,Constants.BGT_ZCJD,13,false);
			List<ArticleEntity> sticklist4 = templateService.getStickArticlesByColid(null,Constants.BGT_HABL,13,false);
			List<ArticleEntity> sticklist5 = templateService.getStickArticlesByColid(null,Constants.BGT_BGPP,9,false);
			List<ArticleEntity> sticklist6 = templateService.getStickArticlesByColid(null,Constants.BGT_BGQY,8,false);
			paramMap.put("xwzx", sticklist1);
			paramMap.put("qwfb", sticklist2);
			paramMap.put("zcjd", sticklist3);
			paramMap.put("habl", sticklist4);
			paramMap.put("bgpp", sticklist5);
			paramMap.put("bgqy", sticklist6);
			paramMap.put("bgtnewlist", bgtnewlist);
		}else if(projectId.equals("102")){//经验分享
			//热门经验
			List<MemberContent> pra_list = templateService.getNewMemContents(Constants.ZXSD_FXINDEX_ID,null,"14","1",false);
			//经验发表数量
			MebContentNum memnum = templateService.getMemberNums(projectId);
			//本周经验发表排行
			List<MebContentNum> weeklist = templateService.getMemContentsByweek(projectId,"4");
			//购买经验
			List<MemberContent> list1 = templateService.queryMemcontByListNewGoods (null,"121", "0","24");
			//使用经验
			List<MemberContent> list2 = templateService.queryMemcontByListNewGoods (null,"122", "0","24");
			//通用经验
			List<MemberContent> list3 = templateService.queryMemcontByListNewGoods (null,"123", "0","23");
			//最新经验
			List<MemberContent> pub_list = new ArrayList<>();
			if(list1.size() > 4){
				pub_list.addAll(list1.subList(0,4));
			}
			if(list2.size() > 4) {
				pub_list.addAll(list2.subList(0, 4));
			}
			if(list3.size() > 3) {
				pub_list.addAll(list3.subList(0, 3));
			}
			//论坛精选
			List<Map<String, Object>> bbslist = new ArrayList<Map<String, Object>>();
			String bbsresult = HttpRequest.sendGet("http://bbs.2ge.cn/post.php","limtit=5");
			if(!StringUtil.isNOrS(bbsresult)){
				JSONObject obj = JSON.parseObject(bbsresult);
				if(obj != null && "1".equals(obj.getString("resCode"))){
					bbslist = JSON.parseObject(obj.getString("forumResult"),new TypeReference<List<Map<String,Object>>>(){});
				}
			}
			paramMap.put("bbslist", bbslist);
			paramMap.put("gmlist", list1);
			paramMap.put("sylist", list2);
			paramMap.put("tylist", list3);
			paramMap.put("memnum", memnum);
			paramMap.put("weeklist", weeklist);
			paramMap.put("pra_list", pra_list);
			paramMap.put("pub_list", pub_list);
		}else if(projectId.equals("110")){//优质品牌
			//优质品牌分类
			String goodslist [] = Constants.INDEX_YPFW_GOODS_LIST.split(",");
			for(String goodsId:goodslist){
				//品牌新闻 家用电器
				List<ArticleEntity> newslist = templateService.queryArticleListByNewGoods(goodsId,Constants.YZPP_PPXW,"12");
				paramMap.put("list_"+goodsId, newslist);
				//十大品牌
				List<BrandEntity> tenbrandslist = templateService.getIsTenBrandList("0", goodsId,null,"10");
				paramMap.put("tenlist_"+goodsId, tenbrandslist);
				//国内品牌
				List<BrandEntity> homebrandslist = templateService.getIsTenBrandList("2", goodsId,null,"5");
				paramMap.put("homelist_"+goodsId, homebrandslist);
				//国际品牌
				List<BrandEntity> intbrandslist = templateService.getIsTenBrandList("1", goodsId,null,"5");
				paramMap.put("intlist_"+goodsId, intbrandslist);
				//新进品牌
				List<BrandEntity> newbrandslist = templateService.getIsTenBrandList("3", goodsId,null,"5");
				paramMap.put("newlist_"+goodsId, newbrandslist);
			}
			//品牌故事
			List<ArticleEntity> storys_list = templateService.getBrandStorys(Constants.YZPP_PPGS, "5");
			//品牌人物
			List<ArticleEntity> pers_list = templateService.getBrandStorys(Constants.YZPP_PPRW, "4");
			paramMap.put("storys_list", storys_list);
			paramMap.put("pers_list", pers_list);
		}else if(projectId.equals("112"))//售后服务
		{
			//行业数据
			List<AfterExhibition> exlist = afterSaleService.queryAfterExhiList();
			//故障维修手册
			List<ArticleEntity> gzwxlist = templateService.getStickArticlesByColid(null, Constants.SH_GZSC, 5,false);
			//温馨提示与使用技巧
			List<ArticleEntity> wxtslist = templateService.getStickArticlesByColid(null, Constants.SH_WXTS, 5,false);
			//售后政策及流程
			List<ArticleEntity> fwlclist = templateService.getStickArticlesByColid(null, Constants.SH_FWLC, 5,false);
			//说明书/电子资料下载
			List<ArticleEntity> dzzllist = templateService.getStickArticlesByColid(null, Constants.SH_DZZL, 5,false);
			//医疗器械使用方法
			List<ArticleEntity> ylqqlist = templateService.getStickArticlesByColid(null, Constants.SH_YLQQ, 5,false);
			//家装/家具/家居维护说明
			List<ArticleEntity> jjwhlist = templateService.getStickArticlesByColid(null, Constants.SH_JJWH, 5,false);
			//服装鞋包饰品搭配指南
			List<ArticleEntity> fzxmlist = templateService.getStickArticlesByColid(null, Constants.SH_FZXM, 5,false);
			//用户手册/尺码对照表
			List<ArticleEntity> yhsclist = templateService.getStickArticlesByColid(null, Constants.SH_YHSC, 5,false);
			//快捷网点
			List<AfterCustomService> cuslist = afterSaleService.queryAfterServiceList();
			//省份
			List<CityAddr> provelist = afterSaleService.findCity();
			//品牌
			List<AfterBrandGclass>  brandList= templateService.findAfterBrandList();
			Map<String,List<BrandEntity>> hashMap = new HashMap<String,List<BrandEntity>>();
			ArrayList<BrandEntity> arrayList = null;
			BrandEntity BrandEntity = null;
			//创建26个集合对应26个字母brandName
			for (char i = 'a'; i <= 'z'; i++) {
				arrayList = new ArrayList<BrandEntity>();
				hashMap.put(i+"", arrayList);
			}
			arrayList = new ArrayList<BrandEntity>();
			hashMap.put("qita", arrayList);
			//遍历所有数据
			for (AfterBrandGclass aBrand : brandList) {
				if(!StringUtil.isNOrS(aBrand.getBrandName())){
				
				BrandEntity = new BrandEntity();
				//获取品牌的第一个字
				String brand_name = aBrand.getBrandName().substring(0, 1);
				//获取第一个字符的首字母
				String pinyin = Pinyin4jUtil.converterToSpell(brand_name);
				//对数字做处理
				Pattern pattern = Pattern.compile("[0-9]*"); 
			    Matcher isNum = pattern.matcher(pinyin);
			    if(isNum.matches()){
			    	List<BrandEntity> list2 = hashMap.get("qita");
			    	BrandEntity.setBrandName(aBrand.getBrandName());
			    	list2.add(BrandEntity);
			    	continue;
			    }
				//对多音字做处理
				String[] split = pinyin.split(",");
				if(split.length<2){
					char c = split[0].toCharArray()[0];
					//判断是否大写
					boolean res = Character.isLowerCase(c);
					//将所有的字母变为小写
					if(!res){
						char lowerCase = Character.toLowerCase(c);
						c = lowerCase;
					}
					//获取对字母对应的集合
					List<BrandEntity> list2 = hashMap.get(c+"");
					//为集合添加数据
					BrandEntity.setBrandName(aBrand.getBrandName());
					if(list2 != null && BrandEntity != null){
						list2.add(BrandEntity);
					}
				}else{
					for (String letter : split) {
						char c = letter.toCharArray()[0];
						//判断是否大写
						boolean res = Character.isLowerCase(c);
						//将所有的字母变为小写
						if(!res){
							char lowerCase = Character.toLowerCase(c);
							c = lowerCase;
						}
						//获取对字母对应的集合
						List<BrandEntity> list2 = hashMap.get(c+"");
						//为集合添加数据
						BrandEntity.setBrandName(aBrand.getBrandName());
						if(list2 != null && BrandEntity != null){
							list2.add(BrandEntity);
						}
					}
				}
				}
			}
			List<String> charList=new ArrayList<String>();
			for (char i = 'A'; i <= 'Z'; i++) {
				charList.add(String.valueOf(i));
				
			}
			paramMap.put("gzwxlist", gzwxlist);
			paramMap.put("wxtslist", wxtslist);
			paramMap.put("fwlclist", fwlclist);
			paramMap.put("dzzllist", dzzllist);
			paramMap.put("ylqqlist", ylqqlist);
			paramMap.put("jjwhlist", jjwhlist);
			paramMap.put("fzxmlist", fzxmlist);
			paramMap.put("yhsclist", yhsclist);
			paramMap.put("charList", charList);
			paramMap.put("pinyin", hashMap);
			paramMap.put("provelist", provelist);
			paramMap.put("exlist", exlist);
			paramMap.put("cuslist", cuslist);
		}else if(projectId.equals("103") || projectId.equals("104") || projectId.equals("105") || 
				projectId.equals("106") || projectId.equals("107") || projectId.equals("111")){
			int firnum = 10;
			String column_id="";
			if(projectId.equals("103")){//购买依据
				firnum = 13;
				column_id = "130";
			}else if(projectId.equals("104")){//购买技巧
				firnum = 15;
				column_id = "131";
			}else if(projectId.equals("105")){//购买提示
				firnum = 5;
				column_id = "132";
			}else if(projectId.equals("106")){//购买误区
				firnum = 10;
				column_id = "135";
			}else if(projectId.equals("107")){//购买陷阱
				firnum = 4;
				column_id = "133";
			}else if(projectId.equals("111")){//保养护理
				firnum = 4;
				column_id = "134";
			}
			//文章
			List<ArticleEntity> ardyjlist = new ArrayList<>();
			List<ArticleEntity> articlelist = templateService.queryArticleListByNewGoods(null, column_id, null);
			if(articlelist != null && articlelist.size() > 0){
				if(articlelist.size() <= firnum){
					firnum = articlelist.size();
				}
				ardyjlist = articlelist.subList(0, firnum);
				articlelist = articlelist.subList(firnum, articlelist.size());
			}
			paramMap.put("ardyjlist", ardyjlist);
			//一级行业分类
			List<GoodsNewTreeEntity> goodslist = goodsNewTreeService.getGoodsNewTreeList(null, "0",false);
			for(GoodsNewTreeEntity goods:goodslist){
				List<WordEntity> catelist = templateService.getCategorysListByNewGoods(goods.getGood_class_id(), "20");
                if(catelist == null || catelist.size() < 20){
                    catelist = templateService.getWordsListByNewGoods(goods.getGood_class_id(), "20",projectId);
                    paramMap.put("iswd_"+goods.getGood_class_id(), "true");
                }
				paramMap.put("wdlist_"+goods.getGood_class_id(), catelist);
				//文章
				List<ArticleEntity> ardgoodslist = new ArrayList<>();
				//每个行业截取7篇文章展示
				for(ArticleEntity article:articlelist){
					if(ardgoodslist.size() >= 7){
						break;
					}
					if(article.getGoods_class_id().substring(0, 3).equals(goods.getGood_class_id())){
						article.setGoods_class_id(goods.getGood_class_id());
						ardgoodslist.add(article);
					}
				}
				paramMap.put("ardlist_"+goods.getGood_class_id(), ardgoodslist);
			}
		}else if(projectId.equals("108")){//购买流程
			List<ArticleEntity> ardfglist = new ArrayList<>();
			List<ArticleEntity> ardjdlist = new ArrayList<>();
			List<ArticleEntity> ardlist = new ArrayList<ArticleEntity>();
			List<ArticleEntity> articlelist = templateService.queryArticleListByNewGoods(null, "101,102", null);
			ardlist.addAll(articlelist);
			if(articlelist != null && articlelist.size() > 0){
				for(ArticleEntity ard:articlelist){
					if(ardfglist.size() >= 6 && ardjdlist.size() >= 6){
						break;
					}
					//个人购买流程
					if(ard.getColumn_id().equals("101") && ardfglist.size() < 6){
						ardfglist.add(ard);
						ardlist.remove(ard);
					}else{//企业采购流程
						if(ardjdlist.size() < 6){ardjdlist.add(ard);ardlist.remove(ard);}
					}
				}
			}
			//一级行业分类
			List<GoodsNewTreeEntity> goodslist = goodsNewTreeService.getGoodsNewTreeList(null, "0",false);
			for(GoodsNewTreeEntity goods:goodslist){
				//文章
				List<ArticleEntity> ardgoodslist = new ArrayList<>();
				//每个行业截取7篇文章展示
				for(ArticleEntity article:articlelist){
					if(ardgoodslist.size() >= 4){
						break;
					}
					if(article.getGoods_class_id().substring(0, 3).equals(goods.getGood_class_id())){
						article.setGoods_class_id(goods.getGood_class_id());
						ardgoodslist.add(article);
					}
				}
				paramMap.put("ardlist_"+goods.getGood_class_id(), ardgoodslist);
			}
			paramMap.put("ardgrlist", ardfglist);
			paramMap.put("ardqylist", ardjdlist);
		}else if(projectId.equals("109")){//国家标准
			List<ArticleEntity> ardfglist = new ArrayList<>();
			List<ArticleEntity> ardjdlist = new ArrayList<>();
			List<ArticleEntity> ardlist = new ArrayList<ArticleEntity>();
			List<ArticleEntity> articlelist = templateService.queryArticleListByNewGoods(null, "103,148", null);
			ardlist.addAll(articlelist);
			if(articlelist != null && articlelist.size() > 0){
				for(ArticleEntity ard:articlelist){
					if(ardfglist.size() >= 7 && ardjdlist.size() >= 7){
						break;
					}
					//政策法规
					if(ard.getColumn_id().equals("103") && ardfglist.size() < 7){
						ardfglist.add(ard);
						ardlist.remove(ard);
					}else{//案例解析
						if(ardjdlist.size() < 7){ardjdlist.add(ard);ardlist.remove(ard);}
					}
				}
			}
			//一级行业分类
			List<GoodsNewTreeEntity> goodslist = goodsNewTreeService.getGoodsNewTreeList(null, "0",false);
			for(GoodsNewTreeEntity goods:goodslist){
				List<WordEntity> catelist = templateService.getCategorysListByNewGoods(goods.getGood_class_id(), "20");
				if(catelist == null || catelist.size() < 20){
					catelist = templateService.getWordsListByNewGoods(goods.getGood_class_id(), "20",projectId);
					paramMap.put("iswd_"+goods.getGood_class_id(), "true");
				}
				paramMap.put("wdlist_"+goods.getGood_class_id(), catelist);
				//文章
				List<ArticleEntity> ardgoodslist = new ArrayList<>();
				//每个行业截取6篇文章展示
				for(ArticleEntity article:ardlist){
					if(ardgoodslist.size() >= 7){
						break;
					}
					if(article.getGoods_class_id().substring(0, 3).equals(goods.getGood_class_id())){
						article.setGoods_class_id(goods.getGood_class_id());
						ardgoodslist.add(article);
					}
				}
				paramMap.put("ardlist_"+goods.getGood_class_id(), ardgoodslist);
			}
			paramMap.put("ardfglist", ardfglist);
			paramMap.put("ardjdlist", ardjdlist);
		}
		paramMap.put("listnew", templateList_new);
		return paramMap;
	}
	
	//生成头部
	private Map<String, Object> createHtmlTop(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<GoodsNewTreeEntity> newList = new ArrayList<>();
		//一级行业
		List<GoodsNewTreeEntity> firList = goodsNewTreeService.getGoodsNewTreeList(null, "0",true);
		if(firList != null && firList.size() > 15){
			firList = firList.subList(0, 15);
		}
		newList.addAll(firList);
		for(GoodsNewTreeEntity firobj:firList){
			//二级行业
			List<GoodsNewTreeEntity> secList = goodsNewTreeService.getGoodsNewTreeList(null,firobj.getGood_class_id(),true);
			newList.addAll(secList);
			for(GoodsNewTreeEntity secobj:secList){
				//三级级行业
				List<GoodsNewTreeEntity> threeList = goodsNewTreeService.getGoodsNewTreeList(null,secobj.getGood_class_id(),true);
				newList.addAll(threeList);
			}
		}
		List<GoodsNewTreeEntity> nodeList = new ArrayList<GoodsNewTreeEntity>();
        for (GoodsNewTreeEntity menu1 : newList) {
            boolean mark = false;
            for (GoodsNewTreeEntity menu2 : newList) {
            	if(!StringUtil.isNOrS(menu1.getParent_good_class_id()))
            	{
                if (menu1.getParent_good_class_id().equals(menu2.getGood_class_id())) {
                    mark = true;
                    if (menu2.getChildlist() == null) {
                        menu2.setChildlist(new ArrayList<GoodsNewTreeEntity>());
                    }
                    menu2.getChildlist().add(menu1);
                    break;
                }
            	}
            }
            if (!mark) {
                nodeList.add(menu1);
            }
        }
		//List<GoodsClassEntity> goodsList = templateService.getAllGoodsResources("1");
		//List<GoodsClassEntity> servsList = templateService.getAllGoodsResources("2");
		//搜索词设置
		List<HotWordEntity> hotwords = templateService.getFindSearchList();
		if(hotwords != null && hotwords.size() > 0){
			paramMap.put("hotwords", hotwords.get(0).getsHotWord().split("\\|"));
			paramMap.put("hotmessage", hotwords.get(0).getpMessage());
		}
		//paramMap.put("servsList", servsList);
		paramMap.put("goodsList", nodeList);
		return paramMap;
	}
	
	//生成词条文章地图
	private Map<String, Object> createHtmlContMap(String projectId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(projectId.equals("3")){
			//一二级商品分类
			List<GoodsClassEntity> goodsList = templateService.getFirGoodsList();
			//词条列表
			List<ArticleEntity> contlist = templateService.getAllWordList();
			paramMap.put("goodsList", goodsList);
			paramMap.put("contlist", contlist);
		}else if(projectId.equals("4")){
			//文章列表
			List<ArticleEntity> contlist = templateService.getAllArticleList();
			//ugc列表
			List<ArticleEntity> ugclist = templateService.getAllUGCList();
			paramMap.put("contlist", contlist);
			paramMap.put("ugclist", ugclist);
		}
		return paramMap;
	}

		
	//生成底部
	private Map<String, Object> createHtmlFoot(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return paramMap;
	}
	
	//生成专题页面
	private Map<String, Object> createHtmlTHEME(String subject_id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<SubjectTemplateEntity> templelist = templateService.getSubjectTemLists(subject_id);
		SubjectEntity subject = templateService.getSubjectInfo(subject_id);
		List<SubjectEntity> subjectlist = templateService.queryPubSubList(subject_id);
		paramMap.put("templelist", populateSubjectTemplate(templelist,subject_id));
		paramMap.put("subject", subject);
		paramMap.put("subjectlist", subjectlist);
		return paramMap;
	}

	//生成话题页面
	private Map<String,Object> createHtmlHUATI(String subject_id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//搜索词设置
		List<HotWordEntity> hotwords = templateService.getFindSearchList();
		if(hotwords != null && hotwords.size() > 0){
			paramMap.put("hotwords", hotwords.get(0).getsHotWord().split("\\|"));
			paramMap.put("hotmessage", hotwords.get(0).getpMessage());
		}
		//话题详情
		TalkEntity talkinfo = talkService.getTalkInfo(subject_id);
		paramMap.put("talkinfo", talkinfo);
		//话题栏目
		List<TalkEntity> columnList = talkService.getTalkColumnList(subject_id,"null");
		List<TalkEntity> resutList = new ArrayList<>();
		if(columnList != null && columnList.size() > 5){
			paramMap.put("ismore", 1);
			resutList = columnList.subList(0,5);
		}else{
			resutList = columnList;
		}
		//配置数据
		for(TalkEntity column:resutList){
			List<TalkTempEntity> templist = talkService.getTalkTempList(column.getId());
			column.setList(templist);
		}
		paramMap.put("columnList", resutList);
		return paramMap;
	}
	
	private List<Map<String,List<SubjectTemplateEntity>>> populateSubjectTemplate(List<SubjectTemplateEntity> templateList,String subjectid){
		List<Map<String,List<SubjectTemplateEntity>>> returnList = new ArrayList<Map<String,List<SubjectTemplateEntity>>>();
		//所有栏目
		List<Tb_subject_section> sectionList = templateService.getSubjectTemplateSection(subjectid);
		for(int i = 0;i<sectionList.size();i++){
			Tb_subject_section section = sectionList.get(i);
			Map<String,List<SubjectTemplateEntity>> map = new HashMap<>();
			for (SubjectTemplateEntity entity : templateList){
				if(section.getSection_id().equals(entity.getT_subject_section_id())){
					List<SubjectTemplateEntity> list = null;
					if(map.containsKey("column"+i)){
						list = map.get("column"+i);
					}else{
						list = new ArrayList<SubjectTemplateEntity>();
					}
					list.add(entity);
					map.put("column"+i,list);
				}
			}
			returnList.add(map);
		}
		return returnList;
	}
	
	
	//生成问卷页面
	private Map<String, Object> createHtmlSURVEY(String subject_id){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<SurveyQuestEntity> templelist = templateService.getSurveyLists(subject_id);
		if(templelist != null && templelist.size()>0)
		{
			SurveyQuestEntity entity = templelist.get(0);
			entity.setQuest_count(String.valueOf(templelist.size()));
			paramMap.put("survey", entity);
		}
		paramMap.put("templelist", templelist);
		return paramMap;
	}
	
	//生成词条
	private Map<String, Object> createHtmlWord(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return paramMap;
	}
	//生成词条维度
	private Map<String, Object> createHtmlWordAttr(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return paramMap;
	}
	//生成文章
	private Map<String, Object> createHtmlArticle(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return paramMap;
	}
	
	private void getGoodsLastName(List<ArticleEntity> list){
		for(ArticleEntity atricle:list){
			String[] ids=atricle.getGoods_class_id().split(",");
			if(ids!=null&&ids.length>0){
				GoodsClass goodsclass=templateService.findGoodsClassById(ids[ids.length-1]);
				if(goodsclass!=null){
					atricle.setGoods_class_Name(goodsclass.getGoodsClassName());
					atricle.setGoods_class_id(goodsclass.getGoodsClassId());
				}
			}
		}
	}
	
	//wap端生成首页
		private Map<String, Object> createHtmlWapIndex(String projectId){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String channleName = wapTemplateService.queryChanname(projectId);
			// 查找首页轮廓图
			List<FocusImageEntity2> focusImageList = wapTemplateService
					.findImageList(projectId);
			paramMap.put("fImageList", focusImageList);
			// 查找频道
			List<ChannelEntity> channelEntitityList = wapTemplateService.findChannelList();
			paramMap.put("channelList", channelEntitityList);
			// 栏目数据查找
			List<templetAffirmApp> columnInfoList;
			List<ColumnAppEntity> columnList = wapTemplateService.getColumnlByChannelId(projectId);
			for (ColumnAppEntity columnEntity : columnList) {
				Integer columnType = Integer.parseInt(columnEntity.getColumnType().toString());
	            String columnId = columnEntity.getColumnId().toString();
	            if (("127".equals(columnId) || "128".equals(columnId)) && DataType.ARTACLE_ID.equals(columnType)) {
	            	columnInfoList = getArticlesByColumnId(columnEntity);
	            } else {
	            	
                     //栏目展示最大数据数量
				     columnInfoList = wapTemplateService
						.getTempletListByColumnId(columnEntity.getId().toString(),
						    columnEntity.getSize().toString());
				for(templetAffirmApp mapinfo:columnInfoList){
					mapinfo.setSource("");
					if(Integer.valueOf(mapinfo.getColumnInfoType())==DataType.ARTACLE_ID){
						Article atricle=wapTemplateService.getArticleById(mapinfo.getId().toString());
						String userId=atricle.getUserId()==null?null:atricle.getUserId().toString();
						String userImg="";
						String userTrueName="";
						if(userId!=null){
							String user_icon = wapTemplateService.getUser(userId);
						  
							userImg=user_icon==null?"":user_icon;
						}
						
						
							String source=atricle.getSource()==null?"":atricle.getSource().toString().trim();
							 userTrueName=atricle.getUserTrueName()==null?"":atricle.getUserTrueName().toString().trim();
							 if(source.equals(userTrueName)&&!source.equals("")){
									mapinfo.setSource(source);
							 }
							 mapinfo.setUserTrueName(userTrueName);
						     mapinfo.setUserImg(userImg);
						
					}
					if(Integer.valueOf(mapinfo.getColumnInfoType())==DataType.UGC_ID){
						String projectId1=mapinfo.getId().toString();
						MemberConEntity member=wapTemplateService.getMemberContentById(projectId1);
					
						if(member==null){
							mapinfo.setUserTrueName("");
							mapinfo.setUserImg("");
						}else{
						String mId=member.getMemberId().toString();
					    String memberIcon=wapTemplateService.getMemberId(mId);
						String nickName=member.getMemberNickname()==null?"":member.getMemberNickname().toString();
						String userImg=member.getTitleImage()==null?"":memberIcon==null?"":memberIcon.toString();

						mapinfo.setUserTrueName(nickName);
						mapinfo.setUserImg(userImg);
						}
					}
					}
	            }
				columnEntity.setColumnInfoList(columnInfoList);
			}
			paramMap.put("columnList", columnList);
			// 挑选技巧（摘要）
			List<ArticleEntity> zylist = templateService.getArticlesByColid(null, Constants.INDEX_SYZY, Constants.INDEX_ZY_NAME, "1","10",true,true);
			paramMap.put("zylist", zylist);
			//查询搜索框文字
			List<HotWordEntity> searchList = templateService.getFindSearchList();
			String  pMessage="";
			if(searchList.size()>0){
			   pMessage= searchList.get(0).getpMessage();
			}
			paramMap.put("pMessage", pMessage);
			
			return paramMap;
		}
		
		/**
		 * 生成频道页
		 */
		public Map<String, Object> createHtmlWapChannel(String projectId){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String channelName=ChannelUtil.getChannelName(projectId);
			// 查找频道页轮廓图
			List<FocusImageEntity2> focusImageList = wapTemplateService
								.findImageList(projectId);
						paramMap.put("fImageList", focusImageList);
			 String goodsClassId=null;
			 List<ColumnAppEntity> columnList=new ArrayList();//整页数据List
			 List<WordAttr> goodsMap=new  ArrayList();//存商品数据
			
			 
			 
			 /*if(projectId.equals(ChannelUtil.JYFX)&&goodsClassId!=null){//该方法主要是实现某个栏目是属于某个商品一级分类，局部刷新返回数据
					columnList=wapTemplateService.getColumnlByColumnId(projectId,JYFXTemplet.JYFX_GMJY.getColumnId(),JYFXTemplet.JYFX_SYJY.getColumnId(),JYFXTemplet.JYFX_TYJY.getColumnId());

				}
				if(projectId.equals(ChannelUtil.YZPP)&&goodsClassId!=null){//该方法主要是实现某个栏目是属于某个商品一级分类，局部刷新返回数据
					columnList=wapTemplateService.getColumnlByColumnId(projectId,YZPPTemplet.YZPP_SDPP.getColumnId(),YZPPTemplet.YZPP_GJPP.getColumnId(),YZPPTemplet.YZPP_GNPP.getColumnId(),YZPPTemplet.YZPP_XJPP.getColumnId(),YZPPTemplet.YZPP_PPXW.getColumnId(),YZPPTemplet.YZPP_PPRW.getColumnId(),YZPPTemplet.YZPP_PPGS.getColumnId());
				}
				if(projectId.equals(ChannelUtil.SHFW)&&goodsClassId!=null){//该方法主要是实现某个栏目是属于某个商品一级分类，局部刷新返回数据

					columnList=wapTemplateService.getColumnlByColumnId(projectId,SHFWTemplet.SHFW_GZSC.getColumnId(),SHFWTemplet.SHFW_WXTS.getColumnId(),SHFWTemplet.SHFW_FWLC.getColumnId(),SHFWTemplet.SHFW_FWBZ.getColumnId(),SHFWTemplet.SHFW_YHHD.getColumnId());
				}*/
				if(goodsClassId==null){
	
					columnList = wapTemplateService.getColumnlByChannelId(projectId);//如果商品id为null可以确定是请求整页频道页数据
				}
				 if (projectId.equals("110")&&goodsClassId==null) {//查找栏目下的详情数据
						for (YZPPTemplet py : YZPPTemplet.values()) {
							if (DataType.GOODS_CLASS_ID == py.getType()) {//判断存入数据库的数据类型，赋值
								WordAttr wordAttr = new WordAttr();
								wordAttr.setGoodsClassId(py.getColumnId());
								wordAttr.setGoodsClassName(py.getColumnName());
								goodsMap.add(wordAttr);
								goodsClassId=goodsMap.get(0).getGoodsClassId().toString();//默认商品分类id为104 
							}
						}
					}

					if (projectId.equals("102")&&goodsClassId==null) {//判断存入数据库的数据类型，赋值
						for (JYFXTemplet py : JYFXTemplet.values()) {
							if (DataType.GOODS_CLASS_ID == py.getType()) {
								WordAttr wordAttr = new WordAttr();
								wordAttr.setGoodsClassId(py.getColumnId());
								wordAttr.setGoodsClassName(py.getColumnName());
								goodsMap.add(wordAttr);
							}
						}
					}
					if (projectId.equals(ChannelUtil.SHFW)&&goodsClassId==null) {//给售后服务频道一级商品分类
						for (SHFWTemplet py : SHFWTemplet.values()) {
							if (DataType.GOODS_CLASS_ID == py.getType()) {
								WordAttr wordAttr = new WordAttr();
								wordAttr.setGoodsClassId(py.getColumnId());
								wordAttr.setGoodsClassName(py.getColumnName());
								goodsMap.add(wordAttr);
							}
						}

					}
			
			for (ColumnAppEntity column : columnList) {//判断存入数据库的数据类型，赋值

				Integer columnType = Integer.parseInt( column.getColumnType());
				String columnId = column.getColumnId();
				String columnIds="107,108,109,110,148,103";
				//新闻资讯。权威发布、政策解读、黑暗爆料、案例解析获取置顶文章列表
		        if (columnIds.contains(columnId) &&columnType == DataType.ARTACLE_ID) {
		        	List<templetAffirmApp> columnInfoList =getArticlesByColumnId(column);
		        	for(templetAffirmApp columnInfo:columnInfoList){
	        			String updatetime=columnInfo.getUpdateTime();
	        			columnInfo.setUpdateTime(updatetime.substring(0, 10));
	        		}
		        	column.setColumnInfoList(columnInfoList);
		        } else {
				if (columnType == DataType.WORD_Attr_ID) {
					List<templetAffirmApp> columnInfoList = wapTemplateService
							     .getTempletListByColumnId(column.getId().toString(),column.getSize().toString());
					for(templetAffirmApp mapinfo:columnInfoList){
						mapinfo.setUserTrueName("");
						mapinfo.setSource("");
					}
					column.setColumnInfoList(columnInfoList);
				}
				if (columnType == DataType.ARTACLE_ID) {
					Integer isAuto=column.getIsAuto();
					if(isAuto==0){//手动获取数据
						List<templetAffirmApp> columnInfoList = wapTemplateService
							     .getTempletListByColumnId(column.getId().toString(),column.getSize().toString());
						for(templetAffirmApp mapinfo:columnInfoList){
							if(Integer.valueOf(mapinfo.getColumnInfoType())==DataType.ARTACLE_ID){
								Article  atricle=wapTemplateService.getArticleById(mapinfo.getId().toString());
								String source=atricle.getSource()==null?"":atricle.getSource().toString().trim();
								String userTrueName=atricle.getUserTrueName()==null?"":atricle.getUserTrueName().toString().trim();
								mapinfo.setSource(source);
								mapinfo.setUpdateTime(mapinfo.getUpdateTime().substring(0, 10));
						}
					}
						column.setColumnInfoList(columnInfoList);
				}
					if(isAuto==1){//自动获取数据
						if("112".equals(projectId)&&goodsClassId==null){//只有售后服务的文章栏目的文章是默认goodsClassId的
							goodsClassId=SHFWTemplet.SHFW_DDBG.getColumnId();
						}
						List<Article>	columnInfoLists=new ArrayList();
						if("112".equals(columnId)||"113".equals(columnId)){
							columnInfoLists=wapTemplateService.getArticlesList(null,columnId,null ,null,null, "0", column.getSize().toString(),"publish_date",projectId);
						}else{
						    columnInfoLists=wapTemplateService.getArticlesList(null,columnId,goodsClassId ,null,null, "0", column.getSize().toString(),"stick_date,publish_date",projectId);
						}
						List<templetAffirmApp> columnInfoList=new ArrayList();
						for(Article article:columnInfoLists){
							Article  atricle=wapTemplateService.getArticleById(article.getArticleId().toString());
							String source=atricle.getSource()==null?"":atricle.getSource().toString().trim();
							String userTrueName=atricle.getUserTrueName()==null?"":atricle.getUserTrueName().toString().trim();
							
					/*		if(source.equals(userTrueName)){
								article.setSource(source);
							}else{
								article.setSource("");
							}*/
							templetAffirmApp tempApp=new templetAffirmApp();
							tempApp.setSource(article.getSource());
							tempApp.setUserTrueName("");
							tempApp.setId(article.getArticleId());
							tempApp.setColumnInfoType(String.valueOf(DataType.ARTACLE_ID));
							tempApp.setImage(article.getTitleImage());
							tempApp.setTitle(article.getTitle());
							tempApp.setDeputyTitle(article.getDeputyTitle());
							String isBold=(tempApp.getIsBold()==null?"1":tempApp.getIsBold());
							tempApp.setIsBold(isBold);
							String updatetime=article.getPublishDate();
							tempApp.setUpdateTime(updatetime.substring(0, 10));
							columnInfoList.add(tempApp);
						}
						column.setColumnInfoList(columnInfoList);
					}
				}
				if (columnType == DataType.BRAND_ID) {
					List<templetAffirmApp> templetList=new ArrayList();
					String goods_Class_Id=goodsClassId==null?"104":goodsClassId;
					List<templetAffirmApp> columnInfoList = wapTemplateService.getBrandList(null,columnId,goodsClassId==null?YZPPTemplet.values()[0].getColumnId():goodsClassId, "0", column.getSize().toString());
					
					for (templetAffirmApp mapinfo : columnInfoList) {
						templetAffirmApp ta=new templetAffirmApp();
						ta.setColumnInfoType(String.valueOf(DataType.BRAND_ID));
						ta.setId(Integer.parseInt(mapinfo.getId().toString()));
						ta.setImage(String.valueOf(mapinfo.getImage()));
						String brandNameC = String.valueOf(mapinfo.getTitle());
						if(brandNameC.indexOf("/")!=-1){
							brandNameC= brandNameC.substring(0,brandNameC.lastIndexOf("/")); 
					     }
						ta.setTitle(brandNameC);
						ta.setSource("");
						templetList.add(ta);

					}
					column.setColumnInfoList(templetList);
				}

				if (columnType == DataType.UGC_ID) {

					if (DataType.UGC_Type_Browse.equals(columnId)) {
						List<templetAffirmApp> templetList=new ArrayList();
						List<templetAffirmApp> browseColumnInfoList = wapTemplateService.getMemberHotContent(column.getId().toString());
						for (templetAffirmApp mapinfo : browseColumnInfoList) {
							templetAffirmApp ta=new templetAffirmApp();
							ta.setId(mapinfo.getId());
							ta.setColumnInfoType(String.valueOf(DataType.UGC_ID));
							ta.setImage(mapinfo.getImage());
							ta.setTitle(mapinfo.getTitle());
							String time = (mapinfo.getUpdateTime().substring(0, 10));
							ta.setUpdateTime(time);
							ta.setUserTrueName(mapinfo.getMemberNickname());
							ta.setSource("");
							ta.setBrowseSumShow(mapinfo.getBrowseSumShow());
							templetList.add(ta);
						}
						column.setColumnInfoList(templetList);
					} else if (DataType.UGC_Type_Time.equals(columnId)) {
						List<templetAffirmApp> templetList=new ArrayList();
						List<MemberConEntity> timeColumnInfoList = wapTemplateService.getMemberContent(null,null, null,null, "0", column.getSize().toString(), "commit_date");

						for (MemberConEntity mapinfo : timeColumnInfoList) {
							templetAffirmApp ta=new templetAffirmApp();
							ta.setId(mapinfo.getContentId());
							ta.setColumnInfoType(String.valueOf(DataType.UGC_ID));
							ta.setImage(mapinfo.getTitleImage());
							ta.setTitle(mapinfo.getTitle());
							String time = (mapinfo.getPublishDate().substring(0, 10));
							ta.setUpdateTime(time);
							ta.setUserTrueName(mapinfo.getMemberNickname());
							ta.setSource("");
							ta.setBrowseSumShow(mapinfo.getBrowseSumShow());
							templetList.add(ta);
						}
						column.setColumnInfoList(templetList);
					} else if (DataType.UGC_Type_PaiHan.equals(columnId)) {
						List<templetAffirmApp> templetList=new ArrayList();
						List<LogExp> logExpList = wapTemplateService.getlogExpShort(0,5);

						for (LogExp mapinfo : logExpList) {
							templetAffirmApp ta=new templetAffirmApp();
							ta.setId(mapinfo.getMemberId());
							ta.setColumnInfoType(String.valueOf(DataType.UGC_ID));
							ta.setImage(mapinfo.getIcon());
							ta.setTitle(String.valueOf(mapinfo.getExp()));
							ta.setUserTrueName(mapinfo.getNickName());
							ta.setUpdateTime("");
							ta.setSource("");
							templetList.add(ta);
						}
						column.setColumnInfoList(templetList);
					}else {
						List<MemberConEntity> otherColumnInfoList=new ArrayList<MemberConEntity>();
						if(DataType.UGC_Type_BaoGuang.equals(columnId)){
							otherColumnInfoList = wapTemplateService.getMemberImageAndContent(column.getColumnId(), goodsClassId, "0",column.getSize().toString(), "publish_date");
						}else{
							otherColumnInfoList = wapTemplateService.getMemberContent(null,column.getColumnId(), goodsClassId==null?"104":goodsClassId,null, "0",column.getSize().toString(), "publish_date");
	   
						}
							List<templetAffirmApp> templetList=new ArrayList();
						for (MemberConEntity mapinfo : otherColumnInfoList) {
							templetAffirmApp ta=new templetAffirmApp();
							ta.setId(mapinfo.getContentId());
							ta.setColumnInfoType(String.valueOf(DataType.UGC_ID));
							ta.setImage(mapinfo.getTitleImage());
							ta.setTitle(mapinfo.getTitle());
							ta.setUpdateTime(mapinfo.getPublishDate().substring(0, 19));
							ta.setUserTrueName(mapinfo.getMemberNickname());
							ta.setSource("");
							ta.setBrowseSumShow(mapinfo.getBrowseSumShow());
							templetList.add(ta);
						}
						column.setColumnInfoList(templetList);
					}

				}
		        }
			}
			for(ColumnAppEntity column : columnList){
			List<templetAffirmApp> columnInfoList = column.getColumnInfoList();
			
				if(columnInfoList.size()==32){
				
					int size=0;
					boolean tag=false;
					int row=0;
					int index=0;
					for(templetAffirmApp infomap:columnInfoList){
						index++;
						if(index==31||index==32)continue;
						if(row==5){
							tag=false;
							size=0;
							row=0;
						}
						row++;
						if(infomap.getTitle()==null) continue;
						int titleLength=infomap.getTitle().toString().length();
						size=size+titleLength;
					
						if(size>11&&!tag){
							infomap.setTitle(infomap.getTitle().toString().substring(0,titleLength-(size-11)));
						
							tag=true;
							continue;
						}
						if(tag){
							infomap.setTitle(null);
				
						}
					}
				}
			}
			paramMap.put("goodsMap", goodsMap);
			paramMap.put("goodsClassId", goodsClassId);
			paramMap.put("columnList", columnList);
			paramMap.put("channelId", projectId);
			paramMap.put("channelName", channelName);
			
			List<AfterSale> list = wapTemplateService.getAfterSaleLists("北京市","市辖区", null,null,0, 10,null);
			List<AfterSale> strTypeList = wapTemplateService.getAfterSaleLists("北京市","市辖区", null,null,null,null,null);
			// 获取到所有数据中的类型,因为有些服务网点是多个类型的，所以需要分割进行比对，生成一个列表，列表中是所有数据中
			//存在的类型列表
			List<String> strTypeValList = new ArrayList<String>();
		   for (AfterSale afterSale : strTypeList) {
			 String typeVal = afterSale.getTypeVal();
			
			if (typeVal != null) {
				String type[] = typeVal.split(",");
				for (int i = 0; i < type.length; i++) {
					if (StringUtil.isNullOrStr(type[i])) {
						continue;
					}
					if (!strTypeValList.contains(type[i])) {
						
						strTypeValList.add(type[i]);
					}
				}
			}
			
		 }
		   paramMap.put("strTypeValList", strTypeValList);
		   paramMap.put("areaList", list);
		   //页面分类
		   String templateName1="";
			if("103".equals(projectId)){
				templateName1="yj";
			}
			if("104".equals(projectId)){
				templateName1="jq";
			}
			if("105".equals(projectId)){
				templateName1="ts";
			}
			if("106".equals(projectId)){
				templateName1="wq";
			}
			if("107".equals(projectId)){
				templateName1="xj";
			}
			if("108".equals(projectId)){
				templateName1="lc";
			}
			if("109".equals(projectId)){
				templateName1="bz";
			}
			if("111".equals(projectId)){
				templateName1="hl";
			}
			if("101".equals(projectId)){
				templateName1="bg";
			}
			if("102".equals(projectId)){
				templateName1="fx";
			}
			if("110".equals(projectId)){
				templateName1="yp";
			}
			 paramMap.put("templateName", templateName1);
		   
		 //查询搜索框文字
			 List<HotWordEntity> searchList = templateService.getFindSearchList();
			 String  pMessage="";
			if(searchList.size()>0){
			   pMessage= searchList.get(0).getpMessage();
			}
			 paramMap.put("pMessage", pMessage);
			return paramMap;
			
		}
			
			 /**
		     * 根据columnId获取文章列表，从栏目模版表中获取数据，再通过文章表中读取该栏目数据
		     * 以及最大数据量
		     *
		     * @return 返回置顶数据和补充的数据列表
		     */
	private List<templetAffirmApp> getArticlesByColumnId(ColumnAppEntity  columnInfo) {

		        String id = columnInfo.getId().toString();
		        //栏目展示最大数据数量
		        String maxSize = columnInfo.getSize().toString();
		        String columnId = columnInfo.getColumnId().toString();

		        Map<String, Object> params = new HashMap<>();
		        params.put("order", "stick_date");
		        params.put("columnId", columnId);
		        params.put("isStick", 1);
		        params.put("pageNum", 0);
		        params.put("pageSize", Integer.parseInt(maxSize));
		        List<templetAffirmApp> columnInfoList = wapTemplateService.getArticlesLists(null,columnId,null,
		        		                                null,null,"0",maxSize, "stick_date", null,"1");
		        //计算需要补充的数据条数
		        int addCount =
		                columnInfoList == null ? 0 : Integer.parseInt(maxSize) - columnInfoList.size();
		        if (addCount != 0) {
		            params.put("isStick", 0);
		            params.put("pageSize", addCount);
		            params.put("order", "stick_date,publish_date");
		            columnInfoList.addAll(wapTemplateService.getArticlesLists(null,columnId,null,
                            null,null,"0",String.valueOf(addCount), "stick_date,publish_date", null,"0"));
		        }
		        for (templetAffirmApp data : columnInfoList) {
		            data.setColumnInfoType("2");
		        }
		        return columnInfoList;
		    }
		/**
		 * 生成售后服务频道页
		 */
		public Map<String, Object> createHtmlWapShfwChannel(String projectId){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String channelName=ChannelUtil.getChannelName(projectId);
			// 查找频道页轮廓图
			List<FocusImageEntity2> focusImageList = wapTemplateService
					.findImageList(projectId);
			paramMap.put("fImageList", focusImageList);
			
			
			List<Map> goodsList=new ArrayList();
			for (NEW_SHFWTemplet py : NEW_SHFWTemplet.values()) {
				Map goodsmap=new HashMap();
				goodsmap.put("id",py.getColumnId());
				goodsmap.put("title", py.getColumnName());
				if("101".equals(py.getColumnId())){
					 paramMap.put("columnName", py.getColumnName());
				}
				
				goodsList.add(goodsmap);
			}
			 paramMap.put("goodsMap", goodsList);
			
		     String	goodsClassId=NEW_SHFWTemplet.SHFW_JDDQ.getColumnId();
		     if("0".equals(goodsClassId)){
					goodsClassId="101";
					paramMap.put("goodsClassIds", "101");
				}else{
					paramMap.put("goodsClassIds", goodsClassId);
				}

			 //根据商品分类查出对应的栏目
		   /*  List<ColumnAppEntity> columnList = wapTemplateService.getColumnlByChannelId(projectId);*/
			 List<ColumnAppEntity> columnList=wapTemplateService.getColumnlByGoodsId(goodsClassId,"112");
		
		/*	 for (ColumnAppEntity column: columnList) {
				 List<templetAffirmApp> columnInfoList=column.getColumnInfoList();
				 for(templetAffirmApp columnInfo:columnInfoList){
					String title = (String) columnInfo.getTitle();
					String contentIntro = (String) columnInfo.getContentIntro();
					for (int i = 9; i < title.length(); i++) {
						if ((i + 1) > 9) {
							String s = StringUtil.subString(title, 9, "...");
							columnInfo.setTitle(s);
							break;
						}
					}
					for (int j = 25; j < contentIntro.length(); j++) {
						if ((j + 1) > 25) {
							String contentIntros = StringUtil.subString(
									contentIntro, 25, "...");
							columnInfo.setContentIntro(contentIntros);
							break;
						}
					}
					String updatetime=DateUtil.getStringFromLongDate(columnInfo.getUpdateTime().toString());
					columnInfo.setUpdateTime(updatetime.substring(0, 10));
			    }
			 }*/
			/*
			 * 查询频道页该分类下需要展示的品牌
			 */
			 
			 ColumnAppEntity brandMap=new ColumnAppEntity();
			brandMap.setColumnId("");
			brandMap.setColumnType("6");
			brandMap.setColumnName("");
			List<Exhibition> brandList=wapTemplateService.getBrandByType(goodsClassId,"2");
			List<templetAffirmApp> brandList111= new ArrayList<templetAffirmApp>();
			for(int i=0;i<brandList.size();i++){
				Exhibition obj = brandList.get(i);
				templetAffirmApp appObj = new templetAffirmApp();
				appObj.setId(obj.getId());
				appObj.setTitle(obj.getTitle());
				appObj.setImage(obj.getImage());
				brandList111.add(appObj);
			}
			brandMap.setColumnInfoList(brandList111);
			columnList.add(0,brandMap);
			/*
			 * 查询该分类下的词条
			 */
			 ColumnAppEntity wordMap=new ColumnAppEntity();
			 wordMap.setColumnId("");
			 wordMap.setColumnType("1");
			 wordMap.setColumnName("");
			 List<Exhibition> wordList=wapTemplateService.getBrandByGoodsId(goodsClassId,"1");
			  for(Exhibition word:wordList){
                	Word  wordById = wapTemplateService.getWordById(word.getId().toString());
                	if(wordById!=null){
                		String wordGoodsClassId=wordById.getGoodsClassId()==null?"":wordById.getGoodsClassId().toString();
                		word.setGoodsClassId(wordGoodsClassId);
                	}
                 }
			  List<templetAffirmApp> wordList11= new ArrayList<templetAffirmApp>();
				for(int i=0;i<wordList.size();i++){
					Exhibition obj = wordList.get(i);
					templetAffirmApp appObj = new templetAffirmApp();
					appObj.setId(obj.getId());
					appObj.setTitle(obj.getTitle());
					appObj.setImage(obj.getImage());
					appObj.setGoodsClassId(obj.getGoodsClassId());
					wordList11.add(appObj);
				}
				wordMap.setColumnInfoList(wordList11);
				columnList.add(0,wordMap);
			  
            //售后服务网点数据加载
			List<AfterSale> list = wapTemplateService.getAfterSaleLists("北京市","市辖区", null,null,0, 10,null);
			List<AfterSale> strTypeList = wapTemplateService.getAfterSaleLists("北京市","市辖区", null,null,null,null,null);
			// 获取到所有数据中的类型,因为有些服务网点是多个类型的，所以需要分割进行比对，生成一个列表，列表中是所有数据中
			//存在的类型列表
			List<String> strTypeValList = new ArrayList<String>();
		   for (AfterSale afterSale : strTypeList) {
			 String typeVal = afterSale.getTypeVal();
			
			if (typeVal != null) {
				String type[] = typeVal.split(",");
				for (int i = 0; i < type.length; i++) {
					if (StringUtil.isNullOrStr(type[i])) {
						continue;
					}
					if (!strTypeValList.contains(type[i])) {
						
						strTypeValList.add(type[i]);
					}
				}
			}
			
		 }
		
		 //查询搜索框文字
		 List<HotWordEntity> searchList = templateService.getFindSearchList();
		   String  pMessage="";
			if(searchList.size()>0){
			   pMessage= searchList.get(0).getpMessage();
			}
		  paramMap.put("pMessage", pMessage);
		   paramMap.put("strTypeValList", strTypeValList);
		   paramMap.put("channelId", "112");
		   paramMap.put("areaList", list);
		   paramMap.put("columnList", columnList);
		
		   return paramMap;
		}
		
		
		
}
