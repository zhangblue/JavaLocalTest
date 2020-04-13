import com.alibaba.fastjson.annotation.JSONField;

public class JsonModel {



  @JSONField(deserializeUsing = StringDefaultValueDeserializer.class, name = "name")
  private String name = "N/A";
  @JSONField(name = "age")
  private int age = 0;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
