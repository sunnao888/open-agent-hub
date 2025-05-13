package com.sunnao.ai.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.modules.system.mapper.UserMapper;
import com.sunnao.ai.modules.system.model.entity.User;
import com.sunnao.ai.modules.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 *
 * @author sunnao
 * @since 2025-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




