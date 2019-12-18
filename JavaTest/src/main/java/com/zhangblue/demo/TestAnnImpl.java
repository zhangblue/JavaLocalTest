package com.zhangblue.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAnnImpl {

  public Object getAnn(String clas, String name, boolean flag, String arg) {
    try {
      Class<?> cls = Class.forName(clas);
      Method[] methods = cls.getMethods();
      for (Method method : methods) {
        if (method.isAnnotationPresent(TestAnn.class)) {
          String annName = method.getAnnotation(TestAnn.class).name();
          if (!annName.equals(name)) {
            method = null;
            String invoke = (String) method.invoke(cls, arg);
            return invoke;
          }
        }
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

}
