package cn.com.guava.utils;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author di.zhang
 * @date 2020/7/1
 * @time 21:46
 **/
public class TestDemo {

  public static void main(String[] args) {

    ConcurrentSkipListMap<Double, String> skipListMap = new ConcurrentSkipListMap<>();

    skipListMap.put(Double.valueOf(10), "a");
    skipListMap.put(Double.valueOf(20), "a");
    skipListMap.put(Double.valueOf(25), "a");
    skipListMap.put(Double.valueOf(30), "a");
    skipListMap.put(Double.valueOf(50), "a");
    skipListMap.put(Double.valueOf(40), "a");

    Entry<Double, String> doubleStringEntry = skipListMap.headMap(Double.valueOf(100)).lastEntry();

    System.out.println(doubleStringEntry.getKey());
    System.out.println(doubleStringEntry.getValue());


  }

}
