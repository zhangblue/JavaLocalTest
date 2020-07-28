package com.zhangblue.ban;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author di.zhang
 * @date 2020/5/18
 * @time 17:15
 **/
@Data
@AllArgsConstructor
public class BanModel {

  @JSONField(name = "type")
  private String type;
  @JSONField(name = "num")
  private int num;
  @JSONField(name = "unit")
  private String unit;

}
