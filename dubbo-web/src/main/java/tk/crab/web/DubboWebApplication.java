package tk.crab.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author liusk
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class DubboWebApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DubboWebApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("crab.name");
        String userAge = applicationContext.getEnvironment().getProperty("crab.age");
        log.info("user name :" + userName + "; age: " + userAge);
    }

    //    @LoadBalanced @todo 加上以后就不好用
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}


