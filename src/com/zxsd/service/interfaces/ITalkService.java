package com.zxsd.service.interfaces;

import com.zxsd.dao.bean.TalkEntity;
import com.zxsd.dao.bean.TalkTempEntity;

import java.util.List;

/**
 * even
 * 2019/7/15
 */
public interface ITalkService {
    TalkEntity getTalkInfo(String talk_id);

    List<TalkEntity> getTalkColumnList(String talk_id,String limit);

    List<TalkTempEntity> getTalkTempList(String column_id);
}
