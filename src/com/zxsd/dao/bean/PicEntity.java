package com.zxsd.dao.bean;

public class PicEntity {
	private String pic_id;
	private String piclib_id;
	private String instructions;
	private String img_url;
	private String create_time;
	private String sort_id;
	public String getPic_id() {
		return pic_id;
	}
	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}
	public String getPiclib_id() {
		return piclib_id;
	}
	public void setPiclib_id(String piclib_id) {
		this.piclib_id = piclib_id;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getSort_id() {
		return sort_id;
	}
	public void setSort_id(String sort_id) {
		this.sort_id = sort_id;
	}
	
}
