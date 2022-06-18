package tk.crab.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tk.crab.Person;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liusk
 */
@Slf4j
@RequestMapping("person")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public Person getPerson(@RequestBody Person person) {
        log.info("====== person ====== : {}", person.toString());

        Person p = new Person();
        p.setName("刘德华");
        p.setAge("500");
        return p;
    }

    @RequestMapping("info")
    public Object info() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****包含服务{}", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("producer");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }
}
