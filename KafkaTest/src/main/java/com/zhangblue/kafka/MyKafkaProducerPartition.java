package com.zhangblue.kafka;

import java.util.Map;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * kafka producer自定义分区接口实现
 *
 * @author zhangdi
 */
public class MyKafkaProducerPartition implements Partitioner {

  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
      Cluster cluster) {
    return 0;
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
