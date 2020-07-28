package com.zhangblue.ban;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author di.zhang
 * @date 2020/5/18
 * @time 17:16
 **/
@Data
@AllArgsConstructor
public class FuseModel {

  @JSONField(name = "unit")
  private String unit;
  @JSONField(name = "num")
  private int num;
  @JSONField(name = "udid_count")
  private int udidCount;


}
