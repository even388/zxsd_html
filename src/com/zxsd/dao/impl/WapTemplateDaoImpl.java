package com.zxsd.dao.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.*;
import com.zxsd.dao.interfaces.WapTemplateDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WapTemplateDaoImpl extends BaseDao<ContTemplate>  implements WapTemplateDao{

	
	@Override
	public ChannelEntity queryChanname(String channelId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tb_channel where channel_id=?");
		return (ChannelEntity) this.queryObject(sql.toString(), new Object[]{channelId}, new BeanPropertyRowMapper(ChannelEntity.class));
	}

	@Override
	public List<FocusImageEntity2> findImageList(String projectId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select image_name as imageName,image_path as imagePath,link_url as linkUrl,project_id as projectId,project_type as projectType,channel_path as channelPath,goods_class_id as goodsClassId from tb_focus_image_affirm_app ");
		sql.append(" where channel_id = ?");
		sql.append(" and is_show =0 ");
		sql.append(" and is_enabled =0 order by image_sort");
		return this.queryList(sql.toString(), new Object[]{projectId}, new BeanPropertyRowMapper(FocusImageEntity2.class));
	}

	@Override
	public List<ChannelEntity> findChannelList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select channel_id as channelId,channel_name as channelName from tb_channel");
		sql.append(" where channel_id != 100 order by channel_sort");
		return this.queryList(sql.toString(), new Object[]{}, new BeanPropertyRowMapper(ChannelEntity.class));
	}

	@Override
	public List<ColumnAppEntity> getColumnlByChannelId(String projectId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select id,channelId,columnName,columnType,columnId,size,isAuto,pageModel from tb_column_app");
		sql.append(" where channelId = ? order by short");
	
		return this.queryList(sql.toString(), new Object[]{projectId}, new BeanPropertyRowMapper(ColumnAppEntity.class));
	}

	@Override
	public List<templetAffirmApp> getTempletListByColumnId(String columnId,String pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append("select projectId as id,columnInfoType,title,contentIntro,isBold,image,updateTime from tb_templet_affirm_app");
		sql.append(" where columnId = ?");
		sql.append(" and isEnabled=0 order by short");
		if(pageSize!=null){
			sql.append(" limit 0,");
			sql.append(pageSize);
		}
		return this.queryList(sql.toString(), new Object[]{columnId}, new BeanPropertyRowMapper(templetAffirmApp.class));
	}

	@Override
	public Article getArticleById(String article_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select article_id as articleId,user_true_name as userTrueName,title,title_image AS titleImage,source,content,publish_date as publishDate ,praise_sum as praiseSum, channel_id as channelId,file_publish_date as filePublishDate,column_id AS columnId,user_id as userId from tb_article");
		sql.append(" where article_id = ?");
		sql.append(" AND is_publish=0 AND STATUS = 3");
		return (Article) this.queryObject(sql.toString(), new Object[]{article_id}, new BeanPropertyRowMapper(Article.class));
	}

	@Override
	public UserEntity getUser(String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tb_user where user_id=? ");
		return (UserEntity) this.queryObject(sql.toString(), new Object[]{userId}, new BeanPropertyRowMapper(UserEntity.class));
	}

	@Override
	public MemberConEntity getMemberContentById(String projectId1) {
		StringBuffer sql = new StringBuffer();
		sql.append("select tc.content_id as contentId,tc.title ,tc.content,");
		sql.append("IFNULL( IFNULL(tm.member_nickname,CONCAT(SUBSTR(tm.member_mobile, 1, 3),'****',SUBSTR(tm.member_mobile, 8))),tm.member_email) AS  memberNickname ,");
		sql.append("tc.publish_date as publishDate,tc.praise_sum as praiseSum,");
		sql.append("tc.title_image as titleImage, tc.column_id AS columnId,");
		sql.append("tc.goods_class_id AS goodsClassId,tc.goods_class_type AS goodsClassType,");
		sql.append("tc.member_id AS memberId,tc.channel_id as channelId");
		sql.append(" FROM tb_member_content tc,tb_member tm");
		sql.append(" WHERE tc.content_id=? and tc.member_id=tm.member_id");
		
		return (MemberConEntity) this.queryObject(sql.toString(), new Object[]{projectId1}, new BeanPropertyRowMapper(MemberConEntity.class));
	}

	@Override
	public MemEntity getMemberId(String mId) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select member_icon as memberIcon from tb_member where member_id=? ");
		
		return (MemEntity) this.queryObject(sql.toString(), new Object[]{mId}, new BeanPropertyRowMapper(MemEntity.class));
	 
	}

	@Override
	public List<Article> getArticleList(String searchTitle, String column_id, String goods_class_id, String code,
			String brandId, String pageNo, String pageSize, String order,String channel_id) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select article_id as articleId,user_true_name as userTrueName,title,content_intro as contentIntro,title_image as titleImage,publish_date as publishDate,source FROM tb_article where 1=1  and is_publish=0 AND STATUS = 3 AND publish_date <= now()"); 
		if(!StringUtil.isNOrS(channel_id)){
			sql.append(" and channel_id ="+channel_id+"");
		}
		if(!StringUtil.isNOrS(column_id)){
			sql.append(" and column_id ="+column_id+"");
		}
