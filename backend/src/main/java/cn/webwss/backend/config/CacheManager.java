package cn.webwss.backend.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CacheManager {
    private static final Cache<String, String> cache = Caffeine.newBuilder()
            .initialCapacity(10)
            .expireAfterWrite(7200, TimeUnit.SECONDS)
            .build();

    public String getAccessToken() {
        return cache.getIfPresent("accessToken");
    }

    public void setAccessToken(String accessToken) {
        cache.put("accessToken", accessToken);
    }
}
