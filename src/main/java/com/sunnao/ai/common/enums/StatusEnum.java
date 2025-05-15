package com.sunnao.ai.common.enums;

import lombok.Getter;

/**
 * 系统通用状态枚举
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Getter
public enum StatusEnum {
    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    private final Integer code;
    private final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
