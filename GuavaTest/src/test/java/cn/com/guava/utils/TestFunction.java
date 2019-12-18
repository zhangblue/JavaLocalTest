package cn.com.guava.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import com.sun.scenario.effect.impl.sw.java.JSWPhongLighting_DISTANTPeer;
import org.junit.Test;

public class TestFunction {


  /**
   * 测试获取目录下所有文件
   */
  @Test
  public void getFileTraversalTest() throws Exception {
    GuavaFilesTools guavaFilesTools = new GuavaFilesTools();
    Iterator<File> fileIterator = guavaFilesTools.getFileTraversal("/Users/zhangdi/test_folder");

    while (fileIterator.hasNext()) {
      System.out.println(fileIterator.next().getPath());
    }

  }


  @Test
  public void getJavaException() throws Exception {
    GuavaStringTools guavaStringTools = new GuavaStringTools();
    String str = "java.lang.Exception: java.lang.RuntimeException: Unable to start activityComponentInfocom.xcwef.wafeaf.fzfemm.ecom.wg.dreampet.GameActivity: java.lang.NullPointerException at com.bangcle.everisk.c.j.uncaughtException(SourceFile:29) at java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:693) at java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:690)";

    String pattern = ":(.*?):";

    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(str);

    if (m.find()) {

      System.out.println("ccc=" + CharMatcher.anyOf(":").removeFrom(m.group()));

      System.out.println("ccc=" + CharMatcher.anyOf(":").trimFrom(m.group()));

      System.out.println(
          "aaa=" + guavaStringTools.removeKey(CharMatcher.anyOf(":").removeFrom(m.group()), " "));
    }
  }

  @Test
  public void guavaBase64EncodingTest() throws Exception {
    String base64 = "amF2YS5sYW5nLk51bGxQb2ludGVyRXhjZXB0aW9uCglhdCBjb20uZXhhbXBsZS5iYW5nY2xlLnNka190ZXN0X2FwcC5DcmFzaExpc3Rlbm5lci5vbkNsaWNrKENyYXNoTGlzdGVubmVyLmphdmE6MzQpCglhdCBhbmRyb2lkLnZpZXcuVmlldy5wZXJmb3JtQ2xpY2soVmlldy5qYXZhOjQ0MzgpCglhdCBhbmRyb2lkLnZpZXcuVmlldyRQZXJmb3JtQ2xpY2sucnVuKFZpZXcuamF2YToxODQ3MykKCWF0IGFuZHJvaWQub3MuSGFuZGxlci5oYW5kbGVDYWxsYmFjayhIYW5kbGVyLmphdmE6NzMzKQoJYXQgYW5kcm9pZC5vcy5IYW5kbGVyLmRpc3BhdGNoTWVzc2FnZShIYW5kbGVyLmphdmE6OTUpCglhdCBhbmRyb2lkLm9zLkxvb3Blci5sb29wKExvb3Blci5qYXZhOjEzNikKCWF0IGFuZHJvaWQuYXBwLkFjdGl2aXR5VGhyZWFkLm1haW4oQWN0aXZpdHlUaHJlYWQuamF2YTo1MTIwKQoJYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZU5hdGl2ZShOYXRpdmUgTWV0aG9kKQoJYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZShNZXRob2QuamF2YTo1MTUpCglhdCBjb20uYW5kcm9pZC5pbnRlcm5hbC5vcy5aeWdvdGVJbml0JE1ldGhvZEFuZEFyZ3NDYWxsZXIucnVuKFp5Z290ZUluaXQuamF2YTo4MTgpCglhdCBjb20uYW5kcm9pZC5pbnRlcm5hbC5vcy5aeWdvdGVJbml0Lm1haW4oWnlnb3RlSW5pdC5qYXZhOjYzNCkKCWF0IGRlLnJvYnYuYW5kcm9pZC54cG9zZWQuWHBvc2VkQnJpZGdlLm1haW4oWHBvc2VkQnJpZGdlLmphdmE6MTMyKQoJYXQgZGFsdmlrLnN5c3RlbS5OYXRpdmVTdGFydC5tYWluKE5hdGl2ZSBNZXRob2QpCg==";

    GuavaStringTools guavaStringTools = new GuavaStringTools();
    String strReturn = guavaStringTools.base64Encoding(base64);

    System.out.println(strReturn);

    System.out.println("---------");

    List<String> list = guavaStringTools.splitString(strReturn, "\n", true);

    System.out.println(list.get(0));
  }

