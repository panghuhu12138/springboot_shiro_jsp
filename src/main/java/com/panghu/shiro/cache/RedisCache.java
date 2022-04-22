package com.panghu.shiro.cache;

import com.panghu.utils.SpringContextUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.util.Collection;
import java.util.Set;

/**
 * @author panghuhu
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private String cacheManagerName;

    public RedisCache() {
    }

    public RedisCache(String cacheManagerName) {
        this.cacheManagerName = cacheManagerName;
    }


    @Override
    public V get(K k) throws CacheException {
        RedisTemplate redisTemplate = this.getRedisTemplate();
        return (V) redisTemplate.opsForHash().get(this.cacheManagerName, k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        RedisTemplate redisTemplate = this.getRedisTemplate();
        redisTemplate.opsForHash().put(this.cacheManagerName, k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }



    private RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
