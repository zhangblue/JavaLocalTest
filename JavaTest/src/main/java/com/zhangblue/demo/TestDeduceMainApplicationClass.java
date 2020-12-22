package com.zhangblue.demo;

/**
 * @author di.zhang
 * @date 2020/8/18
 * @time 14:05
 **/
public class TestDeduceMainApplicationClass {

  public static void main(String[] args) {
    StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();

    for (StackTraceElement stackTraceElement : stackTrace) {
      System.out.println(stackTraceElement.getMethodName());
      System.out.println(stackTraceElement.getClassName());

      System.out.println("----------");
    }
  }

}
