package com.zhangblue.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhangblue.deserializer.StringDefaultValueDeserializer;

/**
 * @author di.zhang
 * @date 2020/11/3
 * @time 11:21
 **/
public class TestEneityModel {
  
  @JSONField(name = "name", deserializeUsing = StringDefaultValueDeserializer.class)
  private String name;
  @JSONField(name = "age")
  private long agent;
  @JSONField(name = "classes", deserializeUsing = StringDefaultValueDeserializer.class)
  private String classes;
}
