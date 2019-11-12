package com.zxsd.dao.bean;

import java.util.List;

/**
 * 文章表(tb_article)
 * 
 * @author 
 * @version 1.0.0 2016-06-16 15:57:02
 */
public class WordEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1304410354688930221L;
    private String word_id;
    private String word_code;
    private String channel_id;
    private String channel_name;
    private String word_attr_id;
    private String goods_class_type;
    private String goods_class_id;
    private String goods_class_name;
    private String title;
    private String deputy_title;
    private String title_image;
    private String key_words;
    private String content;
    private String content_intro;
    private String comment_sum;
    private String praise_sum;
    private String browse_sum;
    private String collection_sum;
    private String remarks;
    private String page_url;
    private String user_id;
    private String user_true_name;
    private String publish_date;
    private String p_title;
    private String p_title_image;
    private String p_praise_sum;
    private String p_comment_sum;
    private String p_browse_sum;
    private String p_collection_sum;
    private String p_page_url;
    private String word_attr_code;
    private String is_publish;
    private String tkd_title;
    private String tkd_key_words;
    private String tkd_description;
    private String goodsClassId;
    private String channelId;
    private String show_name;
    private String type_id;
    
    public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
    public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getGoods_class_name() {
		return goods_class_name;
	}
	public void setGoods_class_name(String goods_class_name) {
		this.goods_class_name = goods_class_name;
	}
	public String getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(String goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getTkd_title() {
		return tkd_title;
	}
	public void setTkd_title(String tkd_title) {
		this.tkd_title = tkd_title;
	}
	public String getTkd_key_words() {
		return tkd_key_words;
	}
	public void setTkd_key_words(String tkd_key_words) {
		this.tkd_key_words = tkd_key_words;
	}
	public String getTkd_description() {
		return tkd_description;
	}
	public void setTkd_description(String tkd_description) {
		this.tkd_description = tkd_description;
	}
	public String getP_page_url() {
		return p_page_url;
	}
	public void setP_page_url(String p_page_url) {
		this.p_page_url = p_page_url;
	}
	public String getIs_publish() {
		return is_publish;
	}
	public void setIs_publish(String is_publish) {
		this.is_publish = is_publish;
	}
	public String getWord_code() {
		return word_code;
	}
	public void setWord_code(String word_code) {
		this.word_code = word_code;
	}
	public String getWord_attr_code() {
		return word_attr_code;
	}
	public void setWord_attr_code(String word_attr_code) {
		this.word_attr_code = word_attr_code;
	}
	public String getP_praise_sum() {
		return p_praise_sum;
	}
	public void setP_praise_sum(String p_praise_sum) {
		this.p_praise_sum = p_praise_sum;
	}
	public String getP_comment_sum() {
		return p_comment_sum;
	}
	public void setP_comment_sum(String p_comment_sum) {
		this.p_comment_sum = p_comment_sum;
	}
	public String getP_collection_sum() {
		return p_collection_sum;
	}
	public void setP_collection_sum(String p_collection_sum) {
		this.p_collection_sum = p_collection_sum;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_title_image() {
		return p_title_image;
	}
	public void setP_title_image(String p_title_image) {
		this.p_title_image = p_title_image;
	}
	public String getP_browse_sum() {
		return p_browse_sum;
	}
	public void setP_browse_sum(String p_browse_sum) {
		this.p_browse_sum = p_browse_sum;
	}
	public String getGoods_class_type() {
		return goods_class_type;
	}
	public void setGoods_class_type(String goods_class_type) {
		this.goods_class_type = goods_class_type;
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
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getWord_id() {
		return word_id;
	}
	public void setWord_id(String word_id) {
		this.word_id = word_id;
	}
	public String getWord_attr_id() {
		return word_attr_id;
	}
	public void setWord_attr_id(String word_attr_id) {
		this.word_attr_id = word_attr_id;
	}
	public String getGoods_class_id() {
		return goods_class_id;
	}
	public void setGoods_class_id(String goods_class_id) {
		this.goods_class_id = goods_class_id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}