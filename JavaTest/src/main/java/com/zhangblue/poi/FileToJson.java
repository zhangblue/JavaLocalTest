package com.zhangblue.poi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author di.zhang
 * @Description: 风险应用文件转化为json格式
 * @date 2020/12/1 上午10:15
 **/
public class FileToJson {


  private final String exe = "[\n"
      + "      {\n"
      + "        \"name\": \"Xposed Installer\",\n"
      + "        \"package\": \"de.robv.android.xposed.installer\",\n"
      + "        \"md5\": \"0AC7584976A96DA619E4D49AD9760CEB\",\n"
      + "        \"platform\": \"android\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"name\": \"Lucky Patcher\",\n"
      + "        \"package\": \"com.android.vending.billing.InAppBillingService.LOCK\",\n"
      + "        \"md5\": \"34017157CE871F963212C302261BA97C\",\n"
      + "        \"platform\": \"android\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"name\": \"RootCloak2\",\n"
      + "        \"package\": \"com.devadvance.rootcloak2\",\n"
      + "        \"md5\": \"C379F91B3595FAA2685D9E7FF79E26F4\",\n"
      + "        \"platform\": \"android\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"name\": \"RootCloakPlus\",\n"
      + "        \"package\": \"com.devadvance.rootcloakplus\",\n"
      + "        \"md5\": \"93707FE19D3CB24E2F6B451357D5E664\",\n"
      + "        \"platform\": \"android\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"name\": \"Xscript\",\n"
      + "        \"package\": \"com.surcumference.xscript\",\n"
      + "        \"md5\": \"E89B158E4BCF988EBD09EB83F5378E87\",\n"
      + "        \"platform\": \"android\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.saurik.Cydia\",\n"
      + "        \"name\": \"Cydia\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.appsec-labs.iNalyzer\",\n"
      + "        \"name\": \"iNalyzer\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.flamingo.XXAssitant\",\n"
      + "        \"name\": \"叉叉助手\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.teiron.pphelperns\",\n"
      + "        \"name\": \"PP助手\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.txyapp.TXYMachine\",\n"
      + "        \"name\": \"冰刃\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.deniu.DaNiu\",\n"
      + "        \"name\": \"Daniu大牛\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"net.85819.ios.OTRLocation\",\n"
      + "        \"name\": \"OTRLocation\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.deniu.DaNiu\",\n"
      + "        \"name\": \"daniu大牛\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      },\n"
      + "      {\n"
      + "        \"package\": \"com.bangcle.riskDemo\",\n"
      + "        \"name\": \"HostCliff\",\n"
      + "        \"platform\": \"ios\"\n"
      + "      }\n"
      + "    ]";


  public static void main(String[] args) {

    try {
      new FileToJson().readExcel("/Users/zhangdi/Downloads/威胁应用APP-20201130-樊林.xlsx");
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public void readExcel(String filePath) throws IOException {
    InputStream fis = new FileInputStream(filePath);
    Workbook workbook = null;
    if (filePath.endsWith(".xlsx")) {
      workbook = new XSSFWorkbook(fis);
    } else if (filePath.endsWith(".xls") || filePath.endsWith(".et")) {
      workbook = new HSSFWorkbook(fis);
    }

    fis.close();

    Sheet sheet = workbook.getSheetAt(0);
    // 获取行
    Iterator<Row> rows = sheet.rowIterator();
    Row row;

    List<JSONObject> jsonObjectList = JSONArray.parseArray(exe).toJavaList(JSONObject.class);

    Set<String> set = new HashSet<>();

    for (JSONObject jsonObject : jsonObjectList) {
      String cc = jsonObject.getString("package") + "_" + jsonObject.getString("platform");
      set.add(cc);
    }

    List<ValueModel> values = new ArrayList<>();
    while (rows.hasNext()) {
      row = rows.next();

      if (set.contains(
          row.getCell(3).getStringCellValue() + "_" + row.getCell(1).getStringCellValue()
              .toLowerCase())) {
        System.out.println(row.getCell(3).getStringCellValue());
      }

      // 获取单元格
      ValueModel valueModel = new ValueModel(row.getCell(3).getStringCellValue(),
          row.getCell(0).getStringCellValue(),
          row.getCell(1).getStringCellValue().toLowerCase(), row.getCell(2).getStringCellValue());
      values.add(valueModel);
    }

    System.out.println(JSONArray.toJSONString(values));
  }


}
