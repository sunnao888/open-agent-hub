package com.sunnao.ai.modules.platform.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.platform.model.dto.BindPlatformDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelAddDTO;
import com.sunnao.ai.modules.platform.model.dto.ModelListDTO;
import com.sunnao.ai.modules.platform.model.entity.BindModel;
import com.sunnao.ai.modules.platform.model.vo.PlatformVO;
import com.sunnao.ai.modules.platform.service.PlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模型平台接口控制层(用户调用)
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Tag(name = "模型服务")
@RestController
@RequestMapping("/platform/client")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService PlatformService;

    /**
     * 获取用户模型平台列表(普通用户接口)
     */
    @GetMapping("/list")
    public Result<List<PlatformVO>> getClientPlatformList() {
        return Result.success(PlatformService.getClientPlatformList());
    }

    /**
     * 获取对应平台的模型列表
     */
    @SaCheckLogin
    @PostMapping("/model/list")
    public Result<List<String>> getAvailableModelList(@RequestBody ModelListDTO modelListDTO) {
        return Result.success(PlatformService.getAvailableModelList(modelListDTO));
    }

    /**
     * 根据支持平台id获取用户平台配置
     */
    @SaCheckLogin
    @GetMapping("/config/{id}")
    public Result<PlatformVO> getPlatformConfig(@PathVariable Long id) {
        return Result.success(PlatformService.getPlatformConfig(id));
    }

    /**
     * 根据用户id和支持平台id获取用户已添加的模型列表
     */
    @SaCheckLogin
    @GetMapping("/model/list/{supportId}")
    public Result<List<String>> getAddedModelList(@PathVariable Long supportId) {
        return Result.success(PlatformService.getAddedModelList(supportId));
    }

    /**
     * 用户绑定平台
     */
    @SaCheckLogin
    @PostMapping("/bind")
    public Result<Boolean> bindPlatform(@RequestBody BindPlatformDTO bindPlatformDTO) {
        return Result.judge(PlatformService.bindPlatform(bindPlatformDTO));
    }

    /**
     * 用户新增模型
     */
    @SaCheckLogin
    @PostMapping("/model/add")
    public Result<Boolean> addModels(@RequestBody ModelAddDTO dto) {
        return Result.judge(PlatformService.addModels(dto));
    }

    /**
     * 用户删除模型
     */
    @SaCheckLogin
    @DeleteMapping("/model/{supportId}/{modelName}")
    public Result<Boolean> deleteModel(@PathVariable Long supportId, @PathVariable String modelName) {
        return Result.judge(PlatformService.deleteModel(supportId, modelName));
    }

    /**
     * 修改绑定平台状态
     */
    @SaCheckLogin
    @PutMapping("/bind/{supportId}")
    public Result<Boolean> updateBindStatus(@PathVariable Long supportId) {
        return Result.judge(PlatformService.updateBindStatus(supportId));
    }

    /**
     * 获取登录用户绑定的模型列表
     */
    @SaCheckLogin
    @GetMapping("/model/bind/list")
    public Result<List<BindModel>> getBindModels() {
        return Result.success(PlatformService.getBindModels());
    }

}
