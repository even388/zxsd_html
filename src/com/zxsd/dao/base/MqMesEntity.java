package com.zxsd.dao.base;

public class MqMesEntity {
	private String projectId;//模板ID
	private String contId;//数据源ID
	private String projectType;//频道页1；顶部2；底部3；词条4；词条维度5；文章6；专题9；问卷8 ；地图7
	
	public String getContId() {
		return contId;
	}
	public void setContId(String contId) {
		this.contId = contId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public MqMesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
