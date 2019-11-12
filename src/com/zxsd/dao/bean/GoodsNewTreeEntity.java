package com.zxsd.dao.bean;

import java.util.List;

public class GoodsNewTreeEntity {
	private String id;
	private String good_class_id;
	private String parent_good_class_id;
	private String label_id;
	private String sort;
	private String label_name;
	private String label_status;
	private String hot_spot;
	
	public String getHot_spot() {
		return hot_spot;
	}
	public void setHot_spot(String hot_spot) {
		this.hot_spot = hot_spot;
	}
	private List<GoodsNewTreeEntity> childlist;
	
	public List<GoodsNewTreeEntity> getChildlist() {
		return childlist;
	}
	public void setChildlist(List<GoodsNewTreeEntity> childlist) {
		this.childlist = childlist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGood_class_id() {
		return good_class_id;
	}
	public void setGood_class_id(String good_class_id) {
		this.good_class_id = good_class_id;
	}
	public String getParent_good_class_id() {
		return parent_good_class_id;
	}
	public void setParent_good_class_id(String parent_good_class_id) {
		this.parent_good_class_id = parent_good_class_id;
	}
	public String getLabel_id() {
		return label_id;
	}
	public void setLabel_id(String label_id) {
		this.label_id = label_id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getLabel_name() {
		return label_name;
	}
	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
	public String getLabel_status() {
		return label_status;
	}
	public void setLabel_status(String label_status) {
		this.label_status = label_status;
	}
	public GoodsNewTreeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
