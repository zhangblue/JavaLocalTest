package cn.com.test;

import com.alibaba.fastjson.annotation.JSONField;

public class DevinfoReplenishModel {

  @JSONField(name = "os_version")
  private String osVersion;
  @JSONField(name = "model")
  private String model;
  @JSONField(name = "manufacturer")
  private String manufacturer;
  @JSONField(name = "imei")
  private String[] imei;
  @JSONField(name = "imsi")
  private String[] imsi;
  @JSONField(name = "mac")
  private String mac;
  @JSONField(name = "bht_mac")
  private String blueToothMac;
  @JSONField(name = "android_id")
  private String androidId;

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public String[] getImsi() {
    return imsi;
  }

  public void setImsi(String[] imsi) {
    this.imsi = imsi;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getBlueToothMac() {
    return blueToothMac;
  }

  public void setBlueToothMac(String blueToothMac) {
    this.blueToothMac = blueToothMac;
  }

  public String getAndroidId() {
    return androidId;
  }

  public void setAndroidId(String androidId) {
    this.androidId = androidId;
  }

  public String[] getImei() {
    return imei;
  }

  public void setImei(String[] imei) {
    this.imei = imei;
  }
}
