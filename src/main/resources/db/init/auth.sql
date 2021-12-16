-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `nick_name` varchar(200) NOT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `is_delete` int(1) DEFAULT 0 COMMENT '是否注销 0->否；1->是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('da6d966d37fcb819546064424a8bdd9f', 'admin', '$2a$10$KJMluGRhQ2ZrOzXck9t5M.k93Y30h6UuX0obPDVbZZ9Zsk/LMX5zi', 'https://photo.choot.top/42e47b8dfcd943ae95da59e1c45bc8d3', '1181881941@qq.com' , '小甜瓜', NULL, '2021-05-13 14:35:29', NULL, 1,0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `description` varchar(64) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('e8a1d847a93cf5cc541731be3c12fd87', '超级管理员','超级管理员', '2021-05-14 14:10:38', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:10:38', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT '0' COMMENT '父级ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `path` varchar(100) NOT NULL COMMENT '菜单url',
  `icon` varchar(200) NOT NULL COMMENT '前端图标',
  `sort` int(4) NOT NULL COMMENT '菜单排序',
  `level` int(4) DEFAULT NULL COMMENT '菜单级别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户菜单表';

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('1', '0', '首页', '/home', 'home', 10, null, '2021-05-18 15:06:06', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-18 15:06:08', 'da6d966d37fcb819546064424a8bdd9f');

INSERT INTO `sys_menus` VALUES ('2', '0', '博客管理', '/blog', 'blog', 20, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('3', '2', '文章管理', '/blog/article', 'blog-article', 30, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('4', '2', '分类管理', '/blog/category', 'blog-category', 40, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('5', '2', '标签管理', '/blog/tag', 'blog-tag', 50, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('7', '2', '导航栏管理', '/blog/nav', 'blog-nav', 31, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('8', '2', '轮播图管理', '/blog/loop', 'blog-loop', 32, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');

INSERT INTO `sys_menus` VALUES ('10', '0', '系统工具', '/tool', 'tool', 70, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('11', '10', '邮件', '/tool/email', 'tool-email', 60, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');

INSERT INTO `sys_menus` VALUES ('20', '0', '资源管理', '/resource', 'resource', 80, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('21', '20', '七牛云', '/resource/qiniu', 'resource-qiniu', 90, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('22', '20', '本地存储', '/resource/localStorage', 'resource-localStorage', 100, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');

INSERT INTO `sys_menus` VALUES ('30', '0', '监控中心', '/monitor', 'monitor', 110, null, '2021-06-16 13:05:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 13:05:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('31', '30', '服务器监控', '/monitor/system', 'monitor', 120, null, '2021-06-16 13:05:50', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 13:05:50', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('32', '30', 'druid', '/monitor/druid', 'monitor-druid', 130, null, '2021-06-16 16:25:43', 'da6d966d37fcb819546064424a8bdd9f' ,'2021-06-16 16:25:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('33', '30', '操作日志', '/monitor/log', 'monitor-log', 140, null, '2021-06-16 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 14:48:31', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('34', '30', '异常日志', '/monitor/exception', 'monitor-exception', 150, null, '2021-06-16 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 14:48:31', 'da6d966d37fcb819546064424a8bdd9f');

INSERT INTO `sys_menus` VALUES ('40', '0', '系统管理', '/sys', 'sys', 160, null, '2021-05-17 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-16 09:33:06', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('41', '40', '用户管理', '/sys/user', 'sys-user', 170, null, '2021-05-17 11:49:04', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-17 11:49:04', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('42', '40', '角色管理', '/sys/role', 'sys-role', 180, null, '2021-05-17 11:49:04', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-17 11:49:04', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('43', '40', '菜单管理', '/sys/menu', 'sys-menu', 190, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('44', '40', '字典管理', '/sys/dict', 'sys-dict', 200, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_menus` VALUES ('45', '40', '任务调度', '/sys/task', 'sys-task', 210, null, '2021-05-18 11:32:02', 'da6d966d37fcb819546064424a8bdd9f', '2021-06-15 19:55:40', 'da6d966d37fcb819546064424a8bdd9f');



-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户与角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'da6d966d37fcb819546064424a8bdd9f', 'e8a1d847a93cf5cc541731be3c12fd87', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for sys_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menus`;
CREATE TABLE `sys_role_menus` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色与权限关联表';

-- ----------------------------
-- Records of sys_role_menus
-- ----------------------------
INSERT INTO `sys_role_menus` VALUES ('1', 'e8a1d847a93cf5cc541731be3c12fd87', '1', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('2', 'e8a1d847a93cf5cc541731be3c12fd87', '2', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('3', 'e8a1d847a93cf5cc541731be3c12fd87', '3', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('4', 'e8a1d847a93cf5cc541731be3c12fd87', '4', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('5', 'e8a1d847a93cf5cc541731be3c12fd87', '5', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('24', 'e8a1d847a93cf5cc541731be3c12fd87', '7', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('25', 'e8a1d847a93cf5cc541731be3c12fd87', '8', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('7', 'e8a1d847a93cf5cc541731be3c12fd87', '10', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('8', 'e8a1d847a93cf5cc541731be3c12fd87', '11', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('9', 'e8a1d847a93cf5cc541731be3c12fd87', '20', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('10', 'e8a1d847a93cf5cc541731be3c12fd87', '21', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('11', 'e8a1d847a93cf5cc541731be3c12fd87', '22', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('12', 'e8a1d847a93cf5cc541731be3c12fd87', '30', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('13', 'e8a1d847a93cf5cc541731be3c12fd87', '31', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('14', 'e8a1d847a93cf5cc541731be3c12fd87', '32', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('15', 'e8a1d847a93cf5cc541731be3c12fd87', '33', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('16', 'e8a1d847a93cf5cc541731be3c12fd87', '34', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('17', 'e8a1d847a93cf5cc541731be3c12fd87', '40', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('18', 'e8a1d847a93cf5cc541731be3c12fd87', '41', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('19', 'e8a1d847a93cf5cc541731be3c12fd87', '42', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('20', 'e8a1d847a93cf5cc541731be3c12fd87', '43', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('21', 'e8a1d847a93cf5cc541731be3c12fd87', '44', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_role_menus` VALUES ('22', 'e8a1d847a93cf5cc541731be3c12fd87', '45', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f', '2021-05-14 14:11:08', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for sys_user_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menus`;
CREATE TABLE `sys_user_menus` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `menu_id` varchar(64) NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户与权限附加表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '唯一id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_id` varchar(64) NOT NULL COMMENT 'uid',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求ip地址',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'ip所属地',
  `url` varchar(255) NOT NULL COMMENT '请求url',
  `type` varchar(32) DEFAULT NULL COMMENT '请求方式',
  `class_path` varchar(255) NOT NULL COMMENT '请求类路径',
  `method` varchar(32) NOT NULL COMMENT '请求方法名',
  `params` longtext COMMENT '请求参数',
  `operation` varchar(32) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `spend_time` int(11) NOT NULL COMMENT '方法请求花费的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log` (
  `id` varchar(64) NOT NULL COMMENT '唯一id',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求ip地址',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_id` varchar(64) NOT NULL COMMENT '管理员uid',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'ip所属地',
  `method` varchar(32) NOT NULL COMMENT '请求方法名',
  `url` varchar(255) NOT NULL COMMENT '请求url',
  `operation` varchar(32) NOT NULL COMMENT '描述',
  `params` longtext DEFAULT NULL COMMENT '请求参数',
  `exceptionJson` mediumtext NOT NULL COMMENT '异常对象json格式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常日志表';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

INSERT INTO `sys_dict` VALUES ('0c3e78bb4aede4d70837cb70e6a79fc5', 'blog_publish_status', '博客是否发布', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict` VALUES ('4412b395ac0051a83b5e3657b77c8081', 'blog_comment_disable', '是否开启评论', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict` VALUES ('9211a2b6c48cdca1543cfec076f184b7', 'blog_original_status', '博客是否原创', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict` VALUES ('9723b0724dc12dbd9462f65cb058bbc1', 'user_status', '用户状态', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `dict_id` varchar(64) DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `status` int(1) DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典详情';


INSERT INTO `sys_dict_detail` VALUES ('05ae2a61717d9760b114f86b1678a13f', '9723b0724dc12dbd9462f65cb058bbc1', '启用', '1', 1, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('51798e8f7560bf6814d92a4bf980c5b8', '0c3e78bb4aede4d70837cb70e6a79fc5', '下架', '0', 1, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('6d4cb501797bc49d61de8bf5a5f3f3f3', '0c3e78bb4aede4d70837cb70e6a79fc5', '发布', '1', 0, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('7938623fd8b7281626e6dba344268d7a', '9211a2b6c48cdca1543cfec076f184b7', '转载', '0', 1, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('84b00f9e6b4ac20946d756a86d5d44cf', '9211a2b6c48cdca1543cfec076f184b7', '原创', '1', 0, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('8924cd3dca10a5eaa7e10fe28c3d0bd2', '4412b395ac0051a83b5e3657b77c8081', '开启', '1', 0, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('c69a2cd2c714ba6ba929fbd6f3f6f62e', '9723b0724dc12dbd9462f65cb058bbc1', '禁用', '0', 2, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_dict_detail` VALUES ('d2148e404896e914a3fc8eee224f416b', '4412b395ac0051a83b5e3657b77c8081', '关闭', '0', 1, 1, '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-16 15:49:30', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for config_email
-- ----------------------------
DROP TABLE IF EXISTS `email_content`;
CREATE TABLE `email_content` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) NOT NULL COMMENT '发件人',
  `subject` varchar(255) DEFAULT NULL COMMENT '邮件主题',
  `content` varchar(255) DEFAULT NULL COMMENT '邮件内容',
  `to_user` varchar(255) NOT NULL COMMENT '收件人',
  `create_time` datetime NOT NULL COMMENT '发送时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮箱发送内容';


-- ----------------------------
-- Table structure for config_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `local_storage` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) NOT NULL COMMENT '路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型 IMAGE-1,TXT-2,MUSIC-3,VIDEO-4,OTHER-5',
  `size` varchar(100) NOT NULL COMMENT '大小',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='本地存储';

-- ----------------------------
-- Table structure for qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_content`;
CREATE TABLE `qiniu_content` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `bucket` varchar(255) NOT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) NOT NULL COMMENT '文件名称',
  `size` varchar(255) NOT NULL COMMENT '文件大小',
  `file_key` varchar(255) NOT NULL COMMENT '文件key',
  `url` varchar(255) NOT NULL COMMENT '文件url',
  `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `type` varchar(255) NOT NULL COMMENT '文件类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云文件存储';


-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) NOT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) NOT NULL COMMENT 'cron 表达式',
  `is_pause` int(1) DEFAULT 0 COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) NOT NULL COMMENT '任务名称',
  `method_name` varchar(255) NOT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) NOT NULL COMMENT '备注',
  `person_in_charge` varchar(100) NOT NULL COMMENT '负责人',
  `email` varchar(100) NOT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` int(1) DEFAULT 1 COMMENT '任务失败后是否暂停',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 COMMENT='定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES (1, 'testTask', '0/5 * * * * ?', 1, '测试1', 'run1', 'test', '带参测试，多参使用json', 'admin', '1181881941@qq.com', NULL, 1, '2019-08-22 14:08:29', 'da6d966d37fcb819546064424a8bdd9f', '2020-05-24 13:58:33', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_quartz_job` VALUES (2, 'testTask', '0/5 * * * * ?', 1, '测试2', 'run', '', '不带参测试', 'admin', '1181881941@qq.com', 3, 1, '2019-09-26 16:44:39', 'da6d966d37fcb819546064424a8bdd9f', '2020-05-24 14:48:12', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_quartz_job` VALUES (3, 'testTask', '0/5 * * * * ?', 1, '测试3', 'run2', '', '测试3', 'admin', '1181881941@qq.com', NULL, 1, '2020-05-05 20:35:41', 'da6d966d37fcb819546064424a8bdd9f', '2020-05-05 20:36:07', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `sys_quartz_job` VALUES (4, 'testTask', '0 0 0 1/1 * ? ', 0, '同步文章到ES', 'run3', NULL, '同步文章到ES', 'admin', '1181881941@qq.com', NULL, 1, '2021-12-16 15:18:58', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) NOT NULL COMMENT 'Spring Bean名称',
  `create_time` datetime NOT NULL COMMENT '执行日期',
  `create_by` datetime NOT NULL COMMENT '执行人',
  `cron_expression` varchar(255) NOT NULL COMMENT 'cron表达式',
  `exception_detail` text DEFAULT NULL COMMENT '异常详情',
  `is_success` int(1) NOT NULL COMMENT '状态 失败0 成功1',
  `job_name` varchar(255) NOT NULL COMMENT '任务名称',
  `method_name` varchar(255) NOT NULL COMMENT '执行方法',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `time` bigint(20) NOT NULL COMMENT '耗时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

commit;