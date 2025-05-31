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
