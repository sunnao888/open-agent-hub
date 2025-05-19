package com.sunnao.ai.modules.ai.model.dto;

import lombok.Data;

@Data
public class BindModelPlatformDTO {

    /**
     * 支持的平台id
     */
    private Long supportId;

    /**
     * apiKey
     */
    private String apiKey;
}
