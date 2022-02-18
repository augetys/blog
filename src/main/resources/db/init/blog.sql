-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(200) NOT NULL COMMENT '博客标题',
  `summary` varchar(200) DEFAULT NULL COMMENT '博客简介',
  `content` longtext NOT NULL COMMENT '博客内容',
  `is_publish` int(1) DEFAULT 1 COMMENT '是否发布：0：否，1：是',
  `tag_id` varchar(255) NOT NULL COMMENT '标签id',
  `cover` varchar(255) NOT NULL COMMENT '封面',
  `click_count` int(11) DEFAULT 0 COMMENT '博客点击数',
  `is_original` int(1) DEFAULT 1 COMMENT '是否原创（0:不是 1：是）',
  `article_part` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `article_author` varchar(255) DEFAULT NULL COMMENT '文章出处',
  `category_id` varchar(32) NOT NULL COMMENT '博客分类ID',
  `sort` int(11) DEFAULT 0 COMMENT '排序字段',
  `open_comment` tinyint(1) DEFAULT 1 COMMENT '是否开启评论(0:否 1:是)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 COMMENT='博客文章表';

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL COMMENT '标签内容',
  `icon` varchar(255) DEFAULT NULL COMMENT '标签图标',
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
  `icon` varchar(255) DEFAULT NULL COMMENT '分类icon',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客分类表';

