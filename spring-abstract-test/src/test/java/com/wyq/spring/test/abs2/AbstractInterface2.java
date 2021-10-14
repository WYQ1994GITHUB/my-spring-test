package com.wyq.spring.test.abs2;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public interface AbstractInterface2 {
    String execute(String a, String b, List<Integer> codes);

    @Data
    class Student implements Serializable {
        private static final long serialVersionUID = -7373268936869709572L;

        private int age;

        private String name;
    }
}
