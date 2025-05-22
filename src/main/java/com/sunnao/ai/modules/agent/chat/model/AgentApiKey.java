package com.sunnao.ai.modules.agent.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgentApiKey {

    private String baseUrl;

    private String apiKey;
}
