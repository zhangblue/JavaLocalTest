package com.zhangblue.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangdi
 */
public class StringDefaultValueDeserializer implements ObjectDeserializer {

  @Override
  public String deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
    System.out.println("aaaaa");
    String val = defaultJSONParser.getLexer().stringVal().trim();
    if (StringUtils.isBlank(val)) {
      val = "N/A";
    }
    return val;
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }
}
