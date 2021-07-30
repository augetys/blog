-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` varchar(64) NOT NULL,
  `title` varchar(200) DEFAULT NULL COMMENT '博客标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '博客简介',
  `admin_id` varchar(36) DEFAULT NULL COMMENT '上传人id',
  `content` longtext DEFAULT NULL COMMENT '博客内容',
  `is_publish` int(1) DEFAULT '1' COMMENT '是否发布：0：否，1：是',
  `tag_id` varchar(255) DEFAULT NULL COMMENT '标签id',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `click_count` int(11) DEFAULT 0 COMMENT '博客点击数',
  `is_original` int(1) DEFAULT '1' COMMENT '是否原创（0:不是 1：是）',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `articles_part` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `article_category_id` varchar(32) DEFAULT NULL COMMENT '博客分类ID',
  `sort` int(11) DEFAULT 0 COMMENT '排序字段',
  `open_comment` tinyint(1) DEFAULT 1 COMMENT '是否开启评论(0:否 1:是)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章表';



-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '标签内容',
  `icon` varchar(255) DEFAULT NULL COMMENT '标签图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客标签表';

INSERT INTO `blog_tag` VALUES ('3bfb57eb21eeac3e3f49370e936bbcf7', 'linux', 'icon-linux', '2021-07-29 10:23:36', '2021-07-29 10:23:36', 3);
INSERT INTO `blog_tag` VALUES ('4338a8bc2afbb095169b03c291e0b8ff', '面试', 'icon-interview', '2021-07-29 10:27:20', '2021-07-29 10:27:20', 5);
INSERT INTO `blog_tag` VALUES ('61bd0d34e03ae3038cb4168be8f67893', '股票', 'icon-banking', '2021-07-29 10:22:45', '2021-07-29 10:22:45', 2);
INSERT INTO `blog_tag` VALUES ('62edf1de6f1bc800d3350da3dfd9e479', 'java', 'icon-java', '2021-07-02 08:00:00', '2021-07-29 10:21:12', 1);
INSERT INTO `blog_tag` VALUES ('7193b408d6ef276b9906d0147064fdbf', 'vue', 'icon-vue', '2021-07-29 10:26:56', '2021-07-29 10:26:56', 4);
INSERT INTO `blog_tag` VALUES ('e7cb6f8a0e571e32ea5d372c6f0493dd', 'Think', 'icon-xingqiu', '2021-07-29 08:00:00', '2021-07-29 10:30:07', 7);
INSERT INTO `blog_tag` VALUES ('f62f8f1730f0d8241367258ec3d69c39', '旅行', 'icon-travel', '2021-07-29 10:28:04', '2021-07-29 10:28:04', 6);


-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '分类内容',
  `content` varchar(255) DEFAULT NULL COMMENT '分类简介',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客分类表';

INSERT INTO `blog_category` VALUES ('49e72a0020525c53c22bdc11518b858a', '开发', '前后端开发专题', 3, '2021-07-02 08:00:00', '2021-07-29 10:20:27');
INSERT INTO `blog_category` VALUES ('61d6476665b0889e1c62358874ac25cf', '技术新闻', '发现世界的每一天', 2, '2021-07-02 08:00:00', '2021-07-02 13:56:23');
INSERT INTO `blog_category` VALUES ('79d9169cef8d7936e0983e4a75e405da', '思考人生', '思考人生', 5, '2021-07-29 10:19:48', '2021-07-29 10:19:48');
INSERT INTO `blog_category` VALUES ('bbd3ad1fa70b95a53e3a006acc3eeb98', '学习笔记', '学习笔记', 1, '2021-07-02 13:55:33', '2021-07-02 13:55:33');



-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` varchar(64) NOT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(64) DEFAULT NULL COMMENT '评论用户',
  `user_avatar` varchar(1024) DEFAULT NULL COMMENT '评论用户头像',
  `article_id` varchar(64) DEFAULT NULL COMMENT '博客文章id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章评论表';

