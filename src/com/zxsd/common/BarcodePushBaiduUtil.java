package com.zxsd.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author rh
 * @version v1.0 2019年1月8日 上午11:08:30
 * @ClassName HttpUtil
 * @Description
 * @date 2019年1月8日 上午11:08:30
 * @history 历史修改记录 <作者> <日期> <版本> <描述> rh 2019年1月8日 上午11:08:30 V1.0
 */
public class BarcodePushBaiduUtil {

    public static String environment; //环境
    public static String wapBaiduPostUrl; // 熊掌号推送地址 wap
    public static String urlPreFix; // 百度推送地址 wap

    public static String pcUrl; // 百度推送地址 pc
    public static String pcUrlPreFix;
    public static String wapUrl; // 百度推送地址 wap
    public static String wapUrlPreFix;
    private static String SERVER_NAME;
    protected final static Logger logger = LoggerFactory.getLogger(BarcodePushBaiduUtil.class);

    static {
        try {
            SERVER_NAME = InetAddress.getLocalHost().getHostName();
            InputStream in = BarcodePushBaiduUtil.class.getClassLoader().getResourceAsStream("baidupush.properties");
            Properties properties = new Properties();
            properties.load(in);
            wapBaiduPostUrl = (String) properties.get("wapBaiduPostUrl");
            urlPreFix = (String) properties.get("urlPreFix");
            environment = (String) properties.get("environment");
            pcUrl = (String) properties.get("pcUrl");
            pcUrlPreFix = (String) properties.get("pcUrlPreFix");
            wapUrl = (String) properties.get("wapUrl");
            wapUrlPreFix = (String) properties.get("wapUrlPreFix");
        } catch (Exception e) {
        }
    }


    /*public static String Post(String[] urlArray, String[] wapUrlArray) {
        if (urlArray == null || urlArray.length == 0 || wapUrlArray == null || wapUrlArray.length == 0) {
            return "params error exception,please check params..... ";
        }
        try {
            String hostName = getCurrentIp().getLocalHost().getHostName();
            String hostAddress = getCurrentIp().getLocalHost().getHostAddress();
            logger.info("hostName  " + hostName);
            logger.info("hostAddress  " + hostAddress);
            if ("192.168.1.174".equals(hostAddress)) {
                logger.info("测试环境，不执行推送.......");
                return null;
            }
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();
            logger.info("hostAddress  " + hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String pcRes = Post2(pcBaiduPostUrl, urlArray);
        logger.info("pc push result : " + pcRes);
        String wapRes = Post2(wapBaiduPostUrl, wapUrlArray);
        logger.info("WAP push result： " + wapRes);
        return pcRes + wapRes;
    }*/

    /**
     * 推送百度熊掌号.只有wap才有
     *
     * @param ids
     * @return
     */
    public static String sendBaiDuPaw(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            return "param is null or empty";
        }
        //生产环境才需要推送
        if (!("server1.2ge.cn").equals(SERVER_NAME)){
            return null;
        }
        String[] urlArr = new String[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            urlArr[i] = urlPreFix + ids.get(i)+".html";
        }
        String pcRes = request(wapBaiduPostUrl, urlArr);
        logger.info("urls===:" + urlArr.toString());
        logger.info("toUrl===:" + wapBaiduPostUrl);
        //logger.info("wap push result : " + pcRes);
        return null;
    }

    /**
     * 推送百度,pc和wap都有
     *
     * @return
     */
    public static String sendBaiDu(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            return "param is null or empty";
        }
        logger.info("current environment:===:" + environment);
        //生产环境才需要推送
        if (!environment.equals("product")) {
            return null;
        }
        String[] pcUrlArr = new String[ids.size()];
        String[] wapUrlArr = new String[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            pcUrlArr[i] = pcUrlPreFix + ids.get(i);
            wapUrlArr[i] = wapUrlPreFix + ids.get(i);
        }
        String pcRes = request(pcUrl, pcUrlArr);
        String wapRes = request(wapUrl, wapUrlArr);
        logger.info("pcUrlArr===:" + ListUtil.getlistStr(Arrays.asList(pcUrlArr), ","));
        logger.info("pcToUrl===:" + pcUrl);
        logger.info("wapUrlArr===:" + ListUtil.getlistStr(Arrays.asList(wapUrlArr), ","));
        logger.info("wapToUrl===:" + wapUrl);
        logger.info("pc push result : " + pcRes);
        logger.info("wap push result : " + wapRes);
        return pcRes + "\n" + wapRes;
    }

    private static String request(String postUrl, String[] parameters) {
        if (null == postUrl || null == parameters || parameters.length == 0) {
            return null;
        }
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.setHeader("User-Agent", "curl/7.12.1");
        httpPost.setHeader("Host", "data.zz.baidu.com");
        httpPost.setHeader("Content-Type", "text/plain");

        String param = StringUtils.join(parameters, "\n");
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        httpPost.setEntity(stringEntity);
        HttpResponse response;
        String s = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            s = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        //	String url = "http://data.zz.baidu.com/urls?site=www.2ge.cn&token=Xq036GuBWMe8JgTm";// 网站的服务器连接
        List<String> param = new ArrayList();
        param.add("1293342");
        param.add("1293343");
        String post = BarcodePushBaiduUtil.sendBaiDu(param);
        System.out.println(post);
        System.out.println("==========================================================");

    }
}
