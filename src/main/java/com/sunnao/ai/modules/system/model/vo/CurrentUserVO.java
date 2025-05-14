package com.sunnao.ai.modules.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当前登录用户对象
 *
 * @author haoxr
 * @since 2022/1/14
 */
@Schema(description = "当前登录用户对象")
@Data
public class CurrentUserVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户联系方式")
    private String mobile;

    @Schema(description = "用户角色")
    private String role;

}
