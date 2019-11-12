package com.zxsd.dao.impl;

import com.zxsd.dao.base.LogBaseDao;
import com.zxsd.dao.bean.AccessLogEntity;
import com.zxsd.dao.interfaces.IAccessLogDao;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * even
 * 2019/4/4
 */
@Repository
public class AccessLogDaoImpl extends LogBaseDao implements IAccessLogDao {


    @Override
    public int insertAccess(final List<AccessLogEntity> accesslist) {
        StringBuffer sql = new StringBuffer();
        String table = "tb_access_log_"+new SimpleDateFormat("yyyy_MM").format(new Date());
        sql.append(" insert into ");
        sql.append(table).append(" (member_id,suid,ip,refer,url,access_time,type)");
        sql.append(" values(?,?,?,?,?,?,?)");
        this.getJdbcTemplatelog().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1,accesslist.get(i).getMember_id());
                preparedStatement.setString(2,accesslist.get(i).getSuid());
                preparedStatement.setString(3,accesslist.get(i).getIp());
                preparedStatement.setString(4,accesslist.get(i).getRefer());
                preparedStatement.setString(5,accesslist.get(i).getUrl());
                preparedStatement.setString(6,accesslist.get(i).getAccess_time());
                preparedStatement.setInt(7,accesslist.get(i).getType());
            }

            @Override
            public int getBatchSize() {
                return accesslist.size();
            }
        });
        return 0;
    }
}
