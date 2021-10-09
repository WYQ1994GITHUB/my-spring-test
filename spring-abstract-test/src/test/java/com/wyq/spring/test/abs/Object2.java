package com.wyq.spring.test.abs;

import org.springframework.stereotype.Component;

@Component
public class Object2 extends AbstractObjectDemo {
    @Override
    public String execute() {
        return "hello-o2";
    }

}
