package cn.com.zhangblue.log4j2;

import test.enmu.TestEnum;

public class TestClass {

  private String name;
  private TestEnum testEnum;


  public TestClass(String name, TestEnum testEnum) {
    this.name = name;
    this.testEnum = testEnum;
  }

  public String getName() {
    return name;
  }

  public TestEnum getTestEnum() {
    return testEnum;
  }
}
