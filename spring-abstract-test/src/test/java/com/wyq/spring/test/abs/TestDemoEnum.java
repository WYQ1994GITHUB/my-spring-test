package com.wyq.spring.test.abs;

import lombok.Getter;

public enum TestDemoEnum {

    TEST_1(1, "test1", Object1.class),

    TEST_2(2, "test2", Object2.class),
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;
    @Getter
    private Class<? extends AbstractObjectDemo> aClass;

    TestDemoEnum(Integer code, String desc, Class aClass) {
        this.code = code;
        this.desc = desc;
        this.aClass = aClass;
    }
}
