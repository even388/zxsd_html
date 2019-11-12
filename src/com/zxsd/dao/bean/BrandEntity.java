package com.zxsd.dao.bean;

public class BrandEntity {
	private String brand_id;
	private String brand_type;
	private String is_ten;
	private String brand_name_s;
	private String brand_name_e;
	private String brand_image;
	private String brand_intro;
	private String brand_sort;
	private String brand_cretime;
	private String remarks;
	private String goods_class_id;
	private String brandName;//txw 添加 20170914
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_type() {
		return brand_type;
	}
	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}
	public String getIs_ten() {
		return is_ten;
	}
	public void setIs_ten(String is_ten) {
		this.is_ten = is_ten;
	}
	public String getBrand_name_s() {
		return brand_name_s;
	}
	public void setBrand_name_s(String brand_name_s) {
		this.brand_name_s = brand_name_s;
	}
	public String getBrand_name_e() {
		return brand_name_e;
	}
	public void setBrand_name_e(String brand_name_e) {
		this.brand_name_e = brand_name_e;
	}
	public String getBrand_image() {
		return brand_image;
	}
	public void setBrand_image(String brand_image) {
		this.brand_image = brand_image;
	}
	public String getBrand_intro() {
		return brand_intro;
	}
	public void setBrand_intro(String brand_intro) {
		this.brand_intro = brand_intro;
	}
	public String getBrand_sort() {
		return brand_sort;
	}
	public void setBrand_sort(String brand_sort) {
		this.brand_sort = brand_sort;
	}
	public String getBrand_cretime() {
		return brand_cretime;
	}
	public void setBrand_cretime(String brand_cretime) {
		this.brand_cretime = brand_cretime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getGoods_class_id() {
		return goods_class_id;
	}
	public void setGoods_class_id(String goods_class_id) {
		this.goods_class_id = goods_class_id;
	}
}
