package com.zxsd.dao.interfaces;

import com.zxsd.dao.bean.AccessLogEntity;

import java.util.List;

/**
 * even
 * 2019/4/4
 */
public interface IAccessLogDao {
    /**
     * 插入访问日志
     * @param access
     * @return
     */
    int insertAccess(List<AccessLogEntity> access);
}
