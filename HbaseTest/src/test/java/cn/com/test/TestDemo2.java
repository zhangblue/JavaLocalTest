package cn.com.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Array;
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
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
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
          .addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("name"))
          .addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("age1"));
      Result result = table.get(get);

      if (!result.isEmpty()) {
        byte[] name = result.getValue(Bytes.toBytes("cf3"), Bytes.toBytes("name"));
        byte[] age = result.getValue(Bytes.toBytes("cf3"), Bytes.toBytes("age1"));

        System.out.println(null == name);
        System.out.println(null == age);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testPutData() {
    List<Put> listPut = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      String rowkey = "aaa_" + (999 - i);
      Put put = new Put(Bytes.toBytes(rowkey))
          .addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("name"), Bytes.toBytes("zhang" + i))
          .addColumn(Bytes.toBytes("cf3"), Bytes.toBytes("age"), Bytes.toBytes(i));
      listPut.add(put);
    }

    try {
      Table t_test_log = connection.getTable(TableName.valueOf("t_test_log"));
      t_test_log.put(listPut);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testFilter() {
    int i = 30;

    SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(
        Bytes.toBytes("cf3"),
        Bytes.toBytes("age"),
        CompareOp.LESS_OR_EQUAL, Bytes.toBytes(20));

    Scan scan = new Scan(Bytes.toBytes("aaa_" + (999 - i)), Bytes.toBytes("aaa_" + (999 - 0)));
    scan.setFilter(singleColumnValueFilter);
    try {
      Table t_test_log = connection.getTable(TableName.valueOf("t_test_log"));
      ResultScanner scanner = t_test_log.getScanner(scan);
      Iterator<Result> iterator = scanner.iterator();
      while (iterator.hasNext()) {
        Result next = iterator.next();
        System.out.println(Bytes.toString(next.getRow()) + "---" + Bytes
            .toString(next.getValue(Bytes.toBytes("cf3"),
                Bytes.toBytes("name"))));
      }


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
