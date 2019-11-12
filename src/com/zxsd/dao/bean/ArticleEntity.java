package com.zxsd.dao.bean;

/**
 * 
 * @ClassName ArticleEntity   
 * @Description 
 * @author  zhy
 * @date 2016-9-1 下午4:25:16  
 * @version v1.0 2016-9-1 下午4:25:16
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2016-9-1 下午4:25:16　　V1.0　　 build此模块
 */
public class ArticleEntity {
	private String article_id;
	private String channel_id;
	private String column_id;
	private String brand_id;
	private String brand_name_c;
	private String goods_class_type;
	private String goods_class_id;
	private String link_type;

	public String getLink_type() {
		return link_type;
	}

	public void setLink_type(String link_type) {
		this.link_type = link_type;
	}

	/**
	 * @return the goods_class_Name
	 */
	public String getGoods_class_Name() {
		return goods_class_Name;
	}
	/**
	 * @param goods_class_Name the goods_class_Name to set
	 */
	public void setGoods_class_Name(String goods_class_Name) {
		this.goods_class_Name = goods_class_Name;
	}
	private String goods_class_Name;
	private String user_id;
	private String user_true_name;
	private String title;
	private String deputy_title;
	private String title_image;
	private String status;
	private String examine_fail;
	private String is_final;
	private String is_publish;
	private String is_comment;
	private String key_words;
	private String content;
	private String content_intro;
	private String commit_date;
	private String publish_date;
	private String comment_sum;
    private String praise_sum;
    private String browse_sum;
    private String collection_sum;
    private String remarks;
    private String page_url;
    private String last_update_date;
    private String source;
    private String attr;
    private String province;
    private String column_name;
    private String is_stick;
    private String stick_date;

	public String getFirst_publish_date() {
		return first_publish_date;
	}

	public void setFirst_publish_date(String first_publish_date) {
		this.first_publish_date = first_publish_date;
	}

	private String first_publish_date;

	public String getIs_stick() {
		return is_stick;
	}
	public void setIs_stick(String is_stick) {
		this.is_stick = is_stick;
	}
	public String getStick_date() {
		return stick_date;
	}
	public void setStick_date(String stick_date) {
		this.stick_date = stick_date;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBrand_name_c() {
		return brand_name_c;
	}
	public void setBrand_name_c(String brand_name_c) {
		this.brand_name_c = brand_name_c;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getColumn_id() {
		return column_id;
	}
	public void setColumn_id(String column_id) {
		this.column_id = column_id;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getGoods_class_type() {
		return goods_class_type;
	}
	public void setGoods_class_type(String goods_class_type) {
		this.goods_class_type = goods_class_type;
	}
	public String getGoods_class_id() {
		return goods_class_id;
	}
	public void setGoods_class_id(String goods_class_id) {
		this.goods_class_id = goods_class_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_true_name() {
		return user_true_name;
	}
	public void setUser_true_name(String user_true_name) {
		this.user_true_name = user_true_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeputy_title() {
		return deputy_title;
	}
	public void setDeputy_title(String deputy_title) {
		this.deputy_title = deputy_title;
	}
	public String getTitle_image() {
		return title_image;
	}
	public void setTitle_image(String title_image) {
		this.title_image = title_image;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExamine_fail() {
		return examine_fail;
	}
	public void setExamine_fail(String examine_fail) {
		this.examine_fail = examine_fail;
	}
	public String getIs_final() {
		return is_final;
	}
	public void setIs_final(String is_final) {
		this.is_final = is_final;
	}
	public String getIs_publish() {
		return is_publish;
	}
	public void setIs_publish(String is_publish) {
		this.is_publish = is_publish;
	}
	public String getIs_comment() {
		return is_comment;
	}
	public void setIs_comment(String is_comment) {
		this.is_comment = is_comment;
	}
	public String getKey_words() {
		return key_words;
	}
	public void setKey_words(String key_words) {
		this.key_words = key_words;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_intro() {
		return content_intro;
	}
	public void setContent_intro(String content_intro) {
		this.content_intro = content_intro;
	}
	public String getCommit_date() {
		return commit_date;
	}
	public void setCommit_date(String commit_date) {
		this.commit_date = commit_date;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getComment_sum() {
		return comment_sum;
	}
	public void setComment_sum(String comment_sum) {
		this.comment_sum = comment_sum;
	}
	public String getPraise_sum() {
		return praise_sum;
	}
	public void setPraise_sum(String praise_sum) {
		this.praise_sum = praise_sum;
	}
	public String getBrowse_sum() {
		return browse_sum;
	}
	public void setBrowse_sum(String browse_sum) {
		this.browse_sum = browse_sum;
	}
	public String getCollection_sum() {
		return collection_sum;
	}
	public void setCollection_sum(String collection_sum) {
		this.collection_sum = collection_sum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
    
}
