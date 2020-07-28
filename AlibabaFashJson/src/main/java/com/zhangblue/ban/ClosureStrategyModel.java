package com.zhangblue.ban;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author di.zhang
 * @date 2020/5/18
 * @time 17:10
 **/
@Data
@AllArgsConstructor
public class ClosureStrategyModel {

  @JSONField(name = "id")
  private String id;

  @JSONField(name = "ban")
  private BanModel banModel;
  @JSONField(name = "fuse")
  private FuseModel fuseModel;


}
