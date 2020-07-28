package com.zhangblue.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author di.zhang
 * @date 2020/5/14
 * @time 20:18
 **/
public class Curriculum {

  @JSONField(name = "c_name")
  private String name;
  @JSONField(name = "c_id")
  private String id;
  @JSONField(name = "test_clo")
  private String testClo;

  public Curriculum(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public Curriculum() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTestClo() {
    return testClo;
  }

  public void setTestClo(String testClo) {
    this.testClo = testClo;
  }
}
