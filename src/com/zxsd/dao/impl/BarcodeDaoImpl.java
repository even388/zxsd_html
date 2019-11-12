package com.zxsd.dao.impl;

import com.zxsd.dao.base.CodeBaseDao;
import com.zxsd.dao.interfaces.IBarcodeDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * even
 * 2019/4/16
 */
@Repository
public class BarcodeDaoImpl extends CodeBaseDao implements IBarcodeDao {

    @Override
    public Map<String, Object> getCodedAttrCode(String code) {
        Map reuslt = null;
        String tabName = "tb_barcode_"+code.substring(code.length()-1);
        List list = this.getJdbcTemplatecode().queryForList("select * from "+tabName+" where goodsCode=?",new Object[]{code});
        if(list!=null&&list.size()>0){
            reuslt = (Map) list.get(0);
        }
        return reuslt;
    }
}
