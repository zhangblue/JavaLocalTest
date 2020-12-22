package cn.com.test;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

/**
 * @author di.zhang
 * @date 2020/8/18
 * @time 17:50
 **/
public class TestJsonToBean {

  @Test
  public void test1(){
    String[] strings = JSONArray.parseArray("[\n"
        + "        \"su_paths_exist\",\n"
        + "        \"su_files_exist\",\n"
        + "        \"magisk_root\"\n"
        + "    ]").toArray(new String[0]);

    System.out.println(strings.length);

    for (String string : strings) {
      System.out.println(string);
    }


  }

}
