package com.sunnao.ai.modules.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.platform.model.entity.SupportPlatform;
import com.sunnao.ai.modules.platform.model.form.SupportPlatformForm;
import com.sunnao.ai.modules.platform.model.query.SupportPlatformPageQuery;
import com.sunnao.ai.modules.platform.model.vo.SupportPlatformVO;

import java.util.List;

public interface SupportPlatformService extends IService<SupportPlatform> {

    /**
     * 分页查询
     */
    IPage<SupportPlatformVO> page(SupportPlatformPageQuery query);

    /**
     * 根据id获取基本信息
     */
    SupportPlatformVO getInfo(Long id);


    /**
     * 获取系统支持的模型平台列表(状态为启用)
     *
     * @return 模型平台列表
     */
    List<SupportPlatform> getActiveEntityList();

    /**
     * 新增系统支持的模型平台
     */
    boolean save(SupportPlatformForm form);

    /**
     * 修改系统支持的模型平台
     */
    boolean update(SupportPlatformForm form);

    /**
     * 删除系统支持的模型平台
     */
    boolean delete(Long id);
}
