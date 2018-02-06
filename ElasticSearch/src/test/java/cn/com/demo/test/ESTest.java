package cn.com.demo.test;


import cn.com.repository.EsRepository;
import com.alibaba.fastjson.JSONObject;
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
    long end = System.currentTimeMillis();

    System.out.println("all time =" + (end - begin) / 1000);

  }
}
