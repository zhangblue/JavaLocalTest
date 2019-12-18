package com.zhangblue.demo;

/**
 * try-with-resource 演示
 *
 * @author zhangdi
 */
public class TryWithResourceDemo {

  public static void main(String[] args) {
    try (MyResource myResource = new MyResource()) {
      myResource.sayHello();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
