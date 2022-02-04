/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : daily

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 04/02/2022 21:55:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for failed_todo
-- ----------------------------
DROP TABLE IF EXISTS `failed_todo`;
CREATE TABLE `failed_todo`  (
  `user_id` int(0) NOT NULL,
  `plan_id` bigint(0) NOT NULL,
  `faile_date` datetime(0) NULL DEFAULT NULL,
  `rate` float NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `plan_id`) USING BTREE,
  INDEX `FK_plan_id`(`plan_id`) USING BTREE,
  INDEX `FK_plan_failed_date`(`faile_date`) USING BTREE,
  CONSTRAINT `FK_plan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
