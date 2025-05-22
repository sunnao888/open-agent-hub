package com.sunnao.ai.modules.agent.controller;

import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.agent.model.dto.ChatDTO;
import com.sunnao.ai.modules.agent.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public Result<String> chat(@RequestBody ChatDTO chatDTO) {
        String response = chatService.chat(chatDTO);
        return Result.success(response);
    }
}
