package com.zxsd.service.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.bean.TalkEntity;
import com.zxsd.dao.bean.TalkTempEntity;
import com.zxsd.dao.interfaces.ITalkDao;
import com.zxsd.service.interfaces.ITalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * even
 * 2019/7/15
 */
@Service
public class TalkServiceImpl implements ITalkService {

    @Autowired
    private ITalkDao talkDao;

    @Override
    public TalkEntity getTalkInfo(String talk_id) {
        return talkDao.getTalkInfo(talk_id);
    }

    @Override
    public List<TalkEntity> getTalkColumnList(String talk_id,String limit) {
        return talkDao.getTalkColumnList(talk_id,limit);
    }

    @Override
    public List<TalkTempEntity> getTalkTempList(String column_id) {
        List<TalkTempEntity> list = talkDao.getTalkTempList(column_id);
        for(TalkTempEntity temp:list){
            if(!StringUtil.isNOrS(temp.getPc_free_url())){
                temp.setPc_url(temp.getPc_free_url());
            }
            if(!StringUtil.isNOrS(temp.getWap_free_url())){
                temp.setWap_url(temp.getWap_free_url());
            }
        }
        return list;
    }
}
