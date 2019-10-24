package com.company.services;


import com.company.StarPlan;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Component
//@Scope("prototype")
public class CacheService
{
    private Cache<Integer, StarPlan> cache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build();

    public ConcurrentMap getMap(){
        return this.cache.asMap();
    }
    public StarPlan getStarPlan(Integer key){
        return cache.getIfPresent(key);
    }

    public void putStarPlan(Integer key, StarPlan starPlan){
        cache.put(key, starPlan);
    }

}
