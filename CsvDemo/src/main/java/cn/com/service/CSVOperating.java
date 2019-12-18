package cn.com.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.List;

/**
 * @ClassName CSVOperating
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/06/24 14:51
 **/
public class CSVOperating<T> {

  public void writeCSVFile(List<T> listData, Class<T> classType, String finalPath, String[] columnMapping, String[] headers, boolean append) {




    Writer writer = null;
    try {
      writer = new FileWriter(finalPath, append);

      if (!append) {
        // 手动加上BOM标识
        writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
        //写表头
        CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, '\\', CSVWriter.RFC4180_LINE_END);
        csvWriter.writeNext(headers);
      }

      //设置显示顺序
      ColumnPositionMappingStrategy<T> mapper =
          new ColumnPositionMappingStrategy<T>();
      mapper.setType(classType);
      mapper.setColumnMapping(columnMapping);

      StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
          .withMappingStrategy(mapper)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
          .withEscapechar('\\').build();
      beanToCsv.write(listData);


    } catch (IOException e) {
      e.printStackTrace();
    } catch (CsvRequiredFieldEmptyException e) {
      e.printStackTrace();
    } catch (CsvDataTypeMismatchException e) {
      e.printStackTrace();
    } finally {
      if (null != writer) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  /**
   * 写csv文件
   *
   * @param listData 数据内容
   * @param finalPath 写文件的目录
   * @param headers 表头
   * @param append 是否要追加
   */
  public String writeCSVFile(List<String[]> listData, String finalPath, String[] headers, boolean append) {
    long l = System.nanoTime();
    String csvFile = Paths.get(finalPath).resolve(String.valueOf(l) + ".csv").toString();
    Writer writer = null;
    try {
      writer = new FileWriter(csvFile, append);
      CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, '\\', CSVWriter.RFC4180_LINE_END);
      if (!append) {
        // 手动加上BOM标识
        writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
        //写表头
        csvWriter.writeNext(headers);
      }
      if (!listData.isEmpty()) {
        csvWriter.writeAll(listData);
        csvWriter.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      listData.clear();
      if (null != writer) {
        try {
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return csvFile;
    }
  }
}
