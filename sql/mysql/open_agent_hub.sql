/*
 Navicat Premium Dump SQL

 Source Server         : 家里云千练-本地
 Source Server Type    : MySQL
 Source Server Version : 80405 (8.4.5)
 Source Host           : localhost:3306
 Source Schema         : open_agent_hub

 Target Server Type    : MySQL
 Target Server Version : 80405 (8.4.5)
 File Encoding         : 65001

 Date: 26/05/2025 00:00:39
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agent_bind_model_list
-- ----------------------------
DROP TABLE IF EXISTS `agent_bind_model_list`;
CREATE TABLE `agent_bind_model_list`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `bind_id`     bigint NOT NULL COMMENT '用户绑定的大模型平台id',
    `model_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模型组',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模型名称',
    `abilities`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '模型能力（1推理 2视觉 3工具）',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户绑定的大模型平台下模型列表' ROW_FORMAT = Dynamic;
-- ----------------------------
-- Table structure for agent_bind_model_platform
-- ----------------------------
DROP TABLE IF EXISTS `agent_bind_model_platform`;
CREATE TABLE `agent_bind_model_platform`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`     bigint NOT NULL COMMENT '用户id',
    `support_id`  bigint NOT NULL COMMENT '支持的平台id',
    `api_key`     varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'apiKey',
    `status`      tinyint(1) NOT NULL COMMENT '状态（0禁用 1弃用）',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户绑定的大模型平台表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for agent_info
-- ----------------------------
DROP TABLE IF EXISTS `agent_info`;
CREATE TABLE `agent_info`
(
    `id`            bigint                                                       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
    `model_id`      bigint NULL DEFAULT NULL COMMENT '模型id',
    `system_prompt` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '系统提示词',
    `web_search`    tinyint(1) NULL DEFAULT 0 COMMENT '是否开启联网搜索(0 否 1是)',
    `create_time`   datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`     bigint NULL DEFAULT NULL COMMENT '创建人ID',
    `update_time`   datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`     bigint NULL DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`    tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    `temperature` double NULL DEFAULT 1.3 COMMENT '温度',
    `context_num`   int NULL DEFAULT NULL COMMENT '上下文轮数',
    `uuid`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '智能体唯一id',
    `user_id`       bigint                                                       NOT NULL COMMENT '用户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agent_info
-- ----------------------------
INSERT INTO `agent_info`
VALUES (1, '测试智能体', 110, '你是一只雪豹', 1, '2025-05-21 20:49:58', 1, '2025-05-21 21:13:36', 1, 0, 1.3, 10,
        'ca577ea7129f4f6baa6ccfa7cc589417', 1);

-- ----------------------------
-- Table structure for agent_support_model_platform
-- ----------------------------
DROP TABLE IF EXISTS `agent_support_model_platform`;
CREATE TABLE `agent_support_model_platform`
(
    `id`          bigint                                                         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '平台名称',
    `icon`        varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图标',
    `redirect`    varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '官网跳转地址',
    `base_url`    varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'api地址',
    `status`      tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0禁用 1弃用）',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '平台介绍',
    `code`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '平台标识',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '大模型平台' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agent_support_model_platform
-- ----------------------------
INSERT INTO `agent_support_model_platform`
VALUES (1, '阿里云百炼',
        'https://open-agent-hub.oss-cn-hangzhou.aliyuncs.com/icon/%E9%98%BF%E9%87%8C%E4%BA%91%E7%99%BE%E7%82%BC.png',
        'https://bailian.console.aliyun.com/', 'https://dashscope.aliyuncs.com/compatible-mode', 1,
        '2025-05-17 21:21:23', 1, '2025-05-18 10:17:02', 1, 0, '阿里推出的百炼大模型平台，该平台支持通义千问等大模型。',
        'qwen');
INSERT INTO `agent_support_model_platform`
VALUES (2, 'OpenRouter', 'https://open-agent-hub.oss-cn-hangzhou.aliyuncs.com/icon/OpenRouter.jpeg',
        'https://openrouter.ai', 'https://openrouter.ai/api', 0, '2025-05-20 22:54:48', 1, '2025-05-21 21:02:17', 1, 0,
        'OpenRouter 是一个大模型 API 路由器，旨在将各种 AI 模型和服务集成到一个统一的接口中。它允许用户通过简单的配置就能调用不同大模型的能力。',
        'OpenRouter');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `username`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
    `nickname`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
    `gender`      tinyint(1) NULL DEFAULT 1 COMMENT '性别((1-男 2-女 0-保密)',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
    `role`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user' COMMENT '角色(admin user)',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
    `mobile`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
    `status`      tinyint(1) NULL DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
    `email`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `create_by`   bigint NULL DEFAULT NULL COMMENT '创建人ID',
    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `update_by`   bigint NULL DEFAULT NULL COMMENT '修改人ID',
    `is_deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
    `openid`      char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信 openid',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `login_name`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'vben', NULL, 1, '123456', 'admin', NULL, NULL, 1, NULL, '2025-05-15 10:58:19', NULL, '2025-05-15 10:58:19',
        NULL, 0, NULL);

SET
FOREIGN_KEY_CHECKS = 1;
