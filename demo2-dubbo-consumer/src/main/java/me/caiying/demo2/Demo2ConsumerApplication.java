package me.caiying.demo2;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.List;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class Demo2ConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Demo2ConsumerApplication.class, args);

    registerSentinelApolloDatasource();
  }

  private static void registerSentinelApolloDatasource() {
    String namespaceName = "sentinel";
    String flowRuleKey = "flowRules";
    // It's better to provide a meaningful default value.
    String defaultFlowRules = "[]";

    ReadableDataSource<String, List<FlowRule>> flowRuleDataSource =
        new ApolloDataSource<>(
            namespaceName,
            flowRuleKey,
            defaultFlowRules,
            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
    FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
  }
}
