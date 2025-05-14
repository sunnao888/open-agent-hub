package com.sunnao.ai.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunnao.ai.common.exception.BusinessException;
import com.sunnao.ai.common.result.ResultCode;
import com.sunnao.ai.modules.system.mapper.UserMapper;
import com.sunnao.ai.modules.system.model.entity.User;
import com.sunnao.ai.modules.system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户业务实现类
 *
 * @author sunnao
 * @since 2025-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getEntityByUsername(String username) {
        return lambdaQuery().eq(User::getUsername, username).one();
    }

    @Override
    public Boolean register(String username, String password) {
        return save(new User(username, password));
    }

    @Override
    public boolean checkPassword(String username, String password) {
        User entity = getEntityByUsername(username);
        Optional.ofNullable(entity).orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_EXIST));
        return password.equals(entity.getPassword());
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return lambdaQuery().eq(User::getUsername, username).exists();
    }
}




