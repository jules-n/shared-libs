package services;

import models.Entry;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class NoCacheImpl<K, V> implements CacheService<K, V>{

    @Override
    public void save(K key, V value, Integer expirationTime, TimeUnit timeUnit) {

    }

    @Override
    public void save(K key, V value) {

    }

    @Override
    public Optional<V> get(K key) {
        return Optional.empty();
    }

    @Override
    public boolean delete(K key) {
        return false;
    }

    @Override
    public boolean deleteMany(K... keys) {
        return false;
    }

    @Override
    public void saveMany(List<Entry<K, V>> entries, Integer expirationTime, TimeUnit timeUnit) {

    }

    @Override
    public void saveMany(List<Entry<K, V>> entries) {

    }
}
