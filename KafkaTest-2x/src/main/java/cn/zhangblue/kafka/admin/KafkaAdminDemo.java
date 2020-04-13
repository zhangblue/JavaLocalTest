package cn.zhangblue.kafka.admin;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeLogDirsResult;
import org.apache.kafka.common.requests.DescribeLogDirsResponse.LogDirInfo;

/**
 * @author zhangdi
 */
public class KafkaAdminDemo {

//  public static void main(String[] args) {
//    Properties properties = new Properties();
//    properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "office_elastic:9092");
//    properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 600000);
//
//    try (AdminClient client = AdminClient.create(properties)) {
//      DescribeLogDirsResult describeLogDirsResult = client
//          .describeLogDirs(Collections.singleton(0));
//      long size = 0L;
//      Collection<Map<String, LogDirInfo>> values = describeLogDirsResult.all().get().values();
//
//      for (Map<String, LogDirInfo> logDirInfoMap : values) {
//        //得到数据存放的目录:/home/elasticsearch/works/kafka_2.11-2.3.1/kafka_data
//        System.out.println(logDirInfoMap.keySet());
//        //计算总使用量
//        size += logDirInfoMap.values().stream().map(logDirInfo -> logDirInfo.replicaInfos).flatMap(
//            topicPartitionReplicaInfoMap -> topicPartitionReplicaInfoMap.values().stream()
//                .map(replicaInfo -> replicaInfo.size)).mapToLong(Long::longValue).sum();
//      }
//
//      System.out.println(size / 1024 / 1024 + "MB");
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    } catch (ExecutionException e) {
//      e.printStackTrace();
//    }
//  }

  public static void main(String[] args) {
    Set<String> set1 = new HashSet<>();

    Set<String> set2 = new HashSet<>();
    set2.add("a");
    set2.add("b");

    set1.addAll(set2);

    System.out.println(set1);
  }
}
