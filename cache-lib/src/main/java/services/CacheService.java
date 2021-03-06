package services;

import models.Entry;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface CacheService<K, V> {
    void save(K key, V value, Integer expirationTime, TimeUnit timeUnit);
    void save(K key, V value);
    Optional<V> get(K key);
    boolean delete(K key);
    boolean deleteMany(K... keys);
    void saveMany(List<Entry<K, V>> entries, Integer expirationTime, TimeUnit timeUnit);
    void saveMany(List<Entry<K, V>> entries);
}
