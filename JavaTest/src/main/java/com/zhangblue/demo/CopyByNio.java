package com.zhangblue.demo;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * @author zhangdi
 */
public class CopyByNio {

  public static void main(String[] args) {
    final Path copy_from = Paths.get("/home/secneo/yum.tar");
    final Path copy_to = Paths.get("/data/yum.tar");

    try (FileChannel fileChannel_from = (FileChannel.open(copy_from,
        EnumSet.of(StandardOpenOption.READ)));
        FileChannel fileChannel_to = (FileChannel.open(copy_to,
            EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

      long left_size = fileChannel_from.size();
      long position = 0;
      long begin = System.currentTimeMillis();
      while (left_size > 0) {
        long write_size = fileChannel_from
            .transferTo(position, fileChannel_from.size(), fileChannel_to);
        position += write_size;
        left_size -= write_size;
      }
      long end = System.currentTimeMillis();
      System.out.println("nio use time = " + (end - begin) / 1000);
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}
