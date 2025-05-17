package com.sunnao.ai.common.enums;

import lombok.Getter;

/**
 * 角色枚举
 *
 * @author sunnao
 * @since 2025-05-17
 */
@Getter
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("admin", "管理员"),

    /**
     * 普通用户
     */
    USER("user", "普通用户");

    private final String code;
    private final String name;

    RoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
