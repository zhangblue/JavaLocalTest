package com.bangcle.ceping.engine.utils;

import com.google.common.io.Files;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestZip {

  public static void readZipFile(String file) throws Exception {
    ZipFile zf = new ZipFile(file);
    InputStream in = new BufferedInputStream(new FileInputStream(file));
    ZipInputStream zin = new ZipInputStream(in);
    ZipEntry ze;
    while ((ze = zin.getNextEntry()) != null) {
      if (ze.isDirectory()) {

      } else {
        System.err.println("file - " + ze.getName() + " : "
            + ze.getSize() + " bytes");
        long size = ze.getSize();
        if (size > 0) {
          BufferedReader br = new BufferedReader(
              new InputStreamReader(zf.getInputStream(ze)));
          String line;
          while ((line = br.readLine()) != null) {
            System.out.println(line);
          }
          br.close();
        }
        System.out.println();
      }
    }
    zin.closeEntry();
  }

  /**
   * 解压缩
   *
   * @param zipFile 当前解压缩的文件，这里包含全路径
   * @param fullPath 要解压缩的位置,这里包含全路径
   */
  public static void unzip(String zipFile, String fullPath) throws Exception {
    // 读取压缩文件
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
        zipFile));

    ZipInputStream zin = new ZipInputStream(new BufferedInputStream(bis));
    ZipEntry entry;
    File targetFold = null;
    try {
      while ((entry = zin.getNextEntry()) != null) {
        // 设置解压输出文件
        int BUFFER = 2048;
        String tmp = fullPath + entry.getName();
        //System.out.println("--"+tmp+" is dir:"+entry.isDirectory());
        //create file
        targetFold = new File(tmp);
        if ((!targetFold.exists() || targetFold.isFile()) && entry.isDirectory()) {
          //(new File(tmp)).mkdirs();
          targetFold.mkdirs();
        }
        if (entry.isDirectory()) {
          continue;
        }
        FileOutputStream fos = new FileOutputStream(targetFold);

        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

        // 读取文件
        int count = 0;
        byte[] data = new byte[BUFFER];
        while ((count = zin.read(data, 0, BUFFER)) != -1) {
          dest.write(data, 0, count);
        }
        dest.flush();// 刷新写入数据
        dest.close();// 关闭输出流
      }
      zin.close(); // 关闭输入流
    } catch (Exception e) {
      throw e;
    }
  }


  public static void main(String[] args) {
    try {
      unzip("/Users/zhangdi/test_folder/Kafka.zip","/Users/zhangdi/test_folder/tmp/");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void testGuava() {
    Iterable<File> it = Files.fileTreeTraverser().breadthFirstTraversal(new File("/Users/zhangdi/test_folder"));

    Iterator<File> iterator = it.iterator();

    while (iterator.hasNext()) {
      File file = iterator.next();
      if (file.isFile()) {
        System.out.println(file.getPath());
      }

    }
  }


}
