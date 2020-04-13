import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

  @org.junit.Test
  public void test001() {
    File file = new File("/Volumes/didi/data");

    List<File> files = Arrays.asList(file.listFiles());

    files.sort(new Comparator<File>() {
      @Override
      public int compare(File o1, File o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    files.forEach(x -> System.out.println(x.getName()));
  }

  @org.junit.Test
  public void test002() {



  }

  @org.junit.Test
  public void test003() {
    Path path = Paths.get("a", "b", "c");
    path.resolve("d");

    System.out.println(path.toString());

  }

  @org.junit.Test
  public void test004() {
    List<String> list = new ArrayList<>(3);
    list.add("aaa_20190101");
    list.add("aaa_20190103");
    list.add("aaa_20190102");
    list.sort(new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        String[] o1Split = o1.split("_");
        String[] o2Split = o2.split("_");
        return o1Split[o1Split.length - 1].compareTo(o2Split[o2Split.length - 1]);
      }
    });

    list.forEach(x -> System.out.println(x));
  }

  @org.junit.Test
  public void test005() {
    String time = "20190101";
    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
    long time1 = 0;
    try {
      time1 = myFormat.parse(time).getTime();
      System.out.println(time1);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}
