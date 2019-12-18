package com.zhangblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangdi
 */
@SpringBootApplication
public class MyApp {

  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(MyApp.class);
    springApplication.run(args);
  }
}
