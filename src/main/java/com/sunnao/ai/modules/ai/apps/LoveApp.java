package com.sunnao.ai.modules.ai.apps;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import com.alibaba.cloud.ai.memory.jdbc.MysqlChatMemoryRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * ai恋爱大师
 *
 * @author sunnao
 * @since 2025-06-01
 */
@Component
public class LoveApp {

    private static final String DEFAULT_MODEL = "qwen3-235b-a22b";

    private static final Double DEFAULT_TEMPERATURE = 1.3;

    private static final int MAX_MESSAGES = 100;

    private static final String SYSTEM_PROMPT = """
                    你是深耕恋爱心理领域的专家小小鹿。
                    开场向用户表明身份，告知用户可倾诉恋爱难题。
                    围绕单身、恋爱、已婚三种状态提问：
                        单身状态询问社交圈拓展及追求心仪对象的困扰；
                        恋爱状态询问沟通、习惯差异引发的矛盾；
                        已婚状态询问家庭责任与亲属关系处理的问题。
                    引导用户详述事情经过、对方反应及自身想法，以便给出专属解决方案。
                    通过提问深入挖掘用户内心感受，提供情感支持和实用建议。
            """;
    private final MessageWindowChatMemory messageWindowChatMemory;

    private final ChatClient loveClient;

    private final ToolCallback[] allTools;

    private final ToolCallbackProvider toolCallbackProvider;

    public LoveApp(ChatModel loveModel, MysqlChatMemoryRepository mysqlChatMemoryRepository,
                   ToolCallback[] allTools, ToolCallbackProvider toolCallbackProvider) {

        this.allTools = allTools;
        this.toolCallbackProvider = toolCallbackProvider;

        messageWindowChatMemory = MessageWindowChatMemory.builder()
                .chatMemoryRepository(mysqlChatMemoryRepository)
                .maxMessages(MAX_MESSAGES)
                .build();

        loveClient = ChatClient.builder(loveModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        // 日志记录顾问
                        new SimpleLoggerAdvisor(),
                        // 对话记忆顾问(内存实现)
                        MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build()
                )
                .build();
    }

    public Flux<String> stream(String conversationId, String prompt) {
        return loveClient.prompt(prompt)
                .advisors(
                        a -> a.param(CONVERSATION_ID, conversationId)
                )
                .options(DashScopeChatOptions
                        .builder()
                        .withModel(DEFAULT_MODEL)
                        .withTemperature(DEFAULT_TEMPERATURE)
                        .withEnableThinking(false)
                        .build())
                .tools(allTools)
                .toolCallbacks(toolCallbackProvider)
                .stream()
                .content();
    }

}
