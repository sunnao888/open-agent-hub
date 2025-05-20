package com.sunnao.ai.modules.platform.model.dto;

import lombok.Data;

@Data
public class BindPlatformDTO {

    /**
     * 支持的平台id
     */
    private Long supportId;

    /**
     * apiKey
     */
    private String apiKey;
}
