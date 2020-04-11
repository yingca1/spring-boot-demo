package me.caiying.demo1;

import me.caiying.demo1.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class DefaultSimpleService implements SimpleService {

  @Override
  public String whoami() {
    return "Hi, i am demo1 dubbo provider.";
  }
}
