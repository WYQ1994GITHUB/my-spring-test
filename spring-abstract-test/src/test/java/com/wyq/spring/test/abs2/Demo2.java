package com.wyq.spring.test.abs2;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class Demo2 extends AbstractObjectDemo2{
    @Override
    public Integer init(int a) {
        return null;
    }

    @Override
    public String execute(AbstractInterface2.Student student) {
        System.out.println("demo2-before:" + JSON.toJSONString(student));
        int age = student.getAge();
        student.setAge(++age);
        student.setName("we");
        System.out.println("demo2-after:" + JSON.toJSONString(student));
        return null;
    }
}
