package test.enmu;

import cn.com.zhangblue.log4j2.TestClass;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

  public static void main(String[] args) {

    List<TestClass> listTestClass = new ArrayList<TestClass>();
    TestClass tczhangsan = new TestClass("lisi", TestEnum.INSERT);
    TestClass tclisi = new TestClass("zhangsan", TestEnum.UPDATE);
    listTestClass.add(tczhangsan);
    listTestClass.add(tclisi);

    for (TestClass tc : listTestClass) {
      if (tc.getTestEnum().getValue() == TestEnum.INSERT.getValue()) {
        System.out.println(tc.getName() + "---" + tc.getTestEnum().getValue());
      } else if (tc.getTestEnum().getValue() == TestEnum.UPDATE.getValue()) {
        System.out.println(tc.getName() + "---" + tc.getTestEnum().getValue());
      } else if (tc.getTestEnum().getValue() == TestEnum.REPLACE.getValue()) {
        System.out.println(tc.getName() + "---" + tc.getTestEnum().getValue());
      }
    }


  }

}
