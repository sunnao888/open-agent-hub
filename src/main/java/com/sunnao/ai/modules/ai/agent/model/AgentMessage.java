package com.sunnao.ai.modules.ai.agent.model;

import lombok.Data;

/**
 * 智能体消息模型
 *
 * @author sunnao
 * @since 2025-06-02
 */
@Data
public class AgentMessage {

    /**
     * 智能体key
     */
    private String agentKey;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 消息内容
     */
    private String prompt;

}
