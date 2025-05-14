package com.sunnao.ai.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunnao.ai.modules.system.model.entity.User;

/**
 * 用户业务接口
 *
 * @author sunnao
 * @since 2025-05-13
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户对象
     *
     * @param username 用户名
     * @return 用户对象
     */
    User getEntityByUsername(String username);

    /**
     * 保存用户
     *
     * @param username 用户名
     * @param password 密码
     * @return true: 保存成功，false: 保存失败
     */
    Boolean register(String username, String password);

    /**
     * 校验用户名和密码，用户名不存在会抛出异常
     *
     * @param username 用户名
     * @param password 密码
     * @return true: 密码正确，false: 密码错误
     */
    boolean checkPassword(String username, String password);

    /**
     * 校验用户名是否存在
     *
     * @param username 用户名
     * @return true: 存在，false: 不存在
     */
    boolean checkUsernameExists(String username);
}
