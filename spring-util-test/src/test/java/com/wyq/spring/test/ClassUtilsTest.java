package com.wyq.spring.test;

import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * Class工具类,对class的一些反射获取
 **/
public class ClassUtilsTest {
    /**
     * 获取类中的方法
     */
    @Test
    public void testA() {
        Method method = ClassUtils.getMethodIfAvailable(String.class, "split", String.class);
        System.out.println(ClassUtils.getQualifiedMethodName(method));
    }

    @Test
    public void testB () {
        String packageName = ClassUtils.getPackageName(String.class);
        System.out.println(packageName);
    }
}
