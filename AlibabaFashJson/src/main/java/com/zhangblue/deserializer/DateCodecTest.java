package com.zhangblue.deserializer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.Jdk8DateCodec;
import java.util.Date;
import lombok.Data;

/**
 * @author di.zhang
 * @date 2020/11/3
 * @time 11:27
 **/
@Data
public class DateCodecTest {

  @JSONField(name = "data_time", deserializeUsing = Jdk8DateCodec.class, serializeUsing = Jdk8DateCodec.class)
  private Date currentDate;
//  private TemporalAccessor temporalAccessor;


}
