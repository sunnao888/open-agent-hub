package com.sunnao.ai.modules.agent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.agent.model.entity.AgentInfo;
import com.sunnao.ai.modules.agent.model.form.AgentInfoForm;
import com.sunnao.ai.modules.agent.model.query.AgentInfoQuery;
import com.sunnao.ai.modules.agent.model.vo.AgentInfoVO;

import java.util.List;

public interface AgentInfoService extends IService<AgentInfo> {

    /**
     * 分页查询Agent信息
     *
     * @param page  分页参数
     * @param query 查询条件
     * @return 分页结果
     */
    Page<AgentInfo> pageList(Page<AgentInfo> page, AgentInfoQuery query);

    /**
     * 根据ID获取Agent信息
     *
     * @param id Agent ID
     * @return Agent信息
     */
    AgentInfo getAgentInfoById(Long id);

    /**
     * 保存或者更新智能体信息
     *
     * @param form 智能体表单
     * @return 结果
     */
    boolean saveOrUpdateAgent(AgentInfoForm form);

    /**
     * 获取登录用户的智能体列表
     *
     * @return 智能体列表
     */
    List<AgentInfoVO> listByLogin();

}