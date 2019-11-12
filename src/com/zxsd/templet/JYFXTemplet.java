package com.zxsd.templet;

import java.util.ArrayList;
import java.util.List;

import com.zxsd.common.DataType;


/**  
* @Title: JYFXTemplet.java
* @Package com.zxsd.TempletUtil
* @Description: TODO(经验分享数据模板)属性参数说明type_id_name type:该模板属性(1:词条,2:文章，3：UGC，4：品牌,5:商品分类 ) id:
* @author 徐腾 
* @date 2016年10月17日 下午3:54:19
* @version V1.0  
*/ 
public enum JYFXTemplet {
	JYFX_RMJY(DataType.UGC_ID,"1","热门经验",5),
	JYFX_ZXJY(DataType.UGC_ID,"2","最新经验",5),
	
	JYFX_FZ(DataType.GOODS_CLASS_ID,"104","服饰鞋包",0),
	JYFX_JD(DataType.GOODS_CLASS_ID,"101","家用电器",0),
	JYFX_JC(DataType.GOODS_CLASS_ID,"503","房地产",0),//暂时未定
	JYFX_YL_1(DataType.GOODS_CLASS_ID,"112","医药/医疗",0),
	JYFX_JY(DataType.GOODS_CLASS_ID,"508","教育培训",0),
	JYFX_JR(DataType.GOODS_CLASS_ID,"505","理财金融",0),
	JYFX_QC(DataType.GOODS_CLASS_ID,"107","汽车/汽车用品",0),
	JYFX_YL(DataType.GOODS_CLASS_ID,"507","休闲娱乐",0),
	JYFX_MY(DataType.GOODS_CLASS_ID,"108","母婴",0),
	JYFX_DN(DataType.GOODS_CLASS_ID,"113","电脑/办公",0),
	JYFX_LY(DataType.GOODS_CLASS_ID,"502","旅游/出行",0),
	JYFX_SJ(DataType.GOODS_CLASS_ID,"102","手机/数码",0),
	JYFX_JJ(DataType.GOODS_CLASS_ID,"103","家居/家装/厨具",0),
	JYFX_GRHL(DataType.GOODS_CLASS_ID,"105","个人护理/化妆品",0),
	JYFX_HW(DataType.GOODS_CLASS_ID,"106","户外/运动/乐器",0),
	JYFX_SP(DataType.GOODS_CLASS_ID,"109","食品/生鲜/酒饮",0),
	JYFX_YYBJ(DataType.GOODS_CLASS_ID,"110","家庭保健",0),
	JYFX_TSYX(DataType.GOODS_CLASS_ID,"111","图书音像",0),
	JYFX_YDJS(DataType.GOODS_CLASS_ID,"501","运动健身",0),
	JYFX_JTFW(DataType.GOODS_CLASS_ID,"504","家庭服务",0),
	JYFX_SWFW(DataType.GOODS_CLASS_ID,"506","商务服务",0),
	JYFX_HL(DataType.GOODS_CLASS_ID,"509","婚恋/交友",0),
	JYFX_NLM(DataType.GOODS_CLASS_ID,"510","农林牧副渔",0),
	
	JYFX_GMJY(DataType.UGC_ID,"121","购买经验",6),
	JYFX_SYJY(DataType.UGC_ID,"122","使用经验",5),
	JYFX_TYJY(DataType.UGC_ID,"123","通用经验",5),

	;
	private final int type;
	 private final String columnId;
	 private final String columnName;

	private final int size;
	private JYFXTemplet(int type,String columnId,String columnName,int size) {
     this.type=type;
     this.columnId=columnId;
     this.columnName=columnName;
     this.size=size;
 }
// 普通方法  
 public static List<String> getColumnNames() {  
 	List list=new ArrayList();
     for (JYFXTemplet c : JYFXTemplet.values()) {    
     	list.add(c.getColumnName()) ;
     }  
     return list;  
 }  
 // 普通方法  
 public static List<String> getColumnIds() {  
 	List list=new ArrayList();
     for (JYFXTemplet c : JYFXTemplet.values()) {    
     	list.add(c.getColumnId()) ;
     }  
     return list;  
 } 
 // 普通方法  
 public static List<Integer> getTypes() {  
 	List list=new ArrayList();
     for (JYFXTemplet c : JYFXTemplet.values()) {    
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
