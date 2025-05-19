package com.sunnao.ai.modules.ai.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModelListDTO {

    @NotBlank(message = "supportId不能为空")
    private Long supportId;

    @NotBlank(message = "apiKey不能为空")
    private String apiKey;
}
