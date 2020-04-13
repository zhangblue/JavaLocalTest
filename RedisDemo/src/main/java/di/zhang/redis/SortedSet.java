package di.zhang.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * @author zhangdi
 */
public class SortedSet {

  public static void main(String[] args) {
    Jedis redisConn = new RedisConnect().createConnect("172.16.36.117", 6379, 1);

    SortedSet sortedSet = new SortedSet();
//    sortedSet.putData(redisConn);

    sortedSet.rangeData(redisConn);

    redisConn.close();
  }

  public void putData(Jedis redisConn) {
    long time = System.currentTimeMillis() / 1000;
    for (int i = 0; i < 10; i++) {
      redisConn.zadd("di.zhang", time + i * 60L, "zhangsan" + i);
    }
    System.out.println(time);
  }

  public void rangeData(Jedis redisConn) {
    Set<Tuple> tuples = redisConn.zrangeByScoreWithScores("di.zhang", 1576570464, 1576570464 + 120);


    for (Tuple tuple : tuples) {
      System.out.println( new Double(tuple.getScore()).longValue() +"----"+tuple.getElement());
    }
  }
}
