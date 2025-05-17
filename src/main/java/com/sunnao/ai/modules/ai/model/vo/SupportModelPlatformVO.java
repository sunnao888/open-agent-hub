package com.sunnao.ai.modules.ai.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SupportModelPlatformVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 平台名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 官网跳转地址
     */
    private String redirect;

    /**
     * api地址
     */
    private String baseUrl;

    /**
     * 状态（0禁用 1启用）
     */
    private Integer status;

    /**
     * 介绍
     */
    private String description;

}
