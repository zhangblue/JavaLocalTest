import com.github.houbb.junitperf.core.annotation.JunitPerfConfig;
import com.zhangblue.stream.Employee;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

public class StreamDemo {

  private List<Employee> employeeList = null;

  @Before
  public void before() {
    Employee employee1 = new Employee(1, 33, "M", "Rick", "Beethovan");
    Employee employee2 = new Employee(2, 13, "F", "Martina", "Hengis");
    Employee employee3 = new Employee(3, 64, "M", "Ricky", "Martin");
    Employee employee4 = new Employee(4, 21, "M", "Jon", "Lowman");
    Employee employee5 = new Employee(5, 39, "F", "Cristine", "Maria");
    Employee employee6 = new Employee(6, 27, "M", "David", "Feezor");
    Employee employee7 = new Employee(7, 45, "F", "Melissa", "Roy");
    Employee employee8 = new Employee(8, 50, "M", "Alex", "Gussin");
    Employee employee9 = new Employee(9, 55, "F", "Neetu", "Singh");
    Employee employee10 = new Employee(10, 71, "M", "Naveen", "Jain");
    employeeList = Arrays
        .asList(employee1, employee2, employee3, employee4, employee5, employee6, employee7,
            employee8, employee9, employee10);
  }

  @Test
  public void orderTest() {
    //Demo1：按照性别降序，按照年龄降序
    employeeList.sort(
        Comparator.comparing(Employee::getGender).thenComparing(Employee::getAge)
            .reversed());
    System.out.println(employeeList);

    //Demo2: 按照性别倒序，按照年龄升序
    employeeList.sort(
        Comparator.comparing(Employee::getGender).reversed().thenComparing(Employee::getAge));
    System.out.println(employeeList);

    //Demo3：按照性别升序，按照年龄降序
    employeeList.sort(
        Comparator.comparing(Employee::getGender).reversed().thenComparing(Employee::getAge)
            .reversed());
    System.out.println(employeeList);

    //Demo4: 按照性别升序，按照年龄升序
    employeeList.sort(Comparator.comparing(Employee::getGender).thenComparing(Employee::getAge));
    System.out.println(employeeList);
  }

  @Test
  public void stringListToLongList() {
    String[] input = {"a", "b", "c", "d"};
    Stream.of(input);
  }

  @Test
  public void listToStream() {
    List<String> stringlist = Arrays.asList("a", "b", "c", "d");
    stringlist.stream();
  }

  @Test
  public void fileToStream() {
    try {
      Stream<String> lines = Files.lines(Paths.get("file.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void filterDemo1() {
    List<Employee> filterEmployee = employeeList.stream()
        .filter(x -> x.getAge() < 30 && "M".equals(x.getGender()))
        .collect(Collectors.toList());
    System.out.println(filterEmployee);
  }

  @Test
  public void filterDemo2() {
    List<Employee> ageLessThan30 = employeeList.stream()
        .filter(Employee.ageLessThan30.or(Employee.genderIsM))
        .collect(Collectors.toList());
    System.out.println(ageLessThan30);
  }

  @Test
  public void fileDemo3() {
    employeeList.stream().map(Employee::getFirstName).collect(Collectors.toList());
  }

  @Test
  public void fileDemo4() {
    //使用map方式
    employeeList.stream().map(x -> {
      x.setAge(x.getAge() + 1);
      return x;
    }).collect(Collectors.toList());

    //使用peek方式
    employeeList.stream().peek(x -> x.setAge(x.getAge() + 1)).collect(Collectors.toList());
  }

  @Test
  public void limitDemo() {
    employeeList.stream().limit(2).collect(Collectors.toList());
  }

  @Test
  public void skipDemo() {
    employeeList.stream().skip(2).collect(Collectors.toList());
  }

  @Test
  public void parallelDemo() {
    employeeList.stream().parallel().filter(x -> x.getAge() > 30).collect(Collectors.toList());
  }

  @Test
  public void noneMatchDemo() {
    //所有元素都不满足，返回true
    boolean b = employeeList.stream().noneMatch(Employee.ageLessThan30);
  }


}
