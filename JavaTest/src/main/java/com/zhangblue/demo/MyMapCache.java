package com.zhangblue.demo;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author zhangdi
 */
public class MyMapCache<K, V> extends LinkedHashMap<K, V> {

  private static final long serialVersionUID = 4504158311663914052L;

  private int maxCacheSize;

  public MyMapCache(int maxCacheSize) {
    this.maxCacheSize = maxCacheSize;
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return size() >= maxCacheSize + 1;
  }
}
