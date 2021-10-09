package com.wyq.spring.test;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * AntPath测试
 */
public class AntPathMatcherTest {
    @Test
    public void match() {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("com/**/test", "com/wyq/spring/test"));
        System.out.println(antPathMatcher.match("com/**/test", "com2/wyq/spring/test"));
    }
}
