package services;

import lombok.extern.log4j.Log4j2;
import models.Entry;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class RedisWithPrefixOptionCacheServiceImpl<K, V> implements CacheService<K, V> {
    private final RedisTemplate<String, V> redisTemplate;
    private String prefix;

    public RedisWithPrefixOptionCacheServiceImpl(RedisTemplate<String, V> redisTemplate, String prefix) {
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    public RedisWithPrefixOptionCacheServiceImpl(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String addPrefix(K key) {
        return prefix == null ? key.toString() : prefix + key.toString();
    }

    @Override
    public void save(K key, V value) {
        var keyPrefix = addPrefix(key);
        redisTemplate.opsForValue().set(keyPrefix, value);
    }

    @Override
    public void save(K key, V value, Integer expirationTime, TimeUnit timeUnit) {
        var keyPrefix = addPrefix(key);
        redisTemplate.opsForValue().set(keyPrefix, value, expirationTime, timeUnit);
    }

    @Override
    public Optional<V> get(K key) {
        var result = redisTemplate.opsForValue().get(addPrefix(key));
        return Optional.ofNullable(result);
    }

    @Override
    public boolean delete(K key) {
        return redisTemplate.delete(addPrefix(key));
    }

    @Override
    public boolean deleteMany(K... keys) {
        for (K key : keys) {
            var result = delete(key);
            if (!result) {
                log.warn("Key {} can not be removed", key);
            }
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
