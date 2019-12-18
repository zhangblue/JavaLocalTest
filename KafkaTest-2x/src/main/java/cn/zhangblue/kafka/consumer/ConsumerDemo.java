package cn.zhangblue.kafka.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * kafka 消息消费者
 */
public class ConsumerDemo {

  public static void main(String[] args) {
    Properties props = new Properties();
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
    consumer.subscribe(Arrays.asList("my-topic"));
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(5000));
    for (ConsumerRecord<String, String> record : records) {
      System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
          record.value());
    }
//      consumer.commitSync();
    consumer.close();
  }

}
