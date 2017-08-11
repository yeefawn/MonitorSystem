/*
Navicat MySQL Data Transfer

Source Server         : location
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-08-11 18:02:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_logs
-- ----------------------------
DROP TABLE IF EXISTS `m_logs`;
CREATE TABLE `m_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作人id',
  `operate_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(1) unsigned DEFAULT '1' COMMENT '是否可用 0-不可用  1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_resource
-- ----------------------------
INSERT INTO `m_resource` VALUES ('1', '系统管理', '00', '', '255', null, null, 'system:manage', 'admin', '2017-08-07 22:01:02', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:12:56', '1');
INSERT INTO `m_resource` VALUES ('2', '用户管理', '01', '/user/list', '2', '1', '1', 'user:list', 'admin', '2017-08-07 22:04:23', 'admin', '2017-08-08 21:16:53', '1');
INSERT INTO `m_resource` VALUES ('3', '资源管理', '01', '/resource/list', '0', '1', '1', 'resource:list', 'admin', '2017-08-07 22:05:17', 'admin', '2017-08-08 21:15:36', '1');
INSERT INTO `m_resource` VALUES ('4', '添加资源', '10', '', '0', '3', '1/3', 'resource:add', 'admin', '2017-08-07 22:09:08', 'admin', '2017-08-08 21:16:23', '1');
INSERT INTO `m_resource` VALUES ('5', '删除资源', '10', '', '1', '3', '1/3', 'resource:delete', 'admin', '2017-08-07 22:09:46', 'admin', '2017-08-08 21:16:19', '1');
INSERT INTO `m_resource` VALUES ('6', '修改资源', '10', '', '2', '3', '1/3', 'resource:edit', 'admin', '2017-08-07 22:11:18', 'admin', '2017-08-08 21:16:14', '1');
INSERT INTO `m_resource` VALUES ('7', '添加用户', '10', '', '0', '2', '1/2', 'admin:add', 'admin', '2017-08-07 22:14:28', 'admin', '2017-08-08 21:15:52', '1');
INSERT INTO `m_resource` VALUES ('8', '修改用户', '10', '', '2', '2', '1/2', 'admin:edit', 'admin', '2017-08-07 22:18:07', 'admin', '2017-08-08 21:15:59', '1');
INSERT INTO `m_resource` VALUES ('9', '删除用户', '10', '', '1', '2', '1/2', 'admin:delete', 'admin', '2017-08-07 22:20:31', 'admin', '2017-08-08 21:18:13', '1');
INSERT INTO `m_resource` VALUES ('10', '角色管理', '01', '/role/list', '1', '1', '1', 'role:list', 'admin', '2017-08-08 21:17:52', 'admin', '2017-08-08 22:36:55', '1');
INSERT INTO `m_resource` VALUES ('11', '添加角色', '10', '', '0', '10', null, 'role:add', 'admin', '2017-08-08 21:19:14', null, null, '1');
INSERT INTO `m_resource` VALUES ('12', '删除角色', '10', '', '1', '10', null, 'role:delete', 'admin', '2017-08-08 21:19:33', null, null, '1');
INSERT INTO `m_resource` VALUES ('13', '修改角色', '10', '', '2', '10', null, 'role:edit', 'admin', '2017-08-08 21:20:05', 'admin', '2017-08-08 21:20:19', '1');
INSERT INTO `m_resource` VALUES ('14', '监控管理', '00', '', '1', null, null, 'monitor:manage', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:12:39', null, null, '1');
INSERT INTO `m_resource` VALUES ('15', '系统列表', '01', '/monitor/list', '0', '14', '14', 'monitor:list', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:17:36', null, null, '1');
INSERT INTO `m_resource` VALUES ('16', '添加系统', '10', '', '0', '15', null, 'monitor:add', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:18:20', null, null, '1');
INSERT INTO `m_resource` VALUES ('17', '删除系统', '10', '', '1', '15', null, 'monitor:delete', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:18:46', null, null, '1');
INSERT INTO `m_resource` VALUES ('18', '修改系统', '10', '', '2', '15', null, 'monitor:edit', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 16:19:13', null, null, '1');

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id` varchar(32) NOT NULL COMMENT '主键ID，使用UUID生成',
  `role` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL,
  `role_type` int(1) unsigned DEFAULT '1' COMMENT '角色类型 0-超级管理员 1-普通管理员',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(1) unsigned DEFAULT '1' COMMENT '是否可用 0-不可用  1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_role
-- ----------------------------
INSERT INTO `m_role` VALUES ('b1c1b282-fea3-4606-85d3-3365e9e', '超级管理员', '', '0', 'admin', '2017-08-08 21:42:34', '53c052b6-eb52-47ba-9cb2-76486a0', '2017-08-11 17:17:23', '1');

-- ----------------------------
-- Table structure for m_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `m_role_resource`;
CREATE TABLE `m_role_resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `resource_id` bigint(20) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_role_resource
-- ----------------------------
INSERT INTO `m_role_resource` VALUES ('50', 'b1c1b282-fea3-4606-85d3-3365e9e', '14');
INSERT INTO `m_role_resource` VALUES ('51', 'b1c1b282-fea3-4606-85d3-3365e9e', '15');
INSERT INTO `m_role_resource` VALUES ('52', 'b1c1b282-fea3-4606-85d3-3365e9e', '16');
INSERT INTO `m_role_resource` VALUES ('53', 'b1c1b282-fea3-4606-85d3-3365e9e', '17');
INSERT INTO `m_role_resource` VALUES ('54', 'b1c1b282-fea3-4606-85d3-3365e9e', '18');
INSERT INTO `m_role_resource` VALUES ('55', 'b1c1b282-fea3-4606-85d3-3365e9e', '1');
INSERT INTO `m_role_resource` VALUES ('56', 'b1c1b282-fea3-4606-85d3-3365e9e', '3');
INSERT INTO `m_role_resource` VALUES ('57', 'b1c1b282-fea3-4606-85d3-3365e9e', '4');
INSERT INTO `m_role_resource` VALUES ('58', 'b1c1b282-fea3-4606-85d3-3365e9e', '5');
INSERT INTO `m_role_resource` VALUES ('59', 'b1c1b282-fea3-4606-85d3-3365e9e', '6');
INSERT INTO `m_role_resource` VALUES ('60', 'b1c1b282-fea3-4606-85d3-3365e9e', '10');
INSERT INTO `m_role_resource` VALUES ('61', 'b1c1b282-fea3-4606-85d3-3365e9e', '11');
INSERT INTO `m_role_resource` VALUES ('62', 'b1c1b282-fea3-4606-85d3-3365e9e', '12');
INSERT INTO `m_role_resource` VALUES ('63', 'b1c1b282-fea3-4606-85d3-3365e9e', '13');
INSERT INTO `m_role_resource` VALUES ('64', 'b1c1b282-fea3-4606-85d3-3365e9e', '2');
INSERT INTO `m_role_resource` VALUES ('65', 'b1c1b282-fea3-4606-85d3-3365e9e', '7');
INSERT INTO `m_role_resource` VALUES ('66', 'b1c1b282-fea3-4606-85d3-3365e9e', '9');
INSERT INTO `m_role_resource` VALUES ('67', 'b1c1b282-fea3-4606-85d3-3365e9e', '8');

-- ----------------------------
-- Table structure for m_system
-- ----------------------------
DROP TABLE IF EXISTS `m_system`;
CREATE TABLE `m_system` (
  `id` varchar(32) NOT NULL COMMENT '主键 UUID',
  `system_name` varchar(100) DEFAULT NULL COMMENT '系统名称',
  `is_email` int(1) unsigned DEFAULT '0' COMMENT '是否发送邮件 0-否 1-是',
  `is_sms` int(1) unsigned DEFAULT '0' COMMENT '是否发送短信通知 0-否 1-是',
  `status` int(1) unsigned DEFAULT '1' COMMENT '系统状态 0-宕机 1-正常',
  `available` int(1) unsigned DEFAULT '1' COMMENT '是否进行监控 0-否 1-是',
  `urls` longtext COMMENT '用于测试的url列表，可多个，多个url以英文逗号","隔开',
  `contact` longtext COMMENT '用户id列表，可多个，多个用户id以英文逗号","隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_system
-- ----------------------------

-- ----------------------------
-- Table structure for m_task_logs
-- ----------------------------
DROP TABLE IF EXISTS `m_task_logs`;
CREATE TABLE `m_task_logs` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_name` varchar(100) DEFAULT NULL COMMENT '系统名称',
  `monitor_url` varchar(255) DEFAULT NULL COMMENT '用于测试的URL',
  `result` varchar(3) DEFAULT NULL COMMENT 'HTTP状态码',
  `execute_time` datetime DEFAULT NULL COMMENT '测试时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_task_logs
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
  `login_count` int(10) unsigned DEFAULT '0' COMMENT '登陆次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登陆的时间',
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后一次的登陆IP',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `locked` int(1) unsigned DEFAULT '1' COMMENT '否被锁 0-被锁 1-可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('53c052b6-eb52-47ba-9cb2-76486a0', 'admin', 'c40c85650500146571a380063bf9f754', '超级管理员', '', '', '', '7', '2017-08-11 17:17:35', '127.0.0.1', 'admin', '2017-08-08 22:37:34', null, null, '1');

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID,自增',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
INSERT INTO `m_user_role` VALUES ('3', '53c052b6-eb52-47ba-9cb2-76486a0', 'b1c1b282-fea3-4606-85d3-3365e9e');
