package cn.com.guava.utils;

import com.google.common.base.CharMatcher;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class TestFunction {


  /**
   * 测试获取目录下所有文件
   * @throws Exception
   */
  @Test
  public void getFileTraversalTest() throws Exception{
    GuavaFilesTools guavaFilesTools = new GuavaFilesTools();
    Iterator<File> fileIterator = guavaFilesTools.getFileTraversal("/Users/zhangdi/test_folder");

    while(fileIterator.hasNext()){
      System.out.println(fileIterator.next().getPath());
    }

  }


  @Test
  public void getJavaException() throws Exception{
    GuavaStringTools guavaStringTools = new GuavaStringTools();
    String str = "java.lang.Exception: java.lang.RuntimeException: Unable to start activityComponentInfocom.xcwef.wafeaf.fzfemm.ecom.wg.dreampet.GameActivity: java.lang.NullPointerException at com.bangcle.everisk.c.j.uncaughtException(SourceFile:29) at java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:693) at java.lang.ThreadGroup.uncaughtException(ThreadGroup.java:690)";

    String pattern = ":(.*?):";

    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(str);

    if(m.find()){


      System.out.println("ccc="+CharMatcher.anyOf(":").removeFrom(m.group()));

      System.out.println("ccc="+CharMatcher.anyOf(":").trimFrom(m.group()));

      System.out.println("aaa="+guavaStringTools.removeKey(CharMatcher.anyOf(":").removeFrom(m.group())," "));
    }
  }

  @Test
  public void guavaBase64EncodingTest() throws Exception{
    String base64="amF2YS5sYW5nLk51bGxQb2ludGVyRXhjZXB0aW9uCglhdCBjb20uZXhhbXBsZS5iYW5nY2xlLnNka190ZXN0X2FwcC5DcmFzaExpc3Rlbm5lci5vbkNsaWNrKENyYXNoTGlzdGVubmVyLmphdmE6MzQpCglhdCBhbmRyb2lkLnZpZXcuVmlldy5wZXJmb3JtQ2xpY2soVmlldy5qYXZhOjQ0MzgpCglhdCBhbmRyb2lkLnZpZXcuVmlldyRQZXJmb3JtQ2xpY2sucnVuKFZpZXcuamF2YToxODQ3MykKCWF0IGFuZHJvaWQub3MuSGFuZGxlci5oYW5kbGVDYWxsYmFjayhIYW5kbGVyLmphdmE6NzMzKQoJYXQgYW5kcm9pZC5vcy5IYW5kbGVyLmRpc3BhdGNoTWVzc2FnZShIYW5kbGVyLmphdmE6OTUpCglhdCBhbmRyb2lkLm9zLkxvb3Blci5sb29wKExvb3Blci5qYXZhOjEzNikKCWF0IGFuZHJvaWQuYXBwLkFjdGl2aXR5VGhyZWFkLm1haW4oQWN0aXZpdHlUaHJlYWQuamF2YTo1MTIwKQoJYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZU5hdGl2ZShOYXRpdmUgTWV0aG9kKQoJYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZShNZXRob2QuamF2YTo1MTUpCglhdCBjb20uYW5kcm9pZC5pbnRlcm5hbC5vcy5aeWdvdGVJbml0JE1ldGhvZEFuZEFyZ3NDYWxsZXIucnVuKFp5Z290ZUluaXQuamF2YTo4MTgpCglhdCBjb20uYW5kcm9pZC5pbnRlcm5hbC5vcy5aeWdvdGVJbml0Lm1haW4oWnlnb3RlSW5pdC5qYXZhOjYzNCkKCWF0IGRlLnJvYnYuYW5kcm9pZC54cG9zZWQuWHBvc2VkQnJpZGdlLm1haW4oWHBvc2VkQnJpZGdlLmphdmE6MTMyKQoJYXQgZGFsdmlrLnN5c3RlbS5OYXRpdmVTdGFydC5tYWluKE5hdGl2ZSBNZXRob2QpCg==";

    GuavaStringTools guavaStringTools = new GuavaStringTools();
    String strReturn = guavaStringTools.base64Encoding(base64);

    System.out.println(strReturn);

    System.out.println("---------");

    List<String> list = guavaStringTools.splitString(strReturn,"\n",true);

    System.out.println(list.get(0));
  }

  @Test
  public void base64Decoding() throws Exception{
    String base64="amF2YS5sYW5nLkV4Y2VwdGlvbjogamF2YS5sYW5nLlJ1bnRpbWVFeGNlcHRpb246IFVuYWJsZSB0byBzdGFydCBhY3Rpdml0eUNvbXBvbmVudEluZm9jb20ueGN3ZWYud2FmZWFmLmZ6ZmVtbS5lY29tLndnLmRyZWFtcGV0LkdhbWVBY3Rpdml0eTogamF2YS5sYW5nLk51bGxQb2ludGVyRXhjZXB0aW9uIGF0IGNvbS5iYW5nY2xlLmV2ZXJpc2suYy5qLnVuY2F1Z2h0RXhjZXB0aW9uKFNvdXJjZUZpbGU6MjkpIGF0IGphdmEubGFuZy5UaHJlYWRHcm91cC51bmNhdWdodEV4Y2VwdGlvbihUaHJlYWRHcm91cC5qYXZhOjY5MykgYXQgamF2YS5sYW5nLlRocmVhZEdyb3VwLnVuY2F1Z2h0RXhjZXB0aW9uKFRocmVhZEdyb3VwLmphdmE6NjkwKSA=";

    String ret = new String(Base64.decodeBase64(base64.getBytes()));
    System.out.println(ret);

    System.out.println("-------");
  }

}
