package com.zxsd.service.impl;

import com.zxsd.common.BarcodePushBaiduUtil;
import com.zxsd.dao.interfaces.ICodeLibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * even
 * 2019/4/9
 */
@Component
public class CategorySeivice {

    @Autowired
    private ICodeLibraryDao codeLibraryDao;

    @Scheduled(cron  = "0 0 21 ? * SUN")
    public void pushCateListByWeek(){
        List<String> catelist = new ArrayList<>();
        //本周数据
        List<Map<String,Object>> list = codeLibraryDao.queryCatetoryByweek();
        for(Map<String,Object> map:list){
            catelist.add(map.get("type_id").toString());
        }
        //熊掌推送
        BarcodePushBaiduUtil.sendBaiDuPaw(catelist);
    }
}
