package com.example.demo.services;

import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.google.common.cache.Cache;

import java.util.concurrent.TimeUnit;

@Service
public class CacheManager {

    @Bean
    Cache<String, String> initCache(){

        return CacheBuilder.newBuilder()
                .maximumSize(100) // Maximum size of the cache
                .expireAfterAccess(30, TimeUnit.MINUTES) // Entries expire after 30 minutes of no access
                .removalListener(notification ->
                        System.out.println("Removed entry: " + notification.getKey() + "=" + notification.getValue()))
                .build();
    }
}
