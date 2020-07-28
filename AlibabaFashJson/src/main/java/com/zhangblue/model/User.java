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

  @JSONField(name = "curriculum")
  private Curriculum curriculum;

  public User(String name, long agent, String classes, Curriculum curriculum) {
    this.name = name;
    this.agent = agent;
    this.classes = classes;
    this.curriculum = curriculum;
  }

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

  public Curriculum getCurriculum() {
    return curriculum;
  }

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }
}
