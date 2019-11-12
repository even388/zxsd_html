/**    
 * @project_name  
 * @Title JsonFormatUtil.java  
 * @Package com.zxsd.home 
 * @author zhy
 * @date 2016-4-7 上午9:57:04 
 * @version v1.0 
 * @history 历史修改记录 
 */ 

package com.zxsd.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/** JSON操作工具类       
 * @ClassName JsonFormatUtil   
 * @Description TODO
 * @author zhy
 * @date 2016-4-7 上午9:57:04  
 * @version v1.0
 * @history 历史修改记录 
 */
public class JsonFormatUtil {
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象
	 * 
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}

	/**
	 * 将json格式的字符串解析成Map对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> getMap4Json(String jsonString) {
		Map<String,String> data = new HashMap<String, String>();  
        JSONObject jsonObject = JSONObject.fromObject(jsonString);  
        Iterator it = jsonObject.keys();  
        while (it.hasNext())  
        {  
            String key = String.valueOf(it.next());  
            String value = (String) jsonObject.get(key);  
            data.put(key, value);  
        }  
        return data;  
	}

	/**
	 * 从json数组中得到相应java数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 从json对象集合表达式中得到java对象列表
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static List getList4Json(String jsonString, Class pojoClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i<jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);
		}
		return list;
	}

	/**
	 * 从json数组中解析出java字符串数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static String[] getStringArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0;i< jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

	/**
	 * 从json数组中解析出javaLong型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Long[] getLongArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i <jsonArray.size(); i++) {
			longArray[i] = jsonArray.getLong(i);
		}
		return longArray;
	}

	/**
	 * 从json数组中解析出java Integer型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Integer[] getIntegerArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i<jsonArray.size(); i++) {
			integerArray[i] = jsonArray.getInt(i);
		}
		return integerArray;
	}

	

	/**
	 * 从json数组中解析出java Double型对象数组
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Double[] getDoubleArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i<jsonArray.size(); i++) {
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}

	/**
	 * 将java对象转换成json字符
	 * 
	 * @param javaObj
	 * @return
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {
		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return json.toString();
	}
	
	/**
	 * 将java对象List转换成json字符
	 * 
	 * @param javaObj
	 * @return
	 */
	public static String getJsonString4JavaList(List list) {
		JSONArray jsonarray ;
		jsonarray = JSONArray.fromObject(list);  
		return jsonarray.toString();
	}
	public static String getLogJsonString(Object javaObj) {
		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return "zxsd_html||LOGHEAD||"+json.toString();
	}

	/**
	 * 将java对象转换成json字符,并设定日期
	 * 
	 * @param javaObj
	 * @param dataFormat
	 * @return
	 */
//	public static String getJsonString4JavaPOJO(Object javaObj,
//			String dataFormat) {
//		JSONObject json;
//		JsonConfig jsonConfig = JsonConfig
//		json = JSONObject.fromObject(javaObj, jsonConfig);
//		return json.toString();
//	}
	
//	/**
//	* JSON 时间解析器具
//	* @param datePattern
//	* @return
//	*/
//	public static JsonConfig configJson(String datePattern) {
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setIgnoreDefaultExcludes(false);
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor(datePattern));
//		return jsonConfig;
//	}
	
}
