package com.zxsd.dao.bean;


import java.io.Serializable;

public class LogFormatHead implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = 1L;
	private String servName;//项目名称 MANAGE
	private String logLevel;//日志级别 INFO/WARN/ERROR
	private String servUrl; //访问URL
	private String logOpera; //日志操作类型 系统 SYS/用户 UYS
	private String userId;   //用户ID
	private String userTrueName;   //用户真实姓名
	private String userBehavior; //用户行为类型   查询 QUERY/更新UPDATE/删除DELETE
	private String ip; //IP地址
	private String parames; //日志详情
	private String logTime; //时间
	private String results; //结果详情
	public String getServName() {
		return servName;
	}
	public void setServName(String servName) {
		this.servName = servName;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getServUrl() {
		return servUrl;
	}
	public void setServUrl(String servUrl) {
		this.servUrl = servUrl;
	}
	public String getLogOpera() {
		return logOpera;
	}
	public void setLogOpera(String logOpera) {
		this.logOpera = logOpera;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserBehavior() {
		return userBehavior;
	}
	public void setUserBehavior(String userBehavior) {
		this.userBehavior = userBehavior;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getParames() {
		return parames;
	}
	public void setParames(String parames) {
		this.parames = parames;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getUserTrueName() {
		return userTrueName;
	}
	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public LogFormatHead() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LogFormatHead(String servName, String logLevel, 
			String logOpera,String userId,String userTrueName,String servUrl, String ip) {
		super();
		this.servName = servName;
		this.logLevel = logLevel;
		this.servUrl = servUrl;
		this.logOpera = logOpera;
		this.userId = userId;
		this.userTrueName = userTrueName;
		this.ip = ip;
	}
	
	public LogFormatHead(String servName, String logLevel, String logOpera) {
		super();
		this.servName = servName;
		this.logLevel = logLevel;
		this.logOpera = logOpera;
	}
	
	
}
