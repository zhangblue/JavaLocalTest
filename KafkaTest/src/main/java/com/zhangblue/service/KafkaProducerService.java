package com.zhangblue.service;

import com.zhangblue.kafka.MyKafkaSendMsgCallback;
import com.zhangblue.properties.KafkaProperty;
import java.util.Properties;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author zhangdi
 */
@Slf4j
public class KafkaProducerService {

  private KafkaProducer<String, String> producer;

  private KafkaProperty kafkaProperty;

  public KafkaProducerService(KafkaProperty kafkaProperty) {
    this.kafkaProperty = kafkaProperty;
  }

  public void initProducer() {
    log.info("create producer begin");
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperty.getBootstrapServers());
    props.put(ProducerConfig.ACKS_CONFIG, "all");
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    producer = new KafkaProducer<String, String>(props);
    log.info("create producer end");
  }

  public String sendMsg(String key, String msg) {
    log.info("send msg begin");
    producer.send(new ProducerRecord<>(kafkaProperty.getTopicName(), key, msg),
        new MyKafkaSendMsgCallback());
    log.info("send msg end");
    return "success";
  }
}
