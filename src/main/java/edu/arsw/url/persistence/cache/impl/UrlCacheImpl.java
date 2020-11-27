package edu.arsw.url.persistence.cache.impl;
import edu.arsw.url.persistence.cache.UrlCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("UrlCacheImpl")
public class UrlCacheImpl implements UrlCache {
    public static final String URL_KEY = "edu:arsw:url";
    private HashOperations hashOperations;
    private ValueOperations valueOperations;
    private RedisTemplate redisTemplate;

    public UrlCacheImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        this.valueOperations = this.redisTemplate.opsForValue();
    }

    @Override
    public boolean exists(String hash) {
        return redisTemplate.hasKey(getKey(hash));
    }

    @Override
    public String get(String hash) {
        return (String) valueOperations.get(getKey(hash));
    }

    @Override
    public void set(String hash, String payload) {
        System.out.println(hash);
        System.out.println(payload);
        valueOperations.set(getKey(hash),payload);
    }

    private String getKey(String hash) {
        System.out.println(String.format("%s:%s", URL_KEY, hash));
        return String.format("%s:%s", URL_KEY, hash);
    }
}