//		if(!StringUtil.isNOrS(goods_class_id)){
//			sql.append(" and goods_class_id like ?");
//			list.add("%"+goods_class_id+"%");
//
//		}
		if(!StringUtil.isNOrS(code)){
			sql.append(" and code like ?");
			list.add("%"+code+"%");
		}
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and title like ?");
			list.add("%"+searchTitle+"%");
		}
//		if(!StringUtil.isNOrS(brandId)){
//			sql.append(" and brand_id like  REGEXP ?");
//			list.add("^"+String.valueOf(brandId)+",|,+"+String.valueOf(brandId)+",");
//		}
		sql.append(" and is_publish = 0");		 
		if(!StringUtil.isNOrS(order)){
			sql.append(" order by "+order+" desc");
		}		 
		if(pageSize!=null){
			sql.append(" limit 0,");
			sql.append(pageSize);
		}
		System.out.println(sql.toString());
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(Article.class));
	}
	@Override
	public List<templetAffirmApp> getArticleLists(String searchTitle, String column_id, String goods_class_id, String code,
			String brandId, String pageNo, String pageSize, String order,String channel_id,String isStick) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select article_id as id,user_true_name as userTrueName,title,content_intro as contentIntro,title_image as image,publish_date as updateTime,source FROM tb_article where 1=1  and is_publish=0 AND STATUS = 3 AND publish_date <= now()"); 
		if(!StringUtil.isNOrS(channel_id)){
			sql.append(" and channel_id =? ");
			list.add(channel_id);
		}
		if(!StringUtil.isNOrS(column_id)){
			sql.append(" and column_id =? ");
			list.add(column_id);
		}
//		if(!StringUtil.isNOrS(goods_class_id)){
//			sql.append(" and goods_class_id like ?");
//			list.add("%"+goods_class_id+"%");
//		}
		if(!StringUtil.isNOrS(code)){
			sql.append(" and code like ?");
			list.add("%"+code+"%");
		}
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and title like ?");
			list.add("%"+searchTitle+"%");
		}
