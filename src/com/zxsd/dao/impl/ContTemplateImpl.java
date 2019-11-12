package com.zxsd.dao.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.*;
import com.zxsd.dao.interfaces.IContTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContTemplateImpl extends BaseDao<ContTemplate>  implements IContTemplate{

	@Override
	public List<ContTemplate> queryIndexList(String channelName,String columnName) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select * from tb_templet_affirm where is_enabled=0 ");
		if(!StringUtil.isNOrS(channelName)){
			sql.append(" and channel_name=? ");
			list.add(channelName);
		}
		if(!StringUtil.isNOrS(columnName)){
			sql.append(" and column_name =? ");
			list.add(columnName);
		}
		sql.append(" order by seq_num asc");
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(ContTemplate.class));
	}

	@Override
	public List<WebsiteLink> queryWebLinkList(String channel_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_website_link where is_show=0 and channel_id=? order by web_sort");
		return this.queryList(sql.toString(), new Object[]{channel_id}, new BeanPropertyRowMapper(WebsiteLink.class));
	}

	@Override
	public List<FocusImageEntity> getImagesByChalId(String channelId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_focus_image_affirm where channel_id=?");
		sql.append(" and is_show = 0 and is_enabled = 0 order by image_sort");
		return this.queryList(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(FocusImageEntity.class));
	}

	@Override
	public List<GoodsClassEntity> getAllGoodsResources(String goods_class_type) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * ");
		sql.append(" from tb_goods_class where 1=1");
		sql.append(" and is_display= 0 and goods_class_type =?");
		sql.append(" order by priority");
		return  this.queryList(sql.toString(), new Object[]{goods_class_type}, new BeanPropertyRowMapper(GoodsClassEntity.class));
	}

	@Override
	public List<ArticleEntity> getArticlesByColid(String channelId,String columnId,String searchType,String limits,boolean isStick,boolean isColid) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select article_id,title,deputy_title,is_stick,stick_date,title_image,source,content_intro,first_publish_date,publish_date,page_url,user_true_name from tb_article ");
		sql.append(" where is_publish=0 and status=3 and publish_date <= now()");
		if(!StringUtil.isNOrS(channelId))
		{
			sql.append(" and channel_id = ?");
			list.add(channelId);
		}
		if(!StringUtil.isNOrS(columnId))
		{
			if(columnId.equals("151")){//首页摘要
				sql.append(" and column_id not in (127,128) ");
			}else{
				sql.append(" and column_id=? ");
				list.add(columnId);
			}
		}
		if(!StringUtil.isNOrS(searchType))
		{
			sql.append(" and search_type = ?");
			list.add(searchType);
		}
		if(isColid)
		{
			sql.append(" order by is_stick desc,publish_date desc limit 0, ");
		}else{
			if(isStick){
				sql.append(" and is_stick=1");
			}else{
				sql.append(" and is_stick=0");
			}
			sql.append(" order by publish_date desc limit 0, ");
		}
		sql.append(limits);
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public ChannelEntity queryChanname(String channelId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_channel where channel_id=? ");
		return (ChannelEntity) this.queryObject(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(ChannelEntity.class));
	}

	@Override
	public List<MemberContent> getNewMemContents(String channelId,
			String columnId, String limits,String isStick,boolean isCloid) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select t1.browse_sum_show,t1.content_id,t1.title,t1.title_image,t1.column_id,t1.content_intro,t1.page_url, t1.publish_date,t1.goods_class_id,t2.goods_class_name,");
		sql.append(" ifnull(ifnull(t3.member_nickname,concat(substring(t3.member_mobile,1,3),'****',substring(t3.member_mobile,8))),t3.member_email) member_nickname ");
		sql.append(" from tb_member_content t1");
		sql.append(" left join tb_goods_class t2 on t1.goods_class_id = t2.goods_class_id");
		sql.append(" left join tb_member t3 on t1.member_id=t3.member_id");
		sql.append(" where t1.is_publish = 0 and t1.status=3");
		if(!StringUtil.isNOrS(channelId)){
			sql.append(" and t1.channel_id=?");
			list.add(channelId);
		}
		if(!StringUtil.isNOrS(columnId)){
			sql.append(" and t1.column_id=?");
			list.add(columnId);
		}
		if(!StringUtil.isNOrS(isStick)){
			sql.append(" and t1.is_stick=?");
			list.add(isStick);
		}
		if(isCloid){
			sql.append(" order by t1.is_stick desc, t1.publish_date desc");
		}else{
			sql.append(" order by t1.publish_date desc");
		}
		sql.append(" limit 0,");
		sql.append(limits);
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(MemberContent.class));
	}

	@Override
	public List<MemberContent> getMemContentsByPraise(String channelId,
			String limits) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.*,t2.goods_class_name from tb_member_content t1,tb_goods_class t2 ");
		sql.append(" where t1.goods_class_id = t2.goods_class_id");
		sql.append(" and t1.is_publish = 0 and t1.status=3 and t1.channel_id=? order by t1.praise_sum desc");
		sql.append(" limit 0,");
		sql.append(limits);
		return this.queryList(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(MemberContent.class));
	}

	@Override
	public List<MemberContent> getMemContentsByDate(String channelId,
			String limits) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.*,t2.goods_class_name from tb_member_content t1,tb_goods_class t2 ");
		sql.append(" where t1.goods_class_id = t2.goods_class_id");
		sql.append(" and t1.is_publish = 0 and t1.status=3 and t1.is_stick=0 and t1.channel_id=? order by t1.publish_date desc");
		sql.append(" limit 0,");
		sql.append(limits);
		return this.queryList(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(MemberContent.class));
	}

	@Override
	public MebContentNum getMemberNums(String channelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) memNum,sum(t.num) contNum from (select member_id,count(*) num from tb_member_content where channel_id=? and status=3 group by member_id) t");
		return (MebContentNum) this.queryObject(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(MebContentNum.class));
	}

	@Override
	public List<MebContentNum> getMemContentsByweek(String channelId,String limit) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t2.member_id,t2.member_nickname,t2.member_icon,count(*) contNum ");
		sql.append(" from tb_member_content t1,tb_member t2");
		sql.append(" where t1.member_id = t2.member_id and t1.channel_id=? and t1.status=3");
		/*sql.append(" and t1.commit_date between adddate(curdate(),-6) and adddate(curdate(),1)");*/
		sql.append(" group by t1.member_id");
		sql.append(" order by contNum desc");
		sql.append(" limit 0,");
		sql.append(limit);
		return this.queryList(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(MebContentNum.class));
	}

	@Override
	public List<ArticleEntity> getBrandStorys(String column_id, String limits) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.* from tb_article t1");
		sql.append(" where t1.column_id=? and t1.is_publish=0 and t1.status=3");
		sql.append(" order by t1.publish_date desc limit 0, ");
		sql.append(limits);
		return this.queryList(sql.toString(), new Object[]{column_id}, new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<GoodsClass> findGoodsClassByParent() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT goods_class_level as goodsClassLevel,goods_class_id as goodsClassId,goods_class_name as goodsClassName ,p_goods_class_id as pGoodsClassId  ");
		sql.append(" FROM tb_goods_class t ");
		sql.append(" where goods_class_level=1");
		return this.queryList(sql.toString(), new Object[]{},new BeanPropertyRowMapper(GoodsClass.class));
	}

	@Override
	public GoodsClass findGoodsClassById(String goodsid) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT goods_class_level as goodsClassLevel,goods_class_id as goodsClassId,goods_class_name as goodsClassName ,p_goods_class_id as pGoodsClassId ,goods_class_type as goodsClassType ");
		sql.append(" FROM tb_goods_class t ");
		sql.append(" where goods_class_id=?");
		return (GoodsClass) this.queryObject(sql.toString(), new Object[]{goodsid}, new BeanPropertyRowMapper(GoodsClass.class));
	}

	@Override
	public List<SubjectEntity> getSubjectList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from tb_subject where subject_state=5 order by subject_publishdate desc limit 0,2");
		return this.queryList(sql.toString(), new Object[]{},new BeanPropertyRowMapper(SubjectEntity.class));
	}

	@Override
	public List<SubjectTemplateEntity> getSubjectTemLists(String subject_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.section_name as section_name from tb_subject_template t1,tb_subject_section_attr t2 ");
		sql.append(" where t1.t_subject_section_id = t2.section_id  and t1.subject_id=t2.subject_id");
		sql.append(" and t1.subject_id=?");
		sql.append(" order by  t1.t_subject_section_id asc, t1.t_subject_serial_number asc");
		return this.queryList(sql.toString(), new Object[]{subject_id},new BeanPropertyRowMapper(SubjectTemplateEntity.class));
	}

	@Override
	public SubjectEntity getSubjectInfo(String subject_id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from tb_subject where subject_id=? ");
		return (SubjectEntity) this.queryObject(sql.toString(), new Object[]{subject_id}, new BeanPropertyRowMapper(SubjectEntity.class));
	}

	@Override
	public List<ContTempletNew> queryNewIndexList(String channelName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_templet_affirm_new where channel_name=? and is_enabled=0 ");
		sql.append(" order by seq_num asc ");
		return this.queryList(sql.toString(), new Object[]{channelName}, new BeanPropertyRowMapper(ContTempletNew.class));
	}

	@Override
	public List<SurveyQuestEntity> getSurveyLists(String survey_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.*,t3.survey_name,t3.survey_intro,t2.answ_val answs,t2.add_answ_val addAnsws,t2.is_val isVals from tb_survey t3");
		sql.append(" left join tb_question t1 on t1.survey_id = t3.survey_id");
		sql.append(" left join tb_answ t2 on t1.quest_id = t2.quest_id ");
		sql.append(" where t3.survey_id=? and t1.status=0");
		sql.append(" order by t1.quest_num");
		return this.queryList(sql.toString(), new Object[]{survey_id}, new BeanPropertyRowMapper(SurveyQuestEntity.class));
	}

	@Override
	public List<ArticleEntity> getAllWordList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT o.* FROM (SELECT t1.p_title title,t1.page_url,t1.goods_class_id FROM tb_word t1 where t1.is_publish=0 ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT t2.title,t2.page_url,t1.goods_class_id FROM tb_word_attr t2,tb_word t1 WHERE t1.word_id = t2.word_id and t1.is_publish=0) o  ");
		sql.append(" ORDER BY o.title ");
		return this.queryList(sql.toString(), new Object[]{}, new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<ArticleEntity> getAllArticleList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.title,t1.page_url,t2.column_name from tb_article t1,tb_column t2 ");
		sql.append(" where t1.column_id = t2.column_id ");
		sql.append(" and t1.is_publish=0 and t1.status=3");
		return this.queryList(sql.toString(), new Object[]{}, new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<GoodsClassEntity> getFirGoodsList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from tb_goods_class ");
		sql.append(" where is_display = 0 and goods_class_level < 3");
		return this.queryList(sql.toString(), new Object[]{},new BeanPropertyRowMapper(GoodsClassEntity.class));
	}

	@Override
	public List<ArticleEntity> getAllUGCList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.title,t1.page_url,t2.column_name from tb_member_content t1,tb_column t2 ");
		sql.append(" where t1.column_id = t2.column_id ");
		sql.append(" and t1.is_publish=0 and t1.status=3");
		return this.queryList(sql.toString(), new Object[]{}, new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<AfterBrandGclass> findAfterBrandList() {
		// TODO Auto-generated method stub
		String sql=" select brand_id brandId,brand_name_s brandName from tb_brand where is_display=0";
		return this.queryList(sql, new Object[]{}, new BeanPropertyRowMapper(AfterBrandGclass.class));
	}

	@Override
	public List<SubjectEntity> queryPubSubList(String subject_id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select subject_id,subject_name,subject_introduction,subject_image,subject_url,subject_keyword");
		sql.append(" from tb_subject where subject_state=5 and subject_id not in (?) order by subject_publishdate desc limit 0,8");
		return this.queryList(sql.toString(), new Object[]{subject_id}, new BeanPropertyRowMapper(SubjectEntity.class));
	}

	@Override
	public List<ArticleEntity> getStickArticlesByColid(String goods_class_id,
			String column_id) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select article_id,title,deputy_title,is_stick,stick_date,title_image,source,content_intro,publish_date,page_url from tb_article ");
		sql.append(" where is_publish=0 and status=3 and is_stick=1 and publish_date <= now()");
		if(!StringUtil.isNOrS(column_id))
		{
			sql.append(" and column_id=? ");
			list.add(column_id);
		}
		sql.append(" order by stick_date DESC  ");
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<HotWordEntity> getFindSearchList() {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT prompt_message as pMessage, search_hot_words as sHotWord FROM tb_search_hot_words WHERE status=2");

		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(HotWordEntity.class));
	}

	@Override
	public MemberContent getMemcontInfoByParam(String page_url,
			String content_id) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select t1.browse_sum_show,t1.content_id,t1.title,t1.title_image,t1.column_id,t1.content_intro,t1.page_url, t1.publish_date,t1.goods_class_id,t2.goods_class_name,");
		sql.append(" ifnull(ifnull(t3.member_nickname,concat(substring(t3.member_mobile,1,3),'****',substring(t3.member_mobile,8))),t3.member_email) member_nickname ");
		sql.append(" from tb_member_content t1 left join tb_goods_class t2 on t1.goods_class_id = t2.goods_class_id");
		sql.append(" left join tb_member t3 on t1.member_id=t3.member_id");
		sql.append(" where 1=1 ");
		if(!StringUtil.isNOrS(page_url)){
			sql.append(" and t1.page_url=?");
			list.add(page_url);
		}
		if(!StringUtil.isNOrS(content_id)){
			sql.append(" and t1.content_id=?");
			list.add(content_id);
		}
		return (MemberContent) this.queryObject(sql.toString(), list.toArray(),  new BeanPropertyRowMapper(MemberContent.class) );
	}

	@Override
	public List<Tb_subject_section> getSubjectTemplateSection(String subjectid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_subject where subject_id=?");
		SubjectEntity subject = (SubjectEntity) this.queryObject(sql.toString(), new Object[]{subjectid}, new BeanPropertyRowMapper(SubjectEntity.class));
		sql = new StringBuffer();
		sql.append("select * from tb_subject_section where s_style_id=? order by section_id asc");
		return this.queryList(sql.toString(), new Object[]{subject.getSubject_style_id()}, new BeanPropertyRowMapper(Tb_subject_section.class));
	}

	@Override
	public List<WordEntity> getWordList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t1.word_id,t1.word_code,t1.p_title from tb_code_library t2");
		sql.append(" inner join tb_word t1 on t1.word_id=t2.id");
		sql.append(" where t1.status=3 and t1.is_publish=0 and t2.type=2");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(WordEntity.class));
	}

	@Override
	public List<CodeEntity> getCodeShowList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT id,coded_attr_id goodsCode");
		sql.append(" FROM tb_code_library");
		sql.append(" where type=1");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(CodeEntity.class));
	}

	@Override
	public List<WordEntity> getWordsListByNewGoods(String goods_class_id, String limit,String channel_id) {
		StringBuffer sql = new StringBuffer( );
		List<Object> list = new ArrayList<Object>(); 
		sql.append(" SELECT t1.good_class_id goods_class_id,t2.label_name goods_class_name,t4.word_id,t4.word_code,t4.p_title,t5.word_attr_code");
		sql.append(" FROM tb_good_tree t1 ");
		sql.append(" INNER JOIN tb_good_label t2 ON t1.label_id=t2.id");
		sql.append(" INNER JOIN tb_label_word t3 ON t1.label_id=t3.label_id");
		sql.append(" INNER JOIN tb_word t4 ON t3.word_id=t4.word_id");
		sql.append(" LEFT JOIN tb_word_attr t5 ON t3.word_id=t5.word_id");
		sql.append(" WHERE t4.status=3 AND t4.is_publish=0 and t2.label_status=1");
		sql.append(" and t5.channel_id=?");
		list.add(channel_id);
		if(!StringUtil.isNOrS(goods_class_id)){
			sql.append(" and t1.good_class_id like ?");
			list.add(goods_class_id+"%");
		}
		sql.append(" group by t3.word_id");
		sql.append(" order by t4.publish_date desc");
		sql.append(" limit 0,");
		sql.append(limit);
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(WordEntity.class));
	}

	@Override
	public List<ArticleEntity> queryArticleListByNewGoods(
			String goods_class_id, String column_id, String limit) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		sql.append(" SELECT t1.good_class_id goods_class_id,t2.label_name goods_class_name,t4.article_id,t4.column_id,t4.source,");
		sql.append(" t4.title,t4.title_image,t4.publish_date,t4.page_url,t4.key_words,t4.content_intro,t4.user_true_name");
		sql.append(" FROM tb_good_tree t1 ");
		sql.append(" INNER JOIN tb_good_label t2 ON t1.label_id=t2.id");
		sql.append(" INNER JOIN tb_label_article t3 ON t1.label_id=t3.label_id");
		sql.append(" INNER JOIN tb_article t4 ON t3.article_id=t4.article_id and t2.label_status=1");
		sql.append(" WHERE t4.status=3 AND t4.is_publish=0 AND t4.publish_date < now()");
		if(!StringUtil.isNOrS(goods_class_id)){
			sql.append(" and t1.good_class_id like ?");
			list.add(goods_class_id+"%");
		}
		if(!StringUtil.isNOrS(column_id)){
			if(column_id.contains(",")){
				sql.append(" and t4.column_id in ");
				sql.append("("+column_id+")");
			}else{
				sql.append(" and t4.column_id = ?");
				list.add(column_id);
			}
		}
		sql.append(" group by t3.article_id");
		sql.append(" order by t4.publish_date desc");
		if(!StringUtil.isNOrS(limit)){
			sql.append(" limit 0,");
			sql.append(limit);
		}
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public BrandEntity getBrandInfo(String brand_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from tb_brand ");
		sql.append(" where brand_id=? and is_display=0");
		return (BrandEntity) this.queryObject(sql.toString(), new Object[]{brand_id},  new BeanPropertyRowMapper(BrandEntity.class));
	}

	@Override
	public ArticleEntity quertArticleInfo(String articleId, String pageUrl,
			String title) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select article_id,title,deputy_title,is_stick,source,title_image,content_intro,first_publish_date,publish_date,page_url from tb_article ");
		sql.append(" where 1=1");
		if(!StringUtil.isNOrS(articleId))
		{
			sql.append(" and article_id=? ");
			list.add(articleId);
		}
		if(!StringUtil.isNOrS(pageUrl))
		{
			sql.append(" and page_url=? ");
			list.add(pageUrl);
		}
		if(!StringUtil.isNOrS(title))
		{
			sql.append(" and title=1 ");
			list.add(title);
		}
		return (ArticleEntity) this.queryObject(sql.toString(), list.toArray(), new BeanPropertyRowMapper(ArticleEntity.class));
	}

	@Override
	public List<PiclibEntity> getPiclibList(String linkGoodsClassId,
			String limit) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select * from tb_piclib where status=4 ");
		if(!StringUtil.isNOrS(linkGoodsClassId)){
			sql.append(" and link_goods_class_id like ? ");
			list.add(linkGoodsClassId+"%");
		}
		sql.append(" order by publish_time desc");
		if(!StringUtil.isNOrS(limit)){
			sql.append(" limit 0,").append(limit);
		}
		return  this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(PiclibEntity.class));
	}

	@Override
	public List<CodeEntity> getBrandClassifyList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select id,classify_name showName from tb_brand_classify where status=5");
		return this.queryList(sql.toString(), null, new BeanPropertyRowMapper(CodeEntity.class));
	}

	@Override
	public List<MemberContent> queryMemcontByListNewGoods(String goodsClassId, String columnId, String isStick, String limit) {
		StringBuffer sql = new StringBuffer( );
		List<String> list = new ArrayList<String>();
		sql.append(" SELECT t1.good_class_id goods_class_id,t2.label_name goods_class_name,t4.content_id,");
		sql.append(" t4.title,t4.title_image,t4.publish_date,t4.page_url,t4.key_words,t4.content_intro,t4.browse_sum_show,");
		sql.append(" ifnull(ifnull(t5.member_nickname,concat(substring(t5.member_mobile,1,3),'****',substring(t5.member_mobile,8))),t5.member_email) member_nickname");
		sql.append(" FROM tb_good_tree t1 ");
		sql.append(" INNER JOIN tb_good_label t2 ON t1.label_id=t2.id");
		sql.append(" INNER JOIN tb_label_ugc t3 ON t1.label_id=t3.label_id");
		sql.append(" INNER JOIN tb_member_content t4 ON t3.member_content_id=t4.content_id");
		sql.append(" LEFT JOIN tb_member t5 on t4.member_id=t5.member_id");
		sql.append(" WHERE t4.status=3 AND t4.is_publish=0 and t2.label_status=1");
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and t1.good_class_id like ?");
			list.add(goodsClassId+"%");
		}
		if(!StringUtil.isNOrS(columnId)){
			sql.append(" and t4.column_id = ?");
			list.add(columnId);
		}
		if(!StringUtil.isNOrS(isStick)){
			sql.append(" and t4.is_stick = ?");
			list.add(isStick);
		}
		sql.append(" group by t3.member_content_id");
		sql.append(" order by t4.publish_date desc");
		sql.append(" limit 0,");
		sql.append(limit);
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(MemberContent.class));
	}

	@Override
	public List<BrandEntity> getIsTenBrandList(String brandType, String goodsClassId, String brandName, String limit) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append(" select t1.brand_id,t1.brand_name_s,t1.brand_image,t1.brand_name_c,t1.brand_name_e,t2.goods_class_id from tb_brand t1,");
		sql.append(" tb_brand_ten_map t2 where t1.brand_id = t2.brand_id ");
		sql.append(" and t1.is_display=0");
		if(!StringUtil.isNOrS(brandType)){
			sql.append(" and t2.brand_type = ? ");
			list.add(brandType);
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and t2.goods_class_id =? ");
			list.add(goodsClassId);
		}
		if(!StringUtil.isNOrS(brandName)){
			sql.append(" and t1.brand_name_s like ? ");
			list.add("%"+brandName+"%");
		}
		sql.append(" group by  t1.brand_id order by t2.brand_sort limit 0,");
		sql.append(limit);
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(BrandEntity.class));
	}

	@Override
	public List<WordEntity> getCategorysListByNewGoods(String goods_class_id,
			String limit) {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>(); 
		sql.append(" SELECT t1.good_class_id goods_class_id,t2.label_name goods_class_name,t4.type_id,t4.show_name");
		sql.append(" FROM tb_good_tree t1 ");
		sql.append(" INNER JOIN tb_good_label t2 ON t1.label_id=t2.id");
		sql.append(" INNER JOIN tb_label_type t3 ON t1.label_id=t3.label_id");
		sql.append(" INNER JOIN tb_category t4 ON t3.type_id=t4.type_id");
		sql.append(" WHERE t4.status=5 and t2.label_status=1");
		if(!StringUtil.isNOrS(goods_class_id)){
			sql.append(" and t1.good_class_id like ?");
			list.add(goods_class_id+"%");
		}
		sql.append(" group by t3.type_id");
		sql.append(" order by t4.publish_time desc");
		sql.append(" limit 0,");
		sql.append(limit);
		return this.queryList(sql.toString(), list.toArray(), new BeanPropertyRowMapper(WordEntity.class));
	}


}
