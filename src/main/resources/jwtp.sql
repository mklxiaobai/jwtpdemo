/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 50646
Source Host           : 49.234.202.210:3306
Source Database       : hengyimes

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2019-12-20 12:54:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `authority` varchar(128) NOT NULL COMMENT '授权标识',
  `authority_name` varchar(128) NOT NULL COMMENT '名称',
  `parent_name` varchar(128) DEFAULT NULL COMMENT '模块',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限';

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('delete:/v1/authorities/role', '移除角色权限', '权限管理', '3', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('delete:/v1/menu/{id}', '删除菜单', '菜单管理', '8', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('delete:/v1/role/{id}', '删除角色', '角色管理', '12', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('delete:/v1/user/{id}', '删除用户', '用户管理', '22', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/authorities', '查询所有权限', '权限管理', '1', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/menu', '查询所有菜单', '菜单管理', '5', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/role', '查询所有角色', '角色管理', '9', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/user', '查询所有用户', '用户管理', '13', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/user/info', '获取个人信息', '个人信息', '16', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('get:/v1/user/menu', '获取所有菜单', '个人信息', '18', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/authorities/role', '给角色添加权限', '权限管理', '2', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/authorities/sync', '同步权限', '权限管理', '4', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/menu', '添加菜单', '菜单管理', '6', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/role', '添加角色', '角色管理', '10', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/user', '添加用户', '用户管理', '14', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('post:/v1/user/login', '用户登录', '个人信息', '17', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/menu', '修改菜单', '菜单管理', '7', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/role', '修改角色', '角色管理', '11', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/user', '修改用户', '用户管理', '15', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/user/psw', '修改自己密码', '用户管理', '19', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/user/psw/{id}', '重置密码', '用户管理', '20', '2019-03-29 13:07:48');
INSERT INTO `authorities` VALUES ('put:/v1/user/state', '修改用户状态', '用户管理', '21', '2019-03-29 13:07:48');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT '-1' COMMENT '父级id',
  `menu_name` varchar(200) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单url',
  `menu_icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  `sort_number` int(11) NOT NULL DEFAULT '0' COMMENT '排序号',
  `authority` varchar(200) DEFAULT NULL COMMENT '对应权限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '-1', '系统管理', '', 'layui-icon layui-icon-set', '1', '', '2019-03-26 13:19:08', '2019-03-26 16:18:08');
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '#/system/user', '', '3', 'get:/v1/user', '2019-03-26 16:21:17', '2019-03-29 13:59:13');
INSERT INTO `menu` VALUES ('3', '1', '角色管理', '#/system/role', '', '4', 'get:/v1/role', '2019-03-26 16:22:03', '2019-03-29 13:59:13');
INSERT INTO `menu` VALUES ('4', '1', '权限管理', '#/system/authorities', '', '5', 'get:/v1/authorities', '2019-03-26 16:22:28', '2019-03-29 13:59:14');
INSERT INTO `menu` VALUES ('5', '1', '菜单管理', '#/system/menu', '', '6', 'get:/v1/menu', '2019-03-26 16:28:14', '2019-03-29 13:59:14');
INSERT INTO `menu` VALUES ('6', '-1', '系统功能', '', 'layui-icon layui-icon-engine', '7', '', '2019-03-28 16:44:19', '2019-03-29 13:59:15');
INSERT INTO `menu` VALUES ('7', '6', 'Druid监控', '#/tpl/iframe/id=druid', '', '8', '', '2019-03-28 16:45:34', '2019-03-29 13:59:19');
INSERT INTO `menu` VALUES ('8', '6', '接口文档', '#/tpl/iframe/id=swagger', '', '9', '', '2019-03-28 16:47:56', '2019-03-29 13:59:20');

-- ----------------------------
-- Table structure for oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_token`;
CREATE TABLE `oauth_token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(128) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `permissions` varchar(512) DEFAULT NULL,
  `roles` varchar(512) DEFAULT NULL,
  `refresh_token` varchar(128) DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `refresh_token_expire_time` datetime DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_token
-- ----------------------------
INSERT INTO `oauth_token` VALUES ('6', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTc2ODA5NDM2fQ.CZThJPIjt4V1mavGRmN7bLV0Eo9uCxEvVpkzwQsblSw', '1', '[\"delete:/v1/role/{id}\",\"get:/v1/menu\",\"post:/v1/menu\",\"put:/v1/menu\",\"delete:/v1/user/{id}\",\"delete:/v1/menu/{id}\",\"get:/v1/user\",\"get:/v1/user/info\",\"post:/v1/user/login\",\"get:/v1/user/menu\",\"delete:/v1/authorities/role\",\"put:/v1/user/state\",\"post:/v1/authorities/sync\",\"get:/v1/role\",\"get:/v1/authorities\",\"post:/v1/role\",\"post:/v1/user\",\"put:/v1/role\",\"put:/v1/user\",\"put:/v1/user/psw\",\"put:/v1/user/psw/{id}\",\"post:/v1/authorities/role\"]', '[\"1\"]', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTg0NTgxODM2fQ.pwdW76NbynLtMySUXO5YC4W6OcM_WwwojUG4ns02mYE', '2019-12-20 02:37:17', '2019-12-20 01:37:17', '2019-12-20 01:37:17', '2020-03-19 01:37:17');
INSERT INTO `oauth_token` VALUES ('7', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTc2ODE1MjM5fQ.Vg9eAk0zMoTiIQsvN6we8C9ntiZteIlXx23oDZ4CM78', '1', '[\"delete:/v1/role/{id}\",\"get:/v1/menu\",\"post:/v1/menu\",\"put:/v1/menu\",\"delete:/v1/user/{id}\",\"delete:/v1/menu/{id}\",\"get:/v1/user\",\"get:/v1/user/info\",\"post:/v1/user/login\",\"get:/v1/user/menu\",\"delete:/v1/authorities/role\",\"put:/v1/user/state\",\"post:/v1/authorities/sync\",\"get:/v1/role\",\"get:/v1/authorities\",\"post:/v1/role\",\"post:/v1/user\",\"put:/v1/role\",\"put:/v1/user\",\"put:/v1/user/psw\",\"put:/v1/user/psw/{id}\",\"post:/v1/authorities/role\"]', '[\"1\"]', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTg0NTg3NjM5fQ.MGi_w2eY-F6_mXe5paXZVc-IZYAji5KzlECsUSFCLhE', '2019-12-20 04:14:00', '2019-12-20 03:14:00', '2019-12-20 03:14:00', '2020-03-19 03:14:00');
INSERT INTO `oauth_token` VALUES ('8', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTc2ODE4OTUxfQ.rLgn2kmfPR8tcyU0oDpKL27R2zTESDrstSEph_7vYl4', '1', '[\"delete:/v1/role/{id}\",\"get:/v1/menu\",\"post:/v1/menu\",\"put:/v1/menu\",\"delete:/v1/user/{id}\",\"delete:/v1/menu/{id}\",\"get:/v1/user\",\"get:/v1/user/info\",\"post:/v1/user/login\",\"get:/v1/user/menu\",\"delete:/v1/authorities/role\",\"put:/v1/user/state\",\"post:/v1/authorities/sync\",\"get:/v1/role\",\"get:/v1/authorities\",\"post:/v1/role\",\"post:/v1/user\",\"put:/v1/role\",\"put:/v1/user\",\"put:/v1/user/psw\",\"put:/v1/user/psw/{id}\",\"post:/v1/authorities/role\"]', '[\"1\"]', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTg0NTkxMzUxfQ.Meuyenoiu16GhYpETD1L-Ey1yk50vOl3jQfQ61VyMIM', '2019-12-20 05:15:51', '2019-12-20 04:15:51', '2019-12-20 04:15:51', '2020-03-19 04:15:51');

-- ----------------------------
-- Table structure for oauth_token_key
-- ----------------------------
DROP TABLE IF EXISTS `oauth_token_key`;
CREATE TABLE `oauth_token_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_key` varchar(128) NOT NULL COMMENT '生成token时的key',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_token_key
-- ----------------------------
INSERT INTO `oauth_token_key` VALUES ('2', 'a01bed957a83722e11a7a9e0556cd30e6440a64fb2ec57df0cb449181b50fe78', '2019-12-19 13:12:14');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `comments` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理员', '2018-12-19 23:11:29', '2018-12-19 23:11:29');
INSERT INTO `role` VALUES ('2', '普通用户', '普通用户', '2018-12-19 23:12:09', '2018-12-19 23:12:09');

-- ----------------------------
-- Table structure for role_authorities
-- ----------------------------
DROP TABLE IF EXISTS `role_authorities`;
CREATE TABLE `role_authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `authority` varchar(128) NOT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_role_permission_pm` (`authority`),
  KEY `FK_sys_role_permission_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色权限';

-- ----------------------------
-- Records of role_authorities
-- ----------------------------
INSERT INTO `role_authorities` VALUES ('1', '1', 'delete:/v1/role/{id}', '2019-12-19 16:35:44');
INSERT INTO `role_authorities` VALUES ('2', '1', 'get:/v1/menu', '2019-03-29 12:49:52');
INSERT INTO `role_authorities` VALUES ('3', '1', 'post:/v1/menu', '2019-03-29 12:49:53');
INSERT INTO `role_authorities` VALUES ('4', '1', 'put:/v1/menu', '2019-03-29 12:49:53');
INSERT INTO `role_authorities` VALUES ('5', '1', 'delete:/v1/user/{id}', '2019-03-29 12:50:04');
INSERT INTO `role_authorities` VALUES ('6', '1', 'delete:/v1/menu/{id}', '2019-03-29 12:50:44');
INSERT INTO `role_authorities` VALUES ('7', '1', 'get:/v1/user', '2019-03-29 12:50:45');
INSERT INTO `role_authorities` VALUES ('8', '1', 'get:/v1/user/info', '2019-03-29 13:47:08');
INSERT INTO `role_authorities` VALUES ('9', '1', 'post:/v1/user/login', '2019-03-29 13:47:08');
INSERT INTO `role_authorities` VALUES ('10', '1', 'get:/v1/user/menu', '2019-03-29 13:47:08');
INSERT INTO `role_authorities` VALUES ('11', '1', 'delete:/v1/authorities/role', '2018-12-26 10:32:09');
INSERT INTO `role_authorities` VALUES ('12', '1', 'put:/v1/user/state', '2018-12-19 23:11:50');
INSERT INTO `role_authorities` VALUES ('13', '1', 'post:/v1/authorities/sync', '2018-12-19 23:11:42');
INSERT INTO `role_authorities` VALUES ('14', '1', 'get:/v1/role', '2018-12-19 23:11:40');
INSERT INTO `role_authorities` VALUES ('15', '1', 'get:/v1/authorities', '2018-12-19 23:11:37');
INSERT INTO `role_authorities` VALUES ('16', '1', 'post:/v1/role', '2018-12-19 23:11:43');
INSERT INTO `role_authorities` VALUES ('17', '1', 'post:/v1/user', '2018-12-19 23:11:44');
INSERT INTO `role_authorities` VALUES ('18', '1', 'put:/v1/role', '2018-12-19 23:11:46');
INSERT INTO `role_authorities` VALUES ('19', '1', 'put:/v1/user', '2018-12-19 23:11:46');
INSERT INTO `role_authorities` VALUES ('20', '1', 'put:/v1/user/psw', '2018-12-19 23:11:47');
INSERT INTO `role_authorities` VALUES ('21', '1', 'put:/v1/user/psw/{id}', '2018-12-19 23:11:47');
INSERT INTO `role_authorities` VALUES ('22', '1', 'post:/v1/authorities/role', '2018-12-19 23:11:41');
INSERT INTO `role_authorities` VALUES ('23', '2', 'get:/v1/user/info', '2019-03-29 13:47:39');
INSERT INTO `role_authorities` VALUES ('24', '2', 'get:/v1/menu', '2019-03-29 13:07:36');
INSERT INTO `role_authorities` VALUES ('25', '2', 'get:/v1/user', '2019-03-29 12:50:56');
INSERT INTO `role_authorities` VALUES ('26', '2', 'get:/v1/authorities', '2018-12-19 23:12:35');
INSERT INTO `role_authorities` VALUES ('27', '2', 'get:/v1/role', '2018-12-19 23:13:06');
INSERT INTO `role_authorities` VALUES ('28', '2', 'get:/v1/user/menu', '2019-03-29 13:47:40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) NOT NULL COMMENT '昵称',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) NOT NULL DEFAULT '男' COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '手机号',
  `email` varchar(256) DEFAULT NULL COMMENT '邮箱',
  `email_verified` int(1) DEFAULT '0' COMMENT '邮箱是否验证，0未验证，1已验证',
  `true_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`username`),
  KEY `FK_sys_user` (`true_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '', '男', '12345678901', '', '0', '', '', '2019-12-19', null, '0', '2018-12-19 23:30:05', '2019-12-19 13:06:30');
INSERT INTO `user` VALUES ('2', 'user01', 'e10adc3949ba59abbe56e057f20f883e', '用户01', '', '男', '12345678901', '', '0', '', '', '2019-12-04', null, '0', '2018-12-19 23:31:25', '2019-12-19 13:06:38');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_sys_user_role` (`user_id`),
  KEY `FK_sys_user_role_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2018-12-19 23:30:06');
INSERT INTO `user_role` VALUES ('2', '2', '2', '2019-03-29 13:13:35');
