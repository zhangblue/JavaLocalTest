import com.zhangblue.demo.CurrentCache;
import org.junit.Test;

public class TestMyMapCache {

  @Test
  public void dorun1() {
    CurrentCache<String, String> stringStringCurrentCache = new CurrentCache<>(100);

    for (int i = 0; i < 103; i++) {
      stringStringCurrentCache.put(i + "i", "" + i);
    }

    System.out.println(stringStringCurrentCache.size());
  }

}
