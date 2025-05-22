package com.sunnao.ai.modules.agent.chat;

import com.sunnao.ai.modules.agent.chat.model.AgentApiKey;
import com.sunnao.ai.modules.agent.chat.model.AgentPrompt;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

/**
 * 智能体入口
 *
 * @author sunnao
 * @since 2025-05-20
 */
public class Agent {

    /**
     * 基础对话
     * 目前只支持兼容openai规范的模型,后续可以扩展更多模型
     */
    public static String chat(AgentApiKey apiKey, AgentPrompt message) {

        OpenAiApi baseApi = OpenAiApi.builder().baseUrl(apiKey.getBaseUrl()).apiKey(apiKey.getApiKey()).build();

        OpenAiChatModel baseModel = OpenAiChatModel.builder()
                .openAiApi(baseApi)
                .defaultOptions(OpenAiChatOptions.builder().model(message.getModel()).build())
                .build();

        ChatClient chatClient = ChatClient.builder(baseModel)
                .defaultSystem(message.getSystemPrompt())
                .build();

        return chatClient
                .prompt(message.getUserPrompt())
                .options(OpenAiChatOptions.builder().temperature(message.getTemperature()).build())
                .call()
                .content();

    }
}
