package com.zhangblue;

import com.alibaba.fastjson.JSONObject;
import com.zhangblue.ban.ClosureStrategyModel;

public class DoMain {

  public static void main(String[] args) {

    ClosureStrategyModel closureStrategyModel = new ClosureStrategyModel("1", null, null);

    String s = JSONObject.toJSONString(closureStrategyModel);

    System.out.println(s);

    ClosureStrategyModel closureStrategyModel1 = JSONObject
        .parseObject(s, ClosureStrategyModel.class);

    System.out.println(null==closureStrategyModel1.getBanModel());
    System.out.println(null==closureStrategyModel1.getFuseModel());

  }

}
