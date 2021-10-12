package com.wyq.spring.test.param;

import com.wyq.spring.test.enums.EnvironmentTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateKnowledgeReq implements Serializable {

    private static final long serialVersionUID = 4021652423474283506L;

    /**
     * 知识库主体信息
     */
    private UpdateKnowledgeBaseReq updateKnowledgeBaseReq;

    /**
     * 知识库流程
     */
    private DecisionReq decisionReq;

    /**
     * 是否校验话术权限
     */
    private Boolean checkPermission;

    /**
     * 环境
     */
    private EnvironmentTypeEnum env;
}
