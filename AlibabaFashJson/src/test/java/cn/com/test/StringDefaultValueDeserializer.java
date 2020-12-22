package cn.com.test;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;

/**
 * @author di.zhang
 * @date 2020/8/18
 * @time 17:54
 **/
public class StringDefaultValueDeserializer implements ObjectDeserializer {


  @Override
  public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object fieldName) {

    String val = defaultJSONParser.getLexer().stringVal().trim();
    if (StringUtils.isBlank(val) || "unknow".equals(val.toLowerCase()) || "unknown"
        .equals(val.toLowerCase()) || "no"
        .equals(val.toLowerCase())) {
      val = "N/A";
    }
    return (T) TypeUtils.castToString(val);
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }
}
