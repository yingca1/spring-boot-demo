package me.caiying.demo2;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.time.LocalDateTime;
import java.util.Collections;
import me.caiying.demo2.dubbo.SentinelFeatureService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class SentinelCaseService implements SentinelFeatureService {

  public static void initFlowRule() {
    FlowRule flowRule = new FlowRule();
    flowRule.setResource("me.caiying.demo2.dubbo.SentinelFeatureService:case1()");
    flowRule.setCount(10);
    flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    flowRule.setLimitApp("default");
    FlowRuleManager.loadRules(Collections.singletonList(flowRule));
  }

  @Override
  public String case1() {
    return "demo2 dubbo provider case1 - " + LocalDateTime.now().toString();
  }

  @Override
  public String case2() {
    return "demo2 dubbo provider case2 - " + LocalDateTime.now().toString();
  }
}
