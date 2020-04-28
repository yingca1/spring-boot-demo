package me.caiying.demo3;

import me.caiying.demo3.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class DefaultSimpleService implements SimpleService {

  @Override
  public String whoami() {
    return "Hi, i am demo3 dubbo provider.";
  }
}
