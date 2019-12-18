package cn.com.model;

/**
 * @ClassName PersonModel
 * @Description TODO
 * @Author zhangdi
 * @Date 2019/06/24 14:52
 **/
public class PersonModel {

  private String name;

  private String age;

  private String sex;

  private String phone;

  private String address;

  public PersonModel() {
  }

  public PersonModel(String name, String age, String sex, String phone, String address) {
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.phone = phone;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
