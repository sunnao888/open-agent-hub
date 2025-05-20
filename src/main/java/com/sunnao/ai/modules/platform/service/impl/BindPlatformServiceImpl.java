package com.sunnao.ai.modules.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.platform.mapper.BindPlatformMapper;
import com.sunnao.ai.modules.platform.model.entity.BindPlatform;
import com.sunnao.ai.modules.platform.service.BindPlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BindPlatformServiceImpl extends ServiceImpl<BindPlatformMapper, BindPlatform> implements BindPlatformService {

    @Override
    public List<BindPlatform> getEntityListByUserId(Long loginId) {
        return lambdaQuery()
                .eq(BindPlatform::getUserId, loginId)
                .list();
    }

    @Override
    public List<Long> getActiveSupportIdListByUserId(Long loginId) {

        LambdaQueryWrapper<BindPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(BindPlatform::getSupportId)
                .eq(BindPlatform::getUserId, loginId)
                .eq(BindPlatform::getStatus, StatusEnum.ENABLE.getCode());
        return listObjs(queryWrapper, o -> Long.valueOf(o.toString()));
    }

    @Override
    public BindPlatform getEntityByUserIdAndSupportId(long loginId, Long id) {
        LambdaQueryWrapper<BindPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BindPlatform::getUserId, loginId)
                .eq(BindPlatform::getSupportId, id);
        return getOne(queryWrapper);
    }

    @Override
    public boolean saveOrUpdateApiKey(Long userId, Long supportId, String apiKey) {
        LambdaQueryWrapper<BindPlatform> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BindPlatform::getUserId, userId)
                .eq(BindPlatform::getSupportId, supportId);
        BindPlatform bindEntity = getOne(queryWrapper);
        if (bindEntity == null) {
            bindEntity = new BindPlatform();
            bindEntity.setUserId(userId);
            bindEntity.setSupportId(supportId);
            bindEntity.setStatus(StatusEnum.DISABLE.getCode());
            bindEntity.setCreateBy(userId);
        }

        bindEntity.setApiKey(apiKey);

        return saveOrUpdate(bindEntity);
    }
}




