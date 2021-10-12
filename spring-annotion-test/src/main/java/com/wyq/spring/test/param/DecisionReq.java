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

import com.wyq.spring.test.enums.NodeCategoryEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class DecisionReq implements Serializable {

    private static final long serialVersionUID = 7075677287660713330L;
    /**
     * INTEGER(10) 必填 自增ID
     */
    private Integer decisionId;

    /**
     * INTEGER(10) 必填 场景ID
     */
    private Integer sceneDefId;

    /**
     * INTEGER(10) 必填 父亲ID
     */
    private Integer parentId;
    /**
     * INTEGER(10) 根ID
     */
    private Integer sceneNodeId;

    /**
     * VARCHAR(256) 必填 名称
     */
    private String name;

    /**
     * VARCHAR(256) 必填 回答名称
     */
    private Set<String> answerIds;

    /**
     * VARCHAR(100) 子节点的用户响应类型
     */
    private Set<String> childrenAnswerIds;

    /**
     * VARCHAR(256) 必填 回答名称
     */
    private String refererId;

    /**
     * 用户标识根节点的排序
     */
    private Integer index;

    /**
     * VARCHAR(128) 关键词，如果文本命中了，直接匹配语义
     */
    private String keyword;

    /**
     * INTEGER(10) 默认值[0] 节点类型:0-主动话术节点,1-普通话术节点 2 -意向被动话术
     */
    private Integer decisionType;

    /**
     * INTEGER(10) 默认值[0] 0-允许在播放时打断,1-不允许在播放时打断
     */
    private Integer acceptBreak;

    /**
     * 允许打断播报进度
     */
    private Integer acceptBreakBroadcastProgress;

    /**
     * INTEGER(10) 默认值[0] 节点被打断后回到现场时:0-重复,1-不重复
     */
    private Integer afterBreakType;

    /**
     * INTEGER(10) 需要执行的动作，
     */
    private Integer action;


    /**
     * INTEGER(10) 知识库id
     */
    private Integer knowledgeBaseId;

    /**
     * VARCHAR(100) 路径
     */
    private String path;

    /**
     * 话术节点类型
     */
    private Integer nodeType;

    /**
     * 意向类型(使用json格式表示)
     */
    private String intentionDef;

    /**
     * 客户自定义标签
     */
    private String labelInfoIds;

    /**
     * 客户自定义标签
     */
    private List<Map<String, Object>> labelInfoItem;

    /**
     * VARCHAR(32) action参数
     */
    private String actionParam;

    /**
     * 话术节点变量
     */
    private Integer smsTemplateId;

    /**
     * VARCHAR(1024) 关键词和响应类型定义
     */
    private String answerKeywordDef;
    /**
     * INTEGER(10) 默认值[0] 是否使用通知监听，1-使用，0-不使用
     */
    private Integer useEavesdrop;

    private String eavesdropParam;

    private Integer attachmentSendFlag;

    private String attachment;

    private Integer ttsType;

    /**
     * 转接坐席组id
     */
    private Integer transferSeatGroupId;
    /**
     * 转接后动作
     */
    private Integer transferAction;

    /**
     * 转接手机号码
     */
    private String transferTelephone;

    /**
     * 转手机TAB是否可编辑标识
     */
    private Boolean transferTelephoneEditable;

    /**
     * 节点规则 1-顺序 2-系统时间
     */
    private Integer ruleType;

    /**
     * 节点是否重复
     */
    private Boolean nodeRepeat;

    /**
     * 节点重复次数
     */
    private Integer nodeRepeatTimes;

    /**
     * 节点知识信息
     */
    private List<DecisionItemReq> decisionItemList;

    private String ivrParam;

    private String extendField;

    private String dataCollectExpression;

    /**
     * 是否开启数据采集，0：关闭，1：开启
     */
    private Integer dataCollectFlag;

    /**
     * 辅助话术id
     */
    private Integer assistRobotId;

    /**
     * 是否转到在线客服坐席 1是 0否 默认0
     */
    private Integer onlineServiceSwitch;

    /**
     * 判断节点是属于主动流程，多轮会话，知识库
     */
    private NodeCategoryEnum nodeCategory;

    /**
     * 高优先级设置
     */
    private Boolean userMeanHighPriority;
}
