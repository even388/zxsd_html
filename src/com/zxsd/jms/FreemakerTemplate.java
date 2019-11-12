package com.zxsd.jms;

import com.zxsd.common.AppEnv;
import com.zxsd.common.Constants;
import com.zxsd.common.ExceptionUtil;
import com.zxsd.common.JsonFormatUtil;
import com.zxsd.dao.bean.LogFormatHead;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public  class FreemakerTemplate {
	private static FreemakerTemplate template = null;
	public static FreemakerTemplate getInstance() {
		if (template == null) {
			template = new FreemakerTemplate();
		}
		return template;
	}
	private final static Logger logger = LoggerFactory.getLogger(FreemakerTemplate.class);
	//静态页面存放路径
	private final static String TARGET_HTML_PAHT=(String) AppEnv.getInstance().getProp("htmlpath");
	//专题静态页面存放路径
	private final static String THEME_HTML_PAHT=(String) AppEnv.getInstance().getProp("themepath");
	//专题静态页面存放路径
	private final static String TARGET_WAPHTML_PAHT=(String) AppEnv.getInstance().getProp("waphtmlpath");
	//话题静态页面存放路径
	private final static String HUATI_HTML_PAHT=(String) AppEnv.getInstance().getProp("huatipath");
	//静态CSS路径
	//private final static String TARGET_HTMLCSS_PAHT=(String) AppEnv.getInstance().getProp("htmlhome");
	public void createHTML(Map<String, Object> data, String templateName){
		
		LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		loghead.setParames("templateName="+templateName);
		logger.info(JsonFormatUtil.getLogJsonString(loghead));
		
		Configuration freemarkerCfg = new Configuration();  
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/template");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			//静态页面生成存放路径+文件名
			File file = new File(TARGET_HTML_PAHT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(TARGET_HTML_PAHT+templateName)), "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
			
		} 
	}
	
	public void createTHEMEHTML(Map<String, Object> data, String templateName,String htmlName){
		LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		StringBuffer logParams=new StringBuffer();
		logParams.append("data="+data.toString()+";");
		logParams.append("templateName="+templateName+";");
		logParams.append("htmlName="+htmlName+";");
		loghead.setParames(logParams.toString()); 
		logger.info(JsonFormatUtil.getLogJsonString(loghead));
		
		Configuration freemarkerCfg = new Configuration();  
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/template");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			template.setClassicCompatible(true);
			//专题页面生成存放路径+文件名
			File file = new File(THEME_HTML_PAHT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(THEME_HTML_PAHT+htmlName)), "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
		} 
	}

	//话题静态页面
	public void createHUATIHTML(Map<String, Object> data, String templateName,String htmlName){
		LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		StringBuffer logParams=new StringBuffer();
		logParams.append("data="+data.toString()+";");
		logParams.append("templateName="+templateName+";");
		logParams.append("htmlName="+htmlName+";");
		loghead.setParames(logParams.toString());
		logger.info(JsonFormatUtil.getLogJsonString(loghead));

		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/template");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			template.setClassicCompatible(true);
			String pathname = HUATI_HTML_PAHT;
			if(templateName.startsWith("m")){
				pathname = TARGET_WAPHTML_PAHT+"huati/";
			}
			//话题页面生成存放路径+文件名
			File file = new File(pathname);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(pathname+htmlName)), "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
		}
	}
	
	public void createContMapHTML(Map<String, Object> data, String templateName){
		
		LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		loghead.setParames("templateName="+templateName);
		logger.info(JsonFormatUtil.getLogJsonString(loghead));
		
		Configuration freemarkerCfg = new Configuration();  
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/template/contmap/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			//静态页面生成存放路径+文件名
			File file = new File(TARGET_HTML_PAHT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(TARGET_HTML_PAHT+templateName)), "UTF-8");
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
			
		} 
	}
	//wap端首页
	public void createWapHTML(Map<String, Object> data, String templateName){
		
		LogFormatHead loghead = new LogFormatHead(Constants.HTML_WAPCHANNEL_TYPE,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		loghead.setParames("templateName="+templateName);
		logger.info(JsonFormatUtil.getLogJsonString(loghead));
		
		Configuration freemarkerCfg = new Configuration();  
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/waptemplate");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			//静态页面生成存放路径+文件名
			File file = new File(TARGET_WAPHTML_PAHT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(TARGET_WAPHTML_PAHT+templateName)), "UTF-8");
			/*out = new OutputStreamWriter(new FileOutputStream("D:\\freemarkhtml\\index.html"), "UTF-8");*/
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
			
		} 
	}
	//wap端频道页
	public void createChannelHTML(Map<String, Object> data, String templateName ,String templateName1){
		LogFormatHead loghead = new LogFormatHead(Constants.HTML_WAPCHANNEL_TYPE,Constants.LOG_INFO,Constants.LOG_OPERA_SYS);
		loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		loghead.setUserBehavior(Constants.LOG_OPERA_ADD);
		loghead.setParames("templateName="+templateName);
		logger.info(JsonFormatUtil.getLogJsonString(loghead));
		
		Configuration freemarkerCfg = new Configuration();  
		freemarkerCfg.setClassForTemplateLoading(FreemakerTemplate.class, "/waptemplate");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");  
		OutputStreamWriter out = null;
		try {
			// 获取模版
			Template template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			//静态页面生成存放路径+文件名
			File file = new File(TARGET_WAPHTML_PAHT);
			if(!file.exists())
			{
				file.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(new File(TARGET_WAPHTML_PAHT+templateName1)), "UTF-8");
			/*out = new OutputStreamWriter(new FileOutputStream("D:\\freemarkhtml\\gmyj2.html"), "UTF-8");*/
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			loghead.setResults(ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
			logger.error(JsonFormatUtil.getLogJsonString(loghead));
			
		} 
	}
}
