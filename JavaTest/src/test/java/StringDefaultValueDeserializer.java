import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;

public class StringDefaultValueDeserializer implements ObjectDeserializer {

  @Override
  public String deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
    String val = defaultJSONParser.getLexer().stringVal().trim();
    if (StringUtils.isBlank(val) || "unknown".equals(val.toLowerCase()) || "no"
        .equals(val.toLowerCase())) {
      val = "GUA";
    }
    return val;
  }

  @Override
  public int getFastMatchToken() {
    return 0;
  }
}
