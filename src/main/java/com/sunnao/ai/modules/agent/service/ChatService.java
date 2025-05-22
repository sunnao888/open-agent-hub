package com.sunnao.ai.modules.agent.service;

import com.sunnao.ai.modules.agent.model.dto.ChatDTO;

public interface ChatService {

    /**
     * 智能体对话
     *
     * @param chatDTO 对话参数
     * @return 智能体返回
     */
    String chat(ChatDTO chatDTO);

}
