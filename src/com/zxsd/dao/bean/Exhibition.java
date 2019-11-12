package com.zxsd.dao.bean;

import java.util.List;

public class Exhibition {
	private String image;
	private Integer id;
	private String title;
	String goodsClassId;
	public String getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(String goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}
