package com.zxsd.service.impl;

import com.zxsd.common.StringUtil;
import com.zxsd.dao.bean.CodeEntity;
import com.zxsd.dao.interfaces.ICodeLibraryDao;
import com.zxsd.dao.interfaces.ICodeLibraryWordDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 产品库定时更新
 * @ClassName CodeLibraryService   
 * @Description 
 * @author  zhy
 * @date 2019-3-12 上午10:44:28  
 * @version v1.0 2019-3-12 上午10:44:28
 * @history 历史修改记录 
 * <作者>　          <日期>　　           <版本>　       <描述> 
 *  zhy　　　2019-3-12 上午10:44:28　　V1.0　　 build此模块
 */
@Component
@EnableScheduling
public class CodeLibraryService {
	@Autowired
	private ICodeLibraryDao codeLibraryDao;
	@Autowired
	private ICodeLibraryWordDao codeLibraryWordDao;

	private static final Logger logger = LoggerFactory.getLogger(CodeLibraryService.class);
	private static final String SERVER_NAME;;
	static {
		String hn;
		try {
			hn = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error("无法获取主机名。",e);
			hn = System.currentTimeMillis()+"";
		}
		SERVER_NAME = hn;
	}
	/**
	 * 更新产品库、词条库
	 * @Title updateCodeLibaray 
	 * @Description TODO
	 * @throws ParseException
	 * @throws 
	 * @author  zhy
	 * @date 2019-3-20 下午2:31:05 
	 * @version v1.0
	 * 更改记录(有修改时记录，并对方法中修改的代码块加注释)
	 */
	@Scheduled(cron  = "0 0 4 * * ?")
	@Transactional
	public void updateCodeLibaray() throws ParseException{
		String codeType="1";
		String wordType="2";
		//抢占资源
		int res = codeLibraryDao.updateCodeUid(SERVER_NAME);
		if(res > 0){
			logger.info(SERVER_NAME+" begin==============");
			//产品展示库最大ID
			CodeEntity libaray = codeLibraryDao.getMaxCodeId(codeType);
			//产品库最大ID
			int codeMaxId = codeLibraryWordDao.getMaxCodeSearchId();
			//产品库最小ID
			int codeMinId = codeLibraryWordDao.getMinCodeSearchId();
			//产品展示库不为空
			if(!StringUtil.isNOrS(libaray.getId()) && !libaray.getId().equals(String.valueOf(codeMaxId))){
				codeMinId = Integer.parseInt(libaray.getId());
			}
			//清空产品展示库
			codeLibraryDao.deleteCodeLibary(codeType);
			//更新产品展示库
			List<Map<String,Object>> codelist = codeLibraryWordDao.getCodeShowList(codeMinId);
			codeLibraryDao.insertIntoCodeLibrary(codelist,codeType);
			
			//词条展示库最大ID
			CodeEntity wordlibaray = codeLibraryDao.getMaxCodeId(wordType);
			//词条库最大ID
			int wordMaxId = codeLibraryDao.getMaxWordId();
			//词条库最小ID
			int wordTypeMinId = codeLibraryDao.getMinWordId();
			//词条展示库不为空
			if(!StringUtil.isNOrS(wordlibaray.getId()) && !wordlibaray.getId().equals(String.valueOf(wordMaxId))){
				wordTypeMinId = Integer.parseInt(wordlibaray.getId());
			}
			//清空词条展示库
			codeLibraryDao.deleteCodeLibary(wordType);
			//更新词条展示库
			codeLibraryDao.updateCodeLibaryWord(wordTypeMinId);
		}
	}
}
