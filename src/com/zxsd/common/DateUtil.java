package com.zxsd.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
* @Title: DateUtil.java
* @Package com.zxsd.util
* @Description: TODO(日期工具类) 
* @author 徐腾 
* @date 2016年10月26日 下午4:11:42
* @version V1.0  
*/ 
public class DateUtil {
	/**
	 * 
	 * getCurrentTimeStr:(获取当前时间字符串，时间格式yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @since JDK 1.6
	 */
	public static String getCurrentTimeStr() {
		return getDateFormatStr("yyyy-MM-dd HH:mm:ss");
	}
/**
 * 
 * getDateFormatStr:(输入任意格式返回当前时间字符串)
 * @param formart 字段格式如 "yyyy-MM-dd HH:mm:ss"
 * @return
 * @since JDK 1.6
 */
	
	public static String getDateFormatStr(final String formart) {
		return new SimpleDateFormat(formart).format(new java.util.Date());
	}
	/**
	 * 
	 * getTime:(输入11位字符串日期返回10位字符串日期)
	 * @param formart
	 * @return
	 * @since JDK 1.6
	 */
	public static String getTime(String user_time) {  
		String re_time = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date d;  
		try {  
	
		d = sdf.parse(user_time);  
		long l = d.getTime();  
		String str = String.valueOf(l);  
		re_time = getStringFromLongDate(str);  
		  
		  
		} catch (ParseException e) {  
		// TODO Auto-generated catch block  
		e.printStackTrace();  
		}  
		return re_time;  
		}  
	
	/**
	 * 
	 * getDate:(输入11位字符串日期返回10位字符串日期)
	 * @param formart
	 * @return
	 * @since JDK 1.6
	 */
	public static String getDate(String user_time) {  
		String re_time = null;  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date d;  
		try {  
	
		d = sdf.parse(user_time);  
		long l = d.getTime();  
		String str = String.valueOf(l);  
		re_time = getStringFromLongDate(str);  
		  
		  
		} catch (ParseException e) {  
		// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
		return re_time.substring(0,10);  
		}  
	/**
	 * 
	 * getStringFromLongDate:(输入时间戳返回日期)
	 * @param longdate 时间戳
	 * @return
	 * @since JDK 1.6
	 */
	public static String getStringFromLongDate(String longdate){
		String date=null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		date = sdf.format(new Date(Long.valueOf(longdate)));
		}catch(Exception e){
			e.printStackTrace();
			return longdate;
		}
		return date;
	}
		/**
		 * 获取时间差
		 * @description:TODO
		 * @param：time1 数据库时间
		 * @param：time2 当前时间
		 * @return:map 返回时间差
		 * @author:txw
		 * @date:2016年11月17日上午11:27:45
		 */
	public static Map<String,Object> getTimeDifference(String time1,String time2){
		   SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   java.util.Date begin;
		try {
			begin = dfs.parse(time1);
			java.util.Date end = dfs.parse(time2);
			long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
			
			long day=between/(24*3600);
			long hour=between%(24*3600)/3600;
			long minute=between%3600/60;
			long second=between%60/60;		
			Map<String,Object> map =new HashMap<String,Object>();
			if(second==0){
				map.put("message", "刚刚");
			}
			if(minute!=0){
				map.put("message", minute+"分钟前");
			}
			if(hour!=0){
				map.put("message", hour+"小时前");
			}
			if(day!=0){
				map.put("message", day+"天前");
			}
			return map;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String[]  monthList=new String[]{"JAN","FEB","Mar","APR","MAY","JUNE","JULY","AUG","SEPT","OCT","NOV","DEC"};
}
