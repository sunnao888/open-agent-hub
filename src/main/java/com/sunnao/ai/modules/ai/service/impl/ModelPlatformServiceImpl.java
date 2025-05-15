package com.sunnao.ai.modules.ai.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.ai.converter.ModelPlatformConverter;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;
import com.sunnao.ai.modules.ai.service.BindModelPlatformService;
import com.sunnao.ai.modules.ai.service.ModelPlatformService;
import com.sunnao.ai.modules.ai.service.SupportModelPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelPlatformServiceImpl implements ModelPlatformService {

    private final SupportModelPlatformService supportModelPlatformService;

    private final BindModelPlatformService bindModelPlatformService;

    private final ModelPlatformConverter modelPlatformConverter;

    @Override
    public List<ModelPlatformVO> getClientPlatformList() {
        // 1. 获取系统支持的模型平台
        List<SupportModelPlatform> supports = supportModelPlatformService.getActiveEntityList();

        // 2. 判断登录态
        Long loginId = StpUtil.getLoginId(-1L);

        // 3. 如果未登录,则返回系统支持的模型平台
        if (loginId != -1L) {
            // 4. 如果已登录,获取用户绑定的模型平台
            List<Long> userBindIds = bindModelPlatformService.getActiveSupportIdListByUserId(loginId);

            // 5. 用户绑定的模型平台状态设置为开启
            supports.forEach(support -> {
                if (userBindIds.contains(support.getId())) {
                    support.setStatus(StatusEnum.ENABLE.getCode());
                } else {
                    support.setStatus(StatusEnum.DISABLE.getCode());
                }
            });
        }

        return modelPlatformConverter.supports2ModelPlatformVOs(supports);
    }
}
