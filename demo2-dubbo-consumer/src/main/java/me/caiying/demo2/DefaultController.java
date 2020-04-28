package me.caiying.demo2;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.SentinelRpcException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import me.caiying.demo2.dubbo.SentinelFeatureService;
import me.caiying.demo2.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {

  private static final ExecutorService pool =
      Executors.newFixedThreadPool(20, new NamedThreadFactory("demo2-dubbo-consumer-pool"));
  private final Logger logger = LoggerFactory.getLogger(getClass());
  @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
  private SimpleService simpleService;
  @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12346")
  private SentinelFeatureService sentinelFeatureService;

  public static void initFlowRule() {
    FlowRule flowRule = new FlowRule();
    flowRule.setResource("me.caiying.demo2.dubbo.SentinelFeatureService:case2()");
    flowRule.setCount(10);
    flowRule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
    flowRule.setLimitApp("default");
    FlowRuleManager.loadRules(Collections.singletonList(flowRule));
  }

  public static void registerFallback() {
    // Register fallback handler for consumer.
    // If you only want to handle degrading, you need to
    // check the type of BlockException.
    DubboFallbackRegistry.setConsumerFallback(
        (invoker, invocation, e) -> {
          Logger logger = LoggerFactory.getLogger("DubboFallbackRegistry.setConsumerFallback");
          logger.error("setConsumerFallback", e);
          return new AsyncRpcResult(invocation);
        });
  }

  @PostConstruct
  void init() {
    initFlowRule();
    //    registerFallback();
  }

  @GetMapping("/whoami")
  @ResponseBody
  public Object whoami() {
    logger.info("demo2 consumer invoke whoami dubbo rpc");
    return simpleService.whoami();
  }

  @GetMapping("/sentinel/case1")
  @ResponseBody
  public Object case1() {
    List<String> results = new ArrayList<>();
    try {
      for (int i = 0; i < 20; i++) {
        results.add(i + " - " + sentinelFeatureService.case1());
      }
    } catch (RpcException e) {
      if (BlockException.isBlockException(e)) {
        logger.warn("Sentinel rules triggered, Blocked!!!");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return results;
  }

  @GetMapping("/sentinel/case2")
  @ResponseBody
  public Object case2() {
    List<String> results = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      int index = i;
      pool.submit(
          () -> {
            try {
              results.add(index + " - " + sentinelFeatureService.case2());
            } catch (SentinelRpcException e) {
              if (BlockException.isBlockException(e)) {
                logger.warn("Sentinel rules triggered, Blocked!!!");
              }
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          });
    }
    pool.submit(() -> results.add("last submit : " + sentinelFeatureService.case1()));
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return results;
  }
}
