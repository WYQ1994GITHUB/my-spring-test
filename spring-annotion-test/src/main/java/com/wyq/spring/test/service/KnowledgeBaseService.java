/*
 *
 *  Copyright 2020 byai.com All right reserved. This software is the
 *  confidential and proprietary information of byai.com ("Confidential
 *  Information"). You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement you entered
 *  into with byai.com.
 * /
 */

package com.wyq.spring.test.service;

import com.wyq.spring.test.param.UpdateKnowledgeReq;

public interface KnowledgeBaseService {
    Boolean updateKnowledge(UpdateKnowledgeReq updateKnowledgeReq);
}
