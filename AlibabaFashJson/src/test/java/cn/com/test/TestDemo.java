package cn.com.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class TestDemo {


  @Test
  public void test1() throws Exception{
    String json="{\"pkg_name\": \"cn.testin.itestin\",\"ver_name\": \"2017.11.13\",\"app_name\": \"客户端测试APP\",\"data_type\": \"apkinfo\",\"location\": \"广东\",\"time\": 1517899128279,\"md5\": \"a91398c9970aec2a644e55f7532bc3ee\"}";

    System.out.println(JSONObject.parseObject(json).getString("time"));

    System.out.println(JSONObject.parseObject(json).getLong("time"));
  }

}
