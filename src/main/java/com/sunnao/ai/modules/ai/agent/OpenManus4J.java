package com.sunnao.ai.modules.ai.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

@Component
public class OpenManus4J extends ToolCallAgent {
    private static final String SYSTEM_PROMPT = """
            你是OpenManus4J，一个全能的AI助手，旨在解决用户提出的任何任务。
            你拥有多种可用工具，可以高效地完成复杂的请求。
            """;
    private static final String NEXT_STEP_PROMPT = """
            根据用户需求，主动选择最合适的工具或工具组合。
            对于复杂任务，可以将问题拆解，并分步骤使用不同的工具来解决。
            每次使用工具后，要清楚地说明执行结果，并提出下一步建议。
            如果你想在任何时候终止交互，可以使用 terminate 工具。
            """;

    public OpenManus4J(ToolCallback[] allTools, ChatModel dashscopeChatModel) {
        super(allTools);
        this.setName("OpenManus4J");
        this.setSystemPrompt(SYSTEM_PROMPT);
        this.setNextStepPrompt(NEXT_STEP_PROMPT);
        this.setMaxSteps(20);
        ChatClient chatClient = ChatClient.builder(dashscopeChatModel).build();
        this.setChatClient(chatClient);
    }
}
