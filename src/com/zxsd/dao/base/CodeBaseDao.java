package com.zxsd.dao.base;

import com.zxsd.common.Constants;
import com.zxsd.common.ExceptionUtil;
import com.zxsd.common.JsonFormatUtil;
import com.zxsd.common.ServiceException;
import com.zxsd.dao.bean.LogFormatHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class CodeBaseDao<T> {

    protected static Logger logger = LoggerFactory.getLogger(CodeBaseDao.class);

    private JdbcTemplate jdbcTemplatecode;

    public JdbcTemplate getJdbcTemplatecode() {
        return jdbcTemplatecode;
    }

    public void setJdbcTemplatecode(JdbcTemplate jdbcTemplatecode) {
        this.jdbcTemplatecode = jdbcTemplatecode;
    }

    private String exceptionMsg = "数据库操作异常";

    private DataSource dataSourcecode;

    public DataSource getDataSourcecode() {
        return dataSourcecode;
    }
    @Autowired
    public void setDataSourcecode(DataSource dataSourcecode) {
        this.dataSourcecode = dataSourcecode;
        jdbcTemplatecode = new JdbcTemplate(dataSourcecode);
    }

    public void exectue(String sql) throws ServiceException {
        try{
            this.getJdbcTemplatecode().execute(sql);
        }catch(Exception e){
			/*e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
        }

    }
    //更新数据库
    public boolean update(String sql,final Object... values){
        try{
            int result = this.getJdbcTemplatecode().update(sql, values);
            if(result < 1){
                return false;
            }
            return true;
        }catch(Exception e){
			/*e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return false;
        }

    }
    //批量更新数据库
    public boolean bachUpdate(String sql,List<Object[]> batchArgs){
        try{
            int[] result = this.getJdbcTemplatecode().batchUpdate(sql, batchArgs);
            if(result != null){
                if(result.length == batchArgs.size())
                {
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(Exception e){
			/*e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return false;
        }

    }

    //查询总数
    protected long getCount(String sql,Object [] args){
        try{
            String sqlStr = "select count(*) from ("+sql+") OLD_TABLE";
            long res = this.getJdbcTemplatecode().queryForLong(sqlStr,args);
            return res;
        }catch(Exception e){
		/*	e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return 0;
        }
    };


    //查询集合
    public List queryList(String sql, Object [] args, RowMapper<Object> rowMapper){
        try{
            return this.getJdbcTemplatecode().query(sql, args, rowMapper);
        }catch(Exception e){
			/*e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return null;
        }
    }
    //查询集合
    public  List queryList(String sql,Object [] args){
        try{
            return this.getJdbcTemplatecode().queryForList(sql, args);
        }catch(Exception e){
		/*	e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return null;
        }
    }

    //查询对象
    public  Object queryObject(String sql,Object [] args,RowMapper<Object> rowMapper){
        try{
            Object t= null;
            List<Object> list =  this.getJdbcTemplatecode().query(sql, args, rowMapper);
            if(list != null && list.size()>0)
            {
                t = list.get(0);
            }
            return t;
        }catch(Exception e){
		/*	e.printStackTrace();
			logger.error(exceptionMsg, e.getCause());
			throw new ServiceException(exceptionMsg);*/
            addLog(e,sql);
            return null;
        }
    }
    public static void addLog(Exception e,String SQL){
        LogFormatHead loghead = new LogFormatHead(Constants.ZXSD_HTML,Constants.LOG_ERROR,Constants.LOG_OPERA_SYS);
        loghead.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        loghead.setParames("SQL:"+SQL+";"+ ExceptionUtil.getStackMsg(e)+";CAUSE:"+ExceptionUtil.toStringLog(e.fillInStackTrace()));
        logger.error(JsonFormatUtil.getLogJsonString(loghead));
    }

}
