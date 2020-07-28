package com.zhangblue.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @author di.zhang
 * @date 2020/4/23
 * @time 13:04
 **/
public class TestDD {

  public static void main(String[] args) {

    Set<String> set = new HashSet<>(Arrays.asList("a","b"));

    System.out.println(set.contains(null));

    Set<String> set2=null;




  }


}

class SortByType implements Comparator<Long> {

  private String sortBy = "desc";

  public SortByType(String sortBy) {
    this.sortBy = sortBy;
  }

  @Override
  public int compare(Long o1, Long o2) {
    if ("desc".equals(sortBy)) {
      return o1.longValue() - o2.longValue() > 0 ? 1 : -1;
    } else {
      return o1.longValue() - o2.longValue() > 0 ? -1 : 1;
    }
  }
}
