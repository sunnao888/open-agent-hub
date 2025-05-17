package com.sunnao.ai.modules.ai.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sunnao.ai.common.result.PageResult;
import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.ai.model.form.SupportModelPlatformForm;
import com.sunnao.ai.modules.ai.model.query.SupportModelPlatformPageQuery;
import com.sunnao.ai.modules.ai.model.vo.SupportModelPlatformVO;
import com.sunnao.ai.modules.ai.service.SupportModelPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform/support")
@RequiredArgsConstructor
public class SupperModelPlatformController {

    private final SupportModelPlatformService supportModelPlatformService;

    /**
     * 分页查询系统支持的模型平台列表(管理员)
     */
    @SaCheckRole("admin")
    @GetMapping("/page")
    public PageResult<SupportModelPlatformVO> page(SupportModelPlatformPageQuery query) {
        IPage<SupportModelPlatformVO> page = supportModelPlatformService.page(query);
        return PageResult.success(page);
    }

    /**
     * 新增系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @PostMapping()
    public Result<Boolean> save(@RequestBody @Validated SupportModelPlatformForm supportModelPlatformForm) {
        return Result.judge(supportModelPlatformService.save(supportModelPlatformForm));
    }
}
