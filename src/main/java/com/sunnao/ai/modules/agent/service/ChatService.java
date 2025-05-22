package com.sunnao.ai.modules.agent.service;

import com.sunnao.ai.modules.agent.model.dto.ChatDTO;
import com.sunnao.ai.modules.agent.model.dto.TestChatDTO;
import reactor.core.publisher.Flux;

public interface ChatService {

    /**
     * 智能体对话
     *
     * @param chatDTO 对话参数
     * @return 智能体返回
     */
    String chat(ChatDTO chatDTO);

    /**
     * 测试对话
     *
     * @param testChatDTO 对话参数
     * @return 测试对话返回
     */
    Flux<String> test(TestChatDTO testChatDTO);

}
