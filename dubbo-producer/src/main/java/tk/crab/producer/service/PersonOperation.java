package tk.crab.producer.service;

import org.apache.dubbo.config.annotation.DubboService;
import tk.crab.IPersonOperation;
import tk.crab.Person;

/**
 * @author liusk
 */
@DubboService(version = "1.0.0")
public class PersonOperation implements IPersonOperation {

    @Override
    public Person getPerson(String id) {
        Person p = new Person();
        p.setName(id);
        p.setAge("liusk");
        return p;
    }
}
