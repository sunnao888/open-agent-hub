package com.sunnao.ai.modules.ai.controller;

import com.sunnao.ai.modules.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * 恋爱大师
     */
    @GetMapping("/love")
    public void love(String prompt) {
        chatService.love(prompt);
    }

    /**
     * manus
     */
    @GetMapping("/manus")
    public void manus(String prompt) {
        chatService.manus(prompt);
    }

}
