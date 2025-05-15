package com.sunnao.ai.shared.auth.service;

import com.sunnao.ai.shared.auth.model.dto.LoginDTO;
import com.sunnao.ai.shared.auth.model.dto.RegisterDTO;
import com.sunnao.ai.shared.auth.model.vo.AuthenticationToken;

/**
 * 认证服务接口
 *
 * @author sunnao
 * @since 2025-05-14
 */
public interface AuthService {

    /**
     * 账号密码登录
     *
     * @param loginDTO 登录参数
     * @return 登录用户令牌信息
     */
    AuthenticationToken login(LoginDTO loginDTO);

    /**
     * 账号密码注册
     *
     * @param registerDTO 注册参数
     * @return 是否注册成功
     */
    Boolean register(RegisterDTO registerDTO);

    /**
     * 账号密码注销
     *
     * @return 是否注销成功
     */
    Boolean logout();
}
