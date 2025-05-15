package com.sunnao.ai.modules.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.ai.model.entity.BindModelPlatform;

import java.util.List;

public interface BindModelPlatformService extends IService<BindModelPlatform> {

    /**
     * 获取用户绑定的模型平台列表
     *
     * @param loginId 登录用户ID
     * @return 模型平台列表
     */
    List<BindModelPlatform> getEntityListByUserId(Long loginId);

    /**
     * 获取用户启用中的模型平台ID
     *
     * @param loginId 登录用户ID
     * @return 模型平台ID列表
     */
    List<Long> getActiveSupportIdListByUserId(Long loginId);
}
