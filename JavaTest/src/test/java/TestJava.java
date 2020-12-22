import com.zhangblue.demo.Users;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class TestJava {


  @Test
  public void test001() {
    Object o = new Object();
    System.out.println(ClassLayout.parseInstance(o).toPrintable());
  }

  @Test
  public void test002() {
    Users users = new Users();
    users.setId(Integer.MAX_VALUE);
    users.setName("aaaa");
    System.out.println(ClassLayout.parseInstance(users).toPrintable());
  }

  @Test
  public void test003() {
    long[] l = new long[3];
    l[1] = 100L;
    System.out.println(ClassLayout.parseInstance(l).toPrintable());

  }

  @Test
  public void test() {
    try {
      for (int i = 0; i < 10; i++) {
        if (i == 5) {
          Integer.parseInt("a");
        }
        System.out.println(i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
