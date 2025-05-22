package com.sunnao.ai.modules.agent.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 智能体消息对象
 *
 * @author sunnao
 * @since 2025-05-20
 */
@Data
@AllArgsConstructor
public class AgentPrompt {

    /**
     * 模型名称
     */
    private String model;

    /**
     * 系统提示词
     */
    private String systemPrompt;

    /**
     * 用户提示词
     */
    private String userPrompt;

    /**
     * 模型温度
     */
    private Double temperature;

    /**
     * 上下文轮数
     */
    private Integer contextNum;

}
