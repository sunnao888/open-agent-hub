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
public class SupportModelPlatformController {

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
     * 根据id获取基本信息
     */
    @GetMapping("/info/{id}")
    public Result<SupportModelPlatformVO> getInfo(@PathVariable Long id) {
        SupportModelPlatformVO vo = supportModelPlatformService.getInfo(id);
        return Result.success(vo);
    }

    /**
     * 新增系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @PostMapping()
    public Result<Boolean> save(@RequestBody @Validated SupportModelPlatformForm supportModelPlatformForm) {
        return Result.judge(supportModelPlatformService.save(supportModelPlatformForm));
    }

    /**
     * 修改系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @PutMapping()
    public Result<Boolean> update(@RequestBody @Validated SupportModelPlatformForm supportModelPlatformForm) {
        return Result.judge(supportModelPlatformService.update(supportModelPlatformForm));
    }

    /**
     * 删除系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.judge(supportModelPlatformService.delete(id));
    }
}
