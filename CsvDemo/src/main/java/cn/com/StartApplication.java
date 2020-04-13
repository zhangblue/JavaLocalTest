package cn.com;

import cn.com.model.PersonModel;
import cn.com.service.CSVOperating;
import com.opencsv.CSVReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StartApplication
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/06/24 14:57
 **/
public class StartApplication {


  public static void main(String[] args) {
    try {

      Path path = Paths.get("/Users/zhangdi/Downloads/用户文件(2017-05-08)");
      File[] list = path.toFile().listFiles();

      StartApplication startApplication = new StartApplication();
      for (File one : list) {
        startApplication.readCsv(Paths.get(one.getPath()));
        System.out.println("finish = [" + one + "]");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public void readCsv(Path file) throws IOException {
    Path fileName = file.getFileName();
    Path destFile = Paths.get("/Users/zhangdi/Documents/工作/梆梆/iptv/地理位置映射表/入库程序/")
        .resolve("test-" + fileName);

    CSVReader reader = new CSVReader(
        new InputStreamReader(new FileInputStream(file.toFile()), "GBK"));

    reader.readNext();
    List<String[]> strings = reader.readAll();

    List<String> collect = strings.stream().map(x -> x[0] + "," + x[4] + "," + x[5])
        .collect(Collectors.toList());

    Files.write(destFile, collect, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
  }


}
