/*
 Navicat Premium Data Transfer

 Source Server         : 人才库
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Schema         : Talent-Pool

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 14/06/2025 09:00:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education`  (
  `education_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学历id',
  `education_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学历名称',
  PRIMARY KEY (`education_id`) USING BTREE,
  INDEX `education_id_idx`(`education_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for interact
-- ----------------------------
DROP TABLE IF EXISTS `interact`;
CREATE TABLE `interact`  (
  `interact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '沟通id',
  `talent_id` int(11) NOT NULL COMMENT '人才id',
  `interact_staff` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '沟通人',
  `interact_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '沟通时间',
  `interact_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '沟通内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`interact_id`) USING BTREE,
  INDEX `interact_id`(`interact_id`) USING BTREE,
  INDEX `talent_id`(`talent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for interview
-- ----------------------------
DROP TABLE IF EXISTS `interview`;
CREATE TABLE `interview`  (
  `interview_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '面试id',
  `talent_id` int(11) NOT NULL COMMENT '人才id',
  `interviewer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '面试官',
  `interview_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '面试时间',
  `interview_access` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '面试评估',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`interview_id`) USING BTREE,
  INDEX `interview_id`(`interview_id`) USING BTREE,
  INDEX `talent_id`(`talent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗位id',
  `post_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume`  (
  `resume_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '简历id',
  `talent_id` int(11) NOT NULL COMMENT '人才id',
  `resume_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '简历图片',
  PRIMARY KEY (`resume_id`) USING BTREE,
  INDEX `resume_id`(`resume_id`) USING BTREE,
  INDEX `talent_id`(`talent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for resume_requestion
-- ----------------------------
DROP TABLE IF EXISTS `resume_requestion`;
CREATE TABLE `resume_requestion`  (
  `resume_requestion_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '简历问题id',
  `resume_id` int(11) NOT NULL COMMENT '简历id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '简历问题',
  PRIMARY KEY (`resume_requestion_id`) USING BTREE,
  UNIQUE INDEX `resume_id`(`resume_id`) USING BTREE,
  INDEX `resume_id_2`(`resume_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `wx_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '企业微信用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 角色',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`staff_id`) USING BTREE,
  INDEX `staff_id`(`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for talent
-- ----------------------------
DROP TABLE IF EXISTS `talent`;
CREATE TABLE `talent`  (
  `talent_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人才id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `post_id` int(11) NOT NULL COMMENT '岗位id',
  `education_id` int(11) NOT NULL COMMENT '学历id',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系方式',
  `sex` int(11) NOT NULL COMMENT '性别（1-男，2-女）',
  `age` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '年龄',
  `marriage` int(11) NOT NULL COMMENT '婚育情况（1-已婚已育、2-已婚未育、3-未婚未育）',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '个人照片',
  `current_salary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '当前薪资',
  `expected_salary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '期望薪资',
  `job_status` int(11) NULL DEFAULT NULL COMMENT '工作状态（1-在职、2-离职）',
  `job_year` int(11) NULL DEFAULT NULL COMMENT '工作年限',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `current_post` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目前岗位',
  `reason_for_leaving` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '离职原因',
  `advantage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优势',
  `disadvantage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '劣势',
  `result` int(11) NULL DEFAULT 1 COMMENT '面试结果（1-待定，2-通过，3-未通过）',
  `next_interview_time` datetime NULL DEFAULT NULL COMMENT '下次面试时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 编辑时间',
  `edit_sum` int(11) NOT NULL DEFAULT 0 COMMENT '编辑次数',
  `del_status` int(11) NULL DEFAULT 1 COMMENT '删除审核（1-默认，2-提交审核）',
  `del_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除原因',
  `create_staff_id` int(11) NOT NULL COMMENT '创建员工',
  PRIMARY KEY (`talent_id`) USING BTREE,
  INDEX `talent_id`(`talent_id`) USING BTREE,
  INDEX `name`(`name`(191)) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `education_id`(`education_id`) USING BTREE,
  INDEX `create_staff_id`(`create_staff_id`) USING BTREE,
  INDEX `next_interview_time`(`next_interview_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
