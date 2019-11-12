package com.zxsd.dao.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.base.BaseDao;
import com.zxsd.dao.bean.TalkEntity;
import com.zxsd.dao.bean.TalkTempEntity;
import com.zxsd.dao.interfaces.ITalkDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * even
 * 2019/7/15
 */
@Repository
public class TalkDaoImpl extends BaseDao implements ITalkDao {

    @Override
    public TalkEntity getTalkInfo(String talk_id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select id,title,pc_image,wap_image,html_name from tb_talk where status=5 and id=?");
        return (TalkEntity) this.queryObject(sql.toString(), new Object[]{talk_id}, new BeanPropertyRowMapper(TalkEntity.class));
    }

    @Override
    public List<TalkEntity> getTalkColumnList(String talk_id,String limit) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select id,talk_id,column_title,order_num from tb_talk_column where talk_id=? order by order_num");
        if(!StringUtil.isNOrS(limit)){
            sql.append(" limit 0,");
            sql.append(limit);
        }
        return this.queryList(sql.toString(), new Object[]{talk_id}, new BeanPropertyRowMapper(TalkEntity.class));
    }

    @Override
    public List<TalkTempEntity> getTalkTempList(String column_id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from tb_talk_template where talk_column_id=? order by order_num");
        return this.queryList(sql.toString(), new Object[]{column_id}, new BeanPropertyRowMapper(TalkTempEntity.class));
    }
}
