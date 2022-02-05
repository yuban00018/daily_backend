/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : daily

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 05/02/2022 11:24:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for failed_todo
-- ----------------------------
DROP TABLE IF EXISTS `failed_todo`;
CREATE TABLE `failed_todo`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `faile_date` datetime(0) NOT NULL,
  `rate` float NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_plan_failed_date`(`faile_date`) USING BTREE,
  INDEX `FK_uid`(`user_id`) USING BTREE,
  CONSTRAINT `FK_uid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of failed_todo
-- ----------------------------
INSERT INTO `failed_todo` VALUES (1, 1, '2022-02-01 11:06:14', 80);
INSERT INTO `failed_todo` VALUES (2, 1, '2022-02-02 11:06:35', 90);
INSERT INTO `failed_todo` VALUES (3, 1, '2022-02-04 11:07:14', 60);

-- ----------------------------
-- Table structure for password
-- ----------------------------
DROP TABLE IF EXISTS `password`;
CREATE TABLE `password`  (
  `id` int(0) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `FK_pw_id` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of password
-- ----------------------------
INSERT INTO `password` VALUES (1, 'test');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `plan_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '计划id',
  `user_id` int(0) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `frequency` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_since` datetime(0) NULL DEFAULT NULL,
  `done` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_done_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`) USING BTREE,
  INDEX `FK_plan_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `FK_plan_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (1, 1, 'test', 'off', '136', '2022-02-05 10:05:50', '1', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '唯一编号，主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径',
  `sign_up_date` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '注册邮箱',
  `level` bigint(0) NULL DEFAULT NULL COMMENT '等级',
  `exp` bigint(0) NULL DEFAULT NULL COMMENT '经验',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户类型（不包括管理员）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Admin', NULL, '2022-01-31 13:16:56', '1433851368@qq.com', 0, 0, 'admin');

SET FOREIGN_KEY_CHECKS = 1;
