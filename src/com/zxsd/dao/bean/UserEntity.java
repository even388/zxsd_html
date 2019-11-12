package com.zxsd.dao.bean;

import java.sql.Date;

public class UserEntity{
	   /**
	    * 用户信息
	    */
		private Integer user_id;
		private String user_name;
		private Integer role_id;
		private String password;
		private String user_icon;
		private String user_email;
		private String user_mobile;
		private String user_telephone;
		private String user_true_name;
		private Integer is_enabled;
		private Date register_time;
		private Date last_login_time;
		private String last_login_ip;
		private Integer login_count;
		private String remarks;
		private Integer user_sex;
		public Integer getUser_id() {
			return user_id;
		}
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public Integer getRole_id() {
			return role_id;
		}
		public void setRole_id(Integer role_id) {
			this.role_id = role_id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUser_icon() {
			return user_icon;
		}
		public void setUser_icon(String user_icon) {
			this.user_icon = user_icon;
		}
		public String getUser_email() {
			return user_email;
		}
		public void setUser_email(String user_email) {
			this.user_email = user_email;
		}
		public String getUser_mobile() {
			return user_mobile;
		}
		public void setUser_mobile(String user_mobile) {
			this.user_mobile = user_mobile;
		}
		public String getUser_telephone() {
			return user_telephone;
		}
		public void setUser_telephone(String user_telephone) {
			this.user_telephone = user_telephone;
		}
		public String getUser_true_name() {
			return user_true_name;
		}
		public void setUser_true_name(String user_true_name) {
			this.user_true_name = user_true_name;
		}
		public Integer getIs_enabled() {
			return is_enabled;
		}
		public void setIs_enabled(Integer is_enabled) {
			this.is_enabled = is_enabled;
		}
		public Date getRegister_time() {
			return register_time;
		}
		public void setRegister_time(Date register_time) {
			this.register_time = register_time;
		}
		public Date getLast_login_time() {
			return last_login_time;
		}
		public void setLast_login_time(Date last_login_time) {
			this.last_login_time = last_login_time;
		}
		public String getLast_login_ip() {
			return last_login_ip;
		}
		public void setLast_login_ip(String last_login_ip) {
			this.last_login_ip = last_login_ip;
		}
		public Integer getLogin_count() {
			return login_count;
		}
		public void setLogin_count(Integer login_count) {
			this.login_count = login_count;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public Integer getUser_sex() {
			return user_sex;
		}
		public void setUser_sex(Integer user_sex) {
			this.user_sex = user_sex;
		}
		
		
	   
}
