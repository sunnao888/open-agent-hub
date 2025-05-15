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

-- ----------------------------
-- Table structure for agent_bind_model_list
-- ----------------------------
DROP TABLE IF EXISTS `agent_bind_model_list`;
CREATE TABLE `agent_bind_model_list`
(
    `id`          bigint NOT NULL COMMENT '主键',
    `bind_id`     bigint NOT NULL COMMENT '用户绑定的大模型平台id',
    `group`       varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模型组',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模型名称',
    `abilities`   varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '模型能力（1推理 2视觉 3工具）',
    `create_time` datetime                                DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint                                  DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime                                DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint                                  DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1)                              DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户绑定的大模型平台下模型列表';

-- ----------------------------
-- Records of agent_bind_model_list
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for agent_bind_model_platform
-- ----------------------------
DROP TABLE IF EXISTS `agent_bind_model_platform`;
CREATE TABLE `agent_bind_model_platform`
(
    `id`          bigint     NOT NULL COMMENT '主键',
    `user_id`     bigint     NOT NULL COMMENT '用户id',
    `support_id`  bigint     NOT NULL COMMENT '支持的平台id',
    `api_key`     varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'apiKey',
    `status`      tinyint(1) NOT NULL COMMENT '状态（0禁用 1弃用）',
    `create_time` datetime                                 DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint                                   DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime                                 DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint                                   DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1)                               DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户绑定的大模型平台表';

-- ----------------------------
-- Records of agent_bind_model_platform
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for agent_support_model_platform
-- ----------------------------
DROP TABLE IF EXISTS `agent_support_model_platform`;
CREATE TABLE `agent_support_model_platform`
(
    `id`          bigint                                   NOT NULL COMMENT '主键',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '平台名称',
    `icon`        varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图标',
    `redirect`    varchar(1024) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '官网跳转地址',
    `base_url`    varchar(1024) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'api地址',
    `api_key`     varchar(2048) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'apiKey',
    `status`      tinyint(1)                               NOT NULL DEFAULT '0' COMMENT '状态（0禁用 1弃用）',
    `create_time` datetime                                          DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint                                            DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime                                          DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint                                            DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1)                                        DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='大模型平台';

-- ----------------------------
-- Records of agent_support_model_platform
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `username`    varchar(64)  DEFAULT NULL COMMENT '用户名',
    `nickname`    varchar(64)  DEFAULT NULL COMMENT '昵称',
    `gender`      tinyint(1)   DEFAULT '1' COMMENT '性别((1-男 2-女 0-保密)',
    `password`    varchar(100) DEFAULT NULL COMMENT '密码',
    `role`        varchar(32)  DEFAULT 'user' COMMENT '角色(admin user)',
    `avatar`      varchar(255) DEFAULT NULL COMMENT '用户头像',
    `mobile`      varchar(20)  DEFAULT NULL COMMENT '联系方式',
    `status`      tinyint(1)   DEFAULT '1' COMMENT '状态(1-正常 0-禁用)',
    `email`       varchar(128) DEFAULT NULL COMMENT '用户邮箱',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint       DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint       DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1)   DEFAULT '0' COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    `openid`      char(28)     DEFAULT NULL COMMENT '微信 openid',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `login_name` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `gender`, `password`, `role`, `avatar`, `mobile`, `status`,
                        `email`, `create_time`, `create_by`, `update_time`, `update_by`, `is_deleted`, `openid`)
VALUES (1, 'vben', NULL, 1, '123456', 'user', NULL, NULL, 1, NULL, '2025-05-15 10:58:19', NULL, '2025-05-15 10:58:19',
        NULL, 0, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
