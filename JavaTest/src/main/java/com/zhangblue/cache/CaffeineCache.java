package com.zhangblue.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author di.zhang
 * @date 2020/11/9
 * @time 17:05
 **/
public class CaffeineCache {

  public static void main(String[] args) {

    CaffeineCache caffeineCache = new CaffeineCache();

    String cc = caffeineCache.manulOperator("cc");
    System.out.println(cc);

  }


  public String manulOperator(String key) {
    Cache<String, String> cache = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
        .expireAfterAccess(1, TimeUnit.SECONDS).maximumSize(10).build();

    cache.get(key, t -> setValue(key).apply(key));

    String ifPresent = cache.getIfPresent(key);

    return ifPresent;
  }

  public Function<String, String> setValue(String key) {

    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return t -> key + "value";
  }


}
