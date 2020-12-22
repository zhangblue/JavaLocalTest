package cn.com.guava.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author di.zhang
 * @date 2020/7/1
 * @time 21:46
 **/
public class TestDemo {

  public static void main(String[] args) {

    Cache<String,String> cache = CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(10,
        TimeUnit.MINUTES).expireAfterAccess(Duration.ofMillis(100)).build();

    try {
      cache.get("aaaaa", new Callable<String>() {
        @Override
        public String call() throws Exception {
          return "yes";
        }
      });
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

  }




}
