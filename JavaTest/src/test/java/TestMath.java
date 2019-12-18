import com.sun.org.apache.xpath.internal.operations.Mod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMath {

  @Test
  public void test() {

    double n = 11.3;

    List<Model> listMode = new ArrayList<>(10);
    listMode.add(new Model(5.1, 7.7, 1, "测试1"));
    listMode.add(new Model(10, 13.5, 2, "测试2"));
    listMode.add(new Model(90, 92.3, 3, "测试3"));
    listMode.add(new Model(53.5, 55, 4, "测试4"));
    listMode.add(new Model(70, 77, 5, "测试5"));
    listMode.add(new Model(50, 52, 6, "测试6"));
    listMode.add(new Model(43, 47, 7, "测试7"));

    ConcurrentSkipListMap<Double, Model> skipListMap = new ConcurrentSkipListMap<Double, Model>();
    listMode.forEach(x -> skipListMap.put(x.getMinScore(), x));

    Map.Entry<Double, Model> doubleModelEntry = skipListMap.headMap(n, true).lastEntry();
    if (doubleModelEntry.getValue().getMaxScore() >= n) {
      System.out.println("yes---" + doubleModelEntry.getValue().getMessage());
    } else {
      System.out.println("no");
    }
  }

  @Test
  public void testNio() {
    final Path copy_from = Paths.get("");
    final Path copy_to = Paths.get("");

    try (FileChannel fileChannel_from = (FileChannel.open(copy_from,
        EnumSet.of(StandardOpenOption.READ)));
        FileChannel fileChannel_to = (FileChannel.open(copy_to,
            EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {



      fileChannel_from.transferTo(0L, fileChannel_from.size(), fileChannel_to);
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }
}

