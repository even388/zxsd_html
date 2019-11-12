package com.zxsd.dao.bean;

import java.util.ArrayList;
import java.util.List;

import com.zxsd.dao.bean.ColumnAppEntity;

/**
 * 栏目
 * @ClassName ColumnAppEntity   
 * @Description 
 * @author  gw
 * @date 2017年10月11日 下午3:31:28  
 * @version v1.0 2017年10月11日 下午3:31:28
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2017年10月11日 下午3:31:28　　V1.0　　 build此模块
 */
public class ColumnAppEntity {
	private Integer conditions;
	private Integer isshort;
	
	private Integer id; 
	private String	columnName;
	private	Integer channelId;
	//'栏目类型(1：词条属性栏目,2:文章栏目，3:ugc栏目,4:词条栏目,6品牌栏目)',
	private String columnType;
	private Integer rowSize;
	private Integer pageModel;
	
	private Integer size;
	private Integer isAuto;
	private String columnId;
	private List<templetAffirmApp> columnInfoList=new ArrayList();
	
	public Integer getConditions() {
		return conditions;
	}
	public void setConditions(Integer conditions) {
		this.conditions = conditions;
	}
	public Integer getIsshort() {
		return isshort;
	}
	public void setIsshort(Integer isshort) {
		this.isshort = isshort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public Integer getRowSize() {
		return rowSize;
	}
	public void setRowSize(Integer rowSize) {
		this.rowSize = rowSize;
	}
	public Integer getPageModel() {
		return pageModel;
	}
	public void setPageModel(Integer pageModel) {
		this.pageModel = pageModel;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public List<templetAffirmApp> getColumnInfoList() {
		return columnInfoList;
	}
	public void setColumnInfoList(List<templetAffirmApp> columnInfoList) {
		this.columnInfoList = columnInfoList;
	}
	
	
    
}
