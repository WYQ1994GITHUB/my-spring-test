package com.wyq.spring.test.abs2;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractObjectDemo2 implements ApplicationContextAware {
    private static ApplicationContext context = null;

    public abstract Integer init(int a);

    public abstract String execute();

    public static String execute2(Integer code) {
        AbstractObjectDemo2 abstractObjectDemo2 = getAbstractObjectDemo2(code);
        if (null == abstractObjectDemo2) {
            return null;
        }
        return abstractObjectDemo2.execute();
    }

    private static AbstractObjectDemo2 getAbstractObjectDemo2(Integer code) {
        TestDemoEnum2 enum2 = TestDemoEnum2.getTestDemoEnum2ByCode(code);
        if (enum2 == null) {
            return null;
        }
        AbstractObjectDemo2 bean = context.getBean(enum2.getAClass());
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
