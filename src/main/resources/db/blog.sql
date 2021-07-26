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

INSERT INTO `blog_tag` VALUES ('187f8729ec18cf905731e310c4e72db3', 'Java', 'icon-search', '2021-07-02 13:36:46', '2021-07-02 13:36:46', 1);
INSERT INTO `blog_tag` VALUES ('20de3df42eeaaef1e5cc95d3de28c6c5', 'Python', 'icon-search', '2021-07-02 13:40:58', '2021-07-02 13:40:58', 17);
INSERT INTO `blog_tag` VALUES ('41c92c174cb4919ee9e308909dd50930', 'Linux', 'icon-search', '2021-07-02 13:36:55', '2021-07-02 13:36:55', 2);
INSERT INTO `blog_tag` VALUES ('468b56ac826c651c1a371755713466ce', '面试', 'icon-search', '2021-07-02 13:37:13', '2021-07-02 13:37:13', 4);
INSERT INTO `blog_tag` VALUES ('57c6c0742164d8610045412b6ee4bbe0', '大数据', 'icon-search', '2021-07-02 13:39:10', '2021-07-02 13:39:10', 13);
INSERT INTO `blog_tag` VALUES ('59a8f4d900a557d4beff96c2489541f1', 'Redis', 'icon-search', '2021-07-02 13:38:30', '2021-07-02 13:38:30', 11);
INSERT INTO `blog_tag` VALUES ('62edf1de6f1bc800d3350da3dfd9e479', 'Nginx', 'icon-search', '2021-07-02 13:40:48', '2021-07-02 13:40:48', 16);
INSERT INTO `blog_tag` VALUES ('6cbdcb2e57728d35c0e04935291d3f83', 'Spring Cloud', 'icon-search', '2021-07-02 13:37:32', '2021-07-02 13:37:32', 6);
INSERT INTO `blog_tag` VALUES ('7f5e239f57b8331cd82fc3d9cbad10ce', '学习笔记', 'icon-search', '2021-07-02 13:37:05', '2021-07-02 13:37:05', 3);
INSERT INTO `blog_tag` VALUES ('8b794439c07b4c81d371c3d700793a91', 'Docker', 'icon-search', '2021-07-02 13:37:53', '2021-07-02 13:37:53', 8);
INSERT INTO `blog_tag` VALUES ('9e68d831c5c2166ee0fd47871adfb700', 'RabbitMQ', 'icon-search', '2021-07-02 13:38:04', '2021-07-02 13:38:04', 10);
INSERT INTO `blog_tag` VALUES ('a30e4e9c6be6f02f8f572459cb584866', '前端开发', 'icon-search', '2021-07-02 13:37:41', '2021-07-02 13:37:41', 7);
INSERT INTO `blog_tag` VALUES ('c0926c417d88c8f02dc0e172bf5a3042', '校园生活', 'icon-search', '2021-07-02 13:37:24', '2021-07-02 13:37:24', 5);
INSERT INTO `blog_tag` VALUES ('d6a8731ca2463914879efed545a9c932', 'Spring Boot', 'icon-search', '2021-07-02 13:38:41', '2021-07-02 13:38:41', 12);
INSERT INTO `blog_tag` VALUES ('ee9a96eeb14a0f3e57e0bb440274790d', 'Vue', 'icon-search', '2021-07-02 13:40:39', '2021-07-02 13:40:39', 15);

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

INSERT INTO `blog_category` VALUES ('49e72a0020525c53c22bdc11518b858a', '后端开发', '后端开发专题', 3, '2021-07-02 08:00:00', '2021-07-02 13:56:31');
INSERT INTO `blog_category` VALUES ('61d6476665b0889e1c62358874ac25cf', '技术新闻', '发现世界的每一天', 2, '2021-07-02 08:00:00', '2021-07-02 13:56:23');
INSERT INTO `blog_category` VALUES ('645415d47b4c66a29ba1cc5b07e758c3', '前端开发', '前端开发专题', 4, '2021-07-02 08:00:00', '2021-07-02 13:56:37');
INSERT INTO `blog_category` VALUES ('bbd3ad1fa70b95a53e3a006acc3eeb98', '学习笔记', '学习笔记', 1, '2021-07-02 13:55:33', '2021-07-02 13:55:33');
INSERT INTO `blog_category` VALUES ('c7d88a558c05e2099b1430b0f77c69c3', '面试', '面试专题', 5, '2021-07-02 13:56:54', '2021-07-02 13:56:54');



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
DROP TABLE IF EXISTS `blog_looper`;
CREATE TABLE `blog_looper` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `title` varchar(128) NOT NULL COMMENT '轮播图标题',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int(11) NOT NULL COMMENT '状态：0表示不可用，1表示正常',
  `target_url` varchar(1024) DEFAULT NULL COMMENT '目标URL',
  `image_url` varchar(2014) NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='轮播图';

commit;