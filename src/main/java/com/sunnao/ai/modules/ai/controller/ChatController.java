package com.sunnao.ai.modules.ai.controller;

import com.sunnao.ai.modules.ai.agent.model.AgentMessage;
import com.sunnao.ai.modules.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * 智能体通用对话
     */
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestBody AgentMessage message) {
        return chatService.chat(message);
    }

    /**
     * manus4J测试
     */
    @GetMapping(value = "/manus4j", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String manus4JTest( String message) {
        return chatService.manus4JTest(message);
    }

}
