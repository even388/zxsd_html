package com.zxsd.dao.interfaces;

import java.util.List;
import java.util.Map;

/**
 * even
 * 2019/6/25
 */
public interface ICodeLibraryWordDao {

    int getMaxCodeSearchId();

    int getMinCodeSearchId();

    List<Map<String, Object>> getCodeShowList(int id);
}
