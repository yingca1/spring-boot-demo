package me.caiying.demo2;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Demo2ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Demo2ProviderApplication.class, args);

		SentinelCaseService.initFlowRule();
	}

}
