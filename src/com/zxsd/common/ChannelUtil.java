package com.zxsd.common;

/**  
* @Title: ChannelType.java
* @Package com.zxsd.TempletUtil
* @Description: TODO(频道工具类)
* @author 徐腾 
* @date 2016年10月18日 下午3:38:35
* @version V1.0  
*/ 
public class ChannelUtil {

	public static final String SY="100";//首页
	public static final String BGT="101";//曝光台
	public static final String JYFX="102";//经验分享
	public static final String GMYJ="103";//购买依据
	public static final String GMJQ="104";//购买技巧
	public static final String GMTS="105";//购买提示
	public static final String GMWQ="106";//购买误区
	public static final String GMXJ="107";//购买陷阱
	public static final String GMLC="108";//购买流程
	public static final String GJBZ="109";//国家标准
	public static final String YZPP="110";//优质品牌
	public static final String BYHL="111";//保养护理
	public static final String SHFW="112";//售后服务
	
	public static String getChannel(String channelName){
		String channelId="";
		switch (channelName) {
		case "曝光台": channelId=BGT;break;
		case "经验分享": channelId=JYFX;break;
		case "购买依据": channelId=GMYJ;break;
		case "购买技巧": channelId=GMJQ;break;
		case "购买提示": channelId=GMTS;break;
		case "购买误区": channelId=GMWQ;break;
		case "购买陷阱": channelId=GMXJ;break;
		case "购买流程": channelId=GMLC;break;
		case "国家标准": channelId=GJBZ;break;
		case "优质品牌": channelId=YZPP;break;
		case "保养护理": channelId=BYHL;break;
		case "售后服务": channelId=SHFW;break;

		default:
			break;
		}
		return channelId;
	}
	public static String getChannelName(String channelId){
		String channelName="";
		switch (channelId) {
		case BGT: channelName="曝光台";break;
		case JYFX: channelName="经验分享";break;
		case GMYJ: channelName="购买依据";break;
		case GMJQ: channelName="购买技巧";break;
		case GMTS: channelName="购买提示";break;
		case GMWQ: channelName="购买误区";break;
		case GMXJ: channelName="购买陷阱";break;
		case GMLC: channelName="购买流程";break;
		case GJBZ: channelName="国家标准";break;
		case YZPP: channelName="优质品牌";break;
		case BYHL: channelName="保养护理";break;
		case SHFW: channelName="售后服务";break;
		
		default:
			break;
		}
		return channelName;
	}
}
