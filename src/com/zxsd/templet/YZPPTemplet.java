package com.zxsd.templet;

import java.util.ArrayList;
import java.util.List;

import com.zxsd.common.DataType;

/**  
* @Title: GMYJTemplet.java
* @Package com.zxsd.TempletUtil
* @Description: TODO(优质品牌数据模板)属性参数说明type_id_name type:该模板属性(1:词条,2:文章，3：UGC，4：品牌 ,5:商品) id:
* @author 徐腾 
* @date 2016年10月17日 下午3:54:19
* @version V1.0  
*/ 
public enum YZPPTemplet {
	
	YZPP_JD(DataType.GOODS_CLASS_ID,"101","家用电器",0),
	YZPP_SJ(DataType.GOODS_CLASS_ID,"105","数码手机",0),
	YZPP_DN(DataType.GOODS_CLASS_ID,"106","电脑办公",0),
	YZPP_QC(DataType.GOODS_CLASS_ID,"104","美妆个护",0),
	YZPP_SP(DataType.GOODS_CLASS_ID,"107","母婴玩具",0),
	YZPP_HW(DataType.GOODS_CLASS_ID,"109","医药保健",0),
	
	
	YZPP_PPXW(DataType.ARTACLE_ID,"111","品牌新闻",5),
	YZPP_SDPP(DataType.BRAND_ID,"4","十大品牌",10),
	YZPP_GJPP(DataType.BRAND_ID,"1","国际品牌",5),
	YZPP_GNPP(DataType.BRAND_ID,"2","国内品牌",5),
	YZPP_XJPP(DataType.BRAND_ID,"3","新进品牌",5),
	YZPP_PPRW(DataType.ARTACLE_ID,"113","品牌人物",5),
	YZPP_PPGS(DataType.ARTACLE_ID,"112","品牌故事",5),
	
	;
	private final int type;
	 private final String columnId;
	 private final String columnName;

	private final int size;
	private YZPPTemplet(int type,String columnId,String columnName,int size) {
    this.type=type;
    this.columnId=columnId;
    this.columnName=columnName;
    this.size=size;
}
//普通方法  
public static List<String> getColumnNames() {  
	List list=new ArrayList();
    for (YZPPTemplet c : YZPPTemplet.values()) {    
    	list.add(c.getColumnName()) ;
    }  
    return list;  
}  
// 普通方法  
public static List<String> getColumnIds() {  
	List list=new ArrayList();
    for (YZPPTemplet c : YZPPTemplet.values()) {    
    	list.add(c.getColumnId()) ;
    }  
    return list;  
} 
// 普通方法  
public static List<Integer> getTypes() {  
	List list=new ArrayList();
    for (YZPPTemplet c : YZPPTemplet.values()) {    
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
