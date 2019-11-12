package com.zxsd.service.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.bean.AccessLogEntity;
import com.zxsd.dao.interfaces.IAccessLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日志分析类
 * even
 * 2019/4/3
 */

@Component
public class AccessLogService {
    private static final Logger logger = LoggerFactory.getLogger(AccessLogService.class);
    @Autowired
    private IAccessLogDao accessLogDao;

    @Scheduled(cron  = "0 0 3 * * ?")
    public void udateAccessLog(){
        logger.info("访问日志统计开始====================================");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH,-1);
        String beforetime = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        //2台服务器日志文件
        for(int i=1;i<=2;i++){
            StringBuffer pathname = new StringBuffer();
            pathname.append("/app/logs/hm/access_hm.2ge.cn_");
            pathname.append(beforetime);
            pathname.append("_"+i+".log");
            logger.info("访问日志统计读取日志文件====="+pathname.toString());
            File file = new File(pathname.toString());
            List<AccessLogEntity> list = new ArrayList<>();
            if(file.exists()){
                InputStreamReader inputstm = null;
                BufferedReader buff = null;
                try {
                    inputstm = new InputStreamReader(new FileInputStream(file), "GBK");
                    buff = new BufferedReader(inputstm);
                    String tempstr = null;
                    while((tempstr = buff.readLine()) != null) {
                        //蜘蛛过滤
                        if (tempstr.indexOf("spider") == -1 && tempstr.indexOf("Spider") == -1
                                && tempstr.indexOf("Googlebot") == -1 && tempstr.indexOf("bingbot") == -1) {
                            AccessLogEntity access = new AccessLogEntity();
                            String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[([\\s\\S]+)\\].*\\?sd=(\\w{32})&md=(.*?)&rf=(.*?)&hf=(.*?)&dt=(\\d{13})";
                            Pattern par = Pattern.compile(regex);
                            Matcher mat = par.matcher(tempstr);
                            while (mat.find()) {
                                //内网IP
                                //if(!Constants.ZXSD_IP_ADDRESS.equals(mat.group(1))){
                                access.setIp(mat.group(1));//IP
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
                                Date date = formatter.parse(mat.group(2));
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                access.setAccess_time(format.format(date));//时间
                                access.setSuid(mat.group(3));//标识
                                if (!StringUtil.isNOrS(mat.group(4))) {
                                    access.setMember_id(Integer.parseInt(mat.group(4)));//ID
                                }
                                if (!StringUtil.isNOrS(mat.group(5)) && mat.group(5).length() < 1000) {
                                    access.setRefer(mat.group(5));//来源
                                }
                                access.setUrl(mat.group(6));//当前页
                                if (!StringUtil.isNOrS(mat.group(6))) {
                                    if (mat.group(6).startsWith("http://m.2ge.cn")) { //wap
                                        access.setType(2);
                                        list.add(access);
                                    } else if (mat.group(6).startsWith("http://www.2ge.cn") || mat.group(6).startsWith("http://tj.2ge.cn")
                                            || mat.group(6).startsWith("http://zhe.2ge.cn")) {//pc
                                        access.setType(1);
                                        list.add(access);
                                    }
                                }
                                //}
                            }
                        }
                    }
                    buff.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                } catch (ParseException e1){

                }finally {
                    if (buff != null){
                        try {
                            buff.close();
                        } catch (IOException e1) {
                        }
                    }
                }
            }
            //入库
            accessLogDao.insertAccess(list);
        }
        logger.info("访问日志统计结束====================================");
    }

    public static void main(String[] args){
        File file = new File("C:\\Users\\even\\Documents\\Tencent Files\\715139446\\FileRecv\\access_hm.2ge.cn_2019-05-15_1.log");
        InputStreamReader inputstm = null;
        BufferedReader buff = null;
        try {
            inputstm = new InputStreamReader(new FileInputStream(file), "GBK");
            buff = new BufferedReader(inputstm);
            String tempstr = null;
            while((tempstr = buff.readLine()) != null) {
                //蜘蛛过滤
                if (tempstr.indexOf("spider") == -1 && tempstr.indexOf("Spider") == -1
                        && tempstr.indexOf("Googlebot") == -1 && tempstr.indexOf("bingbot") == -1) {
                    AccessLogEntity access = new AccessLogEntity();
                    String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+) - - \\[([\\s\\S]+)\\].*\\?sd=(\\w{32})&md=(.*?)&rf=(.*?)&hf=(.*?)&dt=(\\d{13})";
                    Pattern par = Pattern.compile(regex);
                    Matcher mat = par.matcher(tempstr);
                    while (mat.find()) {
                        //内网IP
                        access.setIp(mat.group(1));//IP
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.ENGLISH);
                        Date date = formatter.parse(mat.group(2));
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        access.setAccess_time(format.format(date));//时间
                        access.setSuid(mat.group(3));//标识
                        if (!StringUtil.isNOrS(mat.group(4))) {
                            access.setMember_id(Integer.parseInt(mat.group(4)));//ID
                        }
                        if (!StringUtil.isNOrS(mat.group(5)) && mat.group(5).length() < 1000) {
                            access.setRefer(mat.group(5));//来源
                        }
                        access.setUrl(mat.group(6));//当前页
                        if (!StringUtil.isNOrS(mat.group(6))) {
                            if (mat.group(6).startsWith("http://m.2ge.cn")) { //wap
                                access.setType(2);
                            } else if (mat.group(6).startsWith("http://www.2ge.cn") || mat.group(6).startsWith("http://tj.2ge.cn")
                                    || mat.group(6).startsWith("http://zhe.2ge.cn")) {//pc
                                access.setType(1);
                                System.out.println(tempstr);
                            }
                        }
                    }
                }
            }
            buff.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ParseException e1){

        }finally {
            if (buff != null){
                try {
                    buff.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
