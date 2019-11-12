package com.zxsd.dao.bean;

/**
 * 
 * @ClassName WebsiteLink   
 * @Description 
 * @author  zhy
 * @date 2016-9-12 上午11:36:00  
 * @version v1.0 2016-9-12 上午11:36:00
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2016-9-12 上午11:36:00　　V1.0　　 build此模块
 */
public class WebsiteLink {
	private String link_id;
	private String web_name;
	private String web_url;
	private String link_type;
	private String web_mail;
	private String web_logo;
	private String web_intro;
	private String is_show;
	private String web_sort;
	private String click_sum;
	private String remarks;
	private String web_type;
	private String channel_id;
	
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getWeb_type() {
		return web_type;
	}
	public void setWeb_type(String web_type) {
		this.web_type = web_type;
	}
	public String getLink_id() {
		return link_id;
	}
	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}
	public String getWeb_name() {
		return web_name;
	}
	public void setWeb_name(String web_name) {
		this.web_name = web_name;
	}
	public String getWeb_url() {
		return web_url;
	}
	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}
	public String getLink_type() {
		return link_type;
	}
	public void setLink_type(String link_type) {
		this.link_type = link_type;
	}
	public String getWeb_mail() {
		return web_mail;
	}
	public void setWeb_mail(String web_mail) {
		this.web_mail = web_mail;
	}
	public String getWeb_logo() {
		return web_logo;
	}
	public void setWeb_logo(String web_logo) {
		this.web_logo = web_logo;
	}
	public String getWeb_intro() {
		return web_intro;
	}
	public void setWeb_intro(String web_intro) {
		this.web_intro = web_intro;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public String getWeb_sort() {
		return web_sort;
	}
	public void setWeb_sort(String web_sort) {
		this.web_sort = web_sort;
	}
	public String getClick_sum() {
		return click_sum;
	}
	public void setClick_sum(String click_sum) {
		this.click_sum = click_sum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
