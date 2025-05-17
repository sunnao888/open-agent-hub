package com.sunnao.ai.modules.ai.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.form.SupportModelPlatformForm;
import com.sunnao.ai.modules.ai.model.vo.SupportModelPlatformVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupportModelPlatformConverter {

    Page<SupportModelPlatformVO> toPageVO(Page<SupportModelPlatform> page);

    SupportModelPlatform toEntity(SupportModelPlatformForm form);
}
