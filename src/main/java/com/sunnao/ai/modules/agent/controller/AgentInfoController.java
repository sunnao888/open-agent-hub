package com.sunnao.ai.modules.agent.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.agent.converter.AgentInfoConverter;
import com.sunnao.ai.modules.agent.model.entity.AgentInfo;
import com.sunnao.ai.modules.agent.model.form.AgentInfoForm;
import com.sunnao.ai.modules.agent.model.vo.AgentInfoVO;
import com.sunnao.ai.modules.agent.service.AgentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Agent信息 控制器
 *
 * @author sunnao
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
@SaCheckLogin
public class AgentInfoController {

    private final AgentInfoService agentInfoService;
    private final AgentInfoConverter agentInfoConverter;

    /**
     * 查询登录用户的智能体列表
     */
    @GetMapping("/list")
    public Result<List<AgentInfoVO>> listByLogin() {
        return Result.success(agentInfoService.listByLogin());
    }


    /**
     * 获取Agent信息详情
     */
    @GetMapping("/{id}")
    public Result<AgentInfoVO> getInfo(@PathVariable Long id) {
        AgentInfo entity = agentInfoService.getAgentInfoById(id);
        return Result.success(agentInfoConverter.toVO(entity));
    }

    /**
     * 新增或修改Agent信息
     */
    @PostMapping
    public Result<Void> saveOrUpdate(@RequestBody @Validated AgentInfoForm form) {
        return Result.judge(agentInfoService.saveOrUpdateAgent(form));
    }

    /**
     * 删除Agent信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        agentInfoService.removeById(id);
        return Result.success();
    }
} 