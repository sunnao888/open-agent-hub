package com.sunnao.ai.modules.platform.converter;

import com.sunnao.ai.modules.platform.model.entity.BindPlatform;
import com.sunnao.ai.modules.platform.model.entity.SupportPlatform;
import com.sunnao.ai.modules.platform.model.vo.PlatformVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 模型平台对象转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlatformConverter {

    /**
     * 将模型平台实体转换为模型平台VO
     *
     * @param entity 模型平台实体
     * @return 模型平台VO
     */
    PlatformVO support2ModelPlatformVO(SupportPlatform entity);

    /**
     * 将模型平台实体列表转换为模型平台VO列表
     *
     * @param entityList 模型平台实体列表
     * @return 模型平台VO列表
     */
    List<PlatformVO> supports2ModelPlatformVOs(List<SupportPlatform> entityList);

    /**
     * 将 BindModelPlatform 和 SupportModelPlatform 对象合并转换为 ModelPlatformVO 对象。
     *
     * @param bind    用户绑定的模型平台信息
     * @param support 平台本身的基础信息
     * @return 合并后的 ModelPlatformVO 对象
     */
    @Mappings({
            @Mapping(source = "bind.id", target = "id"),
            @Mapping(source = "support.name", target = "name"),
            @Mapping(source = "support.icon", target = "icon"),
            @Mapping(source = "support.baseUrl", target = "baseUrl"),
            @Mapping(source = "support.redirect", target = "redirect"),
            @Mapping(source = "support.description", target = "description"),
            @Mapping(source = "bind.apiKey", target = "apiKey"),
            @Mapping(source = "bind.status", target = "status")
    })
    PlatformVO toModelPlatformVO(BindPlatform bind, SupportPlatform support);

}
