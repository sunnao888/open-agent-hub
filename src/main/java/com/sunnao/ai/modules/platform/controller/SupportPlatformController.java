package com.sunnao.ai.modules.platform.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sunnao.ai.common.result.PageResult;
import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.platform.model.form.SupportPlatformForm;
import com.sunnao.ai.modules.platform.model.query.SupportPlatformPageQuery;
import com.sunnao.ai.modules.platform.model.vo.SupportPlatformVO;
import com.sunnao.ai.modules.platform.service.SupportPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform/support")
@RequiredArgsConstructor
public class SupportPlatformController {

    private final SupportPlatformService supportPlatformService;

    /**
     * 分页查询系统支持的模型平台列表(管理员)
     */
    @SaCheckRole("admin")
    @GetMapping("/page")
    public PageResult<SupportPlatformVO> page(SupportPlatformPageQuery query) {
        IPage<SupportPlatformVO> page = supportPlatformService.page(query);
        return PageResult.success(page);
    }

    /**
     * 根据id获取基本信息
     */
    @GetMapping("/info/{id}")
    public Result<SupportPlatformVO> getInfo(@PathVariable Long id) {
        SupportPlatformVO vo = supportPlatformService.getInfo(id);
        return Result.success(vo);
    }

    /**
     * 新增系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @PostMapping()
    public Result<Boolean> save(@RequestBody @Validated SupportPlatformForm supportPlatformForm) {
        return Result.judge(supportPlatformService.save(supportPlatformForm));
    }

    /**
     * 修改系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @PutMapping()
    public Result<Boolean> update(@RequestBody @Validated SupportPlatformForm supportPlatformForm) {
        return Result.judge(supportPlatformService.update(supportPlatformForm));
    }

    /**
     * 删除系统支持的模型平台(管理员)
     */
    @SaCheckRole("admin")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        return Result.judge(supportPlatformService.delete(id));
    }
}
