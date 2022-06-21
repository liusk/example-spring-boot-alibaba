package com.example.streamrocketmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liusk
 */
@Slf4j
@RestController
@RequestMapping("person")
public class PersonController {

    @Resource
    private StreamBridge streamBridge;


    @RequestMapping("info")
    public Person getPerson(Person person) {
//        Person p = new Person();
//        p.setName(person.getName());
//        p.setAge("111");
//
//        streamBridge.send("person-in-0", p);

        Animal animal = new Animal();
        animal.setSex("男");
        animal.setName("大犀牛");

        streamBridge.send("animal-in-0", animal);
        return new Person("liusk", "42");
    }

}
