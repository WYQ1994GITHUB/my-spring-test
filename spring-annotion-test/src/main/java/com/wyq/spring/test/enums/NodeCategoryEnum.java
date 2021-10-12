/*
 *
 *  Copyright 2020 byai.com All right reserved. This software is the
 *  confidential and proprietary information of byai.com ("Confidential
 *  Information"). You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement you entered
 *  into with byai.com.
 * /
 */

package com.wyq.spring.test.enums;

public enum NodeCategoryEnum {

    主动流程节点(0),
    多轮会话节点(1),
    知识库节点(2);

    private Integer type;

    NodeCategoryEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
