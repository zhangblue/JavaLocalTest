import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
    String json = "[{\"app_name\":\"\",\"cert_md5\":\"\",\"uname\":\"u0_a16\",\"pname\":\"android.process.acore\",\"startup\":true,\"package_name\":\"android.process.acore\"},{\"app_name\":\"amigo桌面\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a33\",\"pname\":\"com.gionee.amisystem\",\"startup\":true,\"package_name\":\"com.gionee.amisystem\"},{\"app_name\":\"高德地图\",\"cert_md5\":\"3f9eaea4f2d4285c2ddbbda739136479\",\"uname\":\"u0_a120\",\"pname\":\"com.autonavi.minimap:lotuspool\",\"startup\":true,\"package_name\":\"com.autonavi.minimap\"},{\"app_name\":\"首约司机\",\"cert_md5\":\"c256fc911a2ff01226d426fec1fd2dfb\",\"uname\":\"u0_a119\",\"pname\":\"com.ichinait.gbdriver\",\"startup\":true,\"package_name\":\"com.ichinait.gbdriver\"},{\"app_name\":\"系统升级\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a48\",\"pname\":\"gn.com.android.update\",\"startup\":true,\"package_name\":\"gn.com.android.update\"},{\"app_name\":\"搜索\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"system\",\"pname\":\"com.amigo.search\",\"startup\":true,\"package_name\":\"com.amigo.search\"},{\"app_name\":\"百度手机卫士\",\"cert_md5\":\"310a4f78e839b86df7731c2f48fcadae\",\"uname\":\"u0_a4\",\"pname\":\"cn.opda.a.phonoalbumshoushou:dxopt\",\"startup\":true,\"package_name\":\"cn.opda.a.phonoalbumshoushou\"},{\"app_name\":\"\",\"cert_md5\":\"\",\"uname\":\"root\",\"pname\":\"/system/xbin/ku.sud\",\"startup\":true,\"package_name\":\"ku.sud\"},{\"app_name\":\"360手机助手\",\"cert_md5\":\"ca45263bc938da16ef1b069c95e61ba2\",\"uname\":\"u0_a8\",\"pname\":\"com.qihoo.appstore:feedback\",\"startup\":true,\"package_name\":\"com.qihoo.appstore\"},{\"app_name\":\"360浏览器\",\"cert_md5\":\"5b252a142a450b34bd3253acb51882bd\",\"uname\":\"u0_a9\",\"pname\":\"/data/data/com.qihoo.browser/files/so/uin.m\",\"startup\":true,\"package_name\":\"com.qihoo.browser\"},{\"app_name\":\"KingRoot\",\"cert_md5\":\"191240fcb048127db9110d1b30537fde\",\"uname\":\"u0_a109\",\"pname\":\"com.kingroot.kinguser:service\",\"startup\":true,\"package_name\":\"com.kingroot.kinguser\"},{\"app_name\":\"搜狗浏览器\",\"cert_md5\":\"3e0debcab4edcd0773020db42a0e472a\",\"uname\":\"u0_a26\",\"pname\":\"sogou.mobile.explorer:channel\",\"startup\":true,\"package_name\":\"sogou.mobile.explorer\"},{\"app_name\":\"DRM 保护内容存储\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a66\",\"pname\":\"com.mediatek.providers.drm\",\"startup\":true,\"package_name\":\"com.mediatek.providers.drm\"},{\"app_name\":\" 360手机卫士\",\"cert_md5\":\"dc6dbd6e49682a57a8b82889043b93a8\",\"uname\":\"u0_a10\",\"pname\":\"com.qihoo360.mobilesafe:permmgr\",\"startup\":true,\"package_name\":\"com.qihoo360.mobilesafe\"},{\"app_name\":\"搜狐新闻\",\"cert_md5\":\"35c162871bf3051bddbf5d4eeb9decdb\",\"uname\":\"u0_a12\",\"pname\":\"/data/data/com.sohu.newsclient/lib/libxguardian.so\",\"startup\":true,\"package_name\":\"com.sohu.newsclient\"},{\"app_name\":\"WLAN\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"system\",\"pname\":\"com.gionee.setting.adapter.wifi\",\"startup\":true,\"package_name\":\"com.gionee.setting.adapter.wifi\"},{\"app_name\":\"净化大师\",\"cert_md5\":\"191240fcb048127db9110d1b30537fde\",\"uname\":\"u0_a117\",\"pname\":\"com.kingroot.master:vpn\",\"startup\":true,\"package_name\":\"com.kingroot.master\"},{\"app_name\":\"YuloreFrameWork\",\"cert_md5\":\"646e437edb09750be3fc7aced8119e19\",\"uname\":\"u0_a108\",\"pname\":\"com.yulore.framework:remote\",\"startup\":true,\"package_name\":\"com.yulore.framework\"},{\"app_name\":\"amigo帐号\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a29\",\"pname\":\"com.gionee.account\",\"startup\":true,\"package_name\":\"com.gionee.account\"},{\"app_name\":\"GBA Service\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"radio\",\"pname\":\"com.mediatek.gba\",\"startup\":true,\"package_name\":\"com.mediatek.gba\"},{\"app_name\":\"随变\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a34\",\"pname\":\"com.amigo.chameleon\",\"startup\":true,\"package_name\":\"com.amigo.chameleon\"},{\"app_name\":\"金立appsipper\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"system\",\"pname\":\"com.appsipper.demo\",\"startup\":true,\"package_name\":\"com.appsipper.demo\"},{\"app_name\":\"\",\"cert_md5\":\"\",\"uname\":\"u0_a8\",\"pname\":\"com.qihoo.daemon\",\"startup\":true,\"package_name\":\"com.qihoo.daemon\"},{\"app_name\":\"网络位置\",\"cert_md5\":\"c2b0b497d0389e6de1505e7fd8f4d539\",\"uname\":\"u0_a84\",\"pname\":\"com.baidu.map.location\",\"startup\":true,\"package_name\":\"com.baidu.map.location\"},{\"app_name\":\"天下游\",\"cert_md5\":\"512170d671ed4023cb6bec2c236aee7b\",\"uname\":\"u0_a111\",\"pname\":\"com.txy.anywhere\",\"startup\":true,\"package_name\":\"com.txy.anywhere\"},{\"app_name\":\"\",\"cert_md5\":\"\",\"uname\":\"u0_a8\",\"pname\":\"com.qihoo360.accounts\",\"startup\":true,\"package_name\":\"com.qihoo360.accounts\"},{\"app_name\":\"用户反馈\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a53\",\"pname\":\"com.gionee.telepath\",\"startup\":true,\"package_name\":\"com.gionee.telepath\"},{\"app_name\":\"搜狗输入法\",\"cert_md5\":\"15cd0088e2697091f33a2d97da2ea956\",\"uname\":\"u0_a112\",\"pname\":\"com.sohu.inputmethod.sogou:classic\",\"startup\":true,\"package_name\":\"com.sohu.inputmethod.sogou\"},{\"app_name\":\"系统管家\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"system\",\"pname\":\"com.gionee.softmanager:remote\",\"startup\":true,\"package_name\":\"com.gionee.softmanager\"},{\"app_name\":\"amigo系统服务\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"system\",\"pname\":\"com.amigo.server\",\"startup\":true,\"package_name\":\"com.amigo.server\"},{\"app_name\":\"\",\"cert_md5\":\"\",\"uname\":\"u0_a8\",\"pname\":\"com.qihoo.appstore_CoreDaemon\",\"startup\":true,\"package_name\":\"com.qihoo.appstore_CoreDaemon\"},{\"app_name\":\"语音引擎\",\"cert_md5\":\"4da2cad0c942c00af7dd94fe107b0ea2\",\"uname\":\"u0_a104\",\"pname\":\"com.iflytek.speechsuite\",\"startup\":true,\"package_name\":\"com.iflytek.speechsuite\"},{\"app_name\":\"今日头条\",\"cert_md5\":\"aea615ab910015038f73c47e45d21466\",\"uname\":\"u0_a13\",\"pname\":\"com.ss.android.article.news:pushservice\",\"startup\":true,\"package_name\":\"com.ss.android.article.news\"},{\"app_name\":\"MTK NLP Service\",\"cert_md5\":\"b49792a5687b641492e10a29152f7454\",\"uname\":\"u0_a85\",\"pname\":\"com.mediatek.nlpservice\",\"startup\":true,\"package_name\":\"com.mediatek.nlpservice\"}]";

    JSONArray objects = JSONArray.parseArray(json);

    int size = objects.size();
//    for (int i = 0; i < size; i++) {
//      JSONObject jsonObject = objects.getJSONObject(i);
//      String rowkeyConfigSync = "app_289_" + jsonObject.getString("cert_md5") + "_" + jsonObject.getString("package_name");
//      System.out.println(rowkeyConfigSync);
//    }

    for (int i = 0; i < size; i++) {
      JSONObject jsonObject = objects.getJSONObject(i);
      String rowkeyGamePlugin = jsonObject.getString("package_name") + "_" + jsonObject.getString("cert_md5");

      System.out.println(rowkeyGamePlugin);
    }


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
