package com.sunnao.ai.shared.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.sunnao.ai.common.exception.BusinessException;
import com.sunnao.ai.common.result.ResultCode;
import com.sunnao.ai.modules.system.model.entity.User;
import com.sunnao.ai.modules.system.service.UserService;
import com.sunnao.ai.shared.auth.model.dto.LoginDTO;
import com.sunnao.ai.shared.auth.model.dto.RegisterDTO;
import com.sunnao.ai.shared.auth.model.vo.AuthenticationToken;
import com.sunnao.ai.shared.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 认证服务实现类
 *
 * @author sunnao
 * @since 2025-05-14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public AuthenticationToken login(LoginDTO loginDTO) {

        String username = Optional.ofNullable(loginDTO.getUsername())
                .orElseThrow(() -> new BusinessException(ResultCode.USERNAME_VERIFICATION_FAILED));
        String password = Optional.ofNullable(loginDTO.getPassword())
                .orElseThrow(() -> new BusinessException(ResultCode.PASSWORD_VERIFICATION_FAILED));

        // 1. 校验用户名密码
        if (!userService.checkPassword(username, password)) {
            throw new BusinessException(ResultCode.USER_PASSWORD_ERROR);
        }

        // 2. 用户登录
        User userEntity = userService.getEntityByUsername(username);
        StpUtil.login(userEntity.getId());

        // 3. 生成令牌
        return new AuthenticationToken(StpUtil.getTokenValue(), StpUtil.getTokenTimeout());
    }

    @Override
    public Boolean register(RegisterDTO registerDTO) {
        String username = Optional.ofNullable(registerDTO.getUsername())
                .orElseThrow(() -> new BusinessException(ResultCode.USERNAME_VERIFICATION_FAILED));
        String password = Optional.ofNullable(registerDTO.getPassword())
                .orElseThrow(() -> new BusinessException(ResultCode.PASSWORD_VERIFICATION_FAILED));
        String confirmPassword = Optional.ofNullable(registerDTO.getConfirmPassword())
                .orElseThrow(() -> new BusinessException(ResultCode.PASSWORD_VERIFICATION_FAILED));

        // 1. 校验密码
        if (!DigestUtil.bcrypt(password).equals(confirmPassword)) {
            throw new BusinessException(ResultCode.PASSWORD_VERIFICATION_FAILED);
        }

        // 2. 校验用户名是否已存在
        if (userService.checkUsernameExists(username)) {
            throw new BusinessException(ResultCode.USERNAME_ALREADY_EXISTS);
        }

        // 2. 注册用户
        return userService.register(username, password);
    }

    @Override
    public Boolean logout() {
        StpUtil.logout();
        return Boolean.TRUE;
    }
}
