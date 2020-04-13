package cn.zhangblue.kafka.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * kafka 生产者
 */
public class ProducerDemo {

  public static void main(String[] args) {
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
    properties
        .setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());

    Producer<String, String> producer = new KafkaProducer<String, String>(properties);

    for (int i = 0; i < 100; i++) {
      producer.send(
          new ProducerRecord<String, String>("my-topic", "key" + i, "value" + i),
          new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
              if (null != e) {
                e.printStackTrace();
              }
            }
          });
      System.out.println("i=" + i);
    }

    producer.flush();
    producer.close();
  }

}
