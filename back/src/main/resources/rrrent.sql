/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : rrrent

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-06-28 00:02:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT '' COMMENT '文章内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1.有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='发布号作者表';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', 'f', '2020-06-27 18:02:37', '2020-06-27 18:02:37', '1');

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '小区名称',
  `architecture_age` varchar(32) DEFAULT NULL COMMENT '建筑年代',
  `architecture_type` varchar(32) DEFAULT NULL COMMENT '建筑类型',
  `architecture_sum` int(8) DEFAULT NULL COMMENT '总楼栋数',
  `house_sum` int(8) DEFAULT NULL COMMENT '总户数',
  `service_company` varchar(64) DEFAULT NULL COMMENT '物业公司',
  `service_fee` decimal(10,2) DEFAULT NULL COMMENT '物业费',
  `develop_company` varchar(64) DEFAULT NULL COMMENT '开发商',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `second_hand_price` decimal(10,2) DEFAULT NULL COMMENT '二手房价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES ('1', '是', null, null, null, null, null, null, null, null, null);
INSERT INTO `community` VALUES ('2', '电饭锅', 'F90T00', 'TOWER', '45', '567', '黑健康风险', '56.00', '少的发', '好几个款', '46356.00');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `menu_code` varchar(255) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(255) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(255) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` tinyint(1) DEFAULT '2' COMMENT '是否本菜单必选权限, 1.必选 2非必选 通常是"列表"权限是必选',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=708 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('101', 'article', '房源管理', 'article:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('102', 'article', '房源管理', 'article:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('103', 'article', '房源管理', 'article:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('601', 'user', '用户', 'user:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('602', 'user', '用户', 'user:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('603', 'user', '用户', 'user:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('701', 'role', '角色权限', 'role:list', '列表', '1');
INSERT INTO `sys_permission` VALUES ('702', 'role', '角色权限', 'role:add', '新增', '2');
INSERT INTO `sys_permission` VALUES ('703', 'role', '角色权限', 'role:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('704', 'role', '角色权限', 'role:delete', '删除', '2');
INSERT INTO `sys_permission` VALUES ('705', 'community', '社区管理', 'community:list', '列表', '2');
INSERT INTO `sys_permission` VALUES ('706', 'community', '社区管理', 'community:update', '修改', '2');
INSERT INTO `sys_permission` VALUES ('707', 'community', '社区管理', 'community:add', '新增', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '2020-06-19 16:26:21', '2020-06-19 16:26:21', '1');
INSERT INTO `sys_role` VALUES ('2', '作家', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role` VALUES ('3', '程序员', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role` VALUES ('4', '员工', '2020-06-20 11:16:57', '2020-06-20 11:16:57', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效 1有效     2无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '2', '101', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('2', '2', '102', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('5', '2', '602', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('6', '2', '601', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('7', '2', '603', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('8', '2', '703', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('9', '2', '701', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('10', '2', '702', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('11', '2', '704', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('12', '2', '103', '2020-06-19 16:26:21', '2020-06-20 11:16:36', '2');
INSERT INTO `sys_role_permission` VALUES ('13', '3', '601', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('14', '3', '701', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('15', '3', '702', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('16', '3', '704', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('17', '3', '102', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('18', '3', '101', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('19', '3', '603', '2020-06-19 16:26:21', '2020-06-20 11:16:38', '2');
INSERT INTO `sys_role_permission` VALUES ('20', '4', '601', '2020-06-20 11:16:57', '2020-06-20 11:16:57', '1');
INSERT INTO `sys_role_permission` VALUES ('21', '4', '701', '2020-06-20 11:16:57', '2020-06-20 11:17:25', '2');
INSERT INTO `sys_role_permission` VALUES ('22', '4', '702', '2020-06-20 11:17:12', '2020-06-20 11:17:25', '2');
INSERT INTO `sys_role_permission` VALUES ('23', '4', '602', '2020-06-20 11:17:12', '2020-06-20 11:17:12', '1');
INSERT INTO `sys_role_permission` VALUES ('24', '4', '603', '2020-06-20 11:17:12', '2020-06-20 11:17:12', '1');
INSERT INTO `sys_role_permission` VALUES ('25', '4', '703', '2020-06-20 11:17:12', '2020-06-20 11:17:25', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  2无效',
  `wechat_appid` varchar(255) DEFAULT NULL COMMENT '微信id',
  `wechatlet_appid` varchar(255) DEFAULT NULL COMMENT '小程序id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8 COMMENT='运营后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10003', 'admin', '123456', '超级用户23', '1', '2020-06-19 16:26:21', '2020-06-19 16:26:21', '1', null, null);
INSERT INTO `sys_user` VALUES ('10004', 'user', '123456', '莎士比亚', '2', '2020-06-19 16:26:21', '2020-06-20 11:16:25', '2', null, null);
INSERT INTO `sys_user` VALUES ('10005', 'aaa', '123456', 'abba', '1', '2020-06-19 16:26:21', '2020-06-20 11:16:27', '2', null, null);
INSERT INTO `sys_user` VALUES ('10007', 'test', '123456', '就看看列表', '3', '2020-06-19 16:26:21', '2020-06-20 11:16:28', '2', null, null);
