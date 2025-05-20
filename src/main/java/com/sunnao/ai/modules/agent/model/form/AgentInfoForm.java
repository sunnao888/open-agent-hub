package com.sunnao.ai.modules.agent.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Agent信息表单
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Data
public class AgentInfoForm {

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 模型id
     */
    @NotNull(message = "模型id不能为空")
    private Long modelId;

    /**
     * 提示词
     */
    private String systemPrompt;

    /**
     * 是否开启联网搜索(0 否 1是)
     */
    private Integer webSearch;

    /**
     * 温度
     */
    private Double temperature;

    /**
     * 上下文轮数
     */
    private Integer contextNum;
} 