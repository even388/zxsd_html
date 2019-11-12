package com.zxsd.dao.bean;

import java.util.List;

public class GoodsClassEntity {
	private String goods_class_id;//权限ID
	private String goods_class_name;//权限父ID
	private String goods_class_type;//中文权限名称
	private String p_goods_class_id;//英文权限名称
	private String goods_class_level;//权限类型
	private String priority;//权限等级
	private String remarks;//备注
	private List<GoodsClassEntity> children;
	public String getGoods_class_id() {
		return goods_class_id;
	}
	public void setGoods_class_id(String goods_class_id) {
		this.goods_class_id = goods_class_id;
	}
	public String getGoods_class_name() {
		return goods_class_name;
	}
	public void setGoods_class_name(String goods_class_name) {
		this.goods_class_name = goods_class_name;
	}
	public String getGoods_class_type() {
		return goods_class_type;
	}
	public void setGoods_class_type(String goods_class_type) {
		this.goods_class_type = goods_class_type;
	}
	public String getP_goods_class_id() {
		return p_goods_class_id;
	}
	public void setP_goods_class_id(String p_goods_class_id) {
		this.p_goods_class_id = p_goods_class_id;
	}
	public String getGoods_class_level() {
		return goods_class_level;
	}
	public void setGoods_class_level(String goods_class_level) {
		this.goods_class_level = goods_class_level;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<GoodsClassEntity> getChildren() {
		return children;
	}
	public void setChildren(List<GoodsClassEntity> children) {
		this.children = children;
	}
	
}
