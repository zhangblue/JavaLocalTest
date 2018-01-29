package cn.com.guava.utils;

import java.io.File;
import java.util.Iterator;
import org.junit.Test;

public class TestFunction {


  /**
   * 测试获取目录下所有文件
   * @throws Exception
   */
  @Test
  public void getFileTraversalTest() throws Exception{
    GuavaFilesTools guavaFilesTools = new GuavaFilesTools();
    Iterator<File> fileIterator = guavaFilesTools.getFileTraversal("/Users/zhangdi/test_folder");

    while(fileIterator.hasNext()){
      System.out.println(fileIterator.next().getPath());
    }

  }

}
