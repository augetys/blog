-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` varchar(64) NOT NULL,
  `title` varchar(200) NOT NULL COMMENT '博客标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '博客简介',
  `admin_id` varchar(36) NOT NULL COMMENT '上传人id',
  `content` longtext NOT NULL COMMENT '博客内容',
  `is_publish` int(1) DEFAULT 1 COMMENT '是否发布：0：否，1：是',
  `tag_id` varchar(255) NOT NULL COMMENT '标签id',
  `cover` varchar(255) NOT NULL COMMENT '封面',
  `click_count` int(11) DEFAULT 0 COMMENT '博客点击数',
  `is_original` int(1) DEFAULT 1 COMMENT '是否原创（0:不是 1：是）',
  `articles_part` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `category_id` varchar(32) NOT NULL COMMENT '博客分类ID',
  `sort` int(11) DEFAULT 0 COMMENT '排序字段',
  `open_comment` tinyint(1) DEFAULT 1 COMMENT '是否开启评论(0:否 1:是)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章表';


-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '标签内容',
  `icon` varchar(255) NOT NULL COMMENT '标签图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客标签表';

INSERT INTO `blog_tag` VALUES ('3bfb57eb21eeac3e3f49370e936bbcf7', 'linux', 'icon-linux', '2021-07-29 10:23:36', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:23:36', 'da6d966d37fcb819546064424a8bdd9f', 3);
INSERT INTO `blog_tag` VALUES ('4338a8bc2afbb095169b03c291e0b8ff', '面试', 'icon-interview', '2021-07-29 10:27:20', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:27:20', 'da6d966d37fcb819546064424a8bdd9f', 5);
INSERT INTO `blog_tag` VALUES ('61bd0d34e03ae3038cb4168be8f67893', '股票', 'icon-banking', '2021-07-29 10:22:45', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:22:45', 'da6d966d37fcb819546064424a8bdd9f', 2);
INSERT INTO `blog_tag` VALUES ('62edf1de6f1bc800d3350da3dfd9e479', 'java', 'icon-java', '2021-07-02 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:21:12', 'da6d966d37fcb819546064424a8bdd9f', 1);
INSERT INTO `blog_tag` VALUES ('7193b408d6ef276b9906d0147064fdbf', 'vue', 'icon-vue', '2021-07-29 10:26:56', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:26:56', 'da6d966d37fcb819546064424a8bdd9f', 4);
INSERT INTO `blog_tag` VALUES ('e7cb6f8a0e571e32ea5d372c6f0493dd', 'Think', 'icon-xingqiu', '2021-07-29 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:30:07', 'da6d966d37fcb819546064424a8bdd9f', 7);
INSERT INTO `blog_tag` VALUES ('f62f8f1730f0d8241367258ec3d69c39', '旅行', 'icon-travel', '2021-07-29 10:28:04', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:28:04', 'da6d966d37fcb819546064424a8bdd9f', 6);
INSERT INTO `blog_tag` VALUES ('6117dd2e1dff133943fb41f999a2befe', 'leetcode', 'icon-code', '2021-07-29 10:28:04', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:28:04', 'da6d966d37fcb819546064424a8bdd9f', 8);


-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `content` varchar(255) NOT NULL COMMENT '分类简介',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客分类表';

INSERT INTO `blog_category` VALUES ('150a6f7616e6b97816fee7df0517c74f', '无妄海', '前后端开发技术', 1, '2021-11-23 12:30:54', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:54', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('20bdc01a6393225a7ff6546333a40786', '如梦令', '学习笔记', 3, '2021-11-23 12:30:05', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:05', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('7da852b03f1d20c774a38276fbc5ffee', '清平乐', '思考人生', 2, '2021-11-23 12:29:44', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:29:44', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('fceb570dfe261b40e27fc33da30c7ac6', '荏苒', '分享生活', 4, '2021-11-23 12:30:25', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:25', 'da6d966d37fcb819546064424a8bdd9f');


-- ----------------------------
-- Table structure for tb_looper
-- ----------------------------
DROP TABLE IF EXISTS `blog_loop`;
CREATE TABLE `blog_loop` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `title` varchar(128) DEFAULT NULL COMMENT '轮播图标题',
  `sort` int(11) NOT NULL COMMENT '顺序',
  `is_link` int(11) NOT NULL COMMENT '是否外链 0 否 1 是',
  `target_url` varchar(1024) NOT NULL COMMENT '目标URL',
  `image_url` varchar(2014) NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='轮播图';

INSERT INTO `blog_loop` VALUES ('4f5710d7bfa908c1f92bfd5f1f5e97b3', '呜呜呜呜我我', 5, 0, '/article/0cde4db22cac5a4893a660ea2b673c10', 'http://photo.choot.top/pexels-photo-1346295.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:00:10', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('58627203626381717ea3a47766a5fa35', '哈哈哈', 2, 0, '/article/a201923ba970cec68afe92709f0cd82a', 'http://photo.choot.top/pexels-photo-1472887.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:00:46', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('7382725c2dfa596b92c86efc2d356b11', '嫩恩恩额', 4, 0, '/article/be2530b7680b22eadb400906d89f4048', 'http://photo.choot.top/pexels-photo-1184000.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:14:23', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('7cfe21e91d8f12f8877f1cafd9491699', '恩恩额恩恩', 3, 1, 'https://www.baidu.com/', 'http://photo.choot.top/pexels-photo-3824139.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:14:38', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('8d972bef3ab84331d7779f7d0302341b', '呜呜呜', 1, 1, 'https://www.baidu.com/', 'http://photo.choot.top/pexels-photo-7103949.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:14:53', 'da6d966d37fcb819546064424a8bdd9f');

-- ----------------------------
-- Table structure for tb_looper
-- ----------------------------
DROP TABLE IF EXISTS `blog_nav`;
CREATE TABLE `blog_nav` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `name` varchar(128) NOT NULL COMMENT '导航名称',
  `icon` varchar(128) NOT NULL COMMENT '导航图标',
  `sort` int(11) NOT NULL COMMENT '导航排序',
  `path` varchar(128) NOT NULL COMMENT '跳转路由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='导航';

INSERT INTO `blog_nav` VALUES ('1', '醉花阴', 'icon-home', 1, '/', '2021-07-28 09:02:42', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:02:46', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('2', '无妄海', 'icon-code', 2, '/category/150a6f7616e6b97816fee7df0517c74f', '2021-07-28 09:03:03', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:06', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('3', '清平乐', 'icon-think', 3, '/category/7da852b03f1d20c774a38276fbc5ffee', '2021-07-28 09:03:19', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:21', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('4', '如梦令', 'icon-life', 4, '/category/20bdc01a6393225a7ff6546333a40786', '2021-07-28 09:03:37', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('5', '荏苒', 'icon-date', 5, '/category/fceb570dfe261b40e27fc33da30c7ac6', '2021-07-28 09:03:54', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:58', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('6', '琉璃镜', 'icon-photo', 6, '/', '2021-07-28 09:04:14', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:04:16', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('7', '藏经阁', 'icon-file', 7, '/', '2021-07-28 09:04:31', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:04:33', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('8', '山外小楼', 'icon-guestbook', 8, '/', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:00:39', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('9', '天命风流', 'icon-personInfo', 9, '/about', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:00:31', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('10', '海市蜃楼', 'icon-video', 10, '/video', '2021-07-28 09:05:18', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:05:20', 'da6d966d37fcb819546064424a8bdd9f');


-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` varchar(64) NOT NULL,
  `content` varchar(1000) DEFAULT NULL COMMENT '评论内容',
  `user_id` varchar(64) DEFAULT NULL COMMENT '评论用户',
  `nick_name` varchar(64) NOT NULL COMMENT '评论用户昵称',
  `user_avatar` varchar(1024) DEFAULT NULL COMMENT '评论用户头像',
  `mail` varchar(64) NOT NULL COMMENT '邮箱',
  `like_num` int(11) DEFAULT '0' COMMENT '点赞的数量',
  `article_id` varchar(64) NOT NULL COMMENT '博客文章id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章评论表';

-- ----------------------------
-- Table structure for blog_replay
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment_replay`;
CREATE TABLE `blog_comment_replay` (
  `id` varchar(64) NOT NULL,
  `comment_id` varchar(64) NOT NULL COMMENT '评论主表id',
  `from_id` varchar(64) DEFAULT NULL COMMENT '评论者id',
  `from_name` varchar(32) NOT NULL COMMENT '评论者名字',
  `from_avatar` varchar(1024) DEFAULT NULL COMMENT '评论者头像',
  `mail` varchar(64) NOT NULL COMMENT '邮箱',
  `to_id` varchar(64) NOT NULL COMMENT '被评论者id',
  `to_name` varchar(32) NOT NULL COMMENT '被评论者名字',
  `to_avatar` varchar(1024) DEFAULT NULL COMMENT '被评论者头像',
  `content` varchar(1000) DEFAULT NULL COMMENT '回复内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章评论回复表（子评论表）';

commit;