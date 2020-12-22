package cn.com.demo.test;

import cn.com.repository.EsRepository;
import com.alibaba.fastjson.JSONArray;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import java.util.Arrays;
import java.util.List;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesAction;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author di.zhang
 * @date 2020/7/16
 * @time 15:51
 **/
public class TestIndexExists {

  private EsRepository esRepository = null;

  private String INDEX_NAME = "zhangdi_test_001";
  private String ALIAS_NAME = "zhangdi_test_alias";


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
  public void indexExistsTest() {
    IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(INDEX_NAME);
    IndicesExistsResponse indicesExistsResponse = esRepository.getClient().admin().indices()
        .exists(indicesExistsRequest).actionGet();
    if (!indicesExistsResponse.isExists()) {
      CreateIndexRequestBuilder indexTest = esRepository.getClient().admin().indices()
          .prepareCreate(INDEX_NAME);
      indexTest.get();
    }

  }

  @Test
  public void aliasTest() {
    IndicesAliasesRequestBuilder indicesAliasesRequestBuilder = IndicesAliasesAction.INSTANCE
        .newRequestBuilder(esRepository.getClient());
    indicesAliasesRequestBuilder
        .addAlias(INDEX_NAME, ALIAS_NAME);
    indicesAliasesRequestBuilder.get();

    GetAliasesRequest zhangdi_test_alias = new GetAliasesRequest(ALIAS_NAME);
    GetAliasesResponse getAliasesResponse = esRepository.getClient().admin().indices()
        .getAliases(zhangdi_test_alias).actionGet();

    ImmutableOpenMap<String, List<AliasMetaData>> aliases = getAliasesResponse.getAliases();
    String[] indices = zhangdi_test_alias.indices();
    System.out.println(Arrays.asList(indices));
  }

  @Test
  public void closeTest() {

    GetAliasesResponse getAliasesResponse = esRepository.getClient().admin().indices()
        .prepareGetAliases("test-zhangd").get();

    for (ObjectObjectCursor<String, List<AliasMetaData>> alias : getAliasesResponse.getAliases()) {
      System.out.println(alias.key + "====" + JSONArray.toJSONString(alias.value));
    }

//    IndicesStatsResponse indicesStatsResponse = esRepository.getClient().admin().indices()
//        .prepareStats("bangcle_dev_fingerprint_20201021").get();

//    int status = indicesStatsResponse.getStatus().getStatus();

//    System.out.println(status);

  }

}
