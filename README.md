demo1 是最简单的 springboot + dubbo 的结构

demo2 在 demo1 的基础上增加 sentinel 

[Sentinel Dubbo Demo](https://github.com/alibaba/Sentinel/tree/master/sentinel-demo/sentinel-demo-dubbo)

```bash
# provider
-Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=demo2-dubbo-provider

# consumer
-Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8721 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=demo2-dubbo-consumer

# start dashboard (default username/password : sentinel/sentinel)
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```
