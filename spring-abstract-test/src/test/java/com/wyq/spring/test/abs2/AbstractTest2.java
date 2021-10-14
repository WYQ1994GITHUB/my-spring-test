package com.wyq.spring.test.abs2;

import com.wyq.spring.test.ApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class AbstractTest2 {

    @Autowired
    private AbstractInterface2 abstractInterface2;

    @Test
    public void testA() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        abstractInterface2.execute("a", "b", list);
    }
}
