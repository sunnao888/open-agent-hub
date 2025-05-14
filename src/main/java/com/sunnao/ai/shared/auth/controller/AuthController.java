package com.sunnao.ai.shared.auth.controller;

import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.shared.auth.model.dto.LoginDTO;
import com.sunnao.ai.shared.auth.model.dto.RegisterDTO;
import com.sunnao.ai.shared.auth.model.vo.AuthenticationToken;
import com.sunnao.ai.shared.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制层
 *
 * @author sunnao
 * @since 2025-05-14
 */
@Tag(name = "认证中心")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * 账号密码登录
     *
     * @param loginDTO 登录请求参数
     * @return 登录用户令牌信息
     */
    @PostMapping("/login")
    public Result<AuthenticationToken> login(@RequestBody @Validated LoginDTO loginDTO) {
        return Result.success(authService.login(loginDTO));
    }

    /**
     * 账号密码注册
     *
     * @param registerDTO 注册请求参数
     *
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Validated RegisterDTO registerDTO) {
        return Result.success(authService.register(registerDTO));
    }
}
