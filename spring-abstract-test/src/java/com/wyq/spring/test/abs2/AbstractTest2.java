package com.wyq.spring.test.abs2;

import com.wyq.spring.test.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class AbstractTest2 {

    @Autowired
    private AbstractInterface2 abstractInterface2;

    @Test
    public void testA() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 2; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    abstractInterface2.execute("a", "b", list);
                }
            });
            Thread.sleep(2000);
        }

        Thread.sleep(15000);
        fixedThreadPool.shutdown();

    }
}
