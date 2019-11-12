package com.zxsd.dao.bean;

import java.io.Serializable;
/**
 * 短信黑名单
 *  tb_member_short_back
 */
public class BlackSMS implements Serializable {

	private static final long serialVersionUID = 8338657451211101400L;

	private Integer id;
	private String mobile_no;
	private	String create_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	@Override
	public String toString() {
		return "BlackSMS [id=" + id + ", mobile_no=" + mobile_no + ", create_date=" + create_date + "]";
	}
}
