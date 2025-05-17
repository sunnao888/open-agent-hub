package com.sunnao.ai.modules.ai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.form.SupportModelPlatformForm;
import com.sunnao.ai.modules.ai.model.query.SupportModelPlatformPageQuery;
import com.sunnao.ai.modules.ai.model.vo.SupportModelPlatformVO;

import java.util.List;

public interface SupportModelPlatformService extends IService<SupportModelPlatform> {

    /**
     * 分页查询
     */
    IPage<SupportModelPlatformVO> page(SupportModelPlatformPageQuery query);


    /**
     * 获取系统支持的模型平台列表(状态为启用)
     *
     * @return 模型平台列表
     */
    List<SupportModelPlatform> getActiveEntityList();

    /**
     * 新增系统支持的模型平台
     */
    boolean save(SupportModelPlatformForm form);
}
