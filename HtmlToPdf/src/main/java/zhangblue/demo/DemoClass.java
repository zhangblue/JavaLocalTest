package zhangblue.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class DemoClass {


  public void htmlToString() {
    StringBuilder strline = new StringBuilder("");
    File fin = new File("C:\\Users\\Administrator\\Desktop\\共享数据质量报告(1)\\共享数据质量报告\\index.html");
    try (RandomAccessFile accessFile = new RandomAccessFile(fin, "r");
        FileChannel fcin = accessFile.getChannel();
    ) {
      Charset charset = Charset.forName("UTF-8");
      int bufSize = 100000;
      ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
      String enterStr = "\n";
      byte[] bs = new byte[bufSize];

      StringBuilder strBuf = new StringBuilder("");
      while (fcin.read(rBuffer) != -1) {
        int rSize = rBuffer.position();
        rBuffer.rewind();
        rBuffer.get(bs);
        rBuffer.clear();
        String tempString = new String(bs, 0, rSize, charset);
        tempString = tempString.replaceAll("\r", "");

        int fromIndex = 0;
        int endIndex = 0;
        while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
          String line = tempString.substring(fromIndex, endIndex);
          line = strBuf.toString() + line;
          strline.append(line.trim());

          strBuf.delete(0, strBuf.length());
          fromIndex = endIndex + 1;
        }
        if (rSize > tempString.length()) {
          strline.append(tempString.substring(fromIndex, tempString.length()));
          strBuf.append(tempString.substring(fromIndex, tempString.length()));
        } else {
          strline.append(tempString.substring(fromIndex, rSize));
          strBuf.append(tempString.substring(fromIndex, rSize));
        }
      }
      System.out.println(strline.toString().replaceAll("\"", "'"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
