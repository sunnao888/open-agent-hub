package com.sunnao.ai.modules.ai.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.ai.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 大模型平台
 *
 * @author sunnao
 * @since 2025-05-15
 */
@EqualsAndHashCode(callSuper = false)
@TableName(value = "agent_support_model_platform")
@Data
public class SupportModelPlatform extends BaseEntity {

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
    private Integer status;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 修改人ID
     */
    private Long updateBy;

    /**
     * 逻辑删除标识(0-未删除 1-已删除)
     */
    private Integer isDeleted;

    /**
     * 介绍
     */
    private String description;

    /**
     * 模型平台标识
     */
    private String code;
}