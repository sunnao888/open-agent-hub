package com.sunnao.ai.modules.system.converter;

import com.sunnao.ai.modules.system.model.entity.User;
import com.sunnao.ai.modules.system.model.vo.CurrentUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConverter {

    CurrentUserVO entityToCurrentUserVO(User user);
}
