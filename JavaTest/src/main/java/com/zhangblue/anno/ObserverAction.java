package com.zhangblue.anno;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangdi
 * @description: test
 * @date 2020/12/7 下午6:04
 * @since
 **/

public class ObserverAction {

  private Object target;
  private Method method;

  public ObserverAction(Object target, Method method) {
    this.target = Preconditions.checkNotNull(method);
    this.method = method;
  }

  public void execute(Object event) { // event是method方法的参数
    try {
      System.out.println(Preconditions.checkNotNull(method));
      method.invoke(this.target, event);
    } catch (InvocationTargetException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
