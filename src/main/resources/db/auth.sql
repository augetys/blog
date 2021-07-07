-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
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
INSERT INTO `sys_user` VALUES ('da6d966d37fcb819546064424a8bdd9f', 'admin', '$2a$10$KJMluGRhQ2ZrOzXck9t5M.k93Y30h6UuX0obPDVbZZ9Zsk/LMX5zi', 'http://photo.choot.top/test', NULL, '小甜瓜', NULL, '2021-05-13 14:35:29', NULL, 1,0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('e8a1d847a93cf5cc541731be3c12fd87', '超级管理员','超级管理员', '2021-05-14 14:10:38', '2021-05-14 14:10:38');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `path` varchar(100) DEFAULT NULL COMMENT '菜单url',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `level` int(4) DEFAULT NULL COMMENT '菜单级别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户菜单表';

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES ('1', '0', '首页', '/home', 'home', 10, 0, '2021-05-18 15:06:06', '2021-05-18 15:06:08');
INSERT INTO `sys_menus` VALUES ('2', '0', '系统管理', '/sys', 'sys', 20, 0, '2021-05-17 08:00:00', '2021-06-16 09:33:06');
INSERT INTO `sys_menus` VALUES ('3', '2', '用户管理', '/sys/user', 'sys-user', 30, 1, '2021-05-17 11:49:04', '2021-05-17 11:49:04');
INSERT INTO `sys_menus` VALUES ('4', '2', '角色管理', '/sys/role', 'sys-role', 40, 1, '2021-05-17 11:49:04', '2021-05-17 11:49:04');
INSERT INTO `sys_menus` VALUES ('5', '2', '菜单管理', '/sys/menu', 'sys-menu', 50, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('6', '2', '代码生成', '/sys/code', 'sys-config', 60, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('7', '2', '字典管理', '/sys/dict', 'sys-config', 60, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('8', '2', '系统配置', '/sys/config', 'sys-config', 60, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('9', '0', '日志管理', '/log', 'sys-config', 60, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('10', '9', '操作日志', '/sys/log', 'sys-log', 70, 1, '2021-06-16 08:00:00', '2021-06-16 14:48:31');
INSERT INTO `sys_menus` VALUES ('11', '9', '异常日志', '/sys/exception', 'sys-log', 70, 1, '2021-06-16 08:00:00', '2021-06-16 14:48:31');
INSERT INTO `sys_menus` VALUES ('12', '0', '监控中心', '/monitor', 'monitor', 80, 0, '2021-06-16 13:05:30', '2021-06-16 13:05:30');
INSERT INTO `sys_menus` VALUES ('13', '12', '服务器监控', '/monitor/system', 'monitor', 90, 1, '2021-06-16 13:05:50', '2021-06-16 13:05:50');
INSERT INTO `sys_menus` VALUES ('14', '12', 'druid', '/monitor/druid', 'monitor-druid', 100, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('15', '0', '博客管理', '/blog', 'blog', 110, 0, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('16', '15', '文章管理', '/blog/article', 'blog-article', 120, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('17', '15', '分类管理', '/blog/category', 'blog-category', 130, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('18', '15', '标签管理', '/blog/tag', 'blog-tag', 140, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('19', '15', '评论管理', '/blog/comment', 'blog-comment', 150, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户与角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'da6d966d37fcb819546064424a8bdd9f', 'e8a1d847a93cf5cc541731be3c12fd87', '2021-05-14 14:11:08', NULL);

-- ----------------------------
-- Table structure for sys_role_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menus`;
CREATE TABLE `sys_role_menus` (
  `id` varchar(64) NOT NULL,
  `role_id` varchar(64) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(64) DEFAULT NULL COMMENT '菜单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色与权限关联表';

-- ----------------------------
-- Records of sys_role_menus
-- ----------------------------
INSERT INTO `sys_role_menus` VALUES ('1', 'e8a1d847a93cf5cc541731be3c12fd87', '1', '2021-05-17 14:42:43', NULL);
INSERT INTO `sys_role_menus` VALUES ('2', 'e8a1d847a93cf5cc541731be3c12fd87', '2', '2021-05-17 14:43:18', NULL);
INSERT INTO `sys_role_menus` VALUES ('3', 'e8a1d847a93cf5cc541731be3c12fd87', '3', '2021-05-17 14:44:13', NULL);
INSERT INTO `sys_role_menus` VALUES ('4', 'e8a1d847a93cf5cc541731be3c12fd87', '4', '2021-05-18 11:32:24', NULL);
INSERT INTO `sys_role_menus` VALUES ('5', 'e8a1d847a93cf5cc541731be3c12fd87', '5', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('6', 'e8a1d847a93cf5cc541731be3c12fd87', '6', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('7', 'e8a1d847a93cf5cc541731be3c12fd87', '7', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('8', 'e8a1d847a93cf5cc541731be3c12fd87', '8', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('9', 'e8a1d847a93cf5cc541731be3c12fd87', '9', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('10', 'e8a1d847a93cf5cc541731be3c12fd87', '10', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('11', 'e8a1d847a93cf5cc541731be3c12fd87', '11', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('12', 'e8a1d847a93cf5cc541731be3c12fd87', '12', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('13', 'e8a1d847a93cf5cc541731be3c12fd87', '13', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('14', 'e8a1d847a93cf5cc541731be3c12fd87', '14', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('15', 'e8a1d847a93cf5cc541731be3c12fd87', '15', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('16', 'e8a1d847a93cf5cc541731be3c12fd87', '16', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('17', 'e8a1d847a93cf5cc541731be3c12fd87', '17', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('18', 'e8a1d847a93cf5cc541731be3c12fd87', '18', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('19', 'e8a1d847a93cf5cc541731be3c12fd87', '19', '2021-05-18 15:06:32', NULL);

-- ----------------------------
-- Table structure for sys_user_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menus`;
CREATE TABLE `sys_user_menus` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户id',
  `menu_id` varchar(64) DEFAULT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户与权限附加表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(64) NOT NULL COMMENT '唯一id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `user_id` varchar(64) DEFAULT NULL COMMENT '管理员uid',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求ip地址',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'ip所属地',
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  `type` varchar(32) DEFAULT NULL COMMENT '请求方式',
  `class_path` varchar(255) DEFAULT NULL COMMENT '请求类路径',
  `method` varchar(32) DEFAULT NULL COMMENT '请求方法名',
  `params` longtext COMMENT '请求参数',
  `operation` varchar(32) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `spend_time` int(11) DEFAULT '0' COMMENT '方法请求花费的时间',
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
  `user_id` varchar(64) DEFAULT NULL COMMENT '管理员uid',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'ip所属地',
  `method` varchar(32) DEFAULT NULL COMMENT '请求方法名',
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  `operation` varchar(32) DEFAULT NULL COMMENT '描述',
  `params` longtext COMMENT '请求参数',
  `exceptionJson` mediumtext COMMENT '异常对象json格式',
  `exceptionMessage` mediumtext COMMENT '异常简单信息,等同于e.getMessage',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
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
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `status` int(1) DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `dict_id` bigint(11) DEFAULT NULL COMMENT '字典id',
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典详情';


-- ----------------------------
-- Table structure for config_email
-- ----------------------------
DROP TABLE IF EXISTS `config_email`;
CREATE TABLE `config_email` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) DEFAULT NULL COMMENT '发件人',
  `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) DEFAULT NULL COMMENT '密码',
  `port` varchar(255) DEFAULT NULL COMMENT '端口',
  `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮箱配置';

-- ----------------------------
-- Table structure for config_qiniu
-- ----------------------------
DROP TABLE IF EXISTS `config_qiniu`;
CREATE TABLE `config_qiniu` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `access_key` text DEFAULT NULL COMMENT 'accessKey',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) NOT NULL COMMENT '外链域名',
  `secret_key` text DEFAULT NULL COMMENT 'secretKey',
  `type` varchar(255) DEFAULT NULL COMMENT '空间类型',
  `zone` varchar(255) DEFAULT NULL COMMENT '机房',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云配置';

-- ----------------------------
-- Table structure for config_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `config_local_storage` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `size` varchar(100) DEFAULT NULL COMMENT '大小',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='本地存储';

-- ----------------------------
-- Table structure for qiniu_content
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_content`;
CREATE TABLE `qiniu_content` (
  `content_id` varchar(64) NOT NULL COMMENT 'ID',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云文件存储';
