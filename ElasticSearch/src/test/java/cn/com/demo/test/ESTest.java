package cn.com.demo.test;


import cn.com.repository.EsRepository;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.lucene.search.BooleanQuery;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.stats.IndexStats;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsRequest;
import org.elasticsearch.action.admin.indices.stats.IndicesStatsResponse;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.ExistsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESTest {

  private EsRepository esRepository = null;


  @Before
  public void before() throws Exception {
    esRepository = new EsRepository();
    esRepository.buildClient();
    //esRepository.buildBulkProcessor();
  }

  @After
  public void after() throws Exception {
    //esRepository.closeBulkProcessor();
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

    String jsonString = "{\n" +
        "  \"task_id\": \"0\",\n" +
        "  \"_time\": \"1566189249837\",\n" +
        "  \"_rk\": \"8ed47185-a586-4735-b03b-0b2e3c810283\",\n" +
        "  \"_serial\": \"1\",\n" +
        "  \"_v\": \"1.0\",\n" +
        "  \"model\": \"MIX 2\",\n" +
        "  \"rp\": \"1080X2161\",\n" +
        "  \"browser_id\": \"c426bfb675d9e7fdd2ab98cbee6d8c90\",\n" +
        "  \"browser_id_temp\": \"c426bfb675d9e7fdd2ab98cbee6d8c90\",\n" +
        "  \"wm\": \"\",\n" +
        "  \"plt\": \"web\",\n" +
        "  \"sdk_ver\": \"1.2\",\n" +
        "  \"os\": \"Linux; Android 9; MIX 2 Build/PKQ1.190118.001; wv\",\n" +
        "  \"browser_engine\": \"AppleWebKit/537.36 \",\n" +
        "  \"browser_plug\": [\n" +
        "    \"PDF.PdfCtrl\",\n" +
        "    \"Skype.Detection\"\n" +
        "  ],\n" +
        "  \"accept\": [\n" +
        "    \"application/pdf\",\n" +
        "    \"application/x-pnacl\"\n" +
        "  ],\n" +
        "  \"accept_language\": [\n" +
        "    \"zh-CN\",\n" +
        "    \"en-US\"\n" +
        "  ],\n" +
        "  \"accept_encoding\": \"\",\n" +
        "  \"accept_charset\": \"UTF-8\",\n" +
        "  \"screen_size\": \"\",\n" +
        "  \"color_depth\": \"24\",\n" +
        "  \"canvas\": \"708f2eae\",\n" +
        "  \"webgl_vendor\": \"Qualcomm~Adreno (TM) 540\",\n" +
        "  \"cpu_class\": \"\",\n" +
        "  \"cpu_core\": \"8\",\n" +
        "  \"browser_platform\": \"Linux armv8l\",\n" +
        "  \"user_agent\": \"Mozilla/5.0 (Linux; Android 9; MIX 2 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 Html5Plus/1.0\",\n"
        +
        "  \"reinforce_tastics\": [\n" +
        "    \"compact\",\n" +
        "    \"domainLock\",\n" +
        "    \"reservedNames\",\n" +
        "    \"appBind\",\n" +
        "    \"jsObfuscate\"\n" +
        "  ],\n" +
        "  \"ext\": {\n" +
        "  }\n" +
        "}";
    //String jsonString = "{\"server_time\":1518329853941}";
    JSONObject jsonObject = JSONObject.parseObject(jsonString);

    jsonObject.put("dt_msg_time", new Date());

    //更新字段
    IndexRequest indexRequest = new IndexRequest("h5_devinfo_template", "bangcle_type",
        "2")
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

  @Test
  public void testSearchMap4() throws Exception {
    GetRequestBuilder getRequestBuilder = esRepository.client
        .prepareGet("index_dev", "bangcle_type", "1838-00000000-0000-0000-0000-000000001838|1");

    GetResponse getResponse = getRequestBuilder.get();

    if (getResponse.isExists()) {
      System.out.println("OK");
    } else {
      System.out.println("error");
    }
  }

  @Test
  public void testJSONReplace() throws Exception {
    String jsonString = "{\"server_time\": 1517822454218,\"ip_lan\": \"172.17.100.15\",\"run_key\": \"3\"}";

    JSONObject jsonObject = JSONObject.parseObject(jsonString);

    jsonObject.replace("server_time", "cccc");

    System.out.println(jsonObject.toJSONString());

  }

  @Test
  public void indexLargeData() throws Exception {

    File file = new File("/Users/zhangdi/Downloads/test.log");
    Long filelength = file.length();
    byte[] filecontent = new byte[filelength.intValue()];

    FileInputStream in = new FileInputStream(file);
    in.read(filecontent);
    in.close();
    String content = new String(filecontent, "UTF-8");

    JSONObject json = new JSONObject().fluentPut("data_name", "zhangsan")
        .fluentPut("data_content", content);

    //更新字段
    IndexRequest indexRequest = new IndexRequest("zhangd_test", "doc", "1")
        .source(json);

    ActionFuture<IndexResponse> index = esRepository.getClient().index(indexRequest);

    index.actionGet();


  }

  @Test
  public void searchData() throws Exception {

    File file = new File(
        "/Users/zhangdi/work/workspace/github/JavaLocalTest/TestGuava/json/h5_devinfo_template.json");
    Long filelength = file.length();
    byte[] filecontent = new byte[filelength.intValue()];
    FileInputStream in = new FileInputStream(file);
    in.read(filecontent);
    in.close();

    String content = new String(filecontent, "UTF-8");

    Map<String, Object> mapSource = JSONObject.parseObject(content).toJavaObject(Map.class);
    PutIndexTemplateResponse ttttttt = esRepository.getClient().admin().indices()
        .preparePutTemplate("aaaaaaa").setSource(mapSource).get();
  }


  @Test
  public void testDateTime() {
    long l = System.currentTimeMillis();

    String stringByLong = getStringByLong(l / 1000, "yyyy-MM-dd'T'HH:mm:ssZZ");
    System.out.println(stringByLong);
  }

  /**
   * 绝对秒数转成字符型，默认东八区.
   */
  public String getStringByLong(long longTime, String strFormatter) {
    Instant instant = Instant.ofEpochSecond(longTime);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(strFormatter);
    return localDateTime.format(dateTimeFormatter);
  }

  @Test
  public void test003() {
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
    System.out.println(myFormat.format(new Date()));
  }

  @Test
  public void testUpdateByQuery() {

    long begin = System.currentTimeMillis();
    Script script = new Script("ctx._source.agent_id = 1");
    UpdateByQueryRequestBuilder updateByQueryRequestBuilder = UpdateByQueryAction.INSTANCE
        .newRequestBuilder(esRepository.getClient());
    BulkByScrollResponse bulkByScrollResponse = updateByQueryRequestBuilder
        .source("h5_message_20190916").script(script).filter(QueryBuilders.termQuery("agent_id", 2))
        .abortOnVersionConflict(false).get();
    List<BulkItemResponse.Failure> bulkFailures = bulkByScrollResponse.getBulkFailures();

    for (BulkItemResponse.Failure bulkFailure : bulkFailures) {
      System.out.println(bulkFailure.getMessage());
    }

    long end = System.currentTimeMillis();
    System.out.println((end - begin) / 1000);


  }

  @Test
  public void testSearchExists() {
    TransportClient client = esRepository.getClient();

    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    boolQueryBuilder.must(QueryBuilders.termQuery("name", "zhangsan"));
    boolQueryBuilder.must(QueryBuilders.termQuery("age", 20));

    //查询class字段必须为a
    BoolQueryBuilder boolQueryBuilderClass1 = QueryBuilders.boolQuery();
    boolQueryBuilderClass1.must(QueryBuilders.termQuery("class", "a"));
    boolQueryBuilder.should(boolQueryBuilderClass1);

    //查询class字段不存在
    BoolQueryBuilder boolQueryBuilderClass2 = QueryBuilders.boolQuery();
    boolQueryBuilderClass2.mustNot(QueryBuilders.existsQuery("class"));
    boolQueryBuilder.should(boolQueryBuilderClass2);

    SearchResponse searchResponse = client.prepareSearch("bangcle_test")
        .setQuery(boolQueryBuilder).get();

    SearchHit[] hits = searchResponse.getHits().getHits();
    for (SearchHit hit : hits) {
      System.out.println(hit.getId());
    }

  }
}
