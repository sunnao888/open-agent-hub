package com.sunnao.ai.modules.ai.service;

import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;

import java.util.List;

public interface ModelPlatformService {

    /**
     * 获取用户模型平台列表(普通用户接口)
     *
     * @return 模型平台列表
     */
    List<ModelPlatformVO> getClientPlatformList();

}
