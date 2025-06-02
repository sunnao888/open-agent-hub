package com.sunnao.ai.modules.ai.advisor;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.AdvisorChain;
import org.springframework.ai.chat.client.advisor.api.BaseAdvisor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 思考内容顾问
 * 主要作用是把阿里系推理模型的推理过程整合到输出中的<think>标签中。
 *
 * @author sunnao
 * @since 2025-06-01
 */
@Slf4j
public class ReasoningContentAdvisor implements BaseAdvisor {

    private final int order;

    public ReasoningContentAdvisor(Integer order) {
        this.order = order != null ? order : 0;
    }

    @Override
    public int getOrder() {

        return this.order;
    }

    @NotNull
    @Override
    public ChatClientRequest before(@NotNull final ChatClientRequest chatClientRequest, @NotNull final AdvisorChain advisorChain) {
        return chatClientRequest;
    }

    @NotNull
    @Override
    public ChatClientResponse after(@NotNull final ChatClientResponse chatClientResponse, @NotNull final AdvisorChain advisorChain) {
        ChatResponse resp = chatClientResponse.chatResponse();
        if (Objects.isNull(resp)) {

            return chatClientResponse;
        }

        log.debug(String.valueOf(resp.getResults().getFirst().getOutput().getMetadata()));
        String reasoningContent = String.valueOf(resp.getResults().getFirst().getOutput().getMetadata().get("reasoningContent"));

        if (StringUtils.hasText(reasoningContent)) {
            List<Generation> thinkGenerations = resp.getResults().stream()
                    .map(generation -> {
                        AssistantMessage output = generation.getOutput();
                        AssistantMessage thinkAssistantMessage = new AssistantMessage(
                                String.format("<think>%s</think>", reasoningContent) + output.getText(),
                                output.getMetadata(),
                                output.getToolCalls(),
                                output.getMedia()
                        );
                        return new Generation(thinkAssistantMessage, generation.getMetadata());
                    }).toList();

            ChatResponse thinkChatResp = ChatResponse.builder().from(resp).generations(thinkGenerations).build();
            return ChatClientResponse.builder().chatResponse(thinkChatResp).build();

        }

        return chatClientResponse;
    }
}
