package cn.com.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhangblue.deserializer.DateCodecTest;
import com.zhangblue.model.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;

public class TestJson {


  @Test
  public void testJson() {
    String str = "{\"imei\":864701034210003}";

    Object cc = JSONObject.parseObject(str).get("imei");

    System.out.println(cc instanceof JSONArray);

    System.out.println();
  }

  /**
   * 将绝对毫秒数转成ES接受的时区时间格式,时区东八区.
   */

  @Test
  public void test() {
    JSONArray jsonArray = new JSONArray();

    System.out.println(jsonArray.toJSONString());
  }

  @Test
  public void testccc() {
    String line = "{\"imei\":[\"869782026579097\",\"869782026579089\"],\"latitude\":0.0,\"longitude\":0.0,\"manufacturer\":\"Xiaomi\",\"model\":\"Mi Note 2\",\"os_version\":\"7.0\"}";

    DevinfoReplenishModel devinfoReplenishModel = JSONObject
        .parseObject(line, DevinfoReplenishModel.class);

//    System.out.println(devinfoReplenishModel.getImsi().length);

    System.out.println(devinfoReplenishModel.getAndroidId().equals("aa"));

  }

  @Test
  public void testTime() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    String format1 = format.format(new Date());
    System.out.println(format1);

    System.out.println(System.currentTimeMillis());
  }

  @Test
  public void testttt() {
    JSONObject obj1 = new JSONObject().fluentPut("name", "zhangsa");
    JSONObject obj2 = new JSONObject().fluentPut("name", "lisi");
    JSONArray objects = new JSONArray().fluentAdd(obj1).fluentAdd(obj2);

    List<JSONObject> jsonObjects = objects.toJavaList(JSONObject.class);
    for (JSONObject jsonObject : jsonObjects) {
      jsonObject.put("age", 10);
    }

    System.out.println(JSONArray.toJSONString(objects));

  }

  @Test
  public void testttttt() {
    Map<String, String> mapTest = new HashMap<>();
    mapTest.put("a", "a");
    mapTest.put("b", "a");
    mapTest.put("c", "a");

    Set<String> strings = mapTest.keySet();

    mapTest.clear();

    System.out.println(strings.size());
  }

  @Test
  public void testccccc() {
    System.out.println("r1".hashCode() % 8);
    System.out.println("r8".hashCode() % 8);
  }

  @Test
  public void testboolean() {
    String json = "{\"name\":\"aaa\"}";
    JSONObject jsonObject = JSONObject.parseObject(json);
    System.out.println(jsonObject.getBoolean("c"));
  }

  @Test
  public void testJsonToClass() {
    String json = "{\"name\":null}";
    User user = JSONObject.parseObject(json, User.class);
    System.out.println(user.getName());

    System.out
        .println(JSONObject.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty));
  }

  @Test
  public void testJsonToStrings() {
    String json = "{\"data\":[\"aa\"]}";

    JSONObject jsonObject = JSONObject.parseObject(json);

    String[] data = jsonObject.getJSONArray("data").toArray(new String[1]);

    for (String datum : data) {
      System.out.println(datum);
    }

  }

  @Test
  public void testDate() {
    DateCodecTest dateCodecTest = new DateCodecTest();
    dateCodecTest.setCurrentDate(new Date());
    String jsonLine = JSONObject.toJSONString(dateCodecTest);
    System.out.println(jsonLine);

    Date data_time = JSONObject.parseObject(jsonLine).getDate("data_time");
    System.out.println(data_time.getTime());

  }

  @Test
  public void testData2() {

    Optional<String> optional = getOptional();
    String cc = optional.orElse("cc");
    System.out.println(cc);
  }

  public Optional<String> getOptional() {
    return Optional.ofNullable("aaa");
  }


}
