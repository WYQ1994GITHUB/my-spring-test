package com.wyq.spring.test.abs2;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class Demo3 extends AbstractObjectDemo2{
    @Override
    public Integer init(int a) {
        return null;
    }

    @Override
    public String execute(AbstractInterface2.Student student) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("demo3-before:" + JSON.toJSONString(student));
        int age = student.getAge();
        student.setAge(++age);
        System.out.println("demo3-after:" + JSON.toJSONString(student));
        return null;
    }
}
