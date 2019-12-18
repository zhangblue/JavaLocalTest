package com.bangcle.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class TestHdfs {

  @Test
  public void test() {
    FileSystem fs = null;

    try {
       fs = FileSystem.newInstance(URI.create("hdfs://vm-134:8020"), new Configuration());
//       fs = FileSystem.newInstance(configuration);
      Path path = new Path("/");
      FileStatus[] fileStatuses = fs.listStatus(path);
      for (FileStatus fileStatus : fileStatuses) {
        System.out.println(fileStatus.getPath().toString());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if(null!=fs){
        try {
          fs.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
