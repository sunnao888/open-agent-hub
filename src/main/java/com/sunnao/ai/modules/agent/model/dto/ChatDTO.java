package com.sunnao.ai.modules.agent.model.dto;

import lombok.Data;

@Data
public class ChatDTO {

    /**
     * 智能体主键
     */
    private Long agentId;

    /**
     * 用户提示词
     */
    private String userPrompt;
}
