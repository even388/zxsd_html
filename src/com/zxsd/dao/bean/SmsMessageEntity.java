package com.zxsd.dao.bean;

/**
 * 短信
 * @ClassName SmsMessageEntity   
 * @Description 
 * @author  zhy
 * @date 2018-5-4 上午9:57:30  
 * @version v1.0 2018-5-4 上午9:57:30
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2018-5-4 上午9:57:30　　V1.0　　 build此模块
 */
public class SmsMessageEntity {
	private String message_id;
	private String message;
	private String mobile_no;
	private String state;
	private String sender_uuid;
	private String sender_time;
	private String create_time;
	private String type;
	private String terminal;
	
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSender_uuid() {
		return sender_uuid;
	}
	public void setSender_uuid(String sender_uuid) {
		this.sender_uuid = sender_uuid;
	}
	public String getSender_time() {
		return sender_time;
	}
	public void setSender_time(String sender_time) {
		this.sender_time = sender_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public SmsMessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SmsMessageEntity [message_id=" + message_id + ", mobile_no=" + mobile_no + "]";
	}
}
