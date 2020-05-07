package me.caiying.demo3;

import me.caiying.demo3.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Reference(version = "1.0.0")
  private SimpleService simpleService;

  @GetMapping("/whoami")
  @ResponseBody
  public Object whoami() {
    logger.info("demo3 consumer invoke whoami dubbo rpc");
    return simpleService.whoami();
  }
}
