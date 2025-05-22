package com.sunnao.ai.modules.platform.model.bo;

import lombok.Data;

@Data
public class PlatformBO {

    /**
     * 支持平台主键
     */
    private Long supportId;

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
     * 状态（0禁用 1弃用）
     */
    private Integer supportStatus;

    /**
     * 介绍
     */
    private String description;

    /**
     * 模型平台标识
     */
    private String code;

    /**
     * 用户绑定平台主键
     */
    private Long bindId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * apiKey
     */
    private String apiKey;

    /**
     * 状态（0禁用 1弃用）
     */
    private Integer bindStatus;

    /**
     * 模型主键
     */
    private Long modelId;

    /**
     * 模型组
     */
    private String modelGroup;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 模型能力（1推理 2视觉 3工具）
     */
    private String abilities;
}