  @Test
  public void guavaAppendFile() throws Exception {
    String value = "abcdefghijklmnopqrstuvwxyz";
    List<String> list = new ArrayList<>(1);
    list.add(value);
    File file = new File("/Users/zhangdi/test_folder/data/testline");
    GuavaFilesTools guavaFilesTools = new GuavaFilesTools();
    for (int i = 0; i < 500000; i++) {
      guavaFilesTools.appendContentToFile(list, file);
    }
  }

  @Test
  public void ddd() {
    String line = "{\"ip_src\":null}";

    if (Strings.isNullOrEmpty(JSONObject.parseObject(line).getString("ip_src"))) {
      System.out.println("ccc");
    } else {
      System.out.println("ddd'");
    }

    Map<String, Object> map = JSONObject.parseObject(line, Map.class);

    if (Strings.isNullOrEmpty(map.get("ip_src").toString())) {
      System.out.println("111");
    } else {
      System.out.println("222");
    }

  }

  @Test
  public void dddd() {
    String jsonValue = "{\"gyroscope\": \"[[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-7.732163649052382E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-2.4058413691818714E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"-2.4058413691818714E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-2.4058413691818714E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"0.001071476610377431\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.001889944775030017\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"0.0011993075022473931\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-2.4058413691818714E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"0.001071476610377431\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"0.001071476610377431\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"-9.312214097008109E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-9.312214097008109E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"-2.4058413691818714E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-2.4058413691818714E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"-2.4058413691818714E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"0.001071476610377431\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-9.312214097008109E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"0.001889944775030017\\\",\\\"1.340430462732911E-4\\\"],[\\\"0.001071476610377431\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"2.9204809106886387E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"-2.4058413691818714E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"1.340430462732911E-4\\\"],[\\\"5.388443823903799E-4\\\",\\\"0.0013573125470429659\\\",\\\"1.340430462732911E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"8.246803190559149E-4\\\",\\\"-3.985891817137599E-4\\\"],[\\\"6.2121544033288956E-6\\\",\\\"2.9204809106886387E-4\\\",\\\"6.666752742603421E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"-2.4058413691818714E-4\\\",\\\"-9.312214097008109E-4\\\"],[\\\"-5.264200735837221E-4\\\",\\\"0.0013573125470429659\\\",\\\"-3.985891817137599E-4\\\"]]\"}";

    JSONObject jsonBody = JSONObject.parseObject(jsonValue);
    if (!Strings.isNullOrEmpty(jsonBody.getString("gyroscope"))) {
      JSONArray jsonArray = jsonBody.getJSONArray("gyroscope");
      if (jsonArray.size() > 10) {

        System.out.println("count=" + jsonArray.size());
        int count = jsonArray.size();
        for (int i = 0; i < count - 10; i++) {
          jsonArray.remove(0);
        }
        System.out.println(jsonArray.size());

        jsonBody.replace("gyroscope", jsonArray.toJSONString());
      }

      System.out.println(jsonBody.toJSONString());
    }

  }

