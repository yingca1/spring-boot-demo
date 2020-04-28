APOLLO_META=http://10.8.205.175:30002 mvn -pl demo2-dubbo-consumer clean spring-boot:run -DskipTests

-Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=9002 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=demo2-dubbo-consumer