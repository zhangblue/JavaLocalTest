package cn.com.test;

import org.junit.Test;

public class TestDemo3 {

  @Test
  public void testAcs() {
    int i = 0;

    assert i > 1;
    System.out.println("---------");
  }

  @Test
  public void testBool(){

    boolean[] b = new boolean[10];

    for (boolean b1 : b) {
      System.out.println(b1);
    }


  }

}
