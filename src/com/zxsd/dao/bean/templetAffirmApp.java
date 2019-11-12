
package com.zxsd.dao.bean;

/**
 * @author Administrator
 *
 */
public class templetAffirmApp {
	
	private Integer id; 
	private Integer columnId;
	private String	columnInfoType;
	private	String title;
	private String image;
	private String updateTime;
	private String contentIntro;
	private String source;
	private String userImg;
	private String goodsClassId;
	private String deputyTitle;
	private String memberNickname;
	private String browseSumShow;
	
	public String getDeputyTitle() {
		return deputyTitle;
	}
	public void setDeputyTitle(String deputyTitle) {
		this.deputyTitle = deputyTitle;
	}
	public String getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(String goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getContentIntro() {
		return contentIntro;
	}
	public void setContentIntro(String contentIntro) {
		this.contentIntro = contentIntro;
	}
	private String isBold;
	
	public String getIsBold() {
		return isBold;
	}
	public void setIsBold(String isBold) {
		this.isBold = isBold;
	}
	/*private Date updateTime;*/
	/**
	 * @return the userTrueName
	 */
	public String getUserTrueName() {
		return userTrueName;
	}
	/**
	 * @param userTrueName the userTrueName to set
	 */
	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}
	private String userTrueName;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColumnInfoType() {
		return columnInfoType;
	}
	public void setColumnInfoType(String columnInfoType) {
		this.columnInfoType = columnInfoType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getColumnId() {
		return columnId;
	}
	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getBrowseSumShow() {
		return browseSumShow;
	}
	public void setBrowseSumShow(String browseSumShow) {
		this.browseSumShow = browseSumShow;
	}

	
	

}
