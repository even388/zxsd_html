package com.zxsd.common;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class HttpSenderNew {
	private static final Logger logger = LoggerFactory.getLogger(HttpSenderNew.class);
	public static final String charset = "utf-8";
	//短信发送的URL 请登录zz.253.com 获取完整的URL接口信息
	public static String smsSingleRequestServerUrl = "";

	// 请登录zz.253.com 获取创蓝API账号(非登录账号,示例:N1234567)
	public static String account = "";
	
	// 请登录zz.253.com 获取创蓝API密码(非登录密码)
	public static String pswd = "";
	
	static{
		smsSingleRequestServerUrl = (String)AppEnv.getInstance().getProp("sms.url");
		account = (String)AppEnv.getInstance().getProp("sms.account");
		pswd = (String)AppEnv.getInstance().getProp("sms.pswd");
	}
	
	public static void sendSms(String phone,String msg) throws UnsupportedEncodingException {
		
		//状态报告
		String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
		
		logger.info("before request string is: " + smsSingleRequest.toString());
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		String response = CLSmsUtil.sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		logger.info("response after request result is :" + response);
		
		//SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		//logger.info("response  toString is :" + smsSingleResponse);
	}
}
