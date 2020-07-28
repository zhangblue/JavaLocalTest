package com.zhangblue.demo;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @author zhangdi
 */
public class CurrentCache<K, V> extends LinkedHashMap<K, V> {

  private int maxSize = 0;

  public CurrentCache(int maxSize) {
    super(maxSize, 0.75f, true);
    this.maxSize = maxSize;
  }

  @Override
  protected boolean removeEldestEntry(Entry<K, V> eldest) {
    return size() >= maxSize + 1;
  }
}
