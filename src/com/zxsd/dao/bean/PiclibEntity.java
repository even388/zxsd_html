package com.zxsd.dao.bean;

import java.util.List;

public class PiclibEntity {
	private String piclib_id;
	private String status;
	private String create_time;
	private String title;
	private String create_user_id;
	private String user_true_name;
	private String publish_user_id;
	private String publish_time;
	private String goodclass_id;
	private String link_goods_class_id;
	private String takeaway_pic_url;
	private String sign;
	private String audit_user_id;
	private String audit_time;
	private List<PicEntity> piclist;
	private String attribute;
	private String author;
	private String source;
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<PicEntity> getPiclist() {
		return piclist;
	}
	public void setPiclist(List<PicEntity> piclist) {
		this.piclist = piclist;
	}
	public String getPiclib_id() {
		return piclib_id;
	}
	public void setPiclib_id(String piclib_id) {
		this.piclib_id = piclib_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(String create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getUser_true_name() {
		return user_true_name;
	}
	public void setUser_true_name(String user_true_name) {
		this.user_true_name = user_true_name;
	}
	public String getPublish_user_id() {
		return publish_user_id;
	}
	public void setPublish_user_id(String publish_user_id) {
		this.publish_user_id = publish_user_id;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getGoodclass_id() {
		return goodclass_id;
	}
	public void setGoodclass_id(String goodclass_id) {
		this.goodclass_id = goodclass_id;
	}
	public String getLink_goods_class_id() {
		return link_goods_class_id;
	}
	public void setLink_goods_class_id(String link_goods_class_id) {
		this.link_goods_class_id = link_goods_class_id;
	}
	public String getTakeaway_pic_url() {
		return takeaway_pic_url;
	}
	public void setTakeaway_pic_url(String takeaway_pic_url) {
		this.takeaway_pic_url = takeaway_pic_url;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(String audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public String getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}
	
}
