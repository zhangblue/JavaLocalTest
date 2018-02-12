package cn.com.demo.test;


import cn.com.repository.EsRepository;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESTest {

  private EsRepository esRepository = null;


  @Before
  public void before() throws Exception {
    esRepository = new EsRepository();
    esRepository.buildClient();
    esRepository.buildBulkProcessor();
  }

  @After
  public void after() throws Exception {
    esRepository.closeBulkProcessor();
    esRepository.getClient().close();

  }


  @Test
  public void doTest1() throws Exception {

    String[] strIndexs = {"index_message_20180201"};

    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

    TermsQueryBuilder udidQueryBuilder = QueryBuilders
        .termsQuery("udid", "efcc6b7e-92d4-3ca2-8212-fae41fd17be9");
    boolQueryBuilder.must(udidQueryBuilder);
    TermsQueryBuilder agentidQueryBuilder = QueryBuilders.termsQuery("agent_id", "102");
    boolQueryBuilder.must(agentidQueryBuilder);

    TermsQueryBuilder gyroscopeQueryBuilder = QueryBuilders.termsQuery("protol_type", "gyroscope");
    boolQueryBuilder.must(gyroscopeQueryBuilder);

//    ExistsQueryBuilder existsQueryBuilder = QueryBuilders.existsQuery("time_zone");
//    boolQueryBuilder.must(existsQueryBuilder);

    GetRequestBuilder getRequestBuilder = esRepository.getClient().prepareGet("ccc", "aaa", "bbb");

    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("_id", "bbb");

    GetResponse getResponse = getRequestBuilder.get();

    if (!getResponse.isExists()) {
      System.out.println("is null");
    } else {

      System.out.println("not null");
    }

//    SearchRequestBuilder searchRequestBuilder = esRepository.getClient().prepareSearch(strIndexs)
//        .setTypes("bangcle_type").setQuery(boolQueryBuilder).setSize(1);
//
//    SearchResponse searchResponse = searchRequestBuilder.get();
//
//    SearchHit[] searchHits = searchResponse.getHits().getHits();
//    System.out.println(searchHits.length);
//
//    for (SearchHit searchHit : searchHits) {
//
//      Map<String, Object> mapSource = searchHit.getSourceAsMap();
//      System.out.println(mapSource.get("gyroscope").toString());
//    }
  }

  @Test
  public void doTest2() throws Exception {

    String jsonString = "{\"server_time\": 1517822454218,\"ip_lan\": \"172.17.100.15\",\"run_key\": \"3\"}";

    JSONObject jsonObject = JSONObject.parseObject(jsonString);

    long begin = System.currentTimeMillis();

    for (int i = 0; i < 10000; i++) {

      //更新字段
      IndexRequest indexRequest = new IndexRequest("test_zhangd", "test_type", "test_id" + i)
          .source(jsonObject);
      UpdateRequest updateRequest = new UpdateRequest("test_zhangd", "test_type", "test_id" + i)
          .doc(jsonObject).upsert(indexRequest);
//      esRepository.getClient().update(updateRequest).get();

      //重新索引
//      esRepository.getClient().prepareIndex("test_zhangd", "test_type", "test_id" + i)
//          .setSource(jsonObject).get();

      esRepository.getBulkProcessor().add(updateRequest);

    }
  }

  @Test
  public void doTest100() throws Exception {

    String jsonString = "{\"server_time\":1518329853941,\"run_key\":\"04f27530484af27bbcd596020c7df72e\",\"agent_id\":372,\"rule_name\":\"威胁说明\",\"net_type\":\"NETWORK_WIFI\",\"event_def_id\":\"798\",\"dt_server_time\":\"2018-02-11T14:17:33+08:00\",\"dt_time\":\"2018-02-11T14:17:33+08:00\",\"manufacturer\":\"Xiaomi3\",\"safe_event_id\":\"7477-00000000-0000-0000-0000-000000007477_372_1518334287732_433091\",\"os_info\":\"android 6.0.13\",\"self_md5\":\"372000000000000000000000000000000009\",\"app_info\":\"android \",\"client_ip\":\"172.16.12.104\",\"location\":\"unknown\",\"time\":1518329853750,\"udid\":\"7477-00000000-0000-0000-0000-000000007477\",\"msg_id\":5554}";
    //String jsonString = "{\"server_time\":1518329853941}";
    JSONObject jsonObject = JSONObject.parseObject(jsonString);

    //更新字段
    IndexRequest indexRequest = new IndexRequest("index_event_20180211", "bangcle_type",
        "04f27530484af27bbcd596020c7df72e|372|5554|15708")
        .source(jsonObject);
    esRepository.getClient().index(indexRequest).get();

//    esRepository.getBulkProcessor().add(indexRequest);

  }

  @Test
  public void testSearchMap() throws Exception {
    GetRequestBuilder getRequestBuilder = esRepository.client
        .prepareGet("index_dev", "bangcle_type", "3276-00000000-0000-0000-0000-000000003276");

    GetResponse getResponse = getRequestBuilder.get();

    Map<String, Object> hashMap = getResponse.getSourceAsMap();
    Iterator<String> setKey = hashMap.keySet().iterator();

    long begintime = System.currentTimeMillis();
    for (int i = 0; i < 10000; i++) {
      GetRequestBuilder getRequestBuilder2 = esRepository.client
          .prepareGet("index_dev", "bangcle_type", "3276-00000000-0000-0000-0000-000000003276");
      GetResponse getResponse2 = getRequestBuilder2.get();
      Map<String, Object> map2 = getResponse2.getSourceAsMap();
      while (setKey.hasNext()) {
        String messageKey = setKey.next();
        if (map2.containsKey(messageKey)) {
          if (!map2.get(messageKey).toString().equals(hashMap.get(messageKey).toString())) {
            break;
          }
        } else {
          break;
        }
      }
    }
    long endtime = System.currentTimeMillis();

    System.out.println("use time==" + (endtime - begintime));
  }

  @Test
  public void testSearchMap2() throws Exception {
    GetRequestBuilder getRequestBuilder = esRepository.client
        .prepareGet("index_dev", "bangcle_type", "3276-00000000-0000-0000-0000-000000003276");

    GetResponse getResponse = getRequestBuilder.get();

    Map<String, Object> hashMap = getResponse.getSourceAsMap();
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.putAll(hashMap);
    Iterator<String> setKey = hashMap.keySet().iterator();

    long begintime = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      while (setKey.hasNext()) {
        String messageKey = setKey.next();
        if (map2.containsKey(messageKey)) {
          if (!map2.get(messageKey).toString().equals(hashMap.get(messageKey).toString())) {
            System.out.println("跳出1");
            break;
          }
        } else {
          System.out.println("跳出2");
          break;
        }
      }
    }
    long endtime = System.currentTimeMillis();

    System.out.println("use time==" + (endtime - begintime));
  }


  @Test
  public void testMap() throws Exception {
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("b", "a");
    map1.put("a", "a");

    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("a", "a");
    map2.put("b", "a");

    System.out.println(map1.hashCode());
    System.out.println(map2.hashCode());

  }

  @Test
  public void test3() throws Exception {
    Set<String> list = new HashSet<String>();
    list.add("111");
    list.add("333");

    System.out.println(Joiner.on(",").skipNulls().join(list));

    Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    map.put("aaa", list);

    map.get("aaa").add("222");
    map.get("aaa").add("222");

    System.out.println(map.get("aaa").size());

  }
}
