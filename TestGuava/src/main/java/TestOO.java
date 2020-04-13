
import java.io.*;
import java.nio.file.Paths;

public class TestOO {

  public static void main(String[] args) {

    initEsTemplate();
  }

  public static  void initEsTemplate() {
    File file = Paths.get("/Users/zhangdi/work/workspace/bangcle-git/bangcle/h5Server/transfer/config/es_template").toFile();

    File[] filesTemplate = file.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(File dir, String name) {
        if (name.endsWith(".json")) {
          return true;
        } else {
          return false;
        }
      }
    });

    for (File fileItem : filesTemplate) {
      Long filelength = fileItem.length();
      byte[] filecontent = new byte[filelength.intValue()];
      FileInputStream in = null;
      try {
        in = new FileInputStream(fileItem);
        in.read(filecontent);
        in.close();
        in = null;
        String content = new String(filecontent, "UTF-8");
        System.out.println(content);
        System.out.println("--------------");

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (null != in) {
          try {
            in.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      //fileItem.renameTo(new File(fileItem.getPath() + ".loaded"));
    }
  }
}
