package com.sunnao.ai.modules.ai.service;

import cn.hutool.core.util.StrUtil;
import com.sunnao.ai.modules.ai.agent.model.AgentMessage;
import com.sunnao.ai.modules.ai.apps.LoveApp;
import com.sunnao.ai.modules.ai.apps.Manus4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final LoveApp loveApp;
    private final Manus4j openManus4J;

    public SseEmitter chat(AgentMessage message) {
        String agentKey = message.getAgentKey();
        String userPrompt = message.getPrompt();

        if (StrUtil.isBlankIfStr(agentKey)) {
            agentKey = "common";
        }

        if (StrUtil.isBlankIfStr(userPrompt)) {
            throw new IllegalArgumentException("用户提示词不能为空");
        }

        switch (agentKey) {
            case "love" -> {
                return loveApp.sse(message.getConversationId(), userPrompt);
            }
            case "common" -> {
                return openManus4J.sse(message.getConversationId(), userPrompt);
            }
            default -> {
                throw new IllegalArgumentException("非法的智能体key: " + agentKey);
            }
        }
    }

}
