package me.caiying.demo1;

import me.caiying.demo1.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DefaultController {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
  private SimpleService simpleService;

  @GetMapping("/whoami")
  @ResponseBody
  public Object whoami() {
    logger.info("demo1 consumer invoke whoami dubbo rpc");
    return simpleService.whoami();
  }
}
