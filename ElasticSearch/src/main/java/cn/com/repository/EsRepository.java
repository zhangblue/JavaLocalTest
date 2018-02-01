package cn.com.repository;

import com.google.common.base.Splitter;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.MapBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Created by zhaogj on 16/02/2017.
 */
public class EsRepository {

  public static TransportClient client = null;
  public static BulkProcessor bulkProcessor = null;
  private String strClusterName = "bangcle_es";
  public static String strType = "bangcle_type";//写死的值，后续会废弃
  private String strTransportHostNames = "172.16.31.99";
  private Splitter splitter = Splitter.on(",").trimResults();

  /**
   * build es client.
   */
  public void buildClient() throws Exception {
    Settings settings = Settings.builder()
        .put("cluster.name", strClusterName)
        .put("client.transport.sniff", false)//5.4.0版本设置为false，不然会告警，不知道为啥
        .build();
    Iterable<String> itTransportHostName = splitter.split(strTransportHostNames);
    client = new PreBuiltTransportClient(settings);
    for (String strTransportHostName : itTransportHostName) {
      client.addTransportAddress(
          new TransportAddress(InetAddress.getByName(strTransportHostName), 9300));
    }
  }

  /**
   * build bulk processor.
   */
  public void buildBulkProcessor() throws Exception {
    bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
      public void beforeBulk(long l, BulkRequest bulkRequest) {

      }

      public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {

      }

      public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {

      }
    }).setBulkActions(1000)
        .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
        .setFlushInterval(TimeValue.timeValueSeconds(10))
        .setConcurrentRequests(3)
        .setBackoffPolicy(
            BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
        .build();
  }

  /**
   * close es client.
   */
  public void closeClient() {
    if (client != null) {
      client.close();
    }
  }

  /**
   * close bulk processor.
   */
  public void closeBulkProcessor() {
    if (bulkProcessor != null) {
      bulkProcessor.close();
    }
  }

  /**
   * delete template.
   */
  public void deleteTemplate(String strTemplateName) {
    client.admin().indices().prepareDeleteTemplate(strTemplateName).get();
  }

  /**
   * put template.
   */
  public void putTemplate() {
    try {
      Map<String, Object> settings = new MapBuilder<String, Object>()
          .put("number_of_shards", 1)
          .put("number_of_replicas", 0)
          .put("refresh_interval", "10s")
          .map();
      Map<String, Object> mapping = new HashMap<String, Object>();
      mapping.put("_all", new MapBuilder<String, Object>().put("enabled", false).map());
      mapping.put("numeric_detection", false);
      mapping.put("dynamic_templates",
          new Object[]{
              new MapBuilder<String, Object>().put("date_tpl",
                  new MapBuilder<String, Object>().put("match", "dt*")
                      .put("mapping",
                          new MapBuilder<String, Object>().put("type", "date")
                              .map())
                      .map())
                  .map(),
              new MapBuilder<String, Object>().put("geo_point_tpl",
                  new MapBuilder<String, Object>().put("match", "geop*")
                      .put("mapping",
                          new MapBuilder<String, Object>().put("type", "geo_point")
                              .map())
                      .map())
                  .map(),
              new MapBuilder<String, Object>().put("ip_tpl",
                  new MapBuilder<String, Object>().put("match", "ip*")
                      .put("mapping",
                          new MapBuilder<String, Object>().put("type", "ip")
                              .map())
                      .map())
                  .map(),
              new MapBuilder<String, Object>().put("obj_tpl",
                  new MapBuilder<String, Object>().put("match", "obj*")
                      .put("mapping",
                          new MapBuilder<String, Object>().put("type", "object")
                              .map())
                      .map())
                  .map(),
              new MapBuilder<String, Object>().put("all_tpl",
                  new MapBuilder<String, Object>().put("match", "*").put("mapping",
                      new MapBuilder<String, Object>().put("type", "keyword")
                          .map())
                      .map())
                  .map()});
      client.admin().indices().preparePutTemplate("template_bangcle")
          .setPatterns(Collections.singletonList("index_*"))
          .setSettings(settings)
          .setOrder(0)
          .addMapping("_default_", mapping)
          .get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * check index exist.
   */
  public boolean exists(String strIndex) {
    IndicesExistsRequest request = new IndicesExistsRequest(strIndex);
    IndicesExistsResponse response = client.admin().indices().exists(request).actionGet();
    if (response.isExists()) {
      return true;
    }
    return false;
  }

  /**
   * delete index.
   */
  public void delete(String strIndex) {
    if (exists(strIndex)) {
      client.admin().indices().prepareDelete(strIndex).get();
    }
  }

  /**
   * create index.
   */
  public void create(String strIndex, int numShards, int numReplicas) {
    client.admin().indices().prepareCreate(strIndex)
        .setSettings(Settings.builder()
            .put("index.number_of_shards", numShards)
            .put("index.number_of_replicas", numReplicas)
            .put("index.refresh_interval", "10s")
        ).get();
  }

  /**
   * update mapping.
   */
  public void putMapping(String strIndex, String strMapping) {
    try {
      client.admin().indices().preparePutMapping(strIndex)
          .setType(strType)
          .setSource(strMapping, XContentType.JSON)
          .get();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * init client and bulk processor.
   */
  private void esInit() {
    try {
      buildClient();
      buildBulkProcessor();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}