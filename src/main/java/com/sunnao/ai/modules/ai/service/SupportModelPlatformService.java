package com.sunnao.ai.modules.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;

import java.util.List;

public interface SupportModelPlatformService extends IService<SupportModelPlatform> {

    /**
     * 获取系统支持的模型平台列表(状态为启用)
     *
     * @return 模型平台列表
     */
    List<SupportModelPlatform> getActiveEntityList();
}
