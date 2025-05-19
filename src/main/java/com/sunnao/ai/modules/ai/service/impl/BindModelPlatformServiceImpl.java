package com.sunnao.ai.modules.ai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.ai.mapper.BindModelPlatformMapper;
import com.sunnao.ai.modules.ai.model.entity.BindModelPlatform;
import com.sunnao.ai.modules.ai.service.BindModelPlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BindModelPlatformServiceImpl extends ServiceImpl<BindModelPlatformMapper, BindModelPlatform> implements BindModelPlatformService {

    @Override
    public List<BindModelPlatform> getEntityListByUserId(Long loginId) {
        return lambdaQuery()
                .eq(BindModelPlatform::getUserId, loginId)
                .list();
    }

    @Override
    public List<Long> getActiveSupportIdListByUserId(Long loginId) {

        LambdaQueryWrapper<BindModelPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(BindModelPlatform::getSupportId)
                .eq(BindModelPlatform::getUserId, loginId)
                .eq(BindModelPlatform::getStatus, StatusEnum.ENABLE.getCode());
        return listObjs(queryWrapper, o -> Long.valueOf(o.toString()));
    }

    @Override
    public BindModelPlatform getEntityByUserIdAndSupportId(long loginId, Long id) {
        LambdaQueryWrapper<BindModelPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BindModelPlatform::getUserId, loginId)
                .eq(BindModelPlatform::getSupportId, id);
        return getOne(queryWrapper);
    }

    @Override
    public boolean saveOrUpdateApiKey(Long userId, Long supportId, String apiKey) {
        LambdaQueryWrapper<BindModelPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BindModelPlatform::getUserId, userId)
                .eq(BindModelPlatform::getSupportId, supportId);
        BindModelPlatform bindEntity = getOne(queryWrapper);
        if (bindEntity == null) {
            bindEntity = new BindModelPlatform();
            bindEntity.setUserId(userId);
            bindEntity.setSupportId(supportId);
            bindEntity.setStatus(StatusEnum.DISABLE.getCode());
            bindEntity.setCreateBy(userId);
        }

        bindEntity.setApiKey(apiKey);

        return saveOrUpdate(bindEntity);
    }
}




