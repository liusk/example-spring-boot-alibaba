package tk.crab.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tk.crab.IPersonOperation;
import tk.crab.Person;

import javax.annotation.Resource;

/**
 * @author liusk
 */
@Slf4j
@RestController
@RequestMapping("person")
@RefreshScope
public class PersonController {

    @Value("${user.name:zz}")
    String userName;
    @Value("${user.age:30}")
    String age;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @DubboReference(version = "1.0.0", check = false)
    private IPersonOperation personOperation;

    @RequestMapping("info")
    public Person getPerson(Person person) {
        Person p = new Person();
        p.setName(person.getName());
        p.setAge("111");
        return p;
    }

    @RequestMapping("config")
    public String config() {
        log.info("name:{},age:{}", userName, age);
        return String.format("name : %s , age: %s ", userName, age);
    }

    @RequestMapping("restful")
    public Person restful() {

        log.info("name:{},age:{}", userName, age);
        ServiceInstance serviceInstance = loadBalancerClient.choose("producer");
        // 当使用  @LoadBalanced 的时候这里就不能直接使用 ip+port了，需要使用服务id(服务提供者注册的spring.application.name)
        String path = "http://producer/person/hello";
        System.out.println("request path:" + path);

        return restTemplate.postForObject(path, new Person("lsk", "15"), Person.class);
    }

    @RequestMapping("dubbo")
    public Person dubbo() {
        Person person = personOperation.getPerson("123456");
        return person;
    }

}
