package com.sunnao.ai.modules.system.controller;

import com.sunnao.ai.common.result.Result;
import com.sunnao.ai.modules.system.model.vo.CurrentUserVO;
import com.sunnao.ai.modules.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口控制层
 *
 * @author sunnao
 * @since 2025-05-14
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/info")
    public Result<CurrentUserVO> getCurrentUser() {
        CurrentUserVO vo = userService.getCurrentUserInfo();
        return Result.success(vo);
    }

}
