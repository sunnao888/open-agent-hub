package com.sunnao.ai.modules.platform.third.openai.model;

import lombok.Data;

/**
 * Openai模型对象, 兼容openai规范的模型提供商都使用该模型
 *
 * @author sunnao
 * @since 2025-05-18
 */
@Data
public class OpenaiModel {

    /**
     * 模型标识符,可以在API端点中引用
     */
    private String id;

    /**
     * 对象类型,总是"model"
     */
    private String object;

    /**
     * 创建模型的Unix时间戳(秒)
     */
    private Long created;

    /**
     * 拥有该模型的组织
     */
    private String ownedBy;
}
