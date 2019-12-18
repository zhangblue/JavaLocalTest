package cn.zhangblue.kafka.consumer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 指定offset开始消费
 *
 * @author zhangdi
 */
public class ConsumerBySeekDemo {

  public static void main(String[] args) {
    consumerByDuration();
  }

  /**
   * API实现 消费者从Earliest开始消费
   */
  public static void consumerByEarliest() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");
    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);
      /**
       * seekToBeginning方法必须在调用poll方法之后执行
       */
      consumer.seekToBeginning(
          consumer.partitionsFor(topic).stream().map(partitionInfo ->
              new TopicPartition(topic, partitionInfo.partition()))
              .collect(Collectors.toList()));

      while (true) {
        //此处为从最早offset开始消费的主要逻辑
        ConsumerRecords<String, String> poll1 = consumer.poll(Duration.ofSeconds(5));
        System.out.println("poll1 = " + poll1.count());
      }
    }
  }

  /**
   * API实现，消费者从Latest开始消费
   */
  public static void consumerByLatest() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);
      /**
       * seekToEnd方法必须在调用poll方法之后执行
       */
      consumer.seekToEnd(
          consumer.partitionsFor(topic).stream().map(partitionInfo ->
              new TopicPartition(topic, partitionInfo.partition()))
              .collect(Collectors.toList()));

      while (true) {
        //此处为从最早offset开始消费的主要逻辑
        ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
          System.out.println(stringStringConsumerRecord.offset());
        }
      }
    }
  }


  /**
   * API实现，消费者从Current开始消费
   */
  public static void consumerByCurrent() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);
      /**
       * 依次获取每个对应分区上的已提交位移数，并通过seek方法进行重置
       */
      consumer.partitionsFor(topic).stream()
          .map(info -> new TopicPartition(topic, info.partition())).forEach(topicPartition -> {
        long commitedOffset = consumer.committed(topicPartition).offset();
        System.out.println(topicPartition + "---------" + commitedOffset);
        consumer.seek(topicPartition, commitedOffset);
      });

      ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
      System.out.println(poll.count());
    }
  }

  /**
   * API实现，把位移调整到指定位置
   */
  public static void consumerBySpecifiedOffset() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);
      /**
       * 依次获取每个对应分区上的已提交位移数，并通过seek方法进行重置
       */
      long targetOffset = 200L;//要直接跳到的offset位置
      consumer.partitionsFor(topic).stream()
          .map(info -> new TopicPartition(topic, info.partition())).forEach(topicPartition -> {
        consumer.seek(topicPartition, targetOffset);
      });

      ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
      System.out.println(poll.count());
    }
  }

  /**
   * API实现，跳过指定数量的消息
   */
  public static void consumerByShiftByN() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);
      /**
       * 依次获取每个对应分区上的已提交位移数，并通过seek方法进行重置
       */
      consumer.partitionsFor(topic).stream()
          .map(info -> new TopicPartition(topic, info.partition())).forEach(topicPartition -> {
        long targetOffset = consumer.committed(topicPartition).offset() + 100;
        consumer.seek(topicPartition, targetOffset);
      });

      ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
      System.out.println(poll.count());
    }
  }

  /**
   * 将offset跳到指定日期的位置
   */
  public static void consumerByDateTime() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);

      /**
       * 重设位移到 2019 年 12 月 17 日早上 8 点
       */
      long ts = LocalDateTime.of(2019, 12, 17, 8, 0).toInstant(ZoneOffset.ofHours(8))
          .toEpochMilli();
      System.out.println(ts);

      Map<TopicPartition, Long> timeToSearch = consumer.partitionsFor(topic).stream()
          .map(info -> new TopicPartition(topic, info.partition())).collect(Collectors.toMap(
              Function.identity(), tp -> ts));

      consumer.offsetsForTimes(timeToSearch).forEach((k, v) -> consumer.seek(k, v.offset()));

      ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
      System.out.println(poll.count());
    }
  }


  /**
   * 将offset跳到当前日期之前的某个offset
   */
  public static void consumerByDuration() {
    Properties consumerProperties = new Properties();
    consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
    consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProperties
        .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties
        .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");

    String topic = "my-topic";  // 要重设位移的Kafka主题
    try (final KafkaConsumer<String, String> consumer =
        new KafkaConsumer<>(consumerProperties)) {
      consumer.subscribe(Collections.singleton(topic));
      /**
       * 此处必须使用 KafkaConsumer.poll(long timeoutMs) 函数，若使用KafkaConsumer.poll(Duration var1)则会报错
       */
      consumer.poll(0);

      /**
       * 重设位移 调回 30 分钟前
       */
      Map<TopicPartition, Long> timeToSearch = consumer.partitionsFor(topic).stream()
          .map(info -> new TopicPartition(topic, info.partition())).collect(Collectors.toMap(
              Function.identity(), tp -> System.currentTimeMillis() - 1000 * 60 * 30));

      consumer.offsetsForTimes(timeToSearch).forEach((k, v) -> consumer.seek(k, v.offset()));

      ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
      System.out.println(poll.count());
    }
  }

}
