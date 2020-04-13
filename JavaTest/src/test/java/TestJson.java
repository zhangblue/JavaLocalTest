import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class TestJson {

  @Test
  public void test11() {
    List<String> list = new ArrayList<>();
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    list.add("5");

    List<Integer> collect = list.stream().map(Integer::parseInt).collect(Collectors.toList());


  }

  @Test
  public void testMap() {
    String json = "{\"model_score\":100}";

    System.out.println(JSONObject.parseObject(json).getDoubleValue("model_score") / 100);
  }


  @Test
  public void testJsonToClass() {
    String json = "{\"name\":\"no\",\"age\":20}";
//    String json = "{\"age\":20}";

    JsonModel jsonModel = JSONObject.parseObject(json, JsonModel.class);

    String s = JSONObject.toJSONString(jsonModel);
    System.out.println(s);
  }

  @Test
  public void testJSONList() {
    LinkedList<JSONObject> jsonObjects = new LinkedList<>();
    for (int i = 0; i < 10; i++) {
      jsonObjects.add(0, new JSONObject().fluentPut("time", i));
    }

    String json = JSONArray.toJSONString(jsonObjects);
    System.out.println(json);

    List<JSONObject> jsonObjects1 = JSONArray.parseArray(json, JSONObject.class);


    LinkedList<JSONObject> link = new LinkedList<>(jsonObjects1);

    link.add(3, new JSONObject().fluentPut("time", 300));

    System.out.println(JSONArray.toJSONString(link));

  }
}
