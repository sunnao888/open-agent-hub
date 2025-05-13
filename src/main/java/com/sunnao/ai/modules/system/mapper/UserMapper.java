package com.sunnao.ai.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunnao.ai.modules.system.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户持久层接口
 *
 * @author sunnao
 * @since 2025-05-13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




