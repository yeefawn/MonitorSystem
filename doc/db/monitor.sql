/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-08-06 21:49:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_logs
-- ----------------------------
DROP TABLE IF EXISTS `m_logs`;
CREATE TABLE `m_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作人id',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `ip_address` varchar(15) DEFAULT NULL COMMENT '操作人IP',
  `operate_des` varchar(255) DEFAULT '' COMMENT '操作描述',
  `operate_result` varchar(1) DEFAULT NULL COMMENT '操作结果 0-失败 1-成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_logs
-- ----------------------------

-- ----------------------------
-- Table structure for m_resource
-- ----------------------------
DROP TABLE IF EXISTS `m_resource`;
CREATE TABLE `m_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(2) DEFAULT NULL COMMENT '资源类型（0*:菜单（00为主菜单，01为一级菜单，以此类推） 10为操作）',
  `url` varchar(255) DEFAULT NULL COMMENT '资源地址（如果为操作可不填）',
  `priority` int(10) unsigned DEFAULT NULL COMMENT '显示顺序，数值越小越靠前',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '直属父ID',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '所有父ID列表（ID之间以"/"隔开）',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限标识符',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(1) unsigned DEFAULT '1' COMMENT '是否可用 0-不可用  1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_resource
-- ----------------------------

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id` varchar(32) NOT NULL COMMENT '主键ID，使用UUID生成',
  `role` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(1) unsigned DEFAULT '1' COMMENT '是否可用 0-不可用  1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_role
-- ----------------------------

-- ----------------------------
-- Table structure for m_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `m_role_resource`;
CREATE TABLE `m_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码，存储加密后的密码',
  `fullname` varchar(100) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `locked` int(1) unsigned DEFAULT '1' COMMENT '否被锁 0-被锁 1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user
-- ----------------------------

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
