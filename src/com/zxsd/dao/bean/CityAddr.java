/**
 * 
 */
package com.zxsd.dao.bean;

/**
 * @author Administrator
 *
 */
public class CityAddr {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getP_addr_code() {
		return p_addr_code;
	}
	public void setP_addr_code(String p_addr_code) {
		this.p_addr_code = p_addr_code;
	}
	public Integer getAddr_level() {
		return addr_level;
	}
	public void setAddr_level(Integer addr_level) {
		this.addr_level = addr_level;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private Integer id;
	private String code;
	private String address;
	private String p_addr_code;
	private Integer addr_level;
	private String remarks;
}