INSERT INTO `blog_category` VALUES ('code', '无妄海', '浩瀚书海', 'icon-code', 1, '2021-11-23 12:30:54', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:54', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('note', '如梦令', '思考人生', 'icon-note', 3, '2021-11-23 12:30:05', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:05', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('think', '清平乐', '学习笔记', 'icon-think', 2, '2021-11-23 12:29:44', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:29:44', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_category` VALUES ('date', '荏苒', '光阴似箭', 'icon-date', 4, '2021-11-23 12:30:25', 'da6d966d37fcb819546064424a8bdd9f', '2021-11-23 12:30:25', 'da6d966d37fcb819546064424a8bdd9f');


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

INSERT INTO `blog_loop` VALUES ('4f5710d7bfa908c1f92bfd5f1f5e97b3', '啦啦啦', 5, 0, '/article/1', 'http://photo.choot.top/pexels-photo-1346295.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:00:10', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('58627203626381717ea3a47766a5fa35', '哈哈哈', 2, 0, '/article/2', 'http://photo.choot.top/pexels-photo-1472887.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:00:46', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('7382725c2dfa596b92c86efc2d356b11', '滴滴滴', 4, 0, '/article/3', 'http://photo.choot.top/pexels-photo-1184000.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:14:23', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_loop` VALUES ('7cfe21e91d8f12f8877f1cafd9491699', '哒哒哒', 3, 1, 'https://www.baidu.com/', 'http://photo.choot.top/pexels-photo-3824139.jpeg', '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-08-06 15:14:38', 'da6d966d37fcb819546064424a8bdd9f');
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
  `target` varchar(128) NOT NULL COMMENT '是否打开新窗口',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT='导航';

INSERT INTO `blog_nav` VALUES ('1', '醉花阴', 'icon-home', 1, '/', '', '2021-07-28 09:02:42', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:02:46', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('2', '无妄海', 'icon-code', 2, '/category/code', '',  '2021-07-28 09:03:03', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:06', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('3', '清平乐', 'icon-think', 3, '/category/think', '',  '2021-07-28 09:03:19', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:21', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('4', '如梦令', 'icon-note', 4, '/category/note', '',  '2021-07-28 09:03:37', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('5', '荏苒', 'icon-date', 5, '/category/date', '',  '2021-07-28 09:03:54', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:03:58', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('8', '山外小楼', 'icon-guestbook', 6, '/soul', '',  '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:00:39', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('6', '琉璃镜', 'icon-photo', 7, '/photo', '_blank',  '2021-07-28 09:04:14', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:04:16', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('7', '藏经阁', 'icon-file', 8, '/navigation', '_blank',  '2021-07-28 09:04:31', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:04:33', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('9', '天命风流', 'icon-personInfo', 9, '/about', '',  '2021-07-28 08:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-29 10:00:31', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_nav` VALUES ('10', '海市蜃楼', 'icon-video', 10, '/video', '',  '2021-07-28 09:05:18', 'da6d966d37fcb819546064424a8bdd9f', '2021-07-28 09:05:20', 'da6d966d37fcb819546064424a8bdd9f');


-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` varchar(64) NOT NULL,
  `content` varchar(1000) NOT NULL COMMENT '评论内容',
  `user_id` varchar(64) DEFAULT NULL COMMENT '评论用户',
  `nick_name` varchar(64) NOT NULL COMMENT '评论用户昵称',
  `user_avatar` varchar(1024) DEFAULT NULL COMMENT '评论用户头像',
  `mail` varchar(64) NOT NULL COMMENT '邮箱',
  `like_num` int(11) DEFAULT '0' COMMENT '点赞的数量',
  `article_id` varchar(64) NOT NULL COMMENT '博客文章id',
  `create_time` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章评论表';

INSERT INTO `blog_comment` VALUES ('1a30c3d4d8762f244f862a355f0714eb', '收到', 'b83a8f1e63a443e4aaec6a5c68048270', '胡一豪', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 0, '1', '2021-12-29 16:03:03');
INSERT INTO `blog_comment` VALUES ('8d46f8680faa83d083014bdee8dd2136', '嘻嘻嘻', '5cda16a15879430f83a6e96bd51236e8', '李锦', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 0, '1', '2021-12-29 16:04:02');


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
  `content` varchar(1000) NOT NULL COMMENT '回复内容',
  `create_time` datetime NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客文章评论回复表（子评论表）';

INSERT INTO `blog_comment_replay` VALUES ('03394e944d0801478538c820157df9d4', '1a30c3d4d8762f244f862a355f0714eb', 'ec4fa5cfbf524924b74f5c35231e4824', '李强', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'b83a8f1e63a443e4aaec6a5c68048270', '胡一豪', NULL, '收到', '2021-12-29 16:03:19');
INSERT INTO `blog_comment_replay` VALUES ('a1f641cc9ddcaefd0e9929ea9997e84b', '1a30c3d4d8762f244f862a355f0714eb', 'f18c50771ff446d99ec072f1bb51196f', '冯正辉', 'https://photo.choot.top/icon.jpg', '1181881941@qq.com', 'ec4fa5cfbf524924b74f5c35231e4824', '李强', NULL, '收到', '2021-12-29 16:03:46');

-- ----------------------------
-- Table structure for blog_soul
-- ----------------------------
DROP TABLE IF EXISTS `blog_soul`;
CREATE TABLE `blog_soul` (
  `id` varchar(64) NOT NULL,
  `content` varchar(1000) NOT NULL COMMENT '鸡汤内容',
  `source` varchar(100) NOT NULL COMMENT '出处',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='心灵鸡汤表';

INSERT INTO `blog_soul` VALUES ('0f76701492f76fdfb65be5b4c61a3ae9', '树深时见鹿，溪午不闻钟。', '李白', '2021-12-28 16:21:14', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('19a1c44297d44a12ac9a453268ed8564', '月光还是少年的月光，九州一色还是李白的霜。', '余光中《独白》', '2021-12-28 16:22:29', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('1d2d0e7f69daabf910e1f6b2d730d5c4', '宠辱不惊，闲看庭前花开花落，去留无意，漫随天外云卷云舒。', '《菜根谭》', '2021-12-28 16:22:01', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('4baae67fab06cc5786d3891acc2208ce', '人散后， 一钩淡月天如水。', '谢逸《千秋岁.咏夏景》', '2021-12-28 16:17:46', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('5c001354aec76fbe64994bcc7c388647', '人之所以伤心，是因为看得不够远', '林语堂', '2021-12-28 16:23:09', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('67430565a8caeedea7d2f44f6fadb878', '我们好像在池塘的水底，从一个月亮走向另一个月亮。', '王小波', '2021-12-28 16:17:32', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('7c1091f744f225906682277642580502', '心之所向 素履以往 生如逆旅 一苇以航', '七堇年《尘曲》', '2021-12-28 16:21:33', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('9551f9cdb66c1738b722f1e22d1010f1', '我本是槐花院落闲散的人，满襟酒气。小池塘边跌坐看鱼，眉挑烟火过一生。', '沈离淮', '2021-12-28 16:21:48', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('a1e5303c06bfbc5dd54d52d23b922dba', '所求皆如愿，所行化坦途，多喜乐，长安宁。', '网络', '2021-12-28 16:22:16', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('a2622ce672d52e65e43b1a359613cd0e', '一定要爱着点什么，它让我们变得坚韧、宽容、充盈。业余的，爱着。', '汪曾祺《生活是很好玩》', '2021-12-28 16:23:36', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('d115dd790d21083168324da2bbd6ae00', '且将新火试新茶，诗酒趁年华。', '苏轼《望江南·超然台上作》', '2021-12-28 16:21:05', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_soul` VALUES ('f4f0f971da4416601639f5d9b95eb4a9', '一星陨落，黯淡不了星空灿烂；一花凋零，荒芜不了整个春天。', '巴尔扎克', '2021-12-28 16:23:22', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);


-- ----------------------------
-- Table structure for blog_photo
-- ----------------------------
DROP TABLE IF EXISTS `blog_photo`;
CREATE TABLE `blog_photo` (
  `id` varchar(64) NOT NULL,
  `url` varchar(1000) NOT NULL COMMENT '图片地址',
  `tip` varchar(100) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='相册表';

INSERT INTO `blog_photo` VALUES ('062f43b4abd7ae71b2a4b54284ec9ba6', 'https://photo.choot.top/b1b5c7e18b2a4874974e41fa9d45f28f.jpg', '宜昌旅游', '2022-02-13 23:38:28', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('0ddd013800c73507a033295e1b102627', 'https://photo.choot.top/bc2bd33669fa4905876de8ecad1372a2.JPG', '登岳阳楼', '2022-02-13 23:51:51', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('535a98c7abe66526d92d1bc7af71189a', 'https://photo.choot.top/cac9471f980e4f32827062d5c0e91807.JPEG', '五一游湖', '2022-02-13 23:51:02', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('7bae9d0c87068da8f2ff13c166b19489', 'https://photo.choot.top/3eac273f28ce4e17a4a9ca8b9d34929f.jpg', '苍山洱海', '2022-02-13 23:51:15', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('b3cf5c6cbbe84abbdb71a8abff57295b', 'https://photo.choot.top/8d299b8bb16246cca5ff8e3439d713ce.JPG', '合照', '2022-02-14 00:50:53', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('ed6cbcce0856c5a4d92b784c789f4f95', 'https://photo.choot.top/c13de324afdb4abc89fd9ea864068ac1.jpg', '升学宴', '2022-02-13 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2022-02-13 23:51:21', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_photo` VALUES ('f0b9013e3e225c0ec0b2baeb74e3f9ae', 'https://photo.choot.top/48905cbf5c494d47a73f1f0120955997.JPG', '看书', '2022-02-13 23:37:44', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('fe83d793b4d3cfbc36aefaecafefdc92', 'https://photo.choot.top/1b841dcda72845d093b8cdec1ec52f55.JPG', '婚礼自拍', '2022-02-13 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2022-02-13 23:52:07', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_photo` VALUES ('fe93aeeba7ff983cb08cf3b6eade8acd', 'https://photo.choot.top/7a610d2d5e0d4c3587f5171b8edbc83b.JPG', '三亚自拍', '2022-02-13 23:51:36', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);

-- ----------------------------
-- Table structure for blog_website
-- ----------------------------
DROP TABLE IF EXISTS `blog_website`;
CREATE TABLE `blog_website` (
  `id` varchar(64) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '导航名称',
  `url` varchar(1000) NOT NULL COMMENT '跳转地址',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序',
  `category` int(1) NOT NULL COMMENT '类别（详情见字典表）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网址导航表';

commit;