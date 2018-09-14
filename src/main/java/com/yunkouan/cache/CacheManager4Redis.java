package com.yunkouan.cache;

import java.util.Collection;

public class CacheManager4Redis extends org.springframework.cache.support.AbstractCacheManager {
	private Collection<? extends RedisCache4Spring> caches;

    public void setCaches(Collection<? extends RedisCache4Spring> caches) {
        this.caches = caches;
    }

    @Override
	protected Collection<? extends RedisCache4Spring> loadCaches() {
		return caches;
	}

}
