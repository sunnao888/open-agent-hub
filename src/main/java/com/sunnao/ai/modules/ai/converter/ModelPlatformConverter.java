package com.sunnao.ai.modules.ai.converter;

import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 模型平台对象转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModelPlatformConverter {

    /**
     * 将模型平台实体转换为模型平台VO
     *
     * @param entity 模型平台实体
     * @return 模型平台VO
     */
    ModelPlatformVO support2ModelPlatformVO(SupportModelPlatform entity);

    /**
     * 将模型平台实体列表转换为模型平台VO列表
     *
     * @param entityList 模型平台实体列表
     * @return 模型平台VO列表
     */
    List<ModelPlatformVO> supports2ModelPlatformVOs(List<SupportModelPlatform> entityList);

}
