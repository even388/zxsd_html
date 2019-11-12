package com.zxsd.common;

import java.util.ArrayList;
import java.util.List;


/**  
* @Title: SHFWTemplet.java
* @Package com.zxsd.TempletUtil
* @Description: TODO(售后服务数据模板)属性参数说明type_id_name_size(取多少数据) type:该模板属性(1:词条,2:文章，3：UGC，4：品牌 ，5:商品分类) id:
* @author 徐腾 
* @date 2016年10月17日 下午3:54:19
* @version V1.0  
*/ 
public enum NEW_SHFWTemplet {
	SHFW_JDDQ(DataType.GOODS_CLASS_ID,"101","家用电器",0),
	SHFW_SJSM(DataType.GOODS_CLASS_ID,"105","数码手机",0),
	SHFW_DDBG(DataType.GOODS_CLASS_ID,"106","电脑办公",0),
	SHFW_QCYP(DataType.GOODS_CLASS_ID,"104","美妆个护",0),
	SHFW_YYT(DataType.GOODS_CLASS_ID,"107","母婴玩具",0),
	SHFW_JZJJ(DataType.GOODS_CLASS_ID,"109","医药保健",0),
	;
	private final int type;
	 private final String columnId;
	 private final String columnName;

	private final int size;
	  
	private NEW_SHFWTemplet(int type,String columnId,String columnName,int size) {
	        this.type=type;
	        this.columnId=columnId;
	        this.columnName=columnName;
	        this.size=size;
	    }
	   // 普通方法  
	    public static List<String> getColumnNames() {  
	    	List list=new ArrayList();
	        for (SHFWTemplet c : SHFWTemplet.values()) {    
	        	list.add(c.getColumnName()) ;
	        }  
	        return list;  
	    }  
	    // 普通方法  
	    public static List<String> getColumnIds() {  
	    	List list=new ArrayList();
	        for (SHFWTemplet c : SHFWTemplet.values()) {    
	        	list.add(c.getColumnId()) ;
	        }  
	        return list;  
	    } 
	    // 普通方法  
	    public static List<Integer> getTypes() {  
	    	List list=new ArrayList();
	        for (SHFWTemplet c : SHFWTemplet.values()) {    
	        	list.add(c.getType()) ;
	        }  
	        return list;  
	    } 
		 /**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}
	   /**
		 * @return the type
		 */
		public int getType() {
			return type;
		}
		/**
		 * @return the columnId
		 */
		public String getColumnId() {
			return columnId;
		}
		/**
		 * @return the columnName
		 */
		public String getColumnName() {
			return columnName;
		}
		
}

