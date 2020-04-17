package com.zhangblue.stream;

import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangdi
 */
@Data
@AllArgsConstructor
public class Employee {

  /**
   * 编号
   **/
  private int id;
  /**
   * 年龄
   */
  private int age;
  /**
   * 性别
   */
  private String gender;
  /**
   * first name
   **/
  private String firstName;
  /**
   * last name
   **/
  private String lastName;

  /**
   * 谓语条件 年龄<30
   */
  public static Predicate<Employee> ageLessThan30 = x -> x.getAge() < 30;
  /**
   * 谓语 性别是男
   */
  public static Predicate<Employee> genderIsM = x -> "M".equals(x.getGender());
}
