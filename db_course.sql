/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MariaDB
 Source Server Version : 100308
 Source Host           : localhost:3306
 Source Schema         : db_course

 Target Server Type    : MariaDB
 Target Server Version : 100308
 File Encoding         : 65001

 Date: 03/06/2019 22:43:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pe_course
-- ----------------------------
DROP TABLE IF EXISTS `pe_course`;
CREATE TABLE `pe_course`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pe_course
-- ----------------------------
INSERT INTO `pe_course` VALUES (1, '大学英语4级', '非常好的英语免费课');

-- ----------------------------
-- Table structure for pr_user_course
-- ----------------------------
DROP TABLE IF EXISTS `pr_user_course`;
CREATE TABLE `pr_user_course`  (
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pr_user_course
-- ----------------------------
INSERT INTO `pr_user_course` VALUES (2, 1);

SET FOREIGN_KEY_CHECKS = 1;
