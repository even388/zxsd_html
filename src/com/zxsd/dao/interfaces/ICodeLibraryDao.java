package com.zxsd.dao.interfaces;

import com.zxsd.dao.bean.CodeEntity;

import java.util.List;
import java.util.Map;

public interface ICodeLibraryDao {
	
	boolean updateCodeLibaryWord(int codeId);
	
	boolean deleteCodeLibary(String type);
	
	CodeEntity getMaxCodeId(String type);
	
	int updateCodeUid(String suid);
	
	boolean clearCodeUid();

	int getMaxWordId();

	int getMinWordId();

	boolean insertIntoCodeLibrary(List<Map<String, Object>> list,String type);

	List<Map<String, Object>> queryCatetoryByweek();
}
