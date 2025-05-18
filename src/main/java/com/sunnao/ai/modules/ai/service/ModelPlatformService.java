package com.sunnao.ai.modules.ai.service;

import com.sunnao.ai.modules.ai.model.dto.ModelListDTO;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;

import java.util.List;

public interface ModelPlatformService {

    /**
     * 获取用户模型平台列表(普通用户接口)
     *
     * @return 模型平台列表
     */
    List<ModelPlatformVO> getClientPlatformList();

    /**
     * 获取可使用的模型列表
     *
     * @param modelListDTO 请求参数
     * @return 模型名称
     */
    List<String> getAvailableModelList(ModelListDTO modelListDTO);

    /**
     * Retrieves the user-specific platform configuration based on the provided platform ID.
     *
     * @param id the unique identifier of the platform
     * @return the platform configuration details wrapped in a {@link ModelPlatformVO} object
     */
    ModelPlatformVO getPlatformConfig(Long id);
}
