package com.zxsd.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 
 * @param url
 *            应用地址，类似于http://ip:port/msg/
 * @param account
 *            账号
 * @param pswd
 *            密码
 * @param mobile
 *            手机号码，多个号码使用","分割
 * @param msg
 *            短信内容
 * @param needstatus
 *            是否需要状态报告，需要true，不需要false
 * @return 返回值定义参见HTTP协议文档
 * @throws Exception
 */
public class HttpSender {
	private static final String url;
	private static final String account;
	private static final String pswd;
	private static final String extno = null;
	static{
		url = (String)AppEnv.getInstance().getProp("sms.url");
		account = (String)AppEnv.getInstance().getProp("sms.account");
		pswd = (String)AppEnv.getInstance().getProp("sms.pswd");
	}
	
	public static boolean batchSend(String mobile,String msg,String smsCode,boolean needstatus) throws Exception{
		HttpClient client=new HttpClient();
		GetMethod method=new GetMethod();
		try{
			URI base=new URI(url,false);
			method.setURI(new URI(base,"HttpBatchSendSM",false));
			method.setQueryString(new NameValuePair[]{new NameValuePair("account",account),
					new NameValuePair("pswd",pswd),new NameValuePair("mobile",mobile),
					new NameValuePair("needstatus",String.valueOf(needstatus)),new NameValuePair("msg",msg),
					new NameValuePair("extno",extno),});
			int result=client.executeMethod(method);
			if(result==HttpStatus.SC_OK) {
				InputStream in=method.getResponseBodyAsStream();
				ByteArrayOutputStream baos=new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int len=0;
				while((len=in.read(buffer))!=-1){
					baos.write(buffer,0,len);
				}
				String returnString = URLDecoder.decode(baos.toString(),"UTF-8");
				if(!StringUtil.isNOrS(returnString))
				{
					String [] status = returnString.split("\n");
					if(status != null && status.length >= 1)
					{
						String stat [] = status[0].split(",");
						if(stat.length >= 1)
						{
							if(stat[1].equals("0"))
							{
								return true;
							}
						}
					}
				}
				return false;
			}else{
				throw new Exception("HTTP ERROR Status: "+method.getStatusCode()+":"+method.getStatusText());
			}
		}finally{
			method.releaseConnection();
		}
	}
}
