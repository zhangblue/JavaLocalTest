package com.zhangblue.demo;

public class TestFunciont {


  @TestAnn(name = "imei")
  public static String getImei(String a) {
    System.out.println("aaa-" + a);
    return a;
  }

  @TestAnn(name = "imsi")
  public static String alksjdkahsdjajskhdjk(String a) {
    System.out.println("bbb-" + a);
    return a;
  }


}
