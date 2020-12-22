package cn.com.test;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

/**
 * @author di.zhang
 * @date 2020/8/18
 * @time 18:02
 **/
public class IntDefaultValueDeserializer implements ObjectDeserializer {

  @Override
  public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
    return (T) TypeUtils.castToInt(0);
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }
}
