package com.zxsd.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {
	private  final static Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	//redis 连接池
	private static JedisPool jedisPool = null;
	//redis服务器ip
	private static final String JEDIS_POOL;
	//redis服务器端口号
	private static final int JEDIS_PORT;
	
	static{
		JEDIS_POOL = (String) AppEnv.getInstance().getProp("jedis.pool");
		JEDIS_PORT = AppEnv.getInstance().getIntProp("jedis.port",6379);
	}
	
	/**
     * 私有构造函数
     */
    private RedisClient(){}
    
    /** 
     *类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 
     *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。 
     */  
    private static class RedisUtilHolder{  
        /** 
         * 静态初始化器，由JVM来保证线程安全 
         */  
        private static RedisClient instance = new RedisClient();  
    }  
 
    public static RedisClient getInstance(){
        return RedisUtilHolder.instance;
    }
    
    //获取jedisPool连接池
    private static void initPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
        jedisPoolConfig.setMaxActive(-1);  
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
        jedisPoolConfig.setMaxIdle(100);  
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
        jedisPoolConfig.setMaxWait(1000*10);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
        jedisPoolConfig.setTestOnBorrow(true);  
        jedisPool = new JedisPool(jedisPoolConfig, JEDIS_POOL, JEDIS_PORT);
    }
    
    //获取jedis实例
    public static Jedis getJedis(){
    	if(jedisPool == null){
    		initPool();
    	}
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }
    
    /** 
     * 回收Jedis对象资源 
     *  
     * @param pool  
     * @param redis 
     */  
    public static void returnResource(Jedis jedis) {  
        if (jedis != null && jedisPool != null) {  
        	jedisPool.returnResource(jedis);  
        }  
    }  
    
    /** 
     * Jedis对象出异常的时候，回收Jedis对象资源 
     *  
     * @param pool  
     * @param redis 
     */  
    public static void returnBrokenResource(Jedis jedis) {  
        if (jedis != null && jedisPool != null) {  
        	jedisPool.returnBrokenResource(jedis);  
        }  
    }  
    
    /**
     * 删除redis
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public long del(String key){
    	Jedis jedis = getJedis();
    	return jedis.del(key);
    }
 
    /**
     * 添加String 
     * @param key
     * @param value
     * @param keyout 过期时间 秒
     * @return
     * @throws Exception
     */
    public String add(String key,String value,int keyout){
    	Jedis jedis = null;
    	String res = null;
    	try{
    		jedis = getJedis();
    		if(keyout > 0)
    		{
    			res = jedis.setex(key, keyout, value);
    		}else{
    			res = jedis.set(key,value);
    		}
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
    	return res;
    }
    
    
    /**
     * 添加对象 
     * @param key
     * @param value
     * @param keyout 过期时间 秒
     * @return
     * @throws Exception
     */
    public String add(String key,Object pojo,int keyout){
    	Jedis jedis = null;
    	String res = null;
    	try{
    		jedis = getJedis();
    		if(keyout > 0)
    		{
    			res = jedis.setex(key, keyout, JSON.toJSONString(pojo));
    		}else{
    			res = jedis.set(key,JSON.toJSONString(pojo));
    		}
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
    	return res;
    }
    
    /**
     * 添加list
     * @param key
     * @param value
     * @param keyout 过期时间 秒
     * @return
     * @throws Exception
     */
    public String add(String key,List list,int keyout){
    	Jedis jedis = null;
    	String res = null;
    	try{
    		jedis = getJedis();
    		if(keyout > 0)
    		{
    			res = jedis.setex(key, keyout, JSON.toJSONString(list));
    		}else{
    			res = jedis.set(key,JSON.toJSONString(list));
    		}
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
    	return res;
    }
    
    /**
     * 添加Set
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public long addSet(String key,String value){
    	Jedis jedis = null;
    	long res = 0l;
    	try{
    		jedis = getJedis();
    		res = jedis.sadd(key, value);
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
    	return res;
    }
 
    /**
     * 获取String
     * @param key
     * @return
     * @throws Exception
     */
    public String get(String key){
    	Jedis jedis = null;
    	String result = null;
    	try{
    		jedis = getJedis();
    		result = jedis.get(key);
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
        return result;
    }
    
    /**
     * 获取String
     * @param key
     * @return
     * @throws Exception
     */
    public Set<String> getSet(String key){
    	Jedis jedis = null;
    	Set<String> set= null;
    	try{
    		jedis = getJedis();
    		set = jedis.smembers(key);
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
        return set;
    }
    
    /**
     * 获取对象 
     * @param <T>
     * @param key
     * @return
     * @throws Exception
     */
    public <T> Object getPOJO(String key,Class<T> obj){
    	Jedis jedis = null;
    	String result = null;
    	try{
    		jedis = getJedis();
    		result = jedis.get(key);
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
		return JSON.parseObject(result, obj);
    }
    
    /**
     * 获取list
     * @param <T>
     * @param key
     * @return
     * @throws Exception
     */
    public <T> List<T> getPOJOList(String key,Class<T> obj){
        Jedis jedis = null;
    	String result = null;
    	try{
    		jedis = getJedis();
    		result = jedis.get(key);
    	}catch (Exception e) {
    		//释放redis对象  
        	jedisPool.returnBrokenResource(jedis);
			// TODO: handle exception
		}finally{
			jedisPool.returnResource(jedis);
		}
        return JSON.parseArray(result, obj);
    }

    /**
	 * 
	 * @Title Hset
	 * @Description TODO
	 * @param key
	 * @param keymap
	 * @param list
	 * @return
	 * @throws
	 * @author xt
	 * @date 2018年6月26日 下午5:04:59
	 * @version v1.0 更改记录(双层key)
	 */
	public Boolean HsetList(String key, String keymap, List<Integer> list) {
		
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(key, keymap, JsonFormatUtil.getJsonString4JavaList(list));
		} catch (Exception e) {
			// 释放redis对象
			jedisPool.returnBrokenResource(jedis);
		} finally {
			// 返还到连接池
			jedisPool.returnResource(jedis);
		}

		return true;
	}

	public List HgetList(String key, String keymap) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String hget = jedis.hget(key, keymap);
			if (hget != null) {
				Integer[] aa = JsonFormatUtil.getIntegerArray4Json(hget);
				List<Integer> asList = Arrays.asList(aa);
				List<Integer> arrList = new ArrayList(asList);
				return arrList;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 释放redis对象
			jedisPool.returnBrokenResource(jedis);
		} finally {
			// 返还到连接池
			jedisPool.returnResource(jedis);
		}
		return null;

	}
    public static void main(String[] args) {
    	RedisClient client = RedisClient.getInstance();
//    	List<UserTest> list = new ArrayList<UserTest>();
//    	UserTest test1 = new UserTest("aa1","bb1","cc1");
//    	client.add("user", test1);
//    	UserTest test2 = new UserTest("aa2","bb2","cc2");
//    	UserTest test3 = new UserTest("aa23","bb3","cc3");
//    	list.add(test1);
//    	client.add("list1", list);
//    	list.add(test2);
//    	list.add(test3);
//    	//client.add("list", JsonFormatUtil.getJsonString4JavaList(list));
//    	System.out.println(client.get("list"));
//    	List<UserTest> list1 = client.getPOJOList("list", UserTest.class);
//    	UserTest test = (UserTest) client.getPOJO("user", UserTest.class);
//    	String ss = client.get("settest1");
    	Set <String> str = client.getSet("filterSet");
    	Iterator<String> it = str.iterator();
    	while(it.hasNext()){
    		String strr  = it.next();
    		System.out.println(strr);
    		if(strr.equals("刺激"))
    		{
    			System.out.println("===========================================");
    			break;
    		}
    	}
//    	System.out.println(str.size());
//    	System.out.println(str.toString());
    }
}