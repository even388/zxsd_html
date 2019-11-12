package com.zxsd.common;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String getToken() {
		return UUID.randomUUID().toString();
	}

	public static String getCurrentTimeStr() {
		return getDateFormatStr("yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrentTimeStr2() {
		return getDateFormatStr("yyyyMMdd_hhmmssSSS");
	}

	public static String getDateFormatStr(final String formart) {
		return new SimpleDateFormat(formart).format(new java.util.Date());
	}

	public static String convertNull2Empty(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 字符串转Date类型
	 * 
	 * @param str
	 * @return
	 */
	public static Date StrConvertDate(String str) {
		Date date = null;
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (!isEmpty(str)) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				date = null;
			}
			return date;
		}
		return null;
	}

	public static Date StrConvertDate2(String str) {
		Date date = null;
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if (!isEmpty(str)) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				date = null;
			}
			return date;
		}
		return null;
	}

	/**
	 * 日期类型转为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String DateConvertStr() {
		try {
			String pattern = "HHmmss";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(new Date());
		} catch (Exception e) {
		}
		return "";
	}

	public static String DateConvertStr2(Date date) {
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * 如果是null或空返回true,否则false
	 * @param str
	 * @return
	 */
	public static boolean isNOrS(String str) {
		if ( (str == null) || "".equals(str.trim()) || "null".equalsIgnoreCase(str.trim()) ) {
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNullOrStr(String str) {
		if ( (str == null) || "".equals(str.trim())) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 为空返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (null == str || 0 == str.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 清除掉所有特殊字符
	 * */
	public static String stringFilter(String str) {
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public static void main(String[] args) {
		String string = stringFilter("s ss:ff  sss 副本。txt.jpg");
		System.out.println(string);
		String str = getCurrentTimeStr2();
		System.out.println(str);
	}

	/**
	 * 4位随机数
	 * 
	 * @return
	 */
	public static String getCode() {
		Random random = new Random();
		String code = "";
		for (int i = 0; i < 4; i++) {
			code += random.nextInt(10);
		}
		return code;
	}
	
	/**
	 * 10位随机数 //HHMMSS+4为随机数
	 * 
	 * @return
	 */
	public static String nextVal(){
		return DateConvertStr()+getCode();
	}
	//截取中英文混合文字
	   public static String subString(String text, int length, String endWith) {        
	        int textLength = text.length();  
	        int byteLength = 0;  
	        StringBuffer returnStr =  new StringBuffer();  
	        for(int i = 0; i<textLength && byteLength < length*2; i++){  
	            String str_i = text.substring(i, i+1);   
	            if(str_i.getBytes().length == 1){//英文  
	                byteLength++;  
	            }else{//中文  
	                byteLength += 2 ;  
	            }  
	            returnStr.append(str_i);  
	        }  
	        try {  
	            if(byteLength<text.getBytes("GBK").length){//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3  
	                returnStr.append(endWith);  
	            }  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        return returnStr.toString();  
	    }  
}
