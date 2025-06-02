package com.sunnao.ai.modules.ai.agent.model;

import lombok.Getter;

/**
 * 智能体状态
 *
 * @author sunnao
 * @since 2025-06-02
 */

@Getter
public enum AgentState {

    /**
     * 空闲
     */
    IDLE,

    /**
     * 运行中
     */
    RUNNING,

    /**
     * 完成
     */
    FINISHED,

    /**
     * 错误
     */
    ERROR;

}
