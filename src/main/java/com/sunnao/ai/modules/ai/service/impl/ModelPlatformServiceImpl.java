package com.sunnao.ai.modules.ai.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.common.exception.BusinessException;
import com.sunnao.ai.common.result.ResultCode;
import com.sunnao.ai.modules.ai.converter.ModelPlatformConverter;
import com.sunnao.ai.modules.ai.model.dto.ModelListDTO;
import com.sunnao.ai.modules.ai.model.entity.BindModelPlatform;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;
import com.sunnao.ai.modules.ai.service.BindModelPlatformService;
import com.sunnao.ai.modules.ai.service.ModelPlatformService;
import com.sunnao.ai.modules.ai.service.SupportModelPlatformService;
import com.sunnao.ai.modules.ai.third.openai.model.ModelListResponse;
import com.sunnao.ai.modules.ai.third.openai.model.OpenaiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<String> getAvailableModelList(ModelListDTO modelListDTO) {
        String baseUrl = modelListDTO.getBaseUrl();

        // 判断url是否符合openai规范
        String regex = "^(?:https?://)?[\\w.-]+(?::\\d+)?(?:/[\\w.-]*)*/v1/?$";
        boolean matches = baseUrl.matches(regex);
        if (!matches) {
            throw new BusinessException(ResultCode.BASE_URL_NOT_MATCHED);
        }

        // 请求模型列表
        String url = baseUrl + "/models";
        String responseStr = HttpRequest
                .get(URLUtil.normalize(url))
                .header("Authorization", "Bearer " + modelListDTO.getApiKey())
                .execute()
                .body();

        ModelListResponse responseObj = JSON.parseObject(responseStr, ModelListResponse.class);

        List<OpenaiModel> modelList = responseObj.getData();
        if (CollUtil.isEmpty(modelList)) {
            throw new BusinessException(ResultCode.MODEL_LIST_EMPTY);
        }

        List<String> modelNameList = modelList.stream().map(OpenaiModel::getId).toList();

        return modelNameList;

    }

    @Override
    public ModelPlatformVO getPlatformConfig(Long id) {
        // 查询出支持平台信息
        SupportModelPlatform support = supportModelPlatformService.getById(id);
        Optional.ofNullable(support).orElseThrow(() -> new BusinessException(ResultCode.SUPPORT_PLATFORM_NOT_EXIST));

        // 用户是否绑定该平台
        long loginId = StpUtil.getLoginIdAsLong();

        BindModelPlatform bind = bindModelPlatformService.getEntityListByUserIdAndSupportId(loginId, id);
        if (BeanUtil.isEmpty(bind)) {
            // 返回支持平台信息
            return modelPlatformConverter.support2ModelPlatformVO(support);
        } else {
            // 用户已经绑定了该平台,返回用户绑定信息
            return modelPlatformConverter.toModelPlatformVO(bind, support);
        }

    }
}
