/*
 *
 *  Copyright 2020 byai.com All right reserved. This software is the
 *  confidential and proprietary information of byai.com ("Confidential
 *  Information"). You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement you entered
 *  into with byai.com.
 * /
 */

package com.wyq.spring.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.wyq.spring.test.annotion.ApiAnnotation;
import com.wyq.spring.test.exception.CommonErrorCode;
import com.wyq.spring.test.exception.CommonException;
import com.wyq.spring.test.param.UpdateKnowledgeReq;
import com.wyq.spring.test.service.KnowledgeBaseService;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    @ApiAnnotation
    @Override
    public Boolean updateKnowledge(UpdateKnowledgeReq updateKnowledgeReq) {
        System.out.println(JSON.toJSONString(updateKnowledgeReq));
        test();
        return true;
    }

    private void test() {
        throw new CommonException(CommonErrorCode.UNKNOWN_ERROR, "error");
    }
}