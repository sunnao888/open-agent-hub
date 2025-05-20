package com.sunnao.ai.modules.platform.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ModelAddDTO {

    /**
     * 绑定模型平台id
     */
    @NotBlank(message = "模型平台id不能为空")
    private Long supportId;

    /**
     * 模型列表
     */
    @NotBlank(message = "模型列表不能为空")
    private List<String> models;
}
