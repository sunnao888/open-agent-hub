package com.sunnao.ai.modules.ai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.ai.mapper.SupportModelPlatformMapper;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.service.SupportModelPlatformService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportModelPlatformServiceImpl extends ServiceImpl<SupportModelPlatformMapper, SupportModelPlatform> implements SupportModelPlatformService {

    @Override
    public List<SupportModelPlatform> getActiveEntityList() {
        return lambdaQuery()
                .eq(SupportModelPlatform::getStatus, StatusEnum.ENABLE.getCode())
                .list();
    }
}




