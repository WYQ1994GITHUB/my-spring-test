package com.wyq.spring.test.abs2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractInterface2Impl implements AbstractInterface2 {
    @Override
    public String execute(String a, String b, List<Integer> codes) {
        System.out.println(b);
        System.out.println(a);
        System.out.println(codes);

        for (Integer code : codes) {
            AbstractObjectDemo2.execute2(code);
        }
        return null;
    }
}