//		if(!StringUtil.isNOrS(brandId)){
//			sql.append(" and brand_id like  REGEXP ?");
//			list.add("^"+String.valueOf(brandId)+",|,+"+String.valueOf(brandId)+",");
//		}
		if(!StringUtil.isNOrS(isStick)){
			sql.append(" and is_stick = ?");
			list.add(isStick);
		}
		sql.append(" and is_publish = 0");		 
		if(!StringUtil.isNOrS(order)){
			sql.append(" order by "+order+" desc");
		}		 
		if(pageSize!=null){
			sql.append(" limit 0,");
			sql.append(pageSize);
		}
		System.out.println(sql.toString());
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(templetAffirmApp.class));
	}

	@Override
	public List<templetAffirmApp> getBrandList(String searchTitle, String brandType, String goodsClassId, String pageNum,
			String pageSize) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select t1.brand_id as Id,t1.brand_image as Image,t1.brand_name_s as title");
		sql.append(" FROM tb_brand t1,tb_brand_ten_map t2");
		sql.append(" where t1.brand_id = t2.brand_id and t1.is_display=0");
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and t1.brand_name_s like ?");
			list.add("%"+searchTitle+"%");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and t2.goods_class_id =?");
			list.add(goodsClassId);
		}
	    if(!StringUtil.isNOrS(brandType)){
	    	sql.append(" and t2.brand_type=?");
	    	list.add(brandType);
	    }
	    sql.append(" GROUP BY t2.brand_sort");
		sql.append(" limit "+pageNum+","+pageSize);
	    return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(templetAffirmApp.class));
	}

	@Override
	public List<templetAffirmApp> getBrandForNew(String searchTitle, String goodsClassId, String pageNum,
			String pageSize) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select t1.brand_id as Id,t1.brand_image as Image,t1.brand_name_s as title");
		sql.append(" from tb_brand t1,tb_brand_gclass_map t2");
		sql.append(" where t1.brand_id = t2.brand_id");
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and t1.brand_name_s like ?");
			list.add("%"+searchTitle+"%");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and t2.goods_class_id=?");
			list.add(goodsClassId);
		}
		sql.append(" and t1.is_display=0");
		sql.append(" GROUP BY Id order by t1.brand_cretime desc");
		sql.append(" limit "+pageNum+","+pageSize);
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(templetAffirmApp.class));
	}

	@Override
	public List<templetAffirmApp> getBrandTenForWorld(String searchTitle, String goodsClassId, String pageNum,
			String pageSize) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select t1.brand_id as Id,t1.brand_image as Image,t1.brand_name_s as title");
		sql.append(" from tb_brand t1,tb_brand_ten_map t2");
		sql.append(" where t1.brand_id = t2.brand_id and t1.is_display=0");
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and t1.brand_name_s like ?");
			list.add("%"+searchTitle+"%");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and t2.goods_class_id=?");
			list.add(goodsClassId);
		}
		sql.append(" and t2.brand_type = 0 ORDER BY t2.brand_sort");
		sql.append(" limit "+pageNum+","+pageSize);
	
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(templetAffirmApp.class));
	}

	@Override
	public List<MemberConEntity> getMemberContent(String searchKey, String columnId, String goodsClassId, String memberId,
			String pageNum, String pageSize, String order) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select tmc.content_id AS contentId,tmc.title AS title,tmc.title_image AS titleImage,");
		sql.append("tmc.content_intro AS contentIntro,tmc.publish_date AS publishDate,");
		sql.append(" IFNULL(IFNULL(tm.member_nickname,CONCAT(SUBSTR(tm.member_mobile, 1, 3),");
		sql.append("'****',SUBSTR(tm.member_mobile, 8))),tm.member_email) AS memberNickname, tmc.browse_sum_show as browseSumShow");
		sql.append(" from tb_member_content tmc,tb_member tm WHERE tmc.member_id = tm.member_id ");
		sql.append(" and tmc.is_publish = 0");
		if(!StringUtil.isNOrS(searchKey)){
			sql.append(" and tmc.title like ?");
			list.add("%"+searchKey+"%");
		}
		if(!StringUtil.isNOrS(memberId)){
			sql.append(" and tmc.member_Id=?");
			list.add(memberId);
		}
		if(!StringUtil.isNOrS(columnId)){
			sql.append(" and tmc.column_id =?");
			list.add(columnId);
		}
		if(StringUtil.isNOrS(columnId) && StringUtil.isNOrS(memberId)){
			sql.append(" and tmc.column_id !=104");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and tmc.goods_class_id like ?");
			list.add("%"+goodsClassId+"%");
		}
		if(!StringUtil.isNOrS(order)){
			sql.append(" order by "+order+" desc");
		}
		sql.append(" limit "+pageNum+","+pageSize);
		
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(MemberConEntity.class));
	}

	@Override
	public List<LogExp> getMemberContent(String pageNum, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select member_id AS memberId, member_nickname as nickName ,member_icon as icon ,contNum as exp from(");
		sql.append("select t2.member_id,ifnull(ifnull(t2.member_nickname,"
				+ "concat(substring(t2.member_mobile, 1, 3),'****',substring(t2.member_mobile, 8))),");
		sql.append("t2.member_email) member_nickname,t2.member_icon,count(*) contNum");
		sql.append(" from tb_member_content t1,tb_member t2");
		sql.append(" where t1.member_id = t2.member_id AND t1.channel_id =102"
				+ " AND t1. STATUS = 3 GROUP BY t1.member_id "
				+ " ORDER BY contNum DESC LIMIT 0,5) t");
		
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(LogExp.class));
	}

	@Override
	public List<MemberConEntity> getMemberImageAndContent(String columnId, String goodsClassId, String pageNum,
			String pageSize, String order) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select tmc.content_id AS contentId,tmc.title AS title,tm.member_icon AS titleImage,");
		sql.append("tmc.content_intro AS contentIntro,tmc.publish_date AS publishDate,");
		sql.append("IFNULL(IFNULL(tm.member_nickname,CONCAT(SUBSTR(tm.member_mobile, 1, 3),'****',SUBSTR(tm.member_mobile, 8))),");
		sql.append("tm.member_email) AS memberNickname from tb_member_content tmc,tb_member tm");
		sql.append(" where tmc.member_id = tm.member_id and tmc.is_publish = 0");
		if(!StringUtil.isNOrS(columnId)){
			sql.append(" AND tmc.column_id = ?");
			list.add(columnId);
		}
		
		if(columnId == null){
			sql.append(" and tmc.column_id !=104");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and tmc.goods_class_id like?");
			list.add("%"+goodsClassId+"%");
		}
		if(!StringUtil.isNOrS(order)){
			sql.append(" order by "+order+" desc");
		}
		sql.append(" limit "+pageNum+","+pageSize);
		
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(MemberConEntity.class));
	

	}

	@Override
	public List<AfterSale> getAfterSaleLists(String address, String pAddress, String brand_name_c, String brand_name_e,
			String goodsClassId, String pageNum, Integer pageSize, String typeVal) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("select ta.brand_id AS brandId,ta.aftersale_id AS aftersaleId,ta.aftersale_name AS aftersaleName,"
				+ "ta.aftersale_imgurl AS aftersaleImgUrl,ta.member_detail_addr AS memberDetailAddr,"
				+ "ta.telephone_1 AS telephone1,ta.type_val AS typeVal");
		sql.append(" FROM tb_after_sale ta,tb_brand tb");
		sql.append(" WHERE ta.brand_id=tb.brand_id and ta.status=3");
		sql.append(" AND tb.is_display=0");
		if(!StringUtil.isNOrS(brand_name_c)){
			sql.append(" and tb.brand_name_s like?");
			list.add("%"+brand_name_c+"%");
		}
		if(!StringUtil.isNOrS(brand_name_e)){
			sql.append(" and tb.brand_name like?");
			list.add("%"+brand_name_e+"%");
		}
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" and ta.goods_class_id like?");
			list.add("%"+goodsClassId+"%");
		}
		if(!StringUtil.isNOrS(address)){
			sql.append(" and ta.member_province like?");
			list.add("%"+address+"%");
		}
		if(!StringUtil.isNOrS(pAddress)){
			sql.append(" and ta.member_city like?");
			list.add("%"+pAddress+"%");
		}
		if(!StringUtil.isNOrS(typeVal)){
			sql.append(" and ta.type_val like?");
			list.add("%"+typeVal+"%");
		}

		if(!StringUtil.isNOrS(pageNum)){
			sql.append(" limit "+pageNum+","+pageSize);
		}
		
		
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(AfterSale.class));
	}

	@Override
	public List<ColumnAppEntity> getColumnlByGoodsId(String goodsClassId) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT tc.column_id as columnId,tc.column_name AS columnName");
		sql.append(" FROM tb_column_goodsClass tcg,tb_column tc");
		sql.append(" WHERE tcg.column_id = tc.column_id AND tc.is_enabled=0");
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append(" AND tcg.goods_class_id=?");
			list.add(goodsClassId);
		}
		  sql.append(" order by sort asc");
	 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(ColumnAppEntity.class));	
	}

	@Override
	public List<templetAffirmApp> getArticles(String searchTitle, String column_id, String goods_class_id, String brandId,
			String pageNo, String pageSize, String order) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT article_id as id,user_true_name as userTrueName,title,content_intro as contentIntro,title_image as image,publish_date as updateTime,source ");
		sql.append(" FROM tb_article where 1=1 and is_publish=0 AND STATUS = 3");
		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and channel_id ="+112+"");
		}
		if(!StringUtil.isNOrS(column_id)){
			sql.append(" and column_id =?");
			list.add(column_id);
		}
				 	
		if(!StringUtil.isNOrS(column_id)){
			sql.append(" and column_id =?");
			list.add(column_id);
		}
