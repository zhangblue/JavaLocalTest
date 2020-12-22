package com.zhangblue.anno;

import java.util.List;

/**
 * @author zhangdi
 * @description: 主函数
 * @date 2020/12/7 下午6:07
 * @since
 **/
public class TestMain {

  public static void main(String[] args) {
    RegistryTest registryTest = new RegistryTest();
    registryTest.register(new TestClassFunc());

    List<ObserverAction> matchedObserverActions = registryTest
        .getMatchedObserverActions(EventTypeOK.HEALTHY);

    System.out.println(matchedObserverActions.size());
    for (ObserverAction matchedObserverAction : matchedObserverActions) {
        matchedObserverAction.execute(EventTypeOK.HEALTHY);
    }




  }

}
