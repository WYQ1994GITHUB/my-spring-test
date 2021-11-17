package com.wyq.spring.test.abs;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Object1 extends AbstractObjectDemo {
    @Override
    public String execute() {
        return "hello-o1";
    }
}
