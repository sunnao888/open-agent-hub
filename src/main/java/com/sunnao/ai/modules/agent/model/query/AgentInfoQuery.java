package com.sunnao.ai.modules.agent.model.query;

import lombok.Data;

/**
 * Agent信息查询条件
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Data
public class AgentInfoQuery {

    /**
     * 名称
     */
    private String name;

    /**
     * 模型id
     */
    private Long modelId;

    /**
     * 是否开启联网搜索(0 否 1是)
     */
    private Integer webSearch;
} 