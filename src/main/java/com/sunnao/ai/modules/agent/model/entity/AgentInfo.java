package com.sunnao.ai.modules.agent.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.ai.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Agent信息
 *
 * @author sunnao
 * @since 2025-05-15
 */
@EqualsAndHashCode(callSuper = false)
@TableName(value = "agent_info")
@Data
public class AgentInfo extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 提示词
     */
    private String prompt;

    /**
     * 是否开启联网搜索(0 否 1是)
     */
    private Integer webSearch;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 修改人ID
     */
    private Long updateBy;

    /**
     * 逻辑删除标识(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 上下文轮数
     */
    private Integer contextNum;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户id
     */
    private Long userId;
} 