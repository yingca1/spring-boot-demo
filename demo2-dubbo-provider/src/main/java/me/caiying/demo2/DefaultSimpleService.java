package me.caiying.demo2;

import me.caiying.demo2.dubbo.SimpleService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class DefaultSimpleService implements SimpleService {

  @Override
  public String whoami() {
    return "Hi, i am demo2 dubbo provider.";
  }
}
