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
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * ai恋爱大师
 *
 * @author sunnao
 * @since 2025-06-01
 */
@Component
public class Manus4j {

    private static final String DEFAULT_MODEL = "qwen3-235b-a22b";

    private static final Double DEFAULT_TEMPERATURE = 1.3;

    private static final int MAX_MESSAGES = 100;

    private static final String SYSTEM_PROMPT = """
            你是OpenManus4J，一个全能的AI助手，旨在解决用户提出的任何任务。
            你拥有多种可用工具，可以高效地完成复杂的请求。
            根据用户需求，主动选择最合适的工具或工具组合。
            对于复杂任务，可以将问题拆解，并分步骤使用不同的工具来解决。
            每次使用工具后，要清楚地说明执行结果，并提出下一步建议。
            如果你想在任何时候终止交互，可以使用 terminate 工具。
            """;
    private final MessageWindowChatMemory messageWindowChatMemory;

    private final ChatClient loveClient;

    private final ToolCallback[] allTools;

    private final ToolCallbackProvider toolCallbackProvider;

    public Manus4j(ChatModel loveModel, MysqlChatMemoryRepository mysqlChatMemoryRepository,
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
                .toolCallbacks(allTools)
                .toolCallbacks(toolCallbackProvider)
                .stream()
                .content();
    }

    public SseEmitter sse(String conversationId, String userPrompt) {
        SseEmitter emitter = new SseEmitter();
        loveClient.prompt(userPrompt)
                .advisors(
                        a -> a.param(CONVERSATION_ID, conversationId)
                )
                .options(DashScopeChatOptions
                        .builder()
                        .withModel(DEFAULT_MODEL)
                        .withTemperature(DEFAULT_TEMPERATURE)
                        .withEnableThinking(false)
                        .build())
                .toolCallbacks(allTools)
                .toolCallbacks(toolCallbackProvider)
                .stream()
                .content()
                .doOnNext(content -> {
                    try {
                        emitter.send(content);
                    } catch (Exception e) {
                        emitter.completeWithError(e);
                    }
                })
                .doOnComplete(emitter::complete)
                .doOnError(emitter::completeWithError)
                .subscribe();

        return emitter;
    }
}
