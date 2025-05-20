package com.sunnao.ai.modules.platform.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunnao.ai.modules.platform.model.entity.SupportPlatform;
import com.sunnao.ai.modules.platform.model.form.SupportPlatformForm;
import com.sunnao.ai.modules.platform.model.vo.SupportPlatformVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SupportPlatformConverter {

    Page<SupportPlatformVO> toPageVO(Page<SupportPlatform> page);

    SupportPlatform toEntity(SupportPlatformForm form);

    SupportPlatformVO toVO(SupportPlatform entity);
}
