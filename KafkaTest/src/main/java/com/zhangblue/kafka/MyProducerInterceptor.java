package com.zhangblue.kafka;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * kafka producer 拦截器
 *
 * @author zhangdi
 */
public class MyProducerInterceptor implements ProducerInterceptor<String, String> {

  @Override
  public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
    return null;
  }

  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
