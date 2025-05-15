package com.sunnao.ai.modules.ai.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ModelPlatformVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 平台ID
     */
    private String id;

    /**
     * 平台名称
     */
    private String name;

    /**
     * 平台图标
     */
    private String icon;

    /**
     * 平台地址
     */
    private String baseUrl;

    /**
     * 跳转地址
     */
    private String redirect;

    /**
     * 状态
     */
    private Integer status;
}
