package com.sunnao.ai.modules.ai.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.ai.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户绑定的大模型平台包含的模型列表
 *
 * @author sunnao
 * @since 2025-05-15
 */
@EqualsAndHashCode(callSuper = false)
@TableName(value = "agent_bind_model_list")
@Data
public class BindModel extends BaseEntity {

    /**
     * 用户绑定的大模型平台id
     */
    private Long bindId;

    /**
     * 模型组
     */
    private String modelGroup;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 模型能力（1推理 2视觉 3工具）
     */
    private String abilities;

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