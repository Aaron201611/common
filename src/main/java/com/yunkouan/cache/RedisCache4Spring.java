package com.yunkouan.cache;

import java.util.Set;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.yunkouan.util.JsonUtil;

public class RedisCache4Spring implements Cache {
	private static Log log = LogFactory.getLog(RedisCache4Spring.class);

	public static String PREFIX = "spring_";
	private static JedisConnectionFactory jedisClient;
	private String name;

	@Override
	public String getName() {
		return name;
	}
	@Override
	public Object getNativeCache() {
		return jedisClient.getConnection();
	}
	@Override
	public ValueWrapper get(Object key) {
		if(key == null) return null;
		JedisConnection connection = null;
		ValueWrapper result = null;
		try {
	        //修改key的值，与name绑定
	        String _key = PREFIX+String.valueOf(key);//key必须是String 类型
	        //从redis获取数据
	        connection = jedisClient.getConnection();
	        byte[] val = connection.get(_key.getBytes());
	        Object thevalue = val;
	        if (thevalue != null) {
	            result = new SimpleValueWrapper(thevalue);
	        }
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isErrorEnabled()) log.error(e.getMessage(), e);
            	}
            }
        }
        return result;
	}
	@Override
	public <T> T get(Object key, Class<T> type) {
		if(key == null) return null;
		String _key = PREFIX+String.valueOf(key);//key必须是String 类型
		JedisConnection connection = null;
		byte[] val = null;
		try {
			connection = jedisClient.getConnection();
			val = connection.get(_key.getBytes());
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isErrorEnabled()) log.error(e.getMessage(), e);
            	}
            }
        }
		if(val == null) return null;
		return JsonUtil.fromJson(new String(val), type);
	}
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
//		if(log.isInfoEnabled()) log.info(new StringBuffer("spring redis cache get3[key:").append(key).append("]"));
		return null;
	}
	@Override
	public void put(Object key, Object value) {
		if(key == null || value == null) return;
		String _key = PREFIX+String.valueOf(key);//key必须是String 类型
		JedisConnection connection = null;
		try {
			connection = jedisClient.getConnection();
			connection.set(_key.getBytes(), JsonUtil.toJson(value).getBytes());
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isErrorEnabled()) log.error(e.getMessage(), e);
            	}
            }
        }
	}
	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		this.evict(key);
		this.put(key, value);
//		if(log.isInfoEnabled()) log.info(new StringBuffer("spring redis cache put2[key:").append(key).append(",value:").append(value).append("]"));
		return null;
	}
	@Override
	public void evict(Object key) {
		if(key == null) return;
        //修改key的值，与name绑定
        String _key = PREFIX+String.valueOf(key);//key必须是String 类型
        //从redis中删除
		JedisConnection connection = null;
		try {
			connection = jedisClient.getConnection();
			connection.del(_key.getBytes());
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isErrorEnabled()) log.error(e.getMessage(), e);
            	}
            }
        }
	}
	@Override
	public void clear() {
        //清楚缓存，需要根据Cache的name属性，在redis中模糊查询相关key值的集合，并全部删除
        String pattern = PREFIX + "*";//前缀匹配
		JedisConnection connection = null;
		try {
			connection = jedisClient.getConnection();
	        Set<byte[]> ks= connection.keys(pattern.getBytes());
	         for(byte[] bys:ks){
	        	 connection.del(bys);
	         }
		} catch(Exception e) {
			if(log.isErrorEnabled()) log.error(e.getMessage(), e);
		} finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isErrorEnabled()) log.error(e.getMessage(), e);
            	}
            }
        }
	}

	/**
	 * @param jedisClient the jedisClient to set
	 */
	public static void setJedisClient(JedisConnectionFactory jedisClient) {
		RedisCache4Spring.jedisClient = jedisClient;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}