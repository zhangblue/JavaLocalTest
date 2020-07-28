package com.zhangblue.demo;

public class InterfaceDemo001 {

  @FunctionalInterface
  interface InterfaceDemo {

    String myPrinterName(String name, int age);

    default int agePlusOne(int age) {
      return age + 1;
    }
  }

  public String printSomething(String name, int age, InterfaceDemo interfaceDemo) {
    return interfaceDemo.myPrinterName(name, age);
  }

  public static void main(String[] args) {
    InterfaceDemo interfaceDemo = (name, age) -> {
      String result = name + "---" + age;
      return result;
    };

    String zhangsan = new InterfaceDemo001().printSomething("zhangsan", 10, interfaceDemo);
    System.out.println(zhangsan);
  }

}
