package me.caiying.demo3;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Demo3ProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(Demo3ProviderApplication.class, args);
  }
}
