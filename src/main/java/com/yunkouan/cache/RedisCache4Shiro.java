package com.yunkouan.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;


/** 
* @Description: 
* @author tphe06
* @date 2018年2月28日 下午3:32:34  
*/
public class RedisCache4Shiro extends AbstractSessionDAO {
	private static Log log = LogFactory.getLog(RedisCache4Shiro.class);

	public static String PREFIX = "shiro_";
    private static JedisConnectionFactory jedisConnectionFactory;
    private static long EXPIRE_TIME = 15*60;

	@Override
	public void update(Session session) throws UnknownSessionException {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
		if(log.isDebugEnabled()) log.debug(new StringBuilder("update:").append(session.getId()).append(":"));
		set(session.getId(), session);
//		storeSession(session.getId(), session);
	}

	@Override
	public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if(log.isDebugEnabled()) log.debug(new StringBuilder("delete:").append(id).append(":"));
        JedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            connection.del((PREFIX + id).getBytes());
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.getMessage(), e);
        } finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isDebugEnabled()) log.debug(e.getMessage(), e);
            	}
            }
        }
    }

	@Override
	public Collection<Session> getActiveSessions() {
        JedisConnection connection = null;
        Collection<Session> list = new Vector<Session>();
        try {
            connection = jedisConnectionFactory.getConnection();
            Set<byte[]> keys = connection.keys((PREFIX + "*").getBytes());
            for(byte[] key : keys){
            	String id = new String(key);
            	id = id.replaceFirst(PREFIX, "");
            	Session s = doReadSession(id);
            	list.add(s);
            }
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.getMessage(), e);
        } finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isDebugEnabled()) log.debug(e.getMessage(), e);
            	}
            }
        }
        return list;
//		return Collections.emptySet();
    }

	@Override
	protected Serializable doCreate(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        storeSession(sessionId, session);
        return sessionId;
    }
    private Session storeSession(Serializable id, Session session) {
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        }
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        if(log.isDebugEnabled()) log.debug(new StringBuilder("store_new:").append(id).append(":"));
        Session old = doReadSession(id);
        if(old != null) {
        	if(log.isInfoEnabled()) log.debug(new StringBuilder("sotre_old:").append(id).append(":"));
        	return old;
        }
        set(id, session);
        return session;
    }
    private void set(Serializable id, Session session) {
        JedisConnection connection = null;
        try {
        	if(log.isDebugEnabled()) log.debug(new StringBuilder("set:").append(id).append(":"));
            connection = jedisConnectionFactory.getConnection();
//            XStream x = new XStream(new StaxDriver());
//            String xml = x.toXML(session);
//            String xml = JsonUtil.toJson4Ali(session);
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            byte[] s = serializer.serialize(session);
            byte[] key = (PREFIX + id).getBytes();
            connection.set(key, s);
            connection.expire(key, EXPIRE_TIME);
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.getMessage(), e);
        } finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isDebugEnabled()) log.debug(e.getMessage(), e);
            	}
            }
        }
    }

	@Override
	protected Session doReadSession(Serializable id) {
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        }
		Session result = null;
        JedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            byte[] value = connection.get((PREFIX + id).getBytes());
            if(value == null) return null;
//            XStream x = new XStream(new StaxDriver());
//            result = (Session) x.fromXML(new String(value));
//            result = JsonUtil.fromJson4Ali(new String(value), Session.class);
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
            result = (Session) serializer.deserialize(value);
            if(log.isDebugEnabled()) log.debug(new StringBuilder("get:").append(id).append(":"));
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.getMessage(), e);
        } finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isDebugEnabled()) log.debug(e.getMessage(), e);
            	}
            }
        }
        return result;
    }

	protected static void clear() {
        String pattern = PREFIX + "*";//前缀匹配
        JedisConnection connection = null;
        try {
        	connection = jedisConnectionFactory.getConnection();
	        Set<byte[]> ks= connection.keys(pattern.getBytes());
	         for(byte[] bys : ks){
	        	 connection.del(bys);
	         }
        } catch (Exception e) {
        	if(log.isErrorEnabled()) log.error(e.getMessage(), e);
        } finally {
            if (connection != null && !connection.isClosed()) {
            	try {
            		connection.close();
            	} catch(DataAccessException e) {
            		if(log.isDebugEnabled()) log.debug(e.getMessage(), e);
            	}
            }
        }
	}

	/**
	 * @param jedisConnectionFactory the jedisConnectionFactory to set
	 */
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache4Shiro.jedisConnectionFactory = jedisConnectionFactory;
	}

}