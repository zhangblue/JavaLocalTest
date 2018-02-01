package cn.com.demo.test;


import cn.com.repository.EsRepository;
import com.alibaba.fastjson.JSONArray;
import java.util.Map;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ESTest {

  private EsRepository esRepository;


  @Before
  public void before() throws Exception {
    esRepository = new EsRepository();
    esRepository.buildClient();
  }

  @After
  public void after() throws Exception {
    EsRepository.client.close();
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

    TermsQueryBuilder gyroscopeQueryBuilder = QueryBuilders.termsQuery("protol_type","gyroscope");
    boolQueryBuilder.must(gyroscopeQueryBuilder);

//    ExistsQueryBuilder existsQueryBuilder = QueryBuilders.existsQuery("time_zone");
//    boolQueryBuilder.must(existsQueryBuilder);

    GetRequestBuilder getRequestBuilder = EsRepository.client.prepareGet("","","");


    SearchRequestBuilder searchRequestBuilder = EsRepository.client.prepareSearch(strIndexs)
        .setTypes("bangcle_type").setQuery(boolQueryBuilder).setSize(1);

    SearchResponse searchResponse = searchRequestBuilder.get();

    SearchHit[] searchHits = searchResponse.getHits().getHits();
    System.out.println(searchHits.length);

    for (SearchHit searchHit : searchHits) {

      Map<String, Object> mapSource = searchHit.getSourceAsMap();
      System.out.println(mapSource.get("gyroscope").toString());
    }
  }

  @Test
  public void doTest2() throws Exception {


    GetRequestBuilder getRequestBuilder = EsRepository.client.prepareGet("index_message_20180201","bangcle_type","172.16.18.160_1517486253764_efcc6b7e-92d4-3ca2-8212-fae41fd17be9_102");


    GetResponse getResponse = getRequestBuilder.get();

    String str = getResponse.getSourceAsMap().get("gyroscope").toString();

    JSONArray jsonArray = JSONArray.parseArray(str);

    for(Object jsonObject:jsonArray.toArray()){
      System.out.println(jsonObject.toString());
    }

  }

}
