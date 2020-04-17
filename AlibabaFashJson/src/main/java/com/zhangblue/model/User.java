package com.zhangblue.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhangblue.deserializer.StringDefaultValueDeserializer;

/**
 * @author zhangdi
 */
public class User {

  @JSONField(name = "name", deserializeUsing = StringDefaultValueDeserializer.class)
  private String name;
  @JSONField(name = "age")
  private long agent;
  @JSONField(name = "classes", deserializeUsing = StringDefaultValueDeserializer.class)
  private String classes;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getAgent() {
    return agent;
  }

  public void setAgent(long agent) {
    this.agent = agent;
  }

  public String getClasses() {
    return classes;
  }

  public void setClasses(String classes) {
    this.classes = classes;
  }
}
