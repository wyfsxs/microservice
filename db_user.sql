/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MariaDB
 Source Server Version : 100308
 Source Host           : localhost:3306
 Source Schema         : db_user

 Target Server Type    : MariaDB
 Target Server Version : 100308
 File Encoding         : 65001

 Date: 03/06/2019 22:43:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_teacher
-- ----------------------------
DROP TABLE IF EXISTS `pe_teacher`;
CREATE TABLE `pe_teacher`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `intro` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `stars` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_teacher
-- ----------------------------
INSERT INTO `pe_teacher` VALUES (2, '毕业于北京大学，资深物理老师', 5);

-- ----------------------------
-- Table structure for pe_user
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_user
-- ----------------------------
INSERT INTO `pe_user` VALUES (1, 'hanmeimei', '202cb962ac59075b964b07152d234b70', 'NULL', '13800138000', NULL);
INSERT INTO `pe_user` VALUES (2, 'test', '098f6bcd4621d373cade4e832627b4f6', NULL, '13800138000', NULL);
INSERT INTO `pe_user` VALUES (3, 'test1', '5a105e8b9d40e1329780d62ea2265d8a', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
