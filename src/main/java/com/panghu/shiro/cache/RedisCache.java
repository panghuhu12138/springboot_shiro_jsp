package com.panghu.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public V get(K k) throws CacheException {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        return (V) opsForValue.get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(k,v);
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
}