package com.sunnao.ai.modules.agent.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Agent信息VO
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Data
public class AgentInfoVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 提示词
     */
    private String systemPrompt;

    /**
     * 是否开启联网搜索(0 否 1是)
     */
    private Integer webSearch;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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
} 