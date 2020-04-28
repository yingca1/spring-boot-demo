package me.caiying.demo1;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Demo1ProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(Demo1ProviderApplication.class, args);
  }
}
