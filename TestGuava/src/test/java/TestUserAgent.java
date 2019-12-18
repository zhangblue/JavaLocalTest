import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;
import org.junit.Test;

import java.io.IOException;

public class TestUserAgent {

  @Test
  public void test01() {

    String ua = "Mozilla/5.0 (Linux; U; Android 8.0.0; zh-CN; EVA-AL10 Build/HUAWEIEVA-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 UCBrowser/12.2.5.1005 Mobile Safari/537.36";

    try {
      UASparser uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());

      UserAgentInfo userAgentInfo = uaSparser.parse(ua);

      System.out.println("操作系统名称：" + userAgentInfo.getOsFamily());//
      System.out.println("操作系统：" + userAgentInfo.getOsName());//
      System.out.println("浏览器名称：" + userAgentInfo.getUaFamily());//
      System.out.println("浏览器版本：" + userAgentInfo.getBrowserVersionInfo());//
      System.out.println("设备类型：" + userAgentInfo.getDeviceType());
      System.out.println("浏览器:" + userAgentInfo.getUaName());
      System.out.println("类型：" + userAgentInfo.getType());
      System.out.println("-----");
//      System.out.println(userAgentInfo.toString());

      String fromSource = "N/A";
      String equipmentType = "N/A";
      if (null != userAgentInfo) {
        if ("Smartphone".equals(userAgentInfo.getDeviceType())) {
          fromSource = "mobile";
        } else if ("Personal computer".equals(userAgentInfo.getDeviceType())) {
          fromSource = "pc";
        }
        equipmentType = userAgentInfo.getOsName() + " " + userAgentInfo.getUaFamily();
      }

      System.out.println("from_source = " + fromSource);
      System.out.println("equipment_type = " + equipmentType);


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
