package cn.com;

import cn.com.model.PersonModel;
import cn.com.service.CSVOperating;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StartApplication
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/06/24 14:57
 **/
public class StartApplication {


  public static void main(String[] args) {
    writeWithLines();
  }


  public static void writeWithLines() {
    List<String[]> listData = new ArrayList<>(100);
    CSVOperating<PersonModel> csvOperating = new CSVOperating<PersonModel>();
    for (int i = 0; i < 20; i++) {
      if (i % 3 == 0) {
        String[] strLine = new String[]{"=\"10000000000000000\"", null, "男", null, null};
        listData.add(strLine);
      } else {
        String[] strLine = new String[]{"张" + i, "年龄" + i, "春春", "phone" + i, "地址" + i};
        listData.add(strLine);
      }
    }
    String s = csvOperating.writeCSVFile(listData, "/Users/zhangdi/Downloads/", new String[]{"姓名", "年龄", "性别", "手机号", "地址"}, false);
    System.out.println(s);
  }


  public static void writeWithBean() {
    List<PersonModel> listData = new ArrayList<>(100);

    for (int i = 0; i < 10; i++) {

      if (i % 2 == 0) {
        PersonModel personModel = new PersonModel(null, null, null, "phone=" + i, null);
        listData.add(personModel);
      } else {
        PersonModel personModel = new PersonModel("/user//zhangblue=" + i, i + "", 1 + "", "phone=" + i, "addres=" + i);
        listData.add(personModel);
      }
    }

    CSVOperating<PersonModel> csvOperating = new CSVOperating<PersonModel>();
    csvOperating.writeCSVFile(listData, PersonModel.class, "/Users/zhangdi/Downloads/test.csv", new String[]{"name", "age", "sex", "phone", "address"},
        new String[]{"姓名", "年龄", "性别", "手机号", "地址"}, false);
  }

}
