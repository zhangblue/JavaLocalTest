package com.zhangblue.configuration;

import com.zhangblue.properties.KafkaProperty;
import com.zhangblue.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangdi
 */
@Configuration
@EnableConfigurationProperties(KafkaProperty.class)
public class KafkaProducerServiceAutoConfig {

  @Bean(name = "kafkaProducerService", initMethod = "initProducer")
  public KafkaProducerService kafkaProducerService(@Autowired KafkaProperty kafkaProperty) {
    return new KafkaProducerService(kafkaProperty);
  }




}
