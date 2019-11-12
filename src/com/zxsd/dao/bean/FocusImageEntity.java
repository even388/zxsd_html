package com.zxsd.dao.bean;

/**
 * 
 * @ClassName FocusImageEntity   
 * @Description 
 * @author  zhy
 * @date 2016-8-30 上午9:28:16  
 * @version v1.0 2016-8-30 上午9:28:16
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2016-8-30 上午9:28:16　　V1.0　　 build此模块
 */
public class FocusImageEntity {
	private String focus_id;
	private String channel_id;
	private String image_name;
	private String image_sort;
	private String image_path;
	private String link_url;
	private String put_time;
	private String put_user_id;
	private String is_show;
	private String remarks;
	public String getFocus_id() {
		return focus_id;
	}
	public void setFocus_id(String focus_id) {
		this.focus_id = focus_id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_sort() {
		return image_sort;
	}
	public void setImage_sort(String image_sort) {
		this.image_sort = image_sort;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public String getPut_time() {
		return put_time;
	}
	public void setPut_time(String put_time) {
		this.put_time = put_time;
	}
	public String getPut_user_id() {
		return put_user_id;
	}
	public void setPut_user_id(String put_user_id) {
		this.put_user_id = put_user_id;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
