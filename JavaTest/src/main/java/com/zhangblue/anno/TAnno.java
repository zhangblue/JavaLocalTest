package com.zhangblue.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangdi
 * @description: 测试注解
 * @date 2020/12/7 下午5:55
 * @since
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TAnno {

  String value = "";

}
