package com.zhangblue.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * kafka producer 发送消息回调函数
 *
 * @author zhangdi
 */
@Slf4j
public class MyKafkaSendMsgCallback implements Callback {

  @Override
  public void onCompletion(RecordMetadata metadata, Exception exception) {
    if (null != exception) {
      log.error("send msg error=========", exception);
    }
  }
}
