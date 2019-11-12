package com.zxsd.dao.bean;

import java.util.List;

public class SurveyQuestEntity {
	private String quest_id;
	private String survey_id;
	private String survey_name;
	private String survey_intro;
	private String title;
	private String is_single;
	private String is_pre;
	private String type;
	private String quest_num;
	private String status;
	private String remarks;
	private String answs;
	private String addAnsws;
	private String isVals;
	private List<SurveyAnswEntity> answlist;
	private List<SurveyAnswEntity> addanswlist;
	private String quest_count;
	
	public String getQuest_count() {
		return quest_count;
	}
	public void setQuest_count(String quest_count) {
		this.quest_count = quest_count;
	}
	public String getSurvey_name() {
		return survey_name;
	}
	public void setSurvey_name(String survey_name) {
		this.survey_name = survey_name;
	}
	public String getSurvey_intro() {
		return survey_intro;
	}
	public void setSurvey_intro(String survey_intro) {
		this.survey_intro = survey_intro;
	}
	public List<SurveyAnswEntity> getAddanswlist() {
		return addanswlist;
	}
	public void setAddanswlist(List<SurveyAnswEntity> addanswlist) {
		this.addanswlist = addanswlist;
	}
	public String getAddAnsws() {
		return addAnsws;
	}
	public void setAddAnsws(String addAnsws) {
		this.addAnsws = addAnsws;
	}
	public String getIsVals() {
		return isVals;
	}
	public void setIsVals(String isVals) {
		this.isVals = isVals;
	}
	public String getQuest_id() {
		return quest_id;
	}
	public void setQuest_id(String quest_id) {
		this.quest_id = quest_id;
	}
	public String getSurvey_id() {
		return survey_id;
	}
	public void setSurvey_id(String survey_id) {
		this.survey_id = survey_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIs_single() {
		return is_single;
	}
	public void setIs_single(String is_single) {
		this.is_single = is_single;
	}
	public String getIs_pre() {
		return is_pre;
	}
	public void setIs_pre(String is_pre) {
		this.is_pre = is_pre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuest_num() {
		return quest_num;
	}
	public void setQuest_num(String quest_num) {
		this.quest_num = quest_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAnsws() {
		return answs;
	}
	public void setAnsws(String answs) {
		this.answs = answs;
	}
	public List<SurveyAnswEntity> getAnswlist() {
		return answlist;
	}
	public void setAnswlist(List<SurveyAnswEntity> answlist) {
		this.answlist = answlist;
	}
	
}
