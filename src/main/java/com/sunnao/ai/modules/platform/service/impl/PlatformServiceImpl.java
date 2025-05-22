package com.sunnao.ai.modules.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.common.exception.BusinessException;
import com.sunnao.ai.common.result.ResultCode;
import com.sunnao.ai.modules.platform.converter.PlatformConverter;
import com.sunnao.ai.modules.platform.model.bo.PlatformBO;
import com.sunnao.ai.modules.platform.model.dto.BindPlatformDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelAddDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelListDTO;
import com.sunnao.ai.modules.platform.model.entity.BindModel;
import com.sunnao.ai.modules.platform.model.entity.BindPlatform;
import com.sunnao.ai.modules.platform.model.entity.SupportPlatform;
import com.sunnao.ai.modules.platform.model.vo.PlatformVO;
import com.sunnao.ai.modules.platform.service.BindModelListService;
import com.sunnao.ai.modules.platform.service.BindPlatformService;
import com.sunnao.ai.modules.platform.service.PlatformService;
import com.sunnao.ai.modules.platform.service.SupportPlatformService;
import com.sunnao.ai.modules.platform.third.openai.model.ModelListResponse;
import com.sunnao.ai.modules.platform.third.openai.model.OpenaiModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {

    private final SupportPlatformService supportModelPlatformService;

    private final BindPlatformService bindModelPlatformService;

    private final BindModelListService bindModelListService;

    private final PlatformConverter modelPlatformConverter;

    @Override
    public List<PlatformVO> getClientPlatformList() {
        // 1. 获取系统支持的模型平台
        List<SupportPlatform> supports = supportModelPlatformService.getActiveEntityList();

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
        Long supportId = modelListDTO.getSupportId();
        SupportPlatform supportEntity = supportModelPlatformService.getById(supportId);
        Optional.ofNullable(supportEntity).orElseThrow(() -> new BusinessException(ResultCode.SUPPORT_PLATFORM_NOT_EXIST));
        String baseUrl = supportEntity.getBaseUrl();
        bindModelPlatformService.saveOrUpdateApiKey(StpUtil.getLoginIdAsLong(), supportId, modelListDTO.getApiKey());
        // 判断url是否符合openai规范
        String regex = "^(?:https?://)?[\\w.-]+(?::\\d+)?(?:/[\\w.-]*)*/v1/?$";
        boolean matches = (baseUrl + "/v1").matches(regex);
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

        return modelList.stream().map(OpenaiModel::getId).toList();

    }

    @Override
    public PlatformVO getPlatformConfig(Long id) {
        // 查询出支持平台信息
        SupportPlatform support = supportModelPlatformService.getById(id);
        Optional.ofNullable(support).orElseThrow(() -> new BusinessException(ResultCode.SUPPORT_PLATFORM_NOT_EXIST));

        // 用户是否绑定该平台
        long loginId = StpUtil.getLoginIdAsLong();

        BindPlatform bind = bindModelPlatformService.getEntityByUserIdAndSupportId(loginId, id);
        if (BeanUtil.isEmpty(bind)) {
            // 返回支持平台信息
            return modelPlatformConverter.support2ModelPlatformVO(support);
        } else {
            // 用户已经绑定了该平台,返回用户绑定信息
            return modelPlatformConverter.toModelPlatformVO(bind, support);
        }

    }

    @Override
    public boolean bindPlatform(BindPlatformDTO bindModelPlatformDTO) {
        Long supportId = Optional.ofNullable(bindModelPlatformDTO.getSupportId()).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        String apiKey = Optional.ofNullable(bindModelPlatformDTO.getApiKey()).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));

        // 校验支持平台是否存在
        boolean exists = supportModelPlatformService.lambdaQuery().eq(SupportPlatform::getId, supportId).exists();
        Assert.isTrue(exists, "模型平台不存在");

        // 获取用户id
        long loginId = StpUtil.getLoginIdAsLong();

        // 用户是否已经绑定该平台
        BindPlatform oldBindEntity = bindModelPlatformService.getEntityByUserIdAndSupportId(loginId, supportId);

        if (oldBindEntity != null) {
            // 用户已经绑定了该平台,则更新绑定信息
            oldBindEntity.setApiKey(apiKey);
            oldBindEntity.setUpdateBy(loginId);
            return bindModelPlatformService.updateById(oldBindEntity);
        }

        BindPlatform bindEntity = new BindPlatform();
        bindEntity.setUserId(loginId);
        bindEntity.setSupportId(supportId);
        bindEntity.setApiKey(apiKey);
        bindEntity.setStatus(StatusEnum.DISABLE.getCode());
        bindEntity.setCreateBy(loginId);

        return bindModelPlatformService.save(bindEntity);
    }

    @Override
    public boolean addModels(ModelAddDTO dto) {
        Long supportId = Optional.ofNullable(dto.getSupportId()).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        List<String> models = Optional.ofNullable(dto.getModels()).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        SupportPlatform support = supportModelPlatformService.getById(supportId);
        BindPlatform bindEntity = bindModelPlatformService.getEntityByUserIdAndSupportId(StpUtil.getLoginIdAsLong(), supportId);
        Optional.ofNullable(bindEntity).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));

        Long bindId = bindEntity.getId();

        // 批量新增
        List<BindModel> entities = new ArrayList<>(models.size());
        models.forEach(model -> {
            BindModel bindModel = new BindModel();
            bindModel.setBindId(bindId);
            bindModel.setName(model + "|" + support.getName());
            bindModel.setCreateBy(StpUtil.getLoginIdAsLong());
            entities.add(bindModel);
        });
        return bindModelListService.saveBatch(entities);
    }

    @Override
    public List<String> getAddedModelList(Long supportId) {
        long userId = StpUtil.getLoginIdAsLong();
        BindPlatform bindEntity = bindModelPlatformService.getEntityByUserIdAndSupportId(userId, supportId);
        if (BeanUtil.isEmpty(bindEntity)) {
            return CollUtil.newArrayList();
        }
        Long bindId = bindEntity.getId();
        List<BindModel> bindModelList = bindModelListService.lambdaQuery().eq(BindModel::getBindId, bindId).orderBy(true, false, BindModel::getCreateTime).list();
        if (CollUtil.isEmpty(bindModelList)) {
            return new ArrayList<>();
        }
        return bindModelList.stream().map(BindModel::getName).toList();
    }

    @Override
    public boolean deleteModel(Long supportId, String modelName) {
        long loginId = StpUtil.getLoginIdAsLong();

        BindPlatform bindEntity = bindModelPlatformService.getEntityByUserIdAndSupportId(loginId, supportId);
        Optional.ofNullable(bindEntity).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        Long bindId = bindEntity.getId();

        List<BindModel> bindModels = bindModelListService.lambdaQuery().eq(BindModel::getBindId, bindId).eq(BindModel::getName, modelName).list();
        if (CollUtil.isEmpty(bindModels)) {
            return Boolean.TRUE;
        }

        return bindModelListService.removeBatchByIds(bindModels);
    }

    @Override
    public boolean updateBindStatus(Long supportId) {
        long loginId = StpUtil.getLoginIdAsLong();
        BindPlatform bindEntity = bindModelPlatformService.getEntityByUserIdAndSupportId(loginId, supportId);
        Optional.ofNullable(bindEntity).orElseThrow(() -> new BusinessException(ResultCode.MODEL_PLATFORM_NOT_CONFIGURED));
        // 状态取反
        Integer status = bindEntity.getStatus().equals(StatusEnum.ENABLE.getCode()) ? StatusEnum.DISABLE.getCode() : StatusEnum.ENABLE.getCode();
        bindEntity.setStatus(status);
        bindEntity.setUpdateBy(loginId);
        return bindModelPlatformService.updateById(bindEntity);
    }

    @Override
    public List<BindModel> getBindModels() {
        long loginId = StpUtil.getLoginIdAsLong();
        List<BindPlatform> bindPlatforms = bindModelPlatformService.lambdaQuery().eq(BindPlatform::getUserId, loginId).list();
        if (CollUtil.isEmpty(bindPlatforms)) {
            return CollUtil.newArrayList();
        }
        List<Long> bindIds = bindPlatforms.stream().map(BindPlatform::getId).toList();
        return bindModelListService.lambdaQuery().in(BindModel::getBindId, bindIds).orderBy(true, false, BindModel::getCreateTime).list();
    }

    @Override
    public PlatformBO getPlatformInfoByBindModel(Long modelId) {
        BindModel bindModel = bindModelListService.getById(modelId);
        Optional.ofNullable(bindModel).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        Long bindId = bindModel.getBindId();
        BindPlatform bindPlatform = bindModelPlatformService.getById(bindId);
        Optional.ofNullable(bindPlatform).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        SupportPlatform supportPlatform = supportModelPlatformService.getById(bindPlatform.getSupportId());
        return modelPlatformConverter.toPlatformBO(supportPlatform, bindPlatform, bindModel);
    }

    @Override
    public PlatformBO getPlatformInfoByModelName(Long userId, String modelName) {

        // 1. 获取用户绑定的平台
        List<BindPlatform> binds = bindModelPlatformService.lambdaQuery().eq(BindPlatform::getUserId, userId).list();
        if (CollUtil.isEmpty(binds)) {
            return new PlatformBO();
        }

        List<Long> bindIds = binds.stream().map(BindPlatform::getId).toList();
        // 2. 获取用户绑定的模型
        BindModel bindModel = bindModelListService.lambdaQuery()
                .eq(BindModel::getName, modelName)
                .in(BindModel::getBindId, bindIds)
                .one();
        Optional.ofNullable(bindModel).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        Long bindId = bindModel.getBindId();
        BindPlatform bindPlatform = bindModelPlatformService.getById(bindId);
        Optional.ofNullable(bindPlatform).orElseThrow(() -> new BusinessException(ResultCode.REQUEST_REQUIRED_PARAMETER_IS_EMPTY));
        SupportPlatform supportPlatform = supportModelPlatformService.getById(bindPlatform.getSupportId());
        return modelPlatformConverter.toPlatformBO(supportPlatform, bindPlatform, bindModel);
    }
}
