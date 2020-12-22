package cn.com.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author di.zhang
 * @date 2020/8/18
 * @time 17:51
 **/
@Data
public class TestBean {

  @JSONField(name = "name", deserializeUsing = StringDefaultValueDeserializer.class)
  private String name;
  @JSONField(name = "version",deserializeUsing = IntDefaultValueDeserializer.class)
  private int version;

}
