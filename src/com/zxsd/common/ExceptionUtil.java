package com.zxsd.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	public static String getStackMsg(Exception e) {  
		  
        StringBuffer sb = new StringBuffer();  
        StackTraceElement[] stackArray = e.getStackTrace(); 
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append(element.toString() + "\n");  
        } 
       /* StackTraceElement element = stackArray[0];  
        sb.append(element.toString());  */
        return "DETAIL:"+sb.toString();  
    }  
	//获取异常详情
	public static String toStringLog(Throwable e){   
         StringWriter sw = new StringWriter();   
         PrintWriter pw = new PrintWriter(sw, true);   
         e.printStackTrace(pw);   
         pw.flush();   
         sw.flush();   
         return sw.toString();   
	 } 
}
