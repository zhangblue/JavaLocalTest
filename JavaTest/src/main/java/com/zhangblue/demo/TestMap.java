package com.zhangblue.demo;

import com.alibaba.fastjson.JSONObject;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author di.zhang
 * @date 2020/8/31
 * @time 14:00
 **/
public class TestMap {

  public static void main(String[] args) {
    ConcurrentHashMap<String,TestBean> map = new ConcurrentHashMap<>();

    TestBean testBean = new TestBean();
    testBean.getNames().add("a");
    map.put("a",testBean);





    System.out.println(JSONObject.toJSONString(map));






  }

}

class TestBean{
  private Set<String> names = new HashSet<>();

  public Set<String> getNames() {
    return names;
  }

  public void setNames(Set<String> names) {
    this.names = names;
  }
}
