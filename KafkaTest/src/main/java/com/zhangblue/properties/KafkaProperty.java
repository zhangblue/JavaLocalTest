package com.zhangblue.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangdi
 */
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaProperty {
  private String bootstrapServers;
  private String topicName;


}
