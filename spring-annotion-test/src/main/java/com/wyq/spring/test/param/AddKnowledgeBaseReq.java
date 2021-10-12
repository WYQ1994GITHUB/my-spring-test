package com.wyq.spring.test.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddKnowledgeBaseReq implements Serializable {

    private static final long serialVersionUID = 4801144668428800756L;

    /**
     * 话术id
     */
    private Integer robotDefId;

    /**
     * 标准问题
     */
    private String standardQuestion;

    /**
     * 相似问题
     */
    private List<String> similarityQuestion;

    /**
     * 标题
     */
    private String title;

    /**
     * 答案
     */
    private String answer;

    /**
     * 知识库的类型 0:普通被动话术知识 1:意向被动话术知识 2:主动话术知识 3:公共知识 4:默认知识
     */
    private Integer knowledgeType;

    /**
     * 被动树流程id
     */
    private Integer sceneNodeId;

    /**
     * 需要执行的动作，例如挂机 1:挂机 2:等待用户应答 3:执行下一步 4:重复当前问话
     */
    private Integer action;

    /**
     * 公共知识库类型 0：响应类型 1：普通知识点
     */
    private Integer commonKnowledgeType;

    /**
     * aliyun上的知识点id
     */
    private Integer aliyunKnowledgeBaseId;

    /**
     * 知识库类别：0-全局配置 1-公共知识库 2-用户自定义
     */
    private Integer knowledgeCategory;

    /**
     * 分组id
     */
    private Integer knowledgeGroupId;

    /**
     * 标注为关注点
     */
    private Boolean labelConcern;

    /**
     * 是否加入黑名单
     */
    private Boolean onBlack;
}
