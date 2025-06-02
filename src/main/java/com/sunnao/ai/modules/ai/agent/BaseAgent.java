package com.sunnao.ai.modules.ai.agent;

import cn.hutool.core.util.StrUtil;
import com.sunnao.ai.modules.ai.agent.model.AgentState;
import lombok.Data;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础智能体
 * 负责状体控制和流程控制
 *
 * @author sunnao
 * @since 2025-06-02
 */
@Data
public abstract class BaseAgent {

    // 名称
    private String name;

    // 提示词
    private String systemPrompt;
    private String nextStepPrompt;

    // 状态
    private AgentState state = AgentState.IDLE;

    // 执行步骤控制
    private int currentStep = 0;
    private int maxSteps;

    // Spring AI 对话客户端
    private ChatClient chatClient;

    // 消息列表
    private List<Message> messages = new ArrayList<>();

    /**
     * 运行代理
     */
    public String run(String userPrompt) {
        // 代理是否满足运行条件
        if (state != AgentState.IDLE) {
            throw new IllegalStateException("代理执行失败: 非法状态-" + state);
        }

        if (StrUtil.isBlankIfStr(userPrompt)) {
            throw new IllegalArgumentException("代理执行失败: 空的用户提示词");
        }

        // 开始执行，更改状态
        state = AgentState.RUNNING;
        messages.add(new UserMessage(userPrompt));
        // 结果列表
        List<String> results = new ArrayList<>();
        try {
            // 执行循环
            for (int i = 1; i <= maxSteps && state != AgentState.FINISHED; i++) {
                currentStep = i;
                // 执行单个步骤
                String stepResult = step();
                String result = "步骤" + currentStep + ": " + stepResult;
                results.add(result);
            }
            // 检查是否超出步骤限制
            if (currentStep >= maxSteps) {
                state = AgentState.FINISHED;
                results.add("已达到最大步骤限制: " + maxSteps);
            }
            return String.join("\n", results);
        } catch (Exception e) {
            state = AgentState.ERROR;
            return "执行出错: " + e.getMessage();
        } finally {
            cleanup();
        }
    }

    /**
     * 单个步骤
     */
    public abstract String step();

    /**
     * 清理资源
     */
    public void cleanup() {

    }

}
