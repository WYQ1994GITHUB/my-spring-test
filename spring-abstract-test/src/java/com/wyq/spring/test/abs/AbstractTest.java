package com.wyq.spring.test.abs;

import com.wyq.spring.test.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class AbstractTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testA() {
        Class<? extends AbstractObjectDemo> test1AClass = TestDemoEnum.TEST_1.getAClass();
        Class<? extends AbstractObjectDemo> test2AClass = TestDemoEnum.TEST_2.getAClass();
        System.out.println(test1AClass);
        System.out.println(test2AClass);


        AbstractInterface a1 = applicationContext.getBean(test1AClass, AbstractInterface.class);
        AbstractInterface a2 = applicationContext.getBean(test2AClass, AbstractInterface.class);

        System.out.println(a1.getClass());
        System.out.println(a2.getClass());

        System.out.println(a1.execute());
        System.out.println(a2.execute());

    }
}
