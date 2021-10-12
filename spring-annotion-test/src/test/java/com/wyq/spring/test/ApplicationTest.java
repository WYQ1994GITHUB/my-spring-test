package com.wyq.spring.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@Configuration
@SpringBootApplication(scanBasePackages = {"com.wyq.spring.test"})
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
