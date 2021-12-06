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

INSERT INTO `sys_menus` VALUES ('2', '0', '博客管理', '/blog', 'blog', 20, 0, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('3', '2', '文章管理', '/blog/article', 'blog-article', 30, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('4', '2', '分类管理', '/blog/category', 'blog-category', 40, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('5', '2', '标签管理', '/blog/tag', 'blog-tag', 50, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('7', '2', '导航栏管理', '/blog/nav', 'blog-nav', 31, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('8', '2', '轮播图管理', '/blog/loop', 'blog-loop', 32, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');

INSERT INTO `sys_menus` VALUES ('10', '0', '系统工具', '/tool', 'tool', 70, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('11', '10', '邮件', '/tool/email', 'tool-email', 60, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');

INSERT INTO `sys_menus` VALUES ('20', '0', '资源管理', '/resource', 'resource', 80, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('21', '20', '七牛云', '/resource/qiniu', 'resource-qiniu', 90, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('22', '20', '本地存储', '/resource/localStorage', 'resource-localStorage', 100, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');

INSERT INTO `sys_menus` VALUES ('30', '0', '监控中心', '/monitor', 'monitor', 110, 0, '2021-06-16 13:05:30', '2021-06-16 13:05:30');
INSERT INTO `sys_menus` VALUES ('31', '30', '服务器监控', '/monitor/system', 'monitor', 120, 1, '2021-06-16 13:05:50', '2021-06-16 13:05:50');
INSERT INTO `sys_menus` VALUES ('32', '30', 'druid', '/monitor/druid', 'monitor-druid', 130, 1, '2021-06-16 16:25:43', '2021-06-16 16:25:47');
INSERT INTO `sys_menus` VALUES ('33', '30', '操作日志', '/monitor/log', 'monitor-log', 140, 1, '2021-06-16 08:00:00', '2021-06-16 14:48:31');
INSERT INTO `sys_menus` VALUES ('34', '30', '异常日志', '/monitor/exception', 'monitor-exception', 150, 1, '2021-06-16 08:00:00', '2021-06-16 14:48:31');

INSERT INTO `sys_menus` VALUES ('40', '0', '系统管理', '/sys', 'sys', 160, 0, '2021-05-17 08:00:00', '2021-06-16 09:33:06');
INSERT INTO `sys_menus` VALUES ('41', '40', '用户管理', '/sys/user', 'sys-user', 170, 1, '2021-05-17 11:49:04', '2021-05-17 11:49:04');
INSERT INTO `sys_menus` VALUES ('42', '40', '角色管理', '/sys/role', 'sys-role', 180, 1, '2021-05-17 11:49:04', '2021-05-17 11:49:04');
INSERT INTO `sys_menus` VALUES ('43', '40', '菜单管理', '/sys/menu', 'sys-menu', 190, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('44', '40', '字典管理', '/sys/dict', 'sys-dict', 200, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');
INSERT INTO `sys_menus` VALUES ('45', '40', '任务调度', '/sys/task', 'sys-task', 210, 1, '2021-05-18 11:32:02', '2021-06-15 19:55:40');



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
INSERT INTO `sys_role_menus` VALUES ('24', 'e8a1d847a93cf5cc541731be3c12fd87', '7', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('25', 'e8a1d847a93cf5cc541731be3c12fd87', '8', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('7', 'e8a1d847a93cf5cc541731be3c12fd87', '10', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('8', 'e8a1d847a93cf5cc541731be3c12fd87', '11', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('9', 'e8a1d847a93cf5cc541731be3c12fd87', '20', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('10', 'e8a1d847a93cf5cc541731be3c12fd87', '21', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('11', 'e8a1d847a93cf5cc541731be3c12fd87', '22', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('12', 'e8a1d847a93cf5cc541731be3c12fd87', '30', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('13', 'e8a1d847a93cf5cc541731be3c12fd87', '31', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('14', 'e8a1d847a93cf5cc541731be3c12fd87', '32', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('15', 'e8a1d847a93cf5cc541731be3c12fd87', '33', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('16', 'e8a1d847a93cf5cc541731be3c12fd87', '34', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('17', 'e8a1d847a93cf5cc541731be3c12fd87', '40', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('18', 'e8a1d847a93cf5cc541731be3c12fd87', '41', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('19', 'e8a1d847a93cf5cc541731be3c12fd87', '42', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('20', 'e8a1d847a93cf5cc541731be3c12fd87', '43', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('21', 'e8a1d847a93cf5cc541731be3c12fd87', '44', '2021-05-18 15:06:32', NULL);
INSERT INTO `sys_role_menus` VALUES ('22', 'e8a1d847a93cf5cc541731be3c12fd87', '45', '2021-05-18 15:06:32', NULL);

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
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

INSERT INTO `sys_dict` VALUES ('9723b0724dc12dbd9462f65cb058bbc1', 'user_status', '用户状态', NULL, NULL, '2021-07-16 15:24:17', '2021-07-16 15:24:17');

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
  `is_default` int(1) DEFAULT 1 COMMENT '是否默认：0->是；1->否',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典详情';

INSERT INTO `sys_dict_detail` VALUES ('05ae2a61717d9760b114f86b1678a13f', '9723b0724dc12dbd9462f65cb058bbc1', '启用', '1', 1, 1, 1, NULL, NULL, '2021-07-16 15:40:49', '2021-07-16 15:40:49');
INSERT INTO `sys_dict_detail` VALUES ('c69a2cd2c714ba6ba929fbd6f3f6f62e', '9723b0724dc12dbd9462f65cb058bbc1', '禁用', '0', 2, 1, 1, NULL, NULL, '2021-07-16 15:49:30', '2021-07-16 15:49:30');

-- ----------------------------
-- Table structure for config_email
-- ----------------------------
DROP TABLE IF EXISTS `email_config`;
CREATE TABLE `email_config` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) DEFAULT NULL COMMENT '发件人',
  `host` varchar(255) DEFAULT NULL COMMENT '邮件服务器SMTP地址',
  `pass` varchar(255) DEFAULT NULL COMMENT '密码',
  `port` varchar(255) DEFAULT NULL COMMENT '端口',
  `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮箱配置';

INSERT INTO `email_config` VALUES ('1', '15549402651@163.com', 'smtp.163.com', 'YBZMMDFBEENJWSDX', '465', 'choot');


-- ----------------------------
-- Table structure for config_email
-- ----------------------------
DROP TABLE IF EXISTS `email_content`;
CREATE TABLE `email_content` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `from_user` varchar(255) DEFAULT NULL COMMENT '发件人',
  `user` varchar(255) DEFAULT NULL COMMENT '发件者用户名',
  `subject` varchar(255) DEFAULT NULL COMMENT '邮件主题',
  `content` varchar(255) DEFAULT NULL COMMENT '邮件内容',
  `to_user` varchar(255) DEFAULT NULL COMMENT '收件人',
  `create_time` varchar(255) DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮箱发送内容';

-- ----------------------------
-- Table structure for config_qiniu
-- ----------------------------
DROP TABLE IF EXISTS `qiniu_config`;
CREATE TABLE `qiniu_config` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `access_key` text DEFAULT NULL COMMENT 'accessKey',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `host` varchar(255) NOT NULL COMMENT '外链域名',
  `secret_key` text DEFAULT NULL COMMENT 'secretKey',
  `type` varchar(255) DEFAULT NULL COMMENT '空间类型 0私有 1公开',
  `zone` varchar(255) DEFAULT NULL COMMENT '机房区域',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云配置';

INSERT INTO `qiniu_config` VALUES ('1', '6riz6eaJfEtERl28wRX1pQfHgcn6X-WS69N1xgLx', 'hopelittle', 'https://photo.choot.top', 'LXJ0AfRwisSgLfvqDqLcENjgaTK_76CIEHeFrPT_', '1', '华南');
INSERT INTO `qiniu_config` VALUES ('2', '6riz6eaJfEtERl28wRX1pQfHgcn6X-WS69N1xgLx', 'hopefile', 'http://file.choot.top', 'LXJ0AfRwisSgLfvqDqLcENjgaTK_76CIEHeFrPT_', '1', '华南');
INSERT INTO `qiniu_config` VALUES ('3', '6riz6eaJfEtERl28wRX1pQfHgcn6X-WS69N1xgLx', 'hopevideo', 'http://video.choot.top', 'LXJ0AfRwisSgLfvqDqLcENjgaTK_76CIEHeFrPT_', '1', '华南');

-- ----------------------------
-- Table structure for config_local_storage
-- ----------------------------
DROP TABLE IF EXISTS `local_storage`;
CREATE TABLE `local_storage` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `real_name` varchar(255) DEFAULT NULL COMMENT '文件真实的名称',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `type` varchar(255) DEFAULT NULL COMMENT '类型 IMAGE-1,TXT-2,MUSIC-3,VIDEO-4,OTHER-5',
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
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `bucket` varchar(255) DEFAULT NULL COMMENT 'Bucket 识别符',
  `name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `size` varchar(255) DEFAULT NULL COMMENT '文件大小',
  `file_key` varchar(255) DEFAULT NULL COMMENT '文件key值',
  `type` varchar(255) DEFAULT NULL COMMENT '文件类型：私有或公开',
  `url` varchar(255) DEFAULT NULL COMMENT '文件url',
  `suffix` varchar(255) DEFAULT NULL COMMENT '文件后缀',
  `update_time` datetime DEFAULT NULL COMMENT '上传或同步的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='七牛云文件存储';

INSERT INTO `qiniu_content` VALUES ('0e71fffbdd98f40d00d952c4d99104aa', 'hopelittle', NULL, '44.18KB   ', '20210727173925757', '1', 'http://photo.choot.top/20210727173925757.jpg', 'jpg', '2021-07-27 17:39:26');
INSERT INTO `qiniu_content` VALUES ('1387b349b1754c9bc7b11fb6a1757c03', 'hopelittle', NULL, '82.70KB   ', '20210727142243304', '1', 'http://photo.choot.top/20210727142243304.jpg', 'jpg', '2021-07-27 14:22:44');
INSERT INTO `qiniu_content` VALUES ('1adb37b6c15136768f7dedc21b8caaaf', 'hopelittle', NULL, '42.08KB   ', '20210727172945177', '1', 'http://photo.choot.top/20210727172945177.jpg', 'jpg', '2021-07-27 17:29:46');
INSERT INTO `qiniu_content` VALUES ('1dc47d874c9ffa9d7bbfaaa5c06cd14e', 'hopelittle', NULL, '34.38KB   ', '20210728090058863', '1', 'http://photo.choot.top/20210728090058863.jpg', 'jpg', '2021-07-28 09:00:59');
INSERT INTO `qiniu_content` VALUES ('20a0b3242e2dc95c6113a0619d0d05ec', 'hopelittle', NULL, '88.98KB   ', '2021072714513319', '1', 'http://photo.choot.top/2021072714513319.jpg', 'jpg', '2021-07-27 14:51:33');
INSERT INTO `qiniu_content` VALUES ('3a8f3faee45181ea5b0b38495ae4540b', 'hopelittle', NULL, '51.21KB   ', '202107271639237', '1', 'http://photo.choot.top/202107271639237.jpg', 'jpg', '2021-07-27 16:39:23');
INSERT INTO `qiniu_content` VALUES ('3b92223c6f1b8279c9a41e184bee4f29', 'hopelittle', NULL, '82.70KB   ', '20210727173437581', '1', 'http://photo.choot.top/20210727173437581.jpg', 'jpg', '2021-07-27 17:34:38');
INSERT INTO `qiniu_content` VALUES ('433b33151dcb2a641689d37a53d48196', 'hopelittle', NULL, '82.70KB   ', '20210727142609381', '1', 'http://photo.choot.top/20210727142609381.jpg', 'jpg', '2021-07-27 14:26:10');
INSERT INTO `qiniu_content` VALUES ('49ff0b4aabfa88b7437adda6f18b752b', 'hopelittle', NULL, '46.37KB   ', '20210806122417757', '1', 'http://photo.choot.top/20210806122417757.jpg', 'jpg', '2021-08-06 12:24:18');
INSERT INTO `qiniu_content` VALUES ('5178b27138a82abd54c083662e03fe80', 'hopelittle', NULL, '5.69KB   ', '20210728183239522', '1', 'http://photo.choot.top/20210728183239522.jpg', 'jpg', '2021-07-28 18:32:40');
INSERT INTO `qiniu_content` VALUES ('5ecdd3cdcacd13da1eb8809df667b445', 'hopelittle', NULL, '88.98KB   ', '20210727173554575', '1', 'http://photo.choot.top/20210727173554575.jpg', 'jpg', '2021-07-27 17:35:55');
INSERT INTO `qiniu_content` VALUES ('661bb374e6fdfd2702753c40187bc9b2', 'hopelittle', NULL, '82.70KB   ', '20210727142252470', '1', 'http://photo.choot.top/20210727142252470.jpg', 'jpg', '2021-07-27 14:22:53');
INSERT INTO `qiniu_content` VALUES ('66a9e678ef69592da7a4f442b7421427', 'hopelittle', NULL, '82.70KB   ', '20210727142443131', '1', 'http://photo.choot.top/20210727142443131.jpg', 'jpg', '2021-07-27 14:24:43');
INSERT INTO `qiniu_content` VALUES ('6a1d49a6f18b6a596d0a740fd51f9507', 'hopelittle', NULL, '5.69KB   ', '2021072818362344', '1', 'http://photo.choot.top/2021072818362344.jpg', 'jpg', '2021-07-28 18:36:23');
INSERT INTO `qiniu_content` VALUES ('74df6099f4233e496381176cace88372', 'hopelittle', NULL, '46.37KB   ', '20210806122418257', '1', 'http://photo.choot.top/20210806122418257.jpg', 'jpg', '2021-08-06 12:24:19');
INSERT INTO `qiniu_content` VALUES ('75f42d803b565ed8591f67f294c866de', 'hopelittle', NULL, '46.11KB   ', '2021072809003761', '1', 'http://photo.choot.top/2021072809003761.jpg', 'jpg', '2021-07-28 09:00:37');
INSERT INTO `qiniu_content` VALUES ('8f51db13e207b94de90b5f200600b2c3', 'hopelittle', NULL, '5.69KB   ', '20210728181902268', '1', 'http://photo.choot.top/20210728181902268.jpg', 'jpg', '2021-07-28 18:19:03');
INSERT INTO `qiniu_content` VALUES ('a40dc32a253aa1ea34ed6fef447f7c3d', 'hopelittle', NULL, '43.05KB   ', '20210728085852388', '1', 'http://photo.choot.top/20210728085852388.jpg', 'jpg', '2021-07-28 08:58:53');
INSERT INTO `qiniu_content` VALUES ('bda86106441771b5e6742ab184f4dac0', 'hopelittle', NULL, '45.85KB   ', '20210729085839401', '1', 'http://photo.choot.top/20210729085839401.jpg', 'jpg', '2021-07-29 08:58:40');
INSERT INTO `qiniu_content` VALUES ('d3238355eba7c5bce219f02060275942', 'hopelittle', NULL, '19.11KB   ', '20210716140612848', '1', 'http://photo.choot.top/20210716140612848.jpg', 'jpg', '2021-07-16 14:06:13');
INSERT INTO `qiniu_content` VALUES ('de1f6d04d1a8dc40e75d134687150922', 'hopelittle', NULL, '82.70KB   ', '20210727142501560', '1', 'http://photo.choot.top/20210727142501560.jpg', 'jpg', '2021-07-27 14:25:02');
INSERT INTO `qiniu_content` VALUES ('e63e2d59de91b424d08e1f0521ca970a', 'hopelittle', NULL, '37.02KB   ', '20210728085253581', '1', 'http://photo.choot.top/20210728085253581.jpg', 'jpg', '2021-07-28 08:52:54');
INSERT INTO `qiniu_content` VALUES ('e6ad69928a1e28eb2caa37d0127736d2', 'hopelittle', NULL, '37.05KB   ', '20210729085708657', '1', 'http://photo.choot.top/20210729085708657.jpg', 'jpg', '2021-07-29 08:57:09');

-- ----------------------------
-- Table structure for sys_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_job`;
CREATE TABLE `sys_quartz_job` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` int(1) DEFAULT 0 COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `person_in_charge` varchar(100) DEFAULT NULL COMMENT '负责人',
  `email` varchar(100) DEFAULT NULL COMMENT '报警邮箱',
  `sub_task` varchar(100) DEFAULT NULL COMMENT '子任务ID',
  `pause_after_failure` int(1) DEFAULT 1 COMMENT '任务失败后是否暂停',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of sys_quartz_job
-- ----------------------------
INSERT INTO `sys_quartz_job` VALUES ('1', 'testTask', '0/5 * * * * ?', 1, '测试1', 'run1', 'test', '带参测试，多参使用json', 'admin', '1181881941@qq.com', NULL, 1, 'admin', 'admin', '2019-08-22 14:08:29', '2020-05-24 13:58:33');
INSERT INTO `sys_quartz_job` VALUES ('2', 'testTask', '0/5 * * * * ?', 1, '测试2', 'run', '', '不带参测试', 'admin', '1181881941@qq.com', '3', 1, 'admin', 'admin', '2019-09-26 16:44:39', '2020-05-24 14:48:12');
INSERT INTO `sys_quartz_job` VALUES ('3', 'testTask', '0/5 * * * * ?', 1, '测试3', 'run2', '', '测试3', 'admin', '1181881941@qq.com', NULL, 1, 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');
INSERT INTO `sys_quartz_job` VALUES ('4', 'test', '0/5 * * * * ?', 1, '测试4', 'run2', '', '测试3', 'admin', '1181881941@qq.com', NULL, 1, 'admin', 'admin', '2020-05-05 20:35:41', '2020-05-05 20:36:07');

-- ----------------------------
-- Table structure for sys_quartz_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_quartz_log`;
CREATE TABLE `sys_quartz_log` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `bean_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `exception_detail` text DEFAULT NULL,
  `is_success` int(1) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

commit;