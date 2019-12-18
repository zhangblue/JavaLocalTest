package di.zhang.redis;

import redis.clients.jedis.Jedis;

/**
 * @author zhangdi
 */
public class RedisConnect {

  public Jedis createConnect(String host, int port, int indexNum) {
    Jedis jedis = new Jedis(host, port);
    jedis.select(indexNum);
    return jedis;
  }
}
