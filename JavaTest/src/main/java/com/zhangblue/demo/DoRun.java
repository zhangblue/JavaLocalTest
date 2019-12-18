package com.zhangblue.demo;

public class DoRun {

  public static void main(String[] args) {
    String value = "To be, or not to be,--that is the question:--";

    String[] split = value.toLowerCase().split("\\W+");
    for (String s : split) {
      System.out.println(s);
    }


  }

}
