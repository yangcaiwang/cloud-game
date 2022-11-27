/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 43.138.76.94:3306
 Source Schema         : app

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 26/11/2022 23:05:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `coins` bigint(20) NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1586289607091261442 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `user_id`, `nick_name`, `coins`, `create_at`, `update_at`, `deleted`, `version`) VALUES (1586285914379788290, 101, 'user101', 100000, '2022-10-29 17:16:48.412000', '2022-10-29 17:16:48.412000', 0, 1);
INSERT INTO `user` (`id`, `user_id`, `nick_name`, `coins`, `create_at`, `update_at`, `deleted`, `version`) VALUES (1586289607091261441, 101, 'user101', 100000, '2022-10-29 17:31:28.824000', '2022-10-29 17:31:28.827000', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for word_config
-- ----------------------------
DROP TABLE IF EXISTS `word_config`;
CREATE TABLE `word_config` (
  `id` bigint(20) NOT NULL,
  `word_content` varchar(255) NOT NULL,
  `word_reward` varchar(255) NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of word_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for word_user_status
-- ----------------------------
DROP TABLE IF EXISTS `word_user_status`;
CREATE TABLE `word_user_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `cache` json NOT NULL,
  `create_at` datetime(6) NOT NULL,
  `update_at` datetime(6) NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `version` bigint(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of word_user_status
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
