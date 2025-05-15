package com.sunnao.ai.modules.ai.controller;

import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.ai.model.vo.ModelPlatformVO;
import com.sunnao.ai.modules.ai.service.ModelPlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模型平台接口控制层
 *
 * @author sunnao
 * @since 2025-05-15
 */
@Tag(name = "模型服务")
@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelPlatformController {

    private final ModelPlatformService modelPlatformService;

    /**
     * 获取用户模型平台列表(普通用户接口)
     */
    @GetMapping("/client/platform/list")
    public Result<List<ModelPlatformVO>> getClientPlatformList() {
        return Result.success(modelPlatformService.getClientPlatformList());
    }
}
