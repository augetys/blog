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

INSERT INTO `blog_article` VALUES (1, '部署nuxt', '部署nuxt到服务器', '#### **linux 部署nuxt**\n\n**1.安装node环境**\n\n```\nwget https://nodejs.org/dist/v12.18.1/node-v12.18.1-linux-x64.tar.gz\ntar -zxvf node-v12.18.1-linux-x64.tar.gz\n\nvim /etc/profile\n\nexport NODE_HOME=/usr/local/node\nexport PATH=$PATH:$NODE_HOME/bin\n刷新配置\nsource /etc/profile\n\nnode-v\n\n```\n\n**2.安装pm2**\n\n```\n npm i pm2 -g \n```\n\n**3.打包应用**\n\n```\nnpm run build\n\n需要上传\n.nuxt \nstatic\nnuxt.config.js\npackage.json\n\nnpm install\n```\n\n**4.pm2启动项目**\n\n```\npm2 start npm --name \"blog\" -- run start\n```\n\n**5.pm2配置文件启动，监听文件变化自动重启（暂未实现）**\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/30f2882726a9438aab7290460a133224', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 17:08:38', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_article` VALUES (2, '安装redis', 'linux下安装redis', '**Linux安装Redis**\n\n1.获取redis资源\n\n　　wget http://download.redis.io/releases/redis-4.0.8.tar.gz\n\n2.解压\n\n　　tar xzvf redis-4.0.8.tar.gz\n\n3.安装\n\n　　cd redis-4.0.8\n\n　　make\n\n　　cd src\n\n　　make install PREFIX=/usr/local/redis\n\n4.移动配置文件到安装目录下\n\n　　cd ../\n\n　　mkdir /usr/local/redis/etc\n\n　　mv redis.conf /usr/local/redis/etc\n\n5.配置redis为后台启动\n\n　　vi /usr/local/redis/etc/redis.conf //将daemonize no 改成daemonize yes\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/cf98905150704badb32553fc0727e213', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 16:59:18', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_article` VALUES (3, '安装mysql', 'linux下安装mysql', '**1.下载mysql**\n\nhttps://dev.mysql.com/downloads/mysql/5.6.html#downloads\n\n**2.解压安装包**\n\n```\n建议安装在usr/local下\ntar –zxvf mysql-5.7.26-linux-glibc2.12-x86_64.tar.gz \n```\n\n检查系统是否已经安装过\n\nrpm -qa | grep mysql\n\n**3.修改配置文件**\n\n在usr/local/mysql 创建data目录，创建my.cnf文件并放到etc目录下，有可能已经存在，存在的话修改为\n\n```\n[mysql]\n# 设置mysql客户端默认字符集\ndefault-character-set=utf8 \n[mysqld]\nskip-name-resolve\n#设置3306端口\nport = 3306 \n# 设置mysql的安装目录\nbasedir=/usr/local/mysql\n# 设置mysql数据库的数据的存放目录\ndatadir=/usr/local/mysql/data\n# 允许最大连接数\nmax_connections=200\n# 服务端使用的字符集默认为8比特编码的latin1字符集\ncharacter-set-server=utf8\n# 创建新表时将使用的默认存储引擎\ndefault-storage-engine=INNODB \nlower_case_table_names=1\nmax_allowed_packet=16M\n```\n\n**4.添加mysql用户组和用户及修改对应权限**\n\n```\ngroupadd mysql\n\nuseradd -g mysql mysql\n\n更改/usr/local/mysql 文件夹所有者属性和对应权限\n\nchown -R mysql:mysql /usr/local/mysql/\n\nchown -R mysql /usr/local/mysql/\n\nchmod -R 755 /usr/local/mysql/\n```\n\n**5.初始化mysql**\n\n```\n在bin目录下执行\nmysqld --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --initialize\n```\n\n![](C:\\Users\\lenovo\\Desktop\\images\\1912251336140013.png)\n\nbin/mysqld: error while loading shared libraries: libnuma.so.1: cannot open shared object file: No such file or directory\n\n如果报以上错误\n\nyum -y install numactl\n\n**6.启动mysql服务**\n\n```\n将mysql加入服务\ncp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql \n设置为开机自启\nchkconfig mysql on\n启动mysql\nservice mysql start\n重启\nservice mysql restart \n\n看到 Starting MySQL. SUCCESS! 代表启动成功。至此安装启动完成。\n```\n\n**出现-bash: mysql: command not found的方法**\n\n**ln -s /usr/local/mysql/bin/mysql /usr/bin**\n\n**7.修改密码和允许远程连接**\n\n```\nmysql -u root -p\n修改密码\nalter user user() identified by \"123456\"; \n设置允许远程连接\nupdate user set host=\'%\' where user = \'root\';\n刷新使配置生效\nflush privileges;\n```\n\n**注意，在真实的生产环境中，并不建议这么修改，因为安全风险太大。建议根据实际情况将root用户的host项修改为某个指定的ip地址，或仍然保持localhost。**\n\n**8.部分报错**\n\n```\n使用navicat客户端连接报错\nhost not allow to connect mysql\n\nshow tables;\nuse mysql;\nupdate user set host =\'%\' where user =\'root\';\n重启mysql\n```\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/217a1f6db02b49aaabf25d65102a4da9', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-17 16:58:23', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_article` VALUES (4, '安装elasticsearch', 'linux下安装elasticsearch', '单机版配置\n\nElasticsearch7.6.2下载地址: https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.2-linux-x86_64.tar.gz\n\n解压文件\n\n修改配置文件进入config目录下\n\n```\nvim jvm.options\n\n默认配置如下\n-Xms1g\n-Xmx1g\n内存占用太多了，我们调小一些：\n-Xms512m\n-Xmx512m\n```\n\n```\n# ======================== Elasticsearch Configuration =========================\n#\n# NOTE: Elasticsearch comes with reasonable defaults for most settings.\n#       Before you set out to tweak and tune the configuration, make sure you\n#       understand what are you trying to accomplish and the consequences.\n#\n# The primary way of configuring a node is via this file. This template lists\n# the most important settings you may want to configure for a production cluster.\n#\n# Please consult the documentation for further information on configuration options:\n# https://www.elastic.co/guide/en/elasticsearch/reference/index.html\n#\n# ---------------------------------- Cluster -----------------------------------\n#\n# Use a descriptive name for your cluster:\n#\n#cluster.name: my-application\n#\n# ------------------------------------ Node ------------------------------------\n#\n# Use a descriptive name for the node:\n#\nnode.name: node-1\n#\n# Add custom attributes to the node:\n#\n#node.attr.rack: r1\n#\n# ----------------------------------- Paths ------------------------------------\n#\n# Path to directory where to store the data (separate multiple locations by comma):\n#\npath.data: /usr/local/elasticsearch/data\n#\n# Path to log files:\n#\npath.logs: /usr/local/elasticsearch/logs\n#\n# ----------------------------------- Memory -----------------------------------\n#\n# Lock the memory on startup:\n#\n#bootstrap.memory_lock: true\n#\n# Make sure that the heap size is set to about half the memory available\n# on the system and that the owner of the process is allowed to use this\n# limit.\n#\n# Elasticsearch performs poorly when the system is swapping the memory.\n#\n# ---------------------------------- Network -----------------------------------\n#\n# Set the bind address to a specific IP (IPv4 or IPv6):\n#\nnetwork.host: 0.0.0.0\n#\n# Set a custom port for HTTP:\n#\n#http.port: 9200\n#\n# For more information, consult the network module documentation.\n#\n# --------------------------------- Discovery ----------------------------------\n#\n# Pass an initial list of hosts to perform discovery when this node is started:\n# The default list of hosts is [\"127.0.0.1\", \"[::1]\"]\n#\n#discovery.seed_hosts: [\"host1\", \"host2\"]\n#\n# Bootstrap the cluster using an initial set of master-eligible nodes:\n#\ncluster.initial_master_nodes: [\"node-1\"]\n#\n# For more information, consult the discovery and cluster formation module documentation.\n#\n# ---------------------------------- Gateway -----------------------------------\n#\n# Block initial recovery after a full cluster restart until N nodes are started:\n#\n#gateway.recover_after_nodes: 3\n#\n# For more information, consult the gateway module documentation.\n#\n# ---------------------------------- Various -----------------------------------\n#\n# Require explicit names when deleting indices:\n#\n#action.destructive_requires_name: true\nhttp.cors.enabled: true\nhttp.cors.allow-origin: \"*\"\n```\n\nes无法在root用户下启动\n\n```\nadduser es\npasswd lijinqwer\n给文件赋予权限\nchown -R es:es elasticsearch\n\nchmod 777 elasticsearch\n```\n\n./elasticsearch -d 后台启动\n\n启动异常排查\n\nhttps://blog.csdn.net/Hanmin_hm/article/details/106927413\n\n通过ip+端口即可外网访问\n\n**安装head插件**\n\n```\nwget -c  https://codeload.github.com/mobz/elasticsearch-head/zip/master\n```\n\n安装Node\n\n在elasticsearch-head下执行npm install\n\n安装 npm install -g grunt-cli\n\n修改head配置文件 Gruntfile.js\n\n```\nconnect: {\n			server: {\n				options: {\n					port: 9100,\n					base: \'.\',\n					keepalive: true,\n					host: \'*\' //加入这一段\n				}\n			}\n}\n```\n\n后台启动head服务\n\nnohup npm run start &\n\n安装Ik分词器\n\n**ik分词器压缩包不能放在plugins下，注意解压目录的用户要为es**\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/690878237f464213a24219e3a34a1b8a', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-17 17:00:56', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_article` VALUES (5, '安装nginx', 'linux下安装nginx', '**1.下载nginx**\n\nhttp://nginx.org/en/download.html\n\n**2.配置nginx安装所需的环境**\n\n```\n1.安装gcc\nyum install gcc-c++\n2.安装PCRE pcre-devel\nyum install -y pcre pcre-devel\n3.安装zlib\nyum install -y zlib zlib-devel\n4.安装Open SSL\nyum install -y openssl openssl-devel\n```\n\n**3.解压安装包**\n\n```\n建议安装在usr/local下\ntar –zxvf nginx-1.6.3.tar.gz\n```\n\n**4.配置**\n\n推荐使用默认配置，直接./configure就好\n\n**5.编译安装**\n\n```\nmake install\n```\n\n**6.启动**\n\n```\n进入sbin目录\n启动  log日志目录可能要自己新建\n./nginx\n重启\n./nginx -s reload\n关闭\n./nginx -s quit  或者 ./nginx -s stop\n查看进程\nps aux|grep nginx\n```\n\n**7.设置开机启动**\n\n```\nvim /etc/rc.local\n\n底部添加代码\n/usr/local/nginx/sbin/nginx\n```\n\n**配置SSL**\n\nhttps://freessl.cn/\n\n在此网站申请ssl证书\n\n申请后的证书放在nginx  ssl目录下\n\n```\nwww.choot.top_chain.crt\nwww.choot.top_key.key\n```\n\n安装ssl模块\n\n./configure --prefix=/usr/local/nginx --with-http_stub_status_module --with-http_ssl_module\n\nmake 安装\n\n/usr/local/nginx/sbin/nginx -V\n\n关闭nginx命令 : /usr/local/nginx/sbin/nginx -s stop\n\n/usr/local/nginx/sbin/nginx\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/3c5eac086ff24642a849e1f247f3919c', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 17:03:03', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_article` VALUES (6, '安装jdk', 'linux下安装jdk', '**openJDK安装**\n\nyum install -y java-1.8.0-openjdk-devel.x86_64\n\n你如果好奇这个自动安装把jdk安装到哪里去了，其实你可以在usr/lib/jvm下找到它们。\n\n**oracle jdk安装**\n\n账号密码\n\n1181881941@qq.com\n\nLijin926000123@\n\n历史版本下载链接\n\nhttps://www.oracle.com/java/technologies/downloads/archive/\n\nvim /etc/profile\n\n下载解压后配置环境变量\n\n```\nexport JAVA_HOME=/usr/local/jdk/1.7\nexport PATH=.:$JAVA_HOME/bin:$PATH \nexport CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar \n```\n\n刷新配置\n\nsource /etc/profile\n\n**jdk版本问题**\n\nspringboot 2.0不支持jdk1.7\n\ntomcat9不支持jdk1.7\n\n常用对应 springboot2.0-----jdk1.8------tomcat9\n\nspringboot1.5.0-------jdk1.7-----tomcat8\n', 1, '3bfb57eb21eeac3e3f49370e936bbcf7', 'https://photo.choot.top/9cfdf082efb94dc2b57842a71bed3641', 0, 1, NULL, NULL, 'note', 0, 1, '2021-12-17 17:09:25', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_article` VALUES (7, '循环生成checkbox无法选中', 'Vue + Element UI 循环生成checkbox无法选中', '```\n<el-checkbox  v-model=\"item.checked\" :key=\"item.id\"></el-checkbox>\n\n```\n\n一开始的写法，就是获取到数据，遍历生成checked属性\n\n```\n getUserAuditList({\n                   ...\n                }).then(res => {\n                    if (res.data.code == 0) {\n                        this.list = res.data.list;\n                        if (this.list.length > 0) {\n                            this.list.map((item, index) => {\n                                   item.checkd = false;\n                            });\n                        }\n                    }\n                }).catch(err => { })\n\n```\n\n打印出来发现确实存在checked属性，值为false，但是checkbox一直无法选中\n\n原来这是 vue 的深入响应式原理导致的，官方说法和解决方法：\n\nVue 不允许在已经创建的实例上动态添加新的根级响应式属性 (root-level reactive property)\n然而它可以使用 Vue.set(object, key, value) 方法将响应属性添加到嵌套的对象上\n\n```\n  this.$set(item, \'checked\', false);\n\n```\n', 1, '7193b408d6ef276b9906d0147064fdbf', 'https://photo.choot.top/afd3fa8c1271410094575f930b73d190', 0, 0, 'https://blog.csdn.net/yujin0213/article/details/88313154', 'yujin0213', 'think', 0, 1, '2021-12-31 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-31 10:19:32', 'da6d966d37fcb819546064424a8bdd9f');


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