package cn.com.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDemo2 {

  private Connection connection = null;


  @Before
  public void before() throws Exception {
    String filePath = "/Users/zhangdi/Downloads/hbase-conf/";
    Configuration config = HBaseConfiguration.create();
    config.addResource(new Path(filePath + "hbase-site.xml"));
    config.addResource(new Path(filePath + "core-site.xml"));
    connection = ConnectionFactory.createConnection(config);
  }

  @After
  public void after() throws Exception {
    connection.close();
  }


  @Test
  public void testList() throws IOException {
    TableName[] tableNames = connection.getAdmin().listTableNames();
    for (TableName tableName : tableNames) {
      System.out.println(Bytes.toString(tableName.getName()));
    }
  }

  @Test
  public void testGetTTL() throws IOException {
    Admin hbaseAdmin = connection.getAdmin();
    TableName t_test_log = TableName.valueOf("t_test_log");
    HTableDescriptor tableDescriptor = hbaseAdmin
        .getTableDescriptor(t_test_log);
    int timeToLive = tableDescriptor.getFamily(Bytes.toBytes("cf"))
        .getTimeToLive();
    System.out.println(timeToLive);
  }

  @Test
  public void testUpdateTTL() {
    Admin hbaseAdmin = null;
    try {
      hbaseAdmin = connection.getAdmin();
      TableName t_test_log = TableName.valueOf("t_test_log");
      HTableDescriptor tableDescriptor = hbaseAdmin
          .getTableDescriptor(t_test_log);
      hbaseAdmin.disableTable(t_test_log);
      tableDescriptor.getFamily(Bytes.toBytes("cf"))
          .setTimeToLive(Integer.MAX_VALUE);
      hbaseAdmin.modifyTable(t_test_log, tableDescriptor);
      hbaseAdmin.enableTable(t_test_log);
    } catch (IOException e) {
      System.out.println("error-----");
      e.printStackTrace();
    }


  }

  @Test
  public void testUpdateVersion() {
    Admin hbaseAdmin = null;
    try {
      hbaseAdmin = connection.getAdmin();
      TableName t_test_log = TableName.valueOf("t_test_log");
      HTableDescriptor tableDescriptor = hbaseAdmin
          .getTableDescriptor(t_test_log);
      hbaseAdmin.disableTable(t_test_log);
      tableDescriptor.getFamily(Bytes.toBytes("cf"))
          .setVersions(1, 3);
      hbaseAdmin.modifyTable(t_test_log, tableDescriptor);
      hbaseAdmin.enableTable(t_test_log);
    } catch (IOException e) {
      System.out.println("error-----");
      e.printStackTrace();
    }
  }

  @Test
  public void testIntMaxValue() {
    System.out.println(Integer.MAX_VALUE);
  }

  @Test
  public void testGetHbase() {
    TableName t_test_log = TableName.valueOf("t_test_log");
    try {
      Table table = connection.getTable(t_test_log);
      Get get = new Get(Bytes.toBytes("test1"));
      get.setMaxVersions(3);
      List<Cell> columnCells = table.get(get)
          .getColumnCells(Bytes.toBytes("cf"), Bytes.toBytes("sex"));

      for (Cell columnCell : columnCells) {
        String value = Bytes.toString(columnCell.getValueArray(),
            columnCell.getValueOffset(),
            columnCell.getValueLength());

        System.out.println(value + "-----" + columnCell.getTimestamp());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testDeleteCf() {
    try {
      Admin admin = connection.getAdmin();
      TableName t_test_log = TableName.valueOf("t_test_log");
      admin.disableTable(t_test_log);
      admin.deleteColumn(t_test_log, Bytes.toBytes("cf1"));
      admin.deleteColumn(t_test_log, Bytes.toBytes("cf2"));
      admin.enableTable(t_test_log);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testResult1() {
    try {
      Table table = connection.getTable(TableName.valueOf("t_test_log"));
      Get get = new Get(Bytes.toBytes("abc"))
          .addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("name")).addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("age1"));
      Result result = table.get(get);

      if(!result.isEmpty()){
        byte[] name = result.getValue(Bytes.toBytes("cf3"), Bytes.toBytes("name"));
        byte[] age = result.getValue(Bytes.toBytes("cf3"), Bytes.toBytes("age1"));

        System.out.println(null==name);
        System.out.println(null==age);

      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
