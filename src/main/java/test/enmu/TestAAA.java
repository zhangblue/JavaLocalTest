package test.enmu;

import java.util.HashMap;
import java.util.Map;

public class TestAAA {

  public static void main(String[] args) {
    Map<String, String[]> mapDB = new HashMap<>();
    mapDB.put("app_1", new String[]{"测试1", "1"});
    mapDB.put("app_2", new String[]{"测试2", "1"});
    mapDB.put("app_3", new String[]{"测试3", "1"});
    mapDB.put("app_4", new String[]{"测试4", "1"});
    mapDB.put("app_5", new String[]{"测试5", "1"});

    Map<String, String[]> mapHbase = new HashMap<>();
    mapHbase.put("app_1", new String[]{"测试1", "1"});
    mapHbase.put("app_2", new String[]{"测试2", "1"});

//    Set<String> setDB = new HashSet<>(mapDB.keySet());
//    Set<String> setHbase = mapHbase.keySet();
//
//    setDB.removeAll(setHbase);
//
//    System.out.println(setDB.size());
//
//    System.out.println(mapDB.size());


    Map<String,String[]> map2 = new HashMap<>(mapDB);
    map2.remove("app_1");

    System.out.println(mapDB.get("app_1")[0]);

  }
}
