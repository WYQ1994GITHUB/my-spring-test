package com.wyq.spring.test.abs2;

import org.springframework.stereotype.Component;

@Component
public class Demo1 extends AbstractObjectDemo2 {
    @Override
    public Integer init(int a) {
        return null;
    }

    @Override
    public String execute() {
        System.out.println("demo1");
        return null;
    }
}
