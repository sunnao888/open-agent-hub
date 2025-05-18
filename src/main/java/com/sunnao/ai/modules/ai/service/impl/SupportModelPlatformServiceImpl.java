package com.sunnao.ai.modules.ai.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.enums.StatusEnum;
import com.sunnao.ai.modules.ai.converter.SupportModelPlatformConverter;
import com.sunnao.ai.modules.ai.mapper.SupportModelPlatformMapper;
import com.sunnao.ai.modules.ai.model.entity.SupportModelPlatform;
import com.sunnao.ai.modules.ai.model.form.SupportModelPlatformForm;
import com.sunnao.ai.modules.ai.model.query.SupportModelPlatformPageQuery;
import com.sunnao.ai.modules.ai.model.vo.SupportModelPlatformVO;
import com.sunnao.ai.modules.ai.service.SupportModelPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportModelPlatformServiceImpl extends ServiceImpl<SupportModelPlatformMapper, SupportModelPlatform> implements SupportModelPlatformService {

    private final SupportModelPlatformConverter supportModelPlatformConverter;

    @Override
    public IPage<SupportModelPlatformVO> page(SupportModelPlatformPageQuery query) {
        Page<SupportModelPlatform> page = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<SupportModelPlatform> queryWrapper = new LambdaQueryWrapper<>();
        Page<SupportModelPlatform> pageList = this.page(page, queryWrapper);
        return supportModelPlatformConverter.toPageVO(pageList);
    }

    @Override
    public SupportModelPlatformVO getInfo(Long id) {
        SupportModelPlatform entity = getById(id);
        return supportModelPlatformConverter.toVO(entity);
    }

    @Override
    public List<SupportModelPlatform> getActiveEntityList() {
        return lambdaQuery()
                .eq(SupportModelPlatform::getStatus, StatusEnum.ENABLE.getCode())
                .list();
    }

    @Override
    public boolean save(SupportModelPlatformForm form) {
        Assert.isTrue(
                super.count(new LambdaQueryWrapper<SupportModelPlatform>().eq(SupportModelPlatform::getName, form.getName())) == 0,
                "模型平台已存在");
        SupportModelPlatform entity = supportModelPlatformConverter.toEntity(form);
        entity.setCreateBy(StpUtil.getLoginIdAsLong());
        return save(entity);
    }

    @Override
    public boolean update(SupportModelPlatformForm form) {
        SupportModelPlatform entity = supportModelPlatformConverter.toEntity(form);
        entity.setUpdateBy(StpUtil.getLoginIdAsLong());
        return updateById(entity);
    }

    @Override
    public boolean delete(Long id) {
        // TODO 这里需要判断是否有绑定关系,如果有,则不能删除
        return removeById(id);
    }
}




