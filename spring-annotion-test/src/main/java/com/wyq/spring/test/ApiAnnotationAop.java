/*
 *
 *  Copyright 2020 byai.com All right reserved. This software is the
 *  confidential and proprietary information of byai.com ("Confidential
 *  Information"). You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement you entered
 *  into with byai.com.
 * /
 */

package com.wyq.spring.test;

import com.wyq.spring.test.annotion.ApiAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Service拦截, 主要完成: 1. 异常处理和异常返回值封装 2. 日志记录
 */
@Component
@Aspect
@Slf4j
public class ApiAnnotationAop {
    /**
     * 超时时间 (ms)
     */
    private static final int TIME_OUT = 300;

    @Around("@annotation(com.wyq.spring.test.annotion.ApiAnnotation)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        long startTime = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed();
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Throwable ex) {
            log(joinPoint, ex);
            throw ex;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Map<String, Object> args = formatParameters(joinPoint, signature.getParameterNames());
            String methodName = method.getName();
            String fullMethodName = method.getDeclaringClass().getName() + "." + methodName;
            if (costTime > TIME_OUT) {
                log.warn("method={}, args={}, duration={}, slow method", fullMethodName, args, costTime);
            } else {
                log.info("method={}, args={}, duration={}", fullMethodName, args, costTime);
            }
        }
        return obj;
    }

    private void log(ProceedingJoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Map<String, Object> args = formatParameters(joinPoint, signature.getParameterNames());
        String simpleName = ex.getClass().getSimpleName();
        Method method = signature.getMethod();
        log.error("exception: {}, Method: {}, args: {}", simpleName, method, args, ex);
    }

    private Map<String, Object> formatParameters(ProceedingJoinPoint pig, String[] paramNames) {
        Map<String, Object> map = new HashMap<>();
        Object[] args = pig.getArgs();
        for (int i = 0; i < args.length; i++) {
            map.put(paramNames[i], args[i]);
        }
        return map;
    }
}
