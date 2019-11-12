package com.zxsd.dao.interfaces;

import java.util.Map;

/**
 * even
 * 2019/4/16
 */
public interface IBarcodeDao {
    /**
     * 查询条码信息
     * @param code
     * @return
     */
    Map<String,Object> getCodedAttrCode(String code);
}
