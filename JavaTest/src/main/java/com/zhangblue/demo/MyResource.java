package com.zhangblue.demo;

/**
 * 自定义资源类.
 * <p>
 * 自定义资源类必须实现AutoCloseable接口
 *
 * @author zhangdi
 */
public class MyResource implements AutoCloseable {

  public void sayHello() {
    System.out.println("hello");
  }

  @Override
  public void close() throws Exception {
    System.out.println("Resource is close");
  }
}