  @Test
  public void aaa() {
    try {
      String line = Files.readLines(new File("/Users/zhangdi/test_folder/data_test/latitude-longitude-city"), Charset.defaultCharset()).get(0);

      JSONArray jsonArray = JSONArray.parseArray(line);

      File file = new File("/Users/zhangdi/test_folder/data_test/latitude-longitude-format");

      int count = jsonArray.size();
      for (int i = 0; i < count; i++) {
        JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());

        String line1 = jsonObject.getJSONArray("geoCoord").toJSONString();

        String line2 = CharMatcher.anyOf("]").removeFrom(CharMatcher.anyOf("[").removeFrom(line1));

        List<String> lineList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(line2);

        Files.append(lineList.get(0) + "\t" + lineList.get(1) + "\t" + jsonObject.getString("name") + "\n", file, Charset.defaultCharset());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void bbbb() {
    String value = "{\"server_time\":1522807391451,\"body\":{\"server_time\": \"1522823434202\",\"ip_lan\": \"172.16.19.138\",\"agent_id\": 1999,\"start_id\": 1522823434,\"app_version\": \"unknown\",\"os_version\": \"unknown\",\"ip_src\": \"172.16.12.13\",\"user_data\": \"[{\\\"ccb_data\\\":[{\\\"phone\\\":\\\"14416533234\\\",\\\"id_card\\\":\\\"333220198909090000\\\",\\\"id\\\":1,\\\"bank_subbranch\\\":\\\"434343431\\\"}]}]\",\"version\": \"everisk 1.0 beta\",\"dt_server_time\": \"2018-04-04T14:30:34+08:00\",\"platform\": \"android\",\"dt_time\": \"2018-04-04T14:30:34+08:00\",\"manufacturer\": \"unknown\",\"protol_type\": \"userdata\",\"os_info\": \"android unknown\",\"extra\": \"{\\\"location\\\":{\\\"latitude\\\":\\\"31.2090626936\\\",\\\"longitude\\\":\\\"121.4934089939\\\"}}\",\"self_md5\": \"1999000000000000000000000000000000007\",\"protol_version\": 4,\"app_info\": \"android unknown\",\"time\": 1522823434195,\"udid\": \"4768-00000000-0000-0000-0000-000000004768\",\"msg_id\": 8561}}";
    JSONObject jsonObject = JSONObject.parseObject(value);

    System.out.println(jsonObject.getString("server_time"));

    System.out.println(jsonObject.getJSONObject("body").getString("protol_type"));
  }

  @Test
  public void sss() {
    String json = "{\"attack_frame\": \"[{\\\"field_cache\\\":[\\\"xxfield\\\"],\\\"method_cache\\\":[],\\\"constructor_cache\\\":[],\\\"module\\\":[\\\"xxlocation\\\"],\\\"type\\\":\\\"attack_frame\\\",\\\"so_path\\\":\\\"com.gdb\\\"}]\"}";
    JSONObject jsonObject = JSONObject.parseObject(json);
    Object jsonArray = jsonObject.get("attack_frame");
    System.out.println(jsonArray instanceof String);
    System.out.println(jsonArray instanceof JSONArray);
  }

  @Test
  public void asas() {
    File file = new File("/Users/zhangdi/test_folder/data_test/testmessage");
    System.out.println(file.length());
    for (int i = 0; i < 20000000; i++) {
      try {
        Files.append("this is line \n", file, Charset.defaultCharset());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println(file.length());
  }

  @Test
  public void testJson() {
    String strPath = "/Users/zhangdi/test_folder/location_data/sheng/";
    File dataFile = new File("/Users/zhangdi/work/workspace/bangbang-git/everiskServer/geopip/admin_level_4.geojson");

    try {
      List<String> listAllLine = Files.readLines(dataFile, Charset.defaultCharset());

      String jsonString = Joiner.on("").skipNulls().join(listAllLine);
      JSONObject jsonObject = JSONObject.parseObject(jsonString);

      JSONArray jsonArrayFeatures = jsonObject.getJSONArray("features");
      int jsonArraySize = jsonArrayFeatures.size();
      for (int i = 0; i < jsonArraySize; i++) {
        JSONObject featuresOne = jsonArrayFeatures.getJSONObject(i);
        JSONObject jsonProperties = featuresOne.getJSONObject("properties");

        if (!Strings.isNullOrEmpty(jsonProperties.getString("int_name"))) {
          String name = jsonProperties.getString("int_name");

          File fileName = new File(strPath + name);

          JSONArray jsonArrayCoordinates = featuresOne.getJSONObject("geometry").getJSONArray("coordinates");

          JSONArray jsonCode = getJSONArray(jsonArrayCoordinates);
          int arraySize = jsonCode.size();
          for (int j = 0; j < arraySize; j++) {
            Files.append(jsonCode.getString(j) + "\n", fileName, Charset.defaultCharset());
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public JSONArray getJSONArray(JSONArray jsonArray) {
    if (jsonArray.size() != 2) {
      return getJSONArray(jsonArray.getJSONArray(0));
    } else {
      return jsonArray;
    }
  }


  @Test
  public void testJson2() {
    File file = new File("/Users/zhangdi/test_folder/data_test/cityname");
    File fileCity = new File("/Users/zhangdi/test_folder/data_test/daata2");
    try {
      List<String> all = Files.readLines(file, Charset.defaultCharset());
      JSONArray jsonArray = JSONArray.parseArray(all.get(0));

      int size = jsonArray.size();
      for (int i = 0; i < size; i++) {
        JSONArray arrayLine = jsonArray.getJSONArray(i);
        int arraylineSize = arrayLine.size();
        StringBuffer stringBuffer = new StringBuffer();

        Files.append(arrayLine.getString(0) + "," + arrayLine.getString(1) + "\n", fileCity, Charset.defaultCharset());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWriteFile() throws IOException {
    File fileFrom = new File("/Users/zhangdi/test_folder/index_all");
    System.out.println(fileFrom.getAbsolutePath());
    System.out.println(fileFrom.getParent());
    File fileTo = new File("/Users/zhangdi/test_folder/data_test/test.gz");

    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(
        new FileOutputStream(fileTo, true)));

    Files.copy(fileFrom, Charset.defaultCharset(), outputStreamWriter);
    outputStreamWriter.close();


  }

  @Test
  public void test() {
    File[] files = new File("/Users/zhangdi/test_folder/docker_james_final").listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        if (name.startsWith("threat_current_")) {
          return true;
        } else {
          return false;
        }
      }
    });
    if (files.length > 0) {
      System.out.println("yes");
    } else {
      System.out.println("no");
    }
  }

  @Test
  public void testc() {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 3);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    System.out.println(simpleDateFormat.format(calendar.getTime()));

    System.out.println("20180414".compareTo(simpleDateFormat.format(calendar.getTime())));

  }

  @Test
  public void testcc() {
    Path path = Paths.get("/Users/zhangdi/Downloads/docker-ce.repo");
    System.out.println(path.getFileName());
  }

  @Test
  public void testaa() {
    String strLine = "8965-00000000-0000-0000-0000-0000000008965" + "5000000000000000000000000000000000007" + "1544497111";

    String guavaValue = Hashing.md5().hashBytes(strLine.getBytes()).toString();

    String javaValue = calculateRunKey("8965-00000000-0000-0000-0000-0000000008965", "5000000000000000000000000000000000007", 1544497111);

    System.out.println(guavaValue.equals(javaValue));

  }

  public String calculateRunKey(String udid, String selfMd5, long startId) {
    String runKey = null;
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      StringBuffer buffer = new StringBuffer();
      buffer.append(udid);
      buffer.append(selfMd5);
      buffer.append(startId);
      byte[] bytes = md5.digest(buffer.toString().getBytes());

      StringBuffer stringBuffer = new StringBuffer();
      for (byte b : bytes) {
        int bt = b & 0xff;
        if (bt < 16) {
          stringBuffer.append(0);
        }
        stringBuffer.append(Integer.toHexString(bt));
      }
      runKey = stringBuffer.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return runKey;
  }

  @Test
  public void test001() {
    String cc = new JSONObject().fluentPut("code", 0).fluentPut("msg", "成功").fluentPut("data", new JSONObject().fluentPut("maximum_interval", 1 * 60 * 60 * 24 * 30).fluentPut("policy_types", new JSONArray().fluentAddAll(Arrays.asList(false, true, true)))).toJSONString();
    System.out.println(cc);
  }

  @Test
  public void test002() {
    List<JSONObject> jsonObjects = new ArrayList<>(3);
    jsonObjects.add(new JSONObject().fluentPut("time", 5));
    jsonObjects.add(new JSONObject().fluentPut("time", 4));
    jsonObjects.add(new JSONObject().fluentPut("time", 6));


    jsonObjects.sort(new Comparator<JSONObject>() {
      @Override
      public int compare(JSONObject o1, JSONObject o2) {
        if (o1.getIntValue("time") < o2.getIntValue("time")) {
          return -1;
        } else {
          return 1;
        }
      }
    });

    jsonObjects.forEach(x -> System.out.println(x.getIntValue("time")));


  }

  @Test
  public void test003() {

    ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<>(100);

    for (int i = 0; i < 100; i++) {
      try {
        strings.put("a");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    int count = strings.size() / 3;

    for (int i = 0; i < 3; i++) {
      List<String> strings1 = new ArrayList<>(count);
      strings.drainTo(strings1, count);

      System.out.println(strings1.size());
      System.out.println(strings.size());
      System.out.println("------------");
    }
  }

  @Test
  public void test004() {
    File file = new File("/Users/zhangdi/Downloads/message_data_20190819_1566144221291_1566144537409");

    try {
      List<String> strings = Files.readLines(file, Charset.defaultCharset());
      for (String string : strings) {

        String value = string.split("\t")[2];


        JSONObject jsonObject = JSONObject.parseObject(value);
        if (jsonObject.getJSONObject("body").getString("agent_id").equals("289")) {
          System.out.println("yes");
        }else{
          System.out.println("no");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  @Test
  public void test005(){
    long l = System.currentTimeMillis();
    Date date = new Date(l);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    String format = dateFormat.format(date);
    System.out.println(format);
  }


}
