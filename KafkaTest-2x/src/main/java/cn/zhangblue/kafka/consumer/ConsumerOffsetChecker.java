package cn.zhangblue.kafka.consumer;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 用于检查某个groupid 当前没有消费的数据条数
 *
 * @author zhangdi
 */
public class ConsumerOffsetChecker {

  public static void main(String[] args) {
    try {
      Map<TopicPartition, Long> topicPartitionLongMap = lagOf("test-group", "office_elastic:9092");
      topicPartitionLongMap.forEach((k, v) -> System.out.println(k.partition() + "----" + v));

    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }

  /**
   * 检查consumer 消费的 offset
   *
   * @param groupID          消费者组
   * @param bootstrapServers kafka集群地址
   * @return
   * @throws TimeoutException
   */
  public static Map<TopicPartition, Long> lagOf(String groupID, String bootstrapServers)
      throws TimeoutException {
    Properties props = new Properties();
    props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    try (AdminClient client = AdminClient.create(props)) {
      // 重点1：使用AdminClient.listConsumerGroupOffsets 方法获取给定消费者组的最新消费消息的位移
      ListConsumerGroupOffsetsResult result = client.listConsumerGroupOffsets(groupID);
      try {
        Map<TopicPartition, OffsetAndMetadata> consumedOffsets = result
            .partitionsToOffsetAndMetadata().get(10, TimeUnit.SECONDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // 禁止自动提交位移
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class.getName());
        try (final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
          //重点2：获取订阅分区的最新消息位移
          Map<TopicPartition, Long> endOffsets = consumer.endOffsets(consumedOffsets.keySet());
          // 重点3：执行相应的减法操作，获取 Lag 值并封装进一个 Map 对象
          return endOffsets.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(),
              entry -> entry.getValue() - consumedOffsets.get(entry.getKey()).offset()));
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        // 处理中断异常
        // ...
        return Collections.emptyMap();
      } catch (ExecutionException e) {
        // 处理ExecutionException
        // ...
        return Collections.emptyMap();
      } catch (TimeoutException e) {
        throw new TimeoutException("Timed out when getting lag for consumer group " + groupID);
      }
    }
  }
}
