-- ----------------------------
-- 1. 创建数据库
-- ----------------------------
CREATE DATABASE IF NOT EXISTS open_agent_hub CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;


-- ----------------------------
-- 2. 创建表 && 数据初始化
-- ----------------------------
use open_agent_hub;

SET NAMES utf8mb4; # 设置字符集
SET FOREIGN_KEY_CHECKS = 0;
# 关闭外键检查，加快导入速度

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `username`    varchar(64) COMMENT '用户名',
    `nickname`    varchar(64) COMMENT '昵称',
    `gender`      tinyint(1) DEFAULT 1 COMMENT '性别((1-男 2-女 0-保密)',
    `password`    varchar(100) COMMENT '密码',
    `role`        int COMMENT '角色(admin user)',
    `avatar`      varchar(255) COMMENT '用户头像',
    `mobile`      varchar(20) COMMENT '联系方式',
    `status`      tinyint(1) DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
    `email`       varchar(128) COMMENT '用户邮箱',
    `create_time` datetime COMMENT '创建时间',
    `create_by`   bigint COMMENT '创建人ID',
    `update_time` datetime COMMENT '更新时间',
    `update_by`   bigint COMMENT '修改人ID',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    `openid`      char(28) COMMENT '微信 openid',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `login_name` (`username` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 COMMENT = '用户信息表';

SET FOREIGN_KEY_CHECKS = 1; 
