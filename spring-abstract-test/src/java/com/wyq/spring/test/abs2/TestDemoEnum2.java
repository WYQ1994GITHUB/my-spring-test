package com.wyq.spring.test.abs2;

import lombok.Getter;

public enum TestDemoEnum2 {

    TEST_1(1, "test1", Demo1.class),

    TEST_2(2, "test2", Demo2.class),

    TEST_3(3, "test2", Demo3.class),
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;
    @Getter
    private Class<? extends AbstractObjectDemo2> aClass;

    TestDemoEnum2(Integer code, String desc, Class aClass) {
        this.code = code;
        this.desc = desc;
        this.aClass = aClass;
    }

    public static TestDemoEnum2 getTestDemoEnum2ByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (TestDemoEnum2 enum2 : TestDemoEnum2.values()) {
            if (code.compareTo(enum2.getCode()) == 0) {
                return enum2;
            }
        }
        return null;
    }
}
