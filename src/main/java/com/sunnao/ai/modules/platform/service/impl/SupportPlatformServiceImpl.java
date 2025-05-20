package com.sunnao.ai.modules.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.platform.converter.SupportPlatformConverter;
import com.sunnao.ai.modules.platform.mapper.SupportPlatformMapper;
import com.sunnao.ai.modules.platform.model.entity.SupportPlatform;
import com.sunnao.ai.modules.platform.model.form.SupportPlatformForm;
import com.sunnao.ai.modules.platform.model.query.SupportPlatformPageQuery;
import com.sunnao.ai.modules.platform.model.vo.SupportPlatformVO;
import com.sunnao.ai.modules.platform.service.SupportPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportPlatformServiceImpl extends ServiceImpl<SupportPlatformMapper, SupportPlatform> implements SupportPlatformService {

    private final SupportPlatformConverter supportModelPlatformConverter;

    @Override
    public IPage<SupportPlatformVO> page(SupportPlatformPageQuery query) {
        Page<SupportPlatform> page = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<SupportPlatform> queryWrapper = new LambdaQueryWrapper<>();
        Page<SupportPlatform> pageList = this.page(page, queryWrapper);
        return supportModelPlatformConverter.toPageVO(pageList);
    }

    @Override
    public SupportPlatformVO getInfo(Long id) {
        SupportPlatform entity = getById(id);
        return supportModelPlatformConverter.toVO(entity);
    }

    @Override
    public List<SupportPlatform> getActiveEntityList() {
        return lambdaQuery()
                .eq(SupportPlatform::getStatus, StatusEnum.ENABLE.getCode())
                .list();
    }

    @Override
    public boolean save(SupportPlatformForm form) {
        Assert.isTrue(
                super.count(new LambdaQueryWrapper<SupportPlatform>().eq(SupportPlatform::getName, form.getName())) == 0,
                "模型平台已存在");
        SupportPlatform entity = supportModelPlatformConverter.toEntity(form);
        entity.setCreateBy(StpUtil.getLoginIdAsLong());
        return save(entity);
    }

    @Override
    public boolean update(SupportPlatformForm form) {
        SupportPlatform entity = supportModelPlatformConverter.toEntity(form);
        entity.setUpdateBy(StpUtil.getLoginIdAsLong());
        return updateById(entity);
    }

    @Override
    public boolean delete(Long id) {
        // TODO 这里需要判断是否有绑定关系,如果有,则不能删除
        return removeById(id);
    }
}




