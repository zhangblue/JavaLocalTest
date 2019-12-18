package cn.com.test;

import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
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
  public void testPutOneHbase() throws Exception {
    Table table = connection.getTable(TableName.valueOf("zz_test"));

    long begin = System.currentTimeMillis();

    String line = Files
        .readLines(new File("/Users/zhangdi/test_folder/data/testline"), Charset.defaultCharset())
        .get(0);

    System.out.println(line.length());

    Put put = new Put(Bytes.toBytes("zhangdirowKey00"));

    put.addColumn(Bytes.toBytes("test_5mb"), Bytes.toBytes("test_5mb"), Bytes.toBytes(line));

    table.put(put);

    long end = System.currentTimeMillis();

    System.out.println(end - begin);
  }

  @Test
  public void testPutManyHbase() throws Exception {
    Table table = connection.getTable(TableName.valueOf("zz_test"));

    long begin = System.currentTimeMillis();
    String line = Files
        .readLines(new File("/Users/zhangdi/test_folder/data/testline"), Charset.defaultCharset())
        .get(0);

    System.out.println(line.length());

//
    List<Put> putList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      Put put = new Put(Bytes.toBytes("zdrowKey" + i));
      put.addColumn(Bytes.toBytes("test_5mb"), Bytes.toBytes("test_5mb"),
          Bytes.toBytes(line));
      putList.add(put);
    }

    if (putList.size() > 0) {
      table.put(putList);
    }

    long end = System.currentTimeMillis();
    System.out.println(end - begin);
  }

  @Test
  public void testGetOneHbase() throws Exception {
    Table table = connection.getTable(TableName.valueOf("bangcle_config_sync"));

    long begin = System.currentTimeMillis();

    Get get = new Get(Bytes.toBytes("safe_event_rule"));
    get.addFamily(Bytes.toBytes("info"));
    Result result = table.get(get);

    String value = new String(result.getRow(), Charset.defaultCharset());

    System.out
        .println("leng = " + value.length());

    long end = System.currentTimeMillis();

    System.out.println(end - begin);
  }


  @Test
  public void testHbase2() throws Exception {

    Table table = connection.getTable(TableName.valueOf("zz_test2"));
    long begin = System.currentTimeMillis();
    for (int i = 0; i < 342; i++) {
      Get get = new Get(Bytes.toBytes("zdrowKey" + i));
      //get.addColumn(Bytes.toBytes("test_5mb"), Bytes.toBytes("test_5mb")); // 获取指定列族和列修饰符对应的列
      Result result = table.get(get);
      System.out.println(
          new String(result.getValue(Bytes.toBytes("test_5mb"), Bytes.toBytes("test_5mb")))
              .length());
      //System.out.println(new String(result.getRow()));
    }

    long end = System.currentTimeMillis();
    System.out.println(end - begin);
  }

  @Test
  public void testReplace() {

    System.out.println(System.currentTimeMillis());

  }
}
