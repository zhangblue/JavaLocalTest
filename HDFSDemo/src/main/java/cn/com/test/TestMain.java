package cn.com.test;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.HarFileSystem;
import org.apache.hadoop.fs.Path;

public class TestMain {

  public static void main(String[] args) {

    TestMain tm = new TestMain();
    tm.doMainHarSystem();


  }

  public void doMainHarSystem2() {
    HarFileSystem harFileSystem = null;

    try {
      harFileSystem = new HarFileSystem();
      Path path = new Path("/bangcle/bangcle_dataservice/zoo.har");
      harFileSystem.initialize(path.toUri(), initConfig());
      Path[] paths = {new Path("/Users/zhangdi/test_folder/hadoop-cdh-config/data/testcccc.ok")};
      harFileSystem
          .copyFromLocalFile(false, false, paths, new Path("/outputdir/zoo.har/testccc.ok"));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (null != harFileSystem) {
        try {
          harFileSystem.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * get出har文件中的子文件
   */
  public void doMainHarSystem() {
    HarFileSystem harFileSystem = null;
    try {

      harFileSystem = new HarFileSystem();

      Path path = new Path("/outputdir/test2.har");
      harFileSystem.initialize(path.toUri(), initConfig());


      FileStatus[] fileStatuses = harFileSystem.listStatus(path);

      for (FileStatus fileStatus : fileStatuses) {
        System.out.println(fileStatus.getPath());

        System.out.println(fileStatus.getPath() + " finish!");
      }


    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      if (harFileSystem != null) {
        try {
          harFileSystem.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public Configuration initConfig() {

    Configuration hadoopConfig = new Configuration();

//    hadoopConfig.addResource(new Path(
//        "/Users/zhangdi/test_folder/hadoop-cdh-config/hdfs-site.xml"));
    hadoopConfig.addResource(new Path(
        "/Users/zhangdi/Downloads/hadoop-conf/core-site.xml"));

    return hadoopConfig;
  }
}
