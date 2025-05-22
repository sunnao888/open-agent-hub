package com.sunnao.ai.modules.agent.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TestChatDTO {

    /**
     * 模型名称
     */
    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 上下文轮数
     */
    private Integer contextNum;

    /**
     * 系统提示词
     */
    private String systemPrompt;

    /**
     * 用户提示词
     */
    @NotBlank(message = "用户提示词不能为空")
    private String userPrompt;

    /**
     * 是否开启联网搜索
     */
    private Boolean webSearch;

}
