package com.zhangblue.model;

/**
 * @author di.zhang
 * @date 2020/9/1
 * @time 15:46
 **/
public class Entity {

  private Integer id;
  private String name;
  private String[] value;

  public Entity(Integer id, String name, String[] value) {
    this.id = id;
    this.name = name;
    this.value = value;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getValue() {
    return value;
  }

  public void setValue(String[] value) {
    this.value = value;
  }
}
