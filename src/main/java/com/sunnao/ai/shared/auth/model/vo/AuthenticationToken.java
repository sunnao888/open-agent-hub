package com.sunnao.ai.shared.auth.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 认证令牌响应对象
 *
 * @author sunnao
 * @since 2025-05-14
 */
@Schema(description = "认证令牌响应对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationToken {

    @Schema(description = "令牌类型", example = "Bearer")
    private String tokenType;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "过期时间(单位：毫秒)")
    private Long expiresIn;

    public AuthenticationToken(String accessToken, Long expiresIn) {
        this.accessToken = tokenType;
        this.expiresIn = expiresIn;
    }
}
