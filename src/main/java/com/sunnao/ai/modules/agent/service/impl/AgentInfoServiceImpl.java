package com.sunnao.ai.modules.agent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.modules.agent.converter.AgentInfoConverter;
import com.sunnao.ai.modules.agent.mapper.AgentInfoMapper;
import com.sunnao.ai.modules.agent.model.entity.AgentInfo;
import com.sunnao.ai.modules.agent.model.form.AgentInfoForm;
import com.sunnao.ai.modules.agent.model.query.AgentInfoQuery;
import com.sunnao.ai.modules.agent.model.vo.AgentInfoVO;
import com.sunnao.ai.modules.agent.service.AgentInfoService;
import com.sunnao.ai.modules.platform.model.entity.BindModel;
import com.sunnao.ai.modules.platform.service.BindModelListService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgentInfoServiceImpl extends ServiceImpl<AgentInfoMapper, AgentInfo> implements AgentInfoService {

    private final AgentInfoConverter agentInfoConverter;

    private final BindModelListService bindModelListService;

    @Override
    public Page<AgentInfo> pageList(Page<AgentInfo> page, AgentInfoQuery query) {
        LambdaQueryWrapper<AgentInfo> queryWrapper = new LambdaQueryWrapper<>();

        // 添加条件
        if (StringUtils.isNotBlank(query.getName())) {
            queryWrapper.like(AgentInfo::getName, query.getName());
        }

        if (query.getModelId() != null) {
            queryWrapper.eq(AgentInfo::getModelId, query.getModelId());
        }

        if (query.getWebSearch() != null) {
            queryWrapper.eq(AgentInfo::getWebSearch, query.getWebSearch());
        }

        // 排序
        queryWrapper.orderByDesc(AgentInfo::getCreateTime);

        return page(page, queryWrapper);
    }

    @Override
    public AgentInfo getAgentInfoById(Long id) {
        return getById(id);
    }

    @Override
    public boolean saveOrUpdateAgent(AgentInfoForm form) {
        AgentInfo entity = agentInfoConverter.toEntity(form);
        if (entity.getId() == null) {
            // 新增时设置uuid
            String uuid = IdUtil.simpleUUID();
            entity.setUuid(uuid);
            entity.setUserId(StpUtil.getLoginIdAsLong());
            entity.setCreateBy(StpUtil.getLoginIdAsLong());
        } else {
            entity.setUpdateBy(StpUtil.getLoginIdAsLong());
        }
        return saveOrUpdate(entity);
    }

    @Override
    public List<AgentInfoVO> listByLogin() {
        long loginId = StpUtil.getLoginIdAsLong();

        List<AgentInfo> entityList = this.lambdaQuery().eq(AgentInfo::getUserId, loginId).list();

        List<AgentInfoVO> vos = agentInfoConverter.toVOS(entityList);
        if (CollUtil.isNotEmpty(vos)) {
            // 补充模型名称
            Set<Long> modelIds = vos.stream().map(AgentInfoVO::getModelId).collect(Collectors.toSet());
            List<BindModel> bindModels = bindModelListService.listByIds(modelIds);
            if (CollUtil.isNotEmpty(bindModels)) {
                Map<Long, String> bindModelMap = bindModels.stream().collect(Collectors.toMap(BindModel::getId, BindModel::getName, (k1, k2) -> k2));
                for (AgentInfoVO vo : vos) {
                    vo.setModelName(bindModelMap.get(vo.getModelId()));
                }
            }
        }
        return vos;
    }
}