package cn.com.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;

/**
 * @author di.zhang
 * @date 2020/9/1
 * @time 15:46
 **/
public class JsonPathDemo {

  @Test
  public void jsonPath() {

    String context = null;

    try {
      byte[] value = Files.readAllBytes(Paths.get(
          "/Users/zhangdi/work/workspace/github/JavaLocalTest/AlibabaFashJson/source/root.json"));

      context = new String(value);

    } catch (IOException e) {
      e.printStackTrace();
    }

    JSONObject json = JSONObject.parseObject(context);

    long bengin = System.currentTimeMillis();
    for (int i = 0; i < 1000000; i++) {
      JSONPath.containsValue(json, "$.root", true);
    }
    long end = System.currentTimeMillis();

    System.out.println(end - bengin);


  }

}
