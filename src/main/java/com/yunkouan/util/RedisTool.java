package com.yunkouan.util;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import redis.clients.jedis.Jedis;

public class RedisTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean getLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    private static final Long RELEASE_SUCCESS = 1L;
    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /** 
    * @Title: lock 
    * @Description: 加锁
    * @auth tphe06
    * @time 2018 2018年9月11日 下午3:59:02
    * @param host
    * @param port
    * @param password
    * @param dbIndex
    * @param lockName
    * @return
    * RedissonClient
    */
    public static RedissonClient lock(String host, String port, String password, int dbIndex, String lockName) {
    	Config config = new Config();
    	config.useSingleServer()
    	    .setConnectionPoolSize(1000)
    		.setDatabase(dbIndex)
    		.setPassword(password)
    		.setAddress("redis://"+host+":"+port);
    	RedissonClient redisson = Redisson.create(config);
    	RLock lock = redisson.getLock(lockName);
    	lock.lock(20, TimeUnit.SECONDS);
    	return redisson;
    }
    /** 
    * @Title: unLock 
    * @Description: 解锁
    * @auth tphe06
    * @time 2018 2018年9月11日 下午3:59:33
    * @param redisson
    * @param lockName
    * void
    */
    public static void unLock(RedissonClient redisson, String lockName) {
    	redisson.getLock(lockName).unlock();
//    	redisson.shutdown();
    }

    public static void main(String[] args) {
      String host = "127.0.0.1";
      String port = "6379";
      int timeout = 20;
      String pass = "Zyzh#123456";

    	Config config = new Config();
//    	config.setUseLinuxNativeEpoll(false);
//    	config.setTransportMode(TransportMode.EPOLL);
    	config.useSingleServer()
    		.setDatabase(1)
    		.setPassword(pass)
    		.setAddress("redis://"+host+":"+port);
//    	config.useClusterServers()
//    		  .setPassword("Zyzh#123456")
//    		  .setScanInterval(2000) // cluster state scan interval in milliseconds
//    	      .addNodeAddress("redis://192.168.1.103:6379");// use "rediss://" for SSL connection

    	RedissonClient redisson = Redisson.create(config);
    	RLock lock = redisson.getLock("anyLock");
    	lock.lock(timeout, TimeUnit.SECONDS);
//    	lock.unlock();
    	redisson.shutdown();
//    	JedisPoolConfig config = new JedisPoolConfig();
//        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
//        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//        config.setMaxTotal(Integer.parseInt("1000"));
//        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//        config.setMaxIdle(Integer.parseInt("600"));
//        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//        config.setMaxWaitMillis(Long.parseLong("10000"));
//        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//        config.setTestOnBorrow(true);
// 
//        String host = "127.0.0.1";
//        String port = "6379";
//        String timeout = "15000";
//        String pass = "Zyzh#123456";
//        JedisPool jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), pass);
//    	Jedis client = jedisPool.getResource();
//    	jedisPool.close();
//
//    	String key = "redis_key";
//    	String value = "12345678";
//    	for(int i=0; i<5; ++i) {
//	    	boolean isLock = getLock(client, key, value, 60*1000);
//	    	System.out.println(isLock);
//    	}
//    	boolean isRelease = releaseLock(client, key, value);
//    	System.out.println(isRelease);
    }
}