-- ----------------------------
-- Table structure for blog_replay
-- ----------------------------
DROP TABLE IF EXISTS `blog_replay`;
CREATE TABLE `blog_replay` (
  `id` varchar(64) NOT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '回复内容',
  `user_id` varchar(64) DEFAULT NULL COMMENT '回复用户',
  `comment_id` varchar(64) DEFAULT NULL COMMENT '回复评论id',
  `article_id` varchar(64) DEFAULT NULL COMMENT '博客文章id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章回复表';

-- ----------------------------
-- Table structure for tb_looper
-- ----------------------------
DROP TABLE IF EXISTS `blog_loop`;
CREATE TABLE `blog_loop` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `title` varchar(128) NOT NULL COMMENT '轮播图标题',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `target_url` varchar(1024) DEFAULT NULL COMMENT '目标URL',
  `image_url` varchar(2014) NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='轮播图';

INSERT INTO `blog_loop` VALUES ('4f5710d7bfa908c1f92bfd5f1f5e97b3', '呜呜呜呜我我', 5, 'https://www.baidu.com', 'http://photo.choot.top/vape-relx.webp', '2021-07-28 14:38:43', '2021-07-28 14:38:43');
INSERT INTO `blog_loop` VALUES ('58627203626381717ea3a47766a5fa35', '哈哈哈', 2, 'https://www.baidu.com', 'http://photo.choot.top/2020-08-14-sea-1.webp', '2021-07-28 08:00:00', '2021-07-28 14:37:46');
INSERT INTO `blog_loop` VALUES ('7382725c2dfa596b92c86efc2d356b11', '嫩恩恩额', 4, 'https://www.baidu.com', 'http://photo.choot.top/03-15-code-review.webp', '2021-07-28 08:00:00', '2021-07-28 14:37:58');
INSERT INTO `blog_loop` VALUES ('7cfe21e91d8f12f8877f1cafd9491699', '恩恩额恩恩', 3, 'https://www.baidu.com', 'http://photo.choot.top/2021-0526-hope.webp', '2021-07-28 08:00:00', '2021-07-28 14:38:07');
INSERT INTO `blog_loop` VALUES ('8d972bef3ab84331d7779f7d0302341b', '呜呜呜', 1, 'https://www.baidu.com', 'http://photo.choot.top/fuckoff.png', '2021-07-28 08:00:00', '2021-07-28 14:38:19');

-- ----------------------------
-- Table structure for tb_looper
-- ----------------------------
DROP TABLE IF EXISTS `blog_nav`;
CREATE TABLE `blog_nav` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `name` varchar(128) NOT NULL COMMENT '导航名称',
  `icon` varchar(128) NOT NULL COMMENT '导航图标',
  `sort` int(11) DEFAULT 1 COMMENT '导航排序',
  `path` varchar(128) NOT NULL COMMENT '跳转路由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='导航';

INSERT INTO `blog_nav` VALUES ('1', '醉花阴', 'icon-home', 1, '/', '2021-07-28 09:02:42', '2021-07-28 09:02:46');
INSERT INTO `blog_nav` VALUES ('10', '海市蜃楼', 'icon-video', 10, '/', '2021-07-28 09:05:18', '2021-07-28 09:05:20');
INSERT INTO `blog_nav` VALUES ('2', '无妄海', 'icon-code', 2, '/', '2021-07-28 09:03:03', '2021-07-28 09:03:06');
INSERT INTO `blog_nav` VALUES ('3', '清平乐', 'icon-think', 3, '/', '2021-07-28 09:03:19', '2021-07-28 09:03:21');
INSERT INTO `blog_nav` VALUES ('4', '如梦令', 'icon-life', 4, '/', '2021-07-28 09:03:37', '2021-07-28 09:03:40');
INSERT INTO `blog_nav` VALUES ('5', '荏苒', 'icon-date', 5, '/', '2021-07-28 09:03:54', '2021-07-28 09:03:58');
INSERT INTO `blog_nav` VALUES ('6', '琉璃镜', 'icon-photo', 6, '/', '2021-07-28 09:04:14', '2021-07-28 09:04:16');
INSERT INTO `blog_nav` VALUES ('7', '藏经阁', 'icon-file', 7, '/', '2021-07-28 09:04:31', '2021-07-28 09:04:33');
INSERT INTO `blog_nav` VALUES ('8', '山外小楼', 'icon-guestbook', 8, '/', '2021-07-28 08:00:00', '2021-07-29 10:00:39');
INSERT INTO `blog_nav` VALUES ('9', '天命风流', 'icon-personInfo', 9, '/', '2021-07-28 08:00:00', '2021-07-29 10:00:31');

commit;