//		if(!StringUtil.isNOrS(goods_class_id)){
//			sql.append(" and goods_class_id like?");
//			list.add("%"+goods_class_id+"%");
//		}

		if(!StringUtil.isNOrS(searchTitle)){
			sql.append(" and title like?");
			list.add("%"+searchTitle+"%");
		}
		
//		if(!StringUtil.isNOrS(brandId)){
//			sql.append(" and brand_id  REGEXP CONCAT('^',"+brandId+",',|,+',"+brandId+",',')");
//		}

		if(!StringUtil.isNOrS(order)){
			sql.append(" order by "+order+" desc");
		}
		if(!StringUtil.isNOrS(pageNo)){
			sql.append(" limit "+pageNo+","+pageSize);
		}
		
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(templetAffirmApp.class));	
	}

	@Override
	public List<Exhibition> getBrandByType(String goodsClassId, String type) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT tae.image as image,tae.map_id as id,tbb.brand_name_s as title");
		sql.append(" FROM tb_after_exhibition tae,tb_brand tbb");
		sql.append(" WHERE tae.map_id=tbb.brand_id");
		sql.append(" AND tbb.is_display=0");
		sql.append(" AND exhibition_type="+type+"");
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append("  AND tae.goods_class_id =?");
			list.add(goodsClassId);
		}
		sql.append(" ORDER BY tae.seq_num asc");
		 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(Exhibition.class));	
	}

	@Override
	public List<Exhibition> getBrandByGoodsId(String goodsClassId, String type) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT word_name_show as title,image as image,map_id as id");
		sql.append(" FROM tb_after_exhibition WHERE 1=1");
		if(!StringUtil.isNOrS(goodsClassId)){
			sql.append("  AND goods_class_id =?");
			list.add(goodsClassId);
		}
		sql.append(" AND exhibition_type="+type+"");
		sql.append(" ORDER BY block_num asc ,seq_num asc");
	 return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(Exhibition.class));	
	}

	@Override
	public Word getWordById(String wordId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t2.word_code as wordCode,t2.word_id AS wordId,t2.user_true_name AS userTrueName,");
		sql.append("t2.publish_date AS publishDate,t2.tkd_title AS pTitle,");
		sql.append("t2.p_title AS title,t2.p_title_image AS titleImg,t2.p_praise_sum AS praiseSum,");
		sql.append("t2.goods_class_id AS goodsClassId");
		sql.append(" FROM tb_word t2");
		sql.append(" WHERE t2.is_publish='0'");
		sql.append(" AND t2.word_id = ?");
		
		return (Word) this.queryObject(sql.toString(), new Object[]{wordId}, new BeanPropertyRowMapper(Word.class));
	}

	@Override
	public List<ColumnAppEntity> queryByColumnId(String channelId, String columnId) {
		StringBuffer sql = new StringBuffer();
		List<String> list = new ArrayList<>();
		sql.append("SELECT id,channelId,columnName,columnType,columnId,size,isAuto,short as isshort,pageModel,rowSize,conditions");
		sql.append(" FROM tb_column_app WHERE");
		sql.append("  columnId="+columnId+"");
		sql.append(" and channelId="+channelId+"  ORDER BY short");
		
		return this.queryList(sql.toString(),list.toArray(), new BeanPropertyRowMapper(ColumnAppEntity.class));
	}

	@Override
	public List<templetAffirmApp> getMemberHotContent(String columnId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.projectId as id,t.columnInfoType,t.title,t.contentIntro,t.image,t.updateTime,tmc.browse_sum_show as browseSumShow, CASE ");
		sql.append(" WHEN columnInfoType=3 THEN");
		sql.append(" ( SELECT IFNULL(m.member_nickname,IFNULL(CONCAT(SUBSTR(m.member_mobile, 1, 3),'****',SUBSTR(m.member_mobile, 8)),");
		sql.append(" m.member_email)) AS nickName FROM tb_member m");
		sql.append(" WHERE m.member_id =(SELECT mc.member_id FROM tb_member_content mc WHERE mc.content_id=t.projectId))");
		sql.append(" END memberNickname FROM tb_templet_affirm_app t LEFT JOIN tb_member_content tmc ON t.projectId = tmc.content_id WHERE columnId=?");
		return this.queryList(sql.toString(), new Object[]{columnId}, new BeanPropertyRowMapper(templetAffirmApp.class));
	}



	
}
