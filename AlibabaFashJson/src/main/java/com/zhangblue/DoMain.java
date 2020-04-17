package com.zhangblue;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zhangblue.model.User;

public class DoMain {

  public static void main(String[] args) {
    String json = "{\"age\":10}";

    User user = JSONObject.parseObject(json, User.class);

    String line = JSONObject.toJSONString(user, SerializerFeature.DisableCircularReferenceDetect,
        SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteMapNullValue,
        SerializerFeature.WriteNullListAsEmpty,
        SerializerFeature.WriteNullStringAsEmpty);

    System.out.println(line);
  }

}
