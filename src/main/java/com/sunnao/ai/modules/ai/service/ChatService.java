package com.sunnao.ai.modules.ai.service;

import cn.hutool.core.util.StrUtil;
import com.sunnao.ai.modules.ai.agent.OpenManus4J;
import com.sunnao.ai.modules.ai.agent.model.AgentMessage;
import com.sunnao.ai.modules.ai.apps.LoveApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final LoveApp loveApp;
    private final OpenManus4J openManus4J;

    public Flux<String> love(String conversationId, String prompt) {
        return loveApp.stream(conversationId, prompt);
    }

    public SseEmitter manus(String prompt) {
        return openManus4J.sse(prompt);
    }

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
            case "manus" -> {
                return openManus4J.sse(userPrompt);
            }
            default -> {
                throw new IllegalArgumentException("非法的智能体key: " + agentKey);
            }
        }
    }
}
