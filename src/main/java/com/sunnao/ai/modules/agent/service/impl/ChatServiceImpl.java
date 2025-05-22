package com.sunnao.ai.modules.agent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sunnao.ai.modules.agent.chat.Agent;
import com.sunnao.ai.modules.agent.chat.model.AgentApiKey;
import com.sunnao.ai.modules.agent.chat.model.AgentPrompt;
import com.sunnao.ai.modules.agent.model.dto.ChatDTO;
import com.sunnao.ai.modules.agent.model.dto.TestChatDTO;
import com.sunnao.ai.modules.agent.model.entity.AgentInfo;
import com.sunnao.ai.modules.agent.service.AgentInfoService;
import com.sunnao.ai.modules.agent.service.ChatService;
import com.sunnao.ai.modules.platform.model.bo.PlatformBO;
import com.sunnao.ai.modules.platform.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final AgentInfoService agentInfoService;

    private final PlatformService platformService;

    @Override
    public String chat(ChatDTO chatDTO) {
        Long agentId = chatDTO.getAgentId();

        AgentInfo agentInfo = agentInfoService.getById(agentId);

        Optional.ofNullable(agentInfo).orElseThrow(() -> new IllegalArgumentException("Agent not found"));

        Long modelId = agentInfo.getModelId();

        PlatformBO platformBO = platformService.getPlatformInfoByBindModel(modelId);

        Optional.ofNullable(platformBO).orElseThrow(() -> new IllegalArgumentException("Platform not found"));
        String modelName = platformBO.getModelName();
        int pipeIndex = modelName.indexOf('|');
        if (pipeIndex != -1) {
            modelName = modelName.substring(0, pipeIndex);
        }
        AgentApiKey agentApiKey = new AgentApiKey(platformBO.getBaseUrl(), platformBO.getApiKey());
        AgentPrompt agentPrompt = new AgentPrompt(modelName, agentInfo.getSystemPrompt(), chatDTO.getUserPrompt(), agentInfo.getTemperature(), agentInfo.getContextNum());

        return Agent.chat(agentApiKey, agentPrompt);
    }

    @Override
    public Flux<String> test(TestChatDTO testChatDTO) {
        PlatformBO platformBO = platformService.getPlatformInfoByModelName(StpUtil.getLoginIdAsLong(), testChatDTO.getModelName());
        Optional.ofNullable(platformBO).orElseThrow(() -> new IllegalArgumentException("Platform not found"));
        String modelName = testChatDTO.getModelName();
        int pipeIndex = modelName.indexOf('|');
        if (pipeIndex != -1) {
            modelName = modelName.substring(0, pipeIndex);
        }
        AgentApiKey agentApiKey = new AgentApiKey(platformBO.getBaseUrl(), platformBO.getApiKey());
        AgentPrompt agentPrompt = new AgentPrompt(modelName, testChatDTO.getSystemPrompt(), testChatDTO.getUserPrompt(), testChatDTO.getTemperature(), testChatDTO.getContextNum());
        return Agent.test(agentApiKey, agentPrompt);
    }
}
