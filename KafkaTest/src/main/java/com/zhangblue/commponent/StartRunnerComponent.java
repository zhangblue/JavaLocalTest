package com.zhangblue.commponent;

import com.zhangblue.properties.KafkaProperty;
import com.zhangblue.service.KafkaConsumerService;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author zhangdi
 */
@Component
@Slf4j
public class StartRunnerComponent implements CommandLineRunner {

  @Autowired
  private KafkaProperty kafkaProperty;

  @Override
  public void run(String... strings) throws IOException {
    KafkaConsumerService kafkaConsumerService = new KafkaConsumerService();

    log.info(kafkaProperty.getBootstrapServers() + "----------" + kafkaProperty.getTopicName());
    Properties consumerProperty = kafkaConsumerService
        .createConsumerProperty(kafkaProperty.getBootstrapServers(), kafkaProperty.getTopicName(),
            true);

    consumerProperty.put(ConsumerConfig.GROUP_ID_CONFIG, "aksdkjahshdkahskdnakjsbdqb");

    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(
        consumerProperty);

    while (true) {
      ConsumerRecords<String, String> poll = kafkaConsumer.poll(1000 * 2);
      Set<TopicPartition> partitions = poll.partitions();
      for (TopicPartition partition : partitions) {
        log.info(partition.partition() + "----" + partition.topic());
      }
      log.info("current count = [{}]", poll.count());
    }
  }


}
