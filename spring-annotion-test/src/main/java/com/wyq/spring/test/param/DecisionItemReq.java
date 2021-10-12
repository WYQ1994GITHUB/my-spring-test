/*
 *
 *  Copyright 2020 byai.com All right reserved. This software is the
 *  confidential and proprietary information of byai.com ("Confidential
 *  Information"). You shall not disclose such Confidential Information and shall
 *  use it only in accordance with the terms of the license agreement you entered
 *  into with byai.com.
 * /
 */

package com.wyq.spring.test.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class DecisionItemReq implements Serializable {

    private static final long serialVersionUID = 5699670679655155033L;

    /**
     * 知识节点id，空为新增，非空为更新
     */
    private Integer decisionItemId;

    /**
     * 如果是系统时间类型，则有值，为规则开始时间 ex:19:00
     */
    private String startTime;

    /**
     * 如果是系统时间类型，则有值，为规则结束时间 ex:20:00
     */
    private String endTime;

    /**
     * 话术文字
     */
    private String knowledge;

    /**
     * 如果是顺序类型，则有值，为序号
     */
    private Integer index;

    private String knowledgeOssUrl;

    private String knowledgeOssFullUrl;

    private String ttsKnowledgeOssUrl;

    /**
     * 解析knowledge中的模板变量
     * 前端需要的json结构
     */
    private String rawContent;

    /**
     * 附加信息
     */
    private String extraInfo;

    private Integer action;

    /**
     * 执行动作时参数
     */
    private String actionParam;
}
