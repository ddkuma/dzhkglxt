DROP DATABASE IF EXISTS `hk`;
CREATE DATABASE `hk`;
use `hk`;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`username` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '账号',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`avatar` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '头像',
`role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'admin' COMMENT '角色标识',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员表';

INSERT INTO `admin` (username, password, name, avatar, role) VALUES ('admin', '123456', '管理员', 'http://localhost:9090/files/qy-default.png', 'admin');

DROP TABLE IF EXISTS `hkmanteacher`;
CREATE TABLE `hkmanteacher` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`username` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '账号',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`avatar` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '头像',
`phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '手机',
`email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '邮箱',
`role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'hkmanteacher' COMMENT '角色标识',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理老师表';


DROP TABLE IF EXISTS `hk_student`;
CREATE TABLE `hk_student` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`studentid` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '学号',
`studentname` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '学生姓名',
`birthday` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '生日',
`not_disturb` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '免打扰',
`student_teacher` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '辅导员',
`telephone_number` int(10) DEFAULT 0 COMMENT '电话号码',
`hk_class_id` int(10) DEFAULT NULL COMMENT '班级管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学生管理表';


DROP TABLE IF EXISTS `hkteache`;
CREATE TABLE `hkteache` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`username` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '账号',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`avatar` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '头像',
`phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '手机',
`email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '邮箱',
`role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'hkteache' COMMENT '角色标识',
`hk_class_id` int(10) DEFAULT NULL COMMENT '班级管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='辅导员表';


DROP TABLE IF EXISTS `hk_class`;
CREATE TABLE `hk_class` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`classi_d` int(10) DEFAULT 0 COMMENT '班级ID',
`class_name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '班级',
`graduation_time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '毕业时间',
`graduation` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '是否毕业',
`student_teacher` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '辅导员',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='班级管理表';


DROP TABLE IF EXISTS `zh_todaybirthday`;
CREATE TABLE `zh_todaybirthday` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`sent_successfully` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '是否发送成功',
`hk_url` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '贺卡链接',
`hk_student_id` int(10) DEFAULT NULL COMMENT '学生管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='贺卡发送管理表';


DROP TABLE IF EXISTS `hk_hksc`;
CREATE TABLE `hk_hksc` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`generate` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '是否生成',
`hk_url` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '贺卡链接',
`hk_student_id` int(10) DEFAULT NULL COMMENT '学生管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='贺卡生成表';


DROP TABLE IF EXISTS `hk_hk`;
CREATE TABLE `hk_hk` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`hk_student_id` int(10) DEFAULT NULL COMMENT '学生管理ID',
`hk_hksc_id` int(10) DEFAULT NULL COMMENT '贺卡生成ID',
`zh_todaybirthday_id` int(10) DEFAULT NULL COMMENT '贺卡发送管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='贺卡管理表';


DROP TABLE IF EXISTS `hk_dxtj`;
CREATE TABLE `hk_dxtj` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`send_content` longtext COMMENT '发送内容',
`send_results` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '发送结果',
`hk_student_id` int(10) DEFAULT NULL COMMENT '学生管理ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='短信统计表';


