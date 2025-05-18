package com.sunnao.ai.modules.ai.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SupportModelPlatformForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 平台名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空")
    private String icon;

    /**
     * 官网跳转地址
     */
    private String redirect;

    /**
     * api地址
     */
    @NotBlank(message = "api地址不能为空")
    private String baseUrl;

    /**
     * 状态（0禁用 1弃用）
     */
    private Integer status;

    /**
     * 介绍
     */
    @NotBlank(message = "介绍不能为空")
    private String description;

    /**
     * 平台标识符
     */
    @NotBlank(message = "平台标识不能为空")
    private String code;

}
