package com.example.nacosconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liusk
 */
@Slf4j
@SpringBootApplication
@RefreshScope
@RestController
public class NacosConfigApplication implements InitializingBean {

    @Value("${crab.name}")
    private String crabName;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("crab.name");
        String userAge = applicationContext.getEnvironment().getProperty("crab.age");
        log.info("user name :" + userName + "; age: " + userAge);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("======== crabName ====== :" + crabName);
    }

    @RequestMapping("config")
    public String getConfig() {
        return String.format("name : %s", crabName);
    }
}
