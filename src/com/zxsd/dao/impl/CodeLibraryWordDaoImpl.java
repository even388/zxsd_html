package com.zxsd.dao.impl;

import com.zxsd.dao.base.CodeBaseDao;
import com.zxsd.dao.interfaces.ICodeLibraryWordDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * even
 * 2019/6/25
 */
@Repository
public class CodeLibraryWordDaoImpl extends CodeBaseDao implements ICodeLibraryWordDao {


    @Override
    public int getMaxCodeSearchId() {
        return this.getJdbcTemplatecode().queryForInt("select max(id) from tb_coded_attr_show_search");
    }

    @Override
    public int getMinCodeSearchId() {
        return this.getJdbcTemplatecode().queryForInt("select min(id) from tb_coded_attr_show_search");
    }

    @Override
    public List<Map<String, Object>> getCodeShowList(int id) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select id,barcode from tb_coded_attr_show_search where publish_status=1 and id>=? limit 0,100");
        return this.queryList(sql.toString(),new Object[]{id});
    }
}
