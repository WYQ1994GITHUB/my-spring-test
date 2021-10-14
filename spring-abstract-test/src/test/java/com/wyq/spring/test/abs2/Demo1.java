package com.wyq.spring.test.abs2;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class Demo1 extends AbstractObjectDemo2 {
    @Override
    public Integer init(int a) {
        return null;
    }

    @Override
    public String execute(AbstractInterface2.Student student) {
        System.out.println("demo1-before:" + JSON.toJSONString(student));
        int age = student.getAge();
        student.setAge(++age);
        student.setName("ls");
        System.out.println("demo1-after:" + JSON.toJSONString(student));
        return null;
    }
}
