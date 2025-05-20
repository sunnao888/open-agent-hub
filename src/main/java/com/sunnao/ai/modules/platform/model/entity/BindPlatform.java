package com.sunnao.ai.modules.platform.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.ai.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户绑定的大模型平台
 *
 * @author sunnao
 * @since 2025-05-15
 */
@EqualsAndHashCode(callSuper = false)
@TableName(value = "agent_bind_model_platform")
@Data
public class BindPlatform extends BaseEntity {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 支持的平台id
     */
    private Long supportId;

    /**
     * apiKey
     */
    private String apiKey;

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

}