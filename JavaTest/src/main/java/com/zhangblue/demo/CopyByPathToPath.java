package com.zhangblue.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zhangdi
 */
public class CopyByPathToPath {

  public static void main(String[] args) {
    final Path copy_from = Paths.get("/home/secneo/yum.tar");
    final Path copy_to = Paths.get("/data/yum.tar");
    try {
      long begin = System.currentTimeMillis();
      Files.copy(copy_from, copy_to, LinkOption.NOFOLLOW_LINKS);
      long end = System.currentTimeMillis();
      System.out.println("nio use time = " + (end - begin) / 1000);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
