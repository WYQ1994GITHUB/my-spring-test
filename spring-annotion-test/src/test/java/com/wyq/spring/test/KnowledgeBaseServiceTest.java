package com.wyq.spring.test;

import com.alibaba.fastjson.JSON;
import com.wyq.spring.test.param.UpdateKnowledgeReq;
import com.wyq.spring.test.service.KnowledgeBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class KnowledgeBaseServiceTest {

    @Autowired
    private KnowledgeBaseService knowledgeBaseService;

    @Test
    public void testA() throws IOException {
        InputStream in = new ClassPathResource("case/updateKnowledgeReq.json").getInputStream();
        String json = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
        UpdateKnowledgeReq req = JSON.parseObject(json, UpdateKnowledgeReq.class);
        Boolean result = knowledgeBaseService.updateKnowledge(req);
        System.out.println(result);
    }
}
