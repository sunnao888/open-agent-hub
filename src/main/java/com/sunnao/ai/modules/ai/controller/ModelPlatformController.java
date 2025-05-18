package com.sunnao.ai.modules.ai.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.ai.model.dto.ModelListDTO;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;
import com.sunnao.ai.modules.ai.service.ModelPlatformService;
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
public class ModelPlatformController {

    private final ModelPlatformService modelPlatformService;

    /**
     * 获取用户模型平台列表(普通用户接口)
     */
    @GetMapping("/list")
    public Result<List<ModelPlatformVO>> getClientPlatformList() {
        return Result.success(modelPlatformService.getClientPlatformList());
    }

    /**
     * 获取对应平台的模型列表
     */
    @PostMapping("/model/list")
    public Result<List<String>> getAvailableModelList(@RequestBody ModelListDTO modelListDTO) {
        return Result.success(modelPlatformService.getAvailableModelList(modelListDTO));
    }

    /**
     * 根据支持平台id获取用户平台配置
     */
    @SaCheckLogin
    @GetMapping("/config/{id}")
    public Result<ModelPlatformVO> getPlatformConfig(@PathVariable Long id) {
        return Result.success(modelPlatformService.getPlatformConfig(id));
    }

}
