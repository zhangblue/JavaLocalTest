package com.zhangblue.anno;

/**
 * @author zhangdi
 * @description: 事件类型
 * @date 2020/12/7 下午5:59
 * @since
 **/
public enum EventTypeError {

  ERROR("error", "异常中断"),
  CLOSE("close", "正常关闭");

  private String value;

  private String desc;

  EventTypeError(String value, String desc) {
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
