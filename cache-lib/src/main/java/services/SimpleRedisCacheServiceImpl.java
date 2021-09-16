package services;

import models.Entry;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleRedisCacheServiceImpl<K, V> implements CacheService<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    public SimpleRedisCacheServiceImpl(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(K key, V value, Integer expirationTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expirationTime, timeUnit);
    }

    @Override
    public void save(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean delete(K key) {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean deleteMany(K... keys) {
        for (K key : keys) {
           delete(key);
        }
        return true;
    }

    @Override
    public void saveMany(List<Entry<K, V>> entries, Integer expirationTime, TimeUnit timeUnit) {
        entries.forEach(
                entry -> save(entry.getKey(), entry.getValue(), expirationTime, timeUnit)
        );
    }

    @Override
    public void saveMany(List<Entry<K, V>> entries) {
        entries.forEach(
                entry -> save(entry.getKey(), entry.getValue())
        );
    }
}
