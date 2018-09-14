package com.yunkouan.cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 
 * @描述: 使用第三方内存数据库Redis作为Mybatis二级缓存
 * @版权: Copyright (c) 2016 
 * @作者: tphe05
 * @版本: 1.0 
 * @创建日期: 2018年1月11日 
 * @创建时间: 下午8:02:57
 */
public class RedisCache4Mybatis implements Cache {
	private static Log log = LogFactory.getLog(RedisCache4Mybatis.class);

	public static String PREFIX = "mybatis_";
    private static long EXPIRE_TIME = 12*60*60;
    private static JedisConnectionFactory jedisConnectionFactory;

    private String id;

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public RedisCache4Mybatis(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public void clear() {
        String pattern = PREFIX + "*";//前缀匹配
        JedisConnection connection = null;
        try {
        	connection = jedisConnectionFactory.getConnection();
            Set<byte[]> ks= connection.keys(pattern.getBytes());
            for(byte[] bys : ks){
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
    	long start = System.currentTimeMillis();
        if (key == null) {
            return null;
        }
        Object result = null;
        JedisConnection connection = null;
        try {
        	if(jedisConnectionFactory == null) {
//        		if(log.isDebugEnabled()) log.debug("getObject jedisConnectionFactory null");
        		return result;
        	}
            connection = jedisConnectionFactory.getConnection();
            byte[] xml = connection.get((PREFIX + String.valueOf(key)).getBytes());
            if(xml == null) return null;
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(xml);
//            result = JsonUtil.fromJson4Ali(new String(xml), Object.class);
//            XStream x = new XStream(new StaxDriver());
//            result = x.fromXML(new String(xml));
//            if(log.isInfoEnabled()) log.info(new StringBuffer("mybatis redis cache get[key:").append(key).append("]"));
        } catch (Exception e) {
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
        if(log.isDebugEnabled()) log.debug("redis get time:"+(System.currentTimeMillis()-start));
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    @Override
    public int getSize() {
        int result = 0;
        JedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = Integer.valueOf(connection.dbSize().toString());
        } catch (Exception e) {
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
    public void putObject(Object key, Object value) {
    	long start = System.currentTimeMillis();
        if (key == null) {
            return;
        }
        if (value == null) {
            return;
        }
        JedisConnection connection = null;
        try {
        	if(jedisConnectionFactory == null) {
//        		if(log.isDebugEnabled()) log.debug("putObject jedisConnectionFactory null");
        		return;
        	}
            connection = jedisConnectionFactory.getConnection();
//            String xml = JsonUtil.toJson4Ali(value);
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            byte[] result = serializer.serialize(value);
//            XStream x = new XStream(new StaxDriver());
//            String xml = x.toXML(value);
            byte[] key1 = (PREFIX + String.valueOf(key)).getBytes();
            connection.set(key1, result);
            connection.expire(key1, EXPIRE_TIME);
//            if(log.isInfoEnabled()) log.info(new StringBuffer("mybatis redis cache put[key:").append(key).append(",value:").append(value).append("]"));
        } catch (Exception e) {
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
        if(log.isDebugEnabled()) log.debug("redis put time:"+(System.currentTimeMillis()-start));
    }

    @Override
    public Object removeObject(Object key) {
        if (key == null) {
            return null;
        }
        JedisConnection connection = null;
        Object result = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result = connection.del((PREFIX + String.valueOf(key)).getBytes());
        } catch (Exception e) {
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

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache4Mybatis.jedisConnectionFactory = jedisConnectionFactory;
    }

}