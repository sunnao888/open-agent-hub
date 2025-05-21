package com.sunnao.ai.modules.platform.service;

import com.sunnao.ai.modules.platform.model.dto.BindPlatformDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelAddDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelListDTO;
import com.sunnao.ai.modules.platform.model.entity.BindModel;
import com.sunnao.ai.modules.platform.model.vo.PlatformVO;

import java.util.List;

public interface PlatformService {

    /**
     * 获取用户模型平台列表(普通用户接口)
     *
     * @return 模型平台列表
     */
    List<PlatformVO> getClientPlatformList();

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
     * @return the platform configuration details wrapped in a {@link PlatformVO} object
     */
    PlatformVO getPlatformConfig(Long id);

    /**
     * Binds a platform to the user based on the provided platform details.
     *
     * @param bindModelPlatformDTO an object containing the platform details to be bound
     * @return true if the platform was successfully bound, false otherwise
     */
    boolean bindPlatform(BindPlatformDTO bindModelPlatformDTO);

    /**
     * Adds new models to the user's platform based on the provided details.
     *
     * @param dto an object containing the details needed to add models, including the platform binding ID and a list of models
     * @return true if the models were successfully added, false otherwise
     */
    boolean addModels(ModelAddDTO dto);

    /**
     * Retrieves the list of models that a user has added to a specific support platform.
     *
     * @param supportId the ID of the support platform
     * @return a list of model names added by the user to the specified support platform
     */
    List<String> getAddedModelList(Long supportId);

    /**
     * Deletes a model associated with a specific support platform.
     *
     * @param supportId the unique identifier of the support platform
     * @param modelName the name of the model to be deleted
     * @return true if the model was successfully deleted, false otherwise
     */
    boolean deleteModel(Long supportId, String modelName);

    /**
     * Updates the binding status of a platform based on the provided support platform ID.
     *
     * @param supportId the unique identifier of the support platform
     * @return true if the binding status was successfully updated, false otherwise
     */
    boolean updateBindStatus(Long supportId);

    /**
     * 获取用户绑定的平台模型列表
     *
     * @return 模型列表
     */
    List<BindModel> getBindModels();

}
