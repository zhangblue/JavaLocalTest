package cn.com.demo.test;

import cn.com.repository.EsRepository;
import java.util.Arrays;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author di.zhang
 * @date 2020/10/10
 * @time 15:12
 **/
public class TestSearch {

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
  public void searchCount() {
    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
    bqb.must(QueryBuilders.termsQuery("agent_id", Arrays.asList("621")));
    SearchResponse searchResponse = esRepository.getClient()
        .prepareSearch("bangcle_threat_20201009","bangcle_threat_20201109")
        .setTypes("bangcle_type")
        .setQuery(bqb)
        .setSize(0)
        .execute()
        .actionGet();


    System.out.println(searchResponse.getHits().totalHits);
  }
}
