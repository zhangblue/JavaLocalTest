package cn.com.demo.test;

import cn.com.repository.EsRepository;
import org.elasticsearch.action.search.SearchRequest;
import org.junit.Test;

public class TestRouting {

  @Test
  public void testRouting1() {

    String id = "b";
    String routing = "b";
    int routing_partition_size = 1;
    int num_primary_shards = 2;

    //shard_num = (hash(_routing) + hash(_id) % routing_partition_size) % num_primary_shards
    int shard_num =
        (routing.hashCode() + id.hashCode() % routing_partition_size) % num_primary_shards;

    //shard_num = hash(_routing) % num_primary_shards
    int shard_num2 = routing.hashCode() % num_primary_shards;

    System.out.println(shard_num);
    System.out.println(shard_num2);



  }


}
