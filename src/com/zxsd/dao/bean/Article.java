package com.zxsd.dao.bean;

import java.util.ArrayList;
import java.util.List;

public class Article implements java.io.Serializable {
	   
		private Integer articleId;
	    private Integer channelId;
		private String channelName;
	    private Integer columnId;
		private String columnName;
	    private Integer brandId;
	    private Integer goodsClassType;
	    private String goodsClassId;
	    private Integer userId;
	    private String userTrueName;
	    private String title;
	    private String deputyTitle;
	    private String titleImage;
	    private Integer status;
	    private Integer is_final;
	    private Integer is_publish;
	    private Integer is_comment;
	    private String key_words;
	    private String content ;
	    private Integer contentId ;
	    private String contentIntro;
	    private String commitDate;
	    private String publishDate;
		private Integer typeDate;//1为commit_date提交时间2为publish_date发布时间
		private String dateStar;//开始时间
		private String dateEnd;//结束时间
	    private String lastUpdateDate;
	    private Integer praiseSum;
	    private Integer commentSum;
	    private Integer collectionSum;
	    private Integer browseSum=0;
	    private String source;
	    private Integer attr;
	    private String remarks;
	    private String province;
	    private String page_url;
	    private String title_file;
	    private String filePublishDate;
	    private String columnInfoType;
		private List<templetAffirmApp> columnInfoList=new ArrayList();
		private String is_stick;
	    private String stick_date;
	    
		public String getIs_stick() {
			return is_stick;
		}
		public void setIs_stick(String is_stick) {
			this.is_stick = is_stick;
		}
		public String getStick_date() {
			return stick_date;
		}
		public void setStick_date(String stick_date) {
			this.stick_date = stick_date;
		}
		public List<templetAffirmApp> getColumnInfoList() {
			return columnInfoList;
		}
		public void setColumnInfoList(List<templetAffirmApp> columnInfoList) {
			this.columnInfoList = columnInfoList;
		}
		public String getColumnInfoType() {
			return columnInfoType;
		}
		public void setColumnInfoType(String columnInfoType) {
			this.columnInfoType = columnInfoType;
		}
		public Integer getArticleId() {
			return articleId;
		}
		public void setArticleId(Integer articleId) {
			this.articleId = articleId;
		}
		public Integer getChannelId() {
			return channelId;
		}
		public void setChannelId(Integer channelId) {
			this.channelId = channelId;
		}
		public String getChannelName() {
			return channelName;
		}
		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}
		public Integer getColumnId() {
			return columnId;
		}
		public void setColumnId(Integer columnId) {
			this.columnId = columnId;
		}
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public Integer getBrandId() {
			return brandId;
		}
		public void setBrandId(Integer brandId) {
			this.brandId = brandId;
		}
		public Integer getGoodsClassType() {
			return goodsClassType;
		}
		public void setGoodsClassType(Integer goodsClassType) {
			this.goodsClassType = goodsClassType;
		}
		public String getGoodsClassId() {
			return goodsClassId;
		}
		public void setGoodsClassId(String goodsClassId) {
			this.goodsClassId = goodsClassId;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getUserTrueName() {
			return userTrueName;
		}
		public void setUserTrueName(String userTrueName) {
			this.userTrueName = userTrueName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDeputyTitle() {
			return deputyTitle;
		}
		public void setDeputyTitle(String deputyTitle) {
			this.deputyTitle = deputyTitle;
		}
		public String getTitleImage() {
			return titleImage;
		}
		public void setTitleImage(String titleImage) {
			this.titleImage = titleImage;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public Integer getIs_final() {
			return is_final;
		}
		public void setIs_final(Integer is_final) {
			this.is_final = is_final;
		}
		public Integer getIs_publish() {
			return is_publish;
		}
		public void setIs_publish(Integer is_publish) {
			this.is_publish = is_publish;
		}
		public Integer getIs_comment() {
			return is_comment;
		}
		public void setIs_comment(Integer is_comment) {
			this.is_comment = is_comment;
		}
		public String getKey_words() {
			return key_words;
		}
		public void setKey_words(String key_words) {
			this.key_words = key_words;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getContentIntro() {
			return contentIntro;
		}
		public void setContentIntro(String contentIntro) {
			this.contentIntro = contentIntro;
		}
		public String getCommitDate() {
			return commitDate;
		}
		public void setCommitDate(String commitDate) {
			this.commitDate = commitDate;
		}
		public String getPublishDate() {
			return publishDate;
		}
		public void setPublishDate(String publishDate) {
			this.publishDate = publishDate;
		}
		public Integer getTypeDate() {
			return typeDate;
		}
		public void setTypeDate(Integer typeDate) {
			this.typeDate = typeDate;
		}
		public String getDateStar() {
			return dateStar;
		}
		public void setDateStar(String dateStar) {
			this.dateStar = dateStar;
		}
		public String getDateEnd() {
			return dateEnd;
		}
		public void setDateEnd(String dateEnd) {
			this.dateEnd = dateEnd;
		}
		public String getLastUpdateDate() {
			return lastUpdateDate;
		}
		public void setLastUpdateDate(String lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
		public Integer getPraiseSum() {
			return praiseSum;
		}
		public void setPraiseSum(Integer praiseSum) {
			this.praiseSum = praiseSum;
		}
		public Integer getCommentSum() {
			return commentSum;
		}
		public void setCommentSum(Integer commentSum) {
			this.commentSum = commentSum;
		}
		public Integer getCollectionSum() {
			return collectionSum;
		}
		public void setCollectionSum(Integer collectionSum) {
			this.collectionSum = collectionSum;
		}
		public Integer getBrowseSum() {
			return browseSum;
		}
		public void setBrowseSum(Integer browseSum) {
			this.browseSum = browseSum;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public Integer getAttr() {
			return attr;
		}
		public void setAttr(Integer attr) {
			this.attr = attr;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getPage_url() {
			return page_url;
		}
		public void setPage_url(String page_url) {
			this.page_url = page_url;
		}
		public String getTitle_file() {
			return title_file;
		}
		public void setTitle_file(String title_file) {
			this.title_file = title_file;
		}
		public String getFilePublishDate() {
			return filePublishDate;
		}
		public void setFilePublishDate(String filePublishDate) {
			this.filePublishDate = filePublishDate;
		}
		public Integer getContentId() {
			return contentId;
		}
		public void setContentId(Integer contentId) {
			this.contentId = contentId;
		}
	
	
}
