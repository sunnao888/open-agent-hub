package com.sunnao.ai.modules.ai.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModelListDTO {

    @NotBlank(message = "baseUrl不能为空")
    private String baseUrl;

    @NotBlank(message = "apiKey不能为空")
    private String apiKey;
}
