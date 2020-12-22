package com.zhangblue.poi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author di.zhang
 * @Description: 转化为json的格式
 * @date 2020/12/1 上午10:42
 **/
public class ValueModel {

  @JSONField(name = "package")
  private String pkgName;
  @JSONField(name = "name")
  private String appName;
  @JSONField(name = "platform")
  private String platform;
  @JSONField(name = "description")
  private String description;


  public ValueModel(String pkgName, String appName, String platform, String description) {
    this.pkgName = pkgName;
    this.appName = appName;
    this.platform = platform;
    this.description = description;
  }


  public String getPkgName() {
    return pkgName;
  }

  public void setPkgName(String pkgName) {
    this.pkgName = pkgName;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
