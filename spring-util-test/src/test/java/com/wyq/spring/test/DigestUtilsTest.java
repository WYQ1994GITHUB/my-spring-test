package com.wyq.spring.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * md5工具类
 *
 */
public class DigestUtilsTest {

    @Test
    public void test() {
        String s = "I LOVE YOU";
        String md5 = DigestUtils.md5DigestAsHex(s.getBytes());
        System.out.println(md5);
    }
}
