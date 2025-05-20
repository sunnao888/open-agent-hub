package com.sunnao.ai.modules.agent.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunnao.ai.modules.agent.model.entity.AgentInfo;
import com.sunnao.ai.modules.agent.model.form.AgentInfoForm;
import com.sunnao.ai.modules.agent.model.vo.AgentInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AgentInfoConverter {

    Page<AgentInfoVO> toPageVO(Page<AgentInfo> page);

    AgentInfo toEntity(AgentInfoForm form);

    AgentInfoVO toVO(AgentInfo entity);

    List<AgentInfoVO> toVOS(List<AgentInfo> entityList);
}
