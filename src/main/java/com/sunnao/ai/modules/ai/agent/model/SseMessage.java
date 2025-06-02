package com.sunnao.ai.modules.ai.agent.model;

import lombok.Data;

@Data
public class SseMessage {

    private String type;

    private String content;
}
