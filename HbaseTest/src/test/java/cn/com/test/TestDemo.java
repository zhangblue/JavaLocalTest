package cn.com.test;

import java.io.File;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDemo {

  private Connection connection = null;

  @After
  public void after() throws Exception {
    connection.close();
  }

  @Before
  public void before() throws Exception {
    String filePath = System.getProperty("user.dir") + File.separator + "config" + File.separator;
    Configuration config = HBaseConfiguration.create();
    config.addResource(new Path(filePath + "hbase-site.xml"));
    connection = ConnectionFactory.createConnection(config);

  }


  @Test
  public void testHbase() throws Exception {
    Table table = connection.getTable(TableName.valueOf("zz_test2"));

    long begin = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      Get get = new Get(("romKey" + i).getBytes());
      Result result = table.get(get);
    }
    long end = System.currentTimeMillis();

    System.out.println(end - begin);
  }

  @Test
  public void testHbase2() throws Exception {
    Table table = connection.getTable(TableName.valueOf("zz_test2"));

    long begin = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      Get get = new Get(Bytes.toBytes("romKey" + i));
      get.addColumn(Bytes.toBytes("test_5mb"), Bytes.toBytes("test_5mb")); // 获取指定列族和列修饰符对应的列
      Result result = table.get(get);
      System.out.println(result.getRow());
    }
    long end = System.currentTimeMillis();
    System.out.println(end - begin);


  }
}
