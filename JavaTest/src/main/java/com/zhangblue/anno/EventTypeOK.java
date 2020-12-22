package com.zhangblue.anno;

/**
 * @author zhangdi
 * @description: 事件类型
 * @date 2020/12/7 下午5:57
 * @since
 **/
public enum EventTypeOK {

  LOGIN("login", "登录"),
  HEALTHY("healthy", "健康监测");

  private String value;

  private String desc;

  EventTypeOK(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public String getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }


}
