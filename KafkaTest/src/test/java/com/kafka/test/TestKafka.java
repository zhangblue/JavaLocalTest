package com.kafka.test;

import com.zhangblue.properties.KafkaProperty;
import com.zhangblue.service.KafkaConsumerService;
import com.zhangblue.service.KafkaProducerService;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

public class TestKafka {

  @Test
  public void testProducer() {
    KafkaProperty kafkaProperty = new KafkaProperty();
    kafkaProperty.setBootstrapServers("vm-22:9092");
    kafkaProperty.setTopicName("zhangd_test");
    KafkaProducerService kafkaProducerService = new KafkaProducerService(kafkaProperty);
    kafkaProducerService.initProducer();

    try {
      TimeUnit.HOURS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testConsumer() {
    KafkaConsumerService kafkaConsumerService = new KafkaConsumerService();
    Properties zhangdConsumer = kafkaConsumerService
        .createConsumerProperty("vm-21:9092", "zhangd_test", false);
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(zhangdConsumer);
    consumer.subscribe(Arrays.asList("bangcle_message_decrypt_4601"));
    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(5000);
      if (records.count() > 0) {
        System.out.println("now count = "+records.count());
        for (ConsumerRecord<String, String> record : records) {
          System.out.println("offset =" + record.offset());
        }
      } else {
        System.out.println("count <=0");
      }
    }
  }

  @Test
  public void testLagOf(){

  }
}
