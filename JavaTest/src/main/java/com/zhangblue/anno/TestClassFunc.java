package com.zhangblue.anno;

/**
 * @author zhangdi
 * @description: 测试注解类
 * @date 2020/12/7 下午5:56
 * @since
 **/
public class TestClassFunc {

  @TAnno
  public void func1(EventTypeError eventType) {
    System.out.println("this is func1");
  }

  @TAnno
  public void func2(EventTypeOK eventTypeOK) {
    System.out.println("this is func2");
  }


  @TAnno
  public void func3(String name) {
    System.out.println("this is func3");
  }

  @TAnno
  public void func4(String name) {
    System.out.println("this is func4");
  }
}
