package com.sunnao.ai.modules.system.model.entity;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnao.ai.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户对象
 *
 * @author sunnao
 * @since 2025-05-13
 */
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@Data
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别(1-男 2-女 0-保密)
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色(admin-管理员 user-普通用户)
     */
    private String role;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 状态(1-正常 0-禁用)
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;

    /**
     * 是否删除(0-否 1-是)
     */
    private Integer isDeleted;

    /**
     * 微信 OpenID
     */
    private String openid;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public List<String> getRoles() {
        return CollUtil.newArrayList(role);
    }

}