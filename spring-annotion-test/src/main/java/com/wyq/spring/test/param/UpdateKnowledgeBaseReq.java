package com.wyq.spring.test.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateKnowledgeBaseReq extends AddKnowledgeBaseReq {

    private static final long serialVersionUID = -5939932666515745548L;

    /**
     * 知识库id
     */
    private Integer knowledgeBaseId;
}
