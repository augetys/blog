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

INSERT INTO `blog_photo` VALUES ('18d7cc50b9c1dcc9190e960634fbc76b', 'https://photo.choot.top/9351328d3f6d4e889f42cc45b6183503', '合照', '2021-12-30 13:58:59', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('238039328572ec437edff5345778c825', 'https://photo.choot.top/3e871ae02b274a2a847bcfe5ff6415c7', 'Yoyo', '2021-12-29 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 11:51:25', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_photo` VALUES ('3c44aa786997cac49b876b8ea8d171e2', 'https://photo.choot.top/71fd344a3e17466e9296611e684662b1', '旅游', '2021-12-29 16:25:21', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('47da01565bd54c27fed66a7945316aca', 'https://photo.choot.top/d5f9b6a4cb144289b845fa414e0366eb', '登顶', '2021-12-29 16:26:20', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('6d5f55c8aa2c0218b0bc81b55b4aa557', 'https://photo.choot.top/6cb16943cc94443480a3b27da610282d', 'Yoyo', '2021-12-29 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 11:51:33', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_photo` VALUES ('6ea250ce22272ea0c18874e300566a88', 'https://photo.choot.top/887100eec5824b2799f8bf87651599d0', '撸狗', '2021-12-29 10:13:54', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('870264d3ea1e0dd204611cfd2c073c70', 'https://photo.choot.top/1f20bb894764428292297772c149b4c8', '回家', '2021-12-30 13:59:11', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('91b4d0eccbe96f142f82242a19569952', 'https://photo.choot.top/f6dfcd55710840478f7e623da88de940', '玉龙雪山', '2021-12-29 16:26:09', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('c16d404f89d575d496b82448173d3033', 'https://photo.choot.top/7a8c435d51bd4cf1b7111815af32341e', 'Yoyo', '2021-12-30 13:55:59', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('eb3d0e4577618b7fa6992fe6919655a6', 'https://photo.choot.top/5737e5eed5f64ffeb922e7e8ac3707a1', '生活', '2021-12-29 16:24:41', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_photo` VALUES ('ed992c3add426cf82bc041da29e0aed2', 'https://photo.choot.top/c56985b56f2d46e3a9a19768b7118b93', 'Yoyo', '2021-12-29 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 11:51:39', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_photo` VALUES ('ef6c6bf3867e1914619e417b4a266b1a', 'https://photo.choot.top/8be240e17bdc443fbb46c1a88060a782', '玉龙雪山', '2021-12-29 16:25:53', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);

-- ----------------------------
-- Table structure for blog_website
-- ----------------------------
DROP TABLE IF EXISTS `blog_website`;
CREATE TABLE `blog_website` (
  `id` varchar(64) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '导航名称',
  `url` varchar(1000) NOT NULL COMMENT '跳转地址',
  `category` int(1) NOT NULL COMMENT '类别（1:常用网址 2:学习 3:资源 4:影视）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(64) NOT NULL COMMENT '创建人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网址导航表';

INSERT INTO `blog_website` VALUES ('01b9ba0119f8f6e111770b5ef3f046df', '猪肝紫', 'http://zhongguose.com/#shubihong', 3, '2021-12-31 08:49:32', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('06a2d64af7128baf657835ad99edcec4', 'lol外服下载', 'https://cloud.uli1.com/lol/LOL%E7%BE%8E%E6%9C%8DPBE%E6%B5%8B%E8%AF%95%E6%9C%8D/', 3, '2021-12-31 08:40:02', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('06f7838e88223e97e38a0e25be5b79f0', '电脑壁纸', 'http://lcoc.top/bizhi/', 3, '2021-12-30 10:40:56', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('07f6746f02f544c40fe24254d2f694d2', 'springbootDemo', 'https://github.com/xkcoding/spring-boot-demo', 2, '2021-12-31 08:50:18', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('094c88c20811b5ba0844c741d03dd84b', '牛客网', 'https://www.nowcoder.com/home', 2, '2021-12-29 17:11:04', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('09eaa8780492ae7687b460100840c1ec', '多功能图片网站', 'https://www.logosc.cn/so/', 4, '2021-12-30 10:23:45', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('127628cfd033e201e873d4e8443871eb', '阿木影视', 'https://www.aosk.online/', 4, '2021-12-30 10:21:34', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('13b7afea846ce591eff98fa9e495db37', '鸠摩搜书', 'www.jiumodiary.com', 3, '2021-12-30 10:25:54', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('1d5735acc0ef3f641fc22485f5ed5146', '虫洞栈', 'https://bugstack.cn/', 1, '2021-12-29 17:00:30', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('24812da8b2593c2ea53aca180d853517', '力扣', 'https://leetcode-cn.com/', 2, '2021-12-29 17:09:43', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('24f887a90f55915fcb0da201c226ce91', '94神马电影网', 'http://www.9rmb.com/', 4, '2021-12-30 10:22:49', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('29ad941df79b3fc7b23ace9799b94727', '图灵社区', 'https://www.ituring.com.cn/', 2, '2021-12-29 17:07:18', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('2b29b854b08217dbc4f708ac6829caff', '周读', 'http://ireadweek.com/', 3, '2021-12-30 10:26:27', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('2c6ea352f236e03fe7745f8c83b9b559', 'java3y', 'https://github.com/ZhongFuCheng3y/athena', 2, '2021-12-31 08:42:12', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('2d7034fdac530c549577e8d3c7a666cb', '图片搜索', 'https://www.logosc.cn/so/', 3, '2021-12-30 10:44:31', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('2f035d0c469fcde899274b259ebc4c39', '电影天堂', 'https://www.xiaopian.com/html/tv/hytv/', 3, '2021-12-31 08:41:39', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('2feb382d7a395436fe8392b646aed5f0', '世界网址大全', 'http://www.world68.com/', 1, '2021-12-30 10:33:26', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('322f46e0537798fdd575dc4b939143ae', '百度一下', 'https://www.baidu.com/', 1, '2021-12-29 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-29 16:47:21', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_website` VALUES ('399762bc8a52a54bb089c7ea3550a3c0', 'caj云阅读', 'http://cajviewer.cnki.net/cajcloud/', 3, '2021-12-30 10:28:04', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('3f561da920364c0350c9d9dc70f37882', '爱扒趣', 'https://www.zyboe.com/', 3, '2021-12-30 10:29:59', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('3fdb370cbc144bdf989b2b28ff1fe469', '书格', 'https://new.shuge.org/', 3, '2021-12-30 10:27:52', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('412690203342096eadcb42f01659a72e', '彼岸图库', 'https://pic.netbian.com/', 3, '2021-12-30 10:43:52', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('444f512d395e9106de07e277139de928', '脚本之家电子书下载', 'https://www.jb51.net/books/', 3, '2021-12-30 10:27:05', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('45094168aec1296f7fa68e92df4530e0', '策驰影院', 'http://www.ynzdhc.com/cc_play/zTRW5N-1-1.html', 4, '2021-12-31 08:38:52', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('465e161c92f0c09af758f6b57a60cf63', 'PDF派', 'https://www.pdfpai.com/', 1, '2021-12-29 16:50:13', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('47e05923af56abbce8946f7a0cb7f16a', '去看TV', 'https://www.qukantv.net/', 4, '2021-12-30 10:22:13', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('48d14334d84755135079585aa48929ea', '原创技术博客大联盟', 'http://techblog.pub/', 2, '2021-12-29 17:05:32', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('48d71118ddf95340410018656eeff1e9', '大数据导航', 'http://hao.199it.com/', 4, '2021-12-30 10:23:32', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('495f62e5f9dda1d5f7a12ad6e1c0a8a9', '豆瓣', 'https://www.douban.com/', 1, '2021-12-29 16:59:40', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('4a8e3c01c934dc82bf31bab9ff03b106', '蓝调网站', 'http://lcoc.top/vip2.3/', 4, '2021-12-30 10:24:29', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('4b341814ec66c36738742b6948456731', '电影推荐（分类别致）', 'http://www.mvcat.com/', 4, '2021-12-30 10:21:50', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('4eb7e1f93baaf1ec27a8ef5000426315', '图库', 'https://www.pexels.com/zh-tw/', 1, '2021-12-29 16:49:26', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('541a52540bdba49d484f6ec185c14f37', '飘花资源网', 'https://www.piaohua.com/html/dianying.html', 3, '2021-12-31 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-31 08:46:47', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_website` VALUES ('595340fd447236c66dddba35b862f969', 'free-api', 'https://www.free-api.com/', 1, '2021-12-30 17:20:26', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('5b9a591b3bf5c50c7cb6d392f8db318e', 'VideoFk', 'http://www.videofk.com/', 4, '2021-12-30 10:24:09', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('5baab856aa29dc0acdeacdf2887c40f4', '聚合数据API', 'https://www.juhe.cn/', 1, '2021-12-31 16:32:48', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('5bae4e35d99194e1ecd4d859d88cfd73', '搜书VIP-电子书搜索', 'http://www.soshuvip.com/all.html', 3, '2021-12-30 10:27:34', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('5d067b27d25124f95c8bd344bef74d6a', 'windows激活', 'https://cmwtat.cloudmoe.com/cn.html', 3, '2021-12-29 17:04:11', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('5e4df37585ff2baf7b9d45de3a51ca81', '在线实用工具', 'http://www.toolnb.com', 1, '2021-12-30 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 14:51:40', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_website` VALUES ('6454202151ee09935762c1d0555d27d3', '书栈网', 'https://www.bookstack.cn/', 3, '2021-12-30 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 10:25:36', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_website` VALUES ('64fdeaa1dc94ec8d864060d2a22e0a06', 'pixabay', 'https://pixabay.com/', 3, '2021-12-30 10:43:15', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('65dacff0a0871847af8784f0edbdae64', '免费SSL证书', 'https://freessl.cn/', 3, '2021-12-29 17:10:04', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('681a4199063a2d27c4c88a6ed3d3e76b', '免费api', 'https://github.com/fangzesheng/free-api#521', 3, '2021-12-30 17:11:50', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('6cd7da284b663524d4d4944d2450b57f', 'pdf转换', 'https://www.alltoall.net/', 3, '2021-12-31 08:43:48', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('6e1f788588d909332bbb6b5c3600b476', '在线看剧', 'http://dy.27234.cn/', 4, '2021-12-30 10:23:22', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('717ad39e7c6506359a93efb3531ff934', '永久资源采集网', 'http://www.yongjiuzy1.com/', 4, '2021-12-30 10:24:44', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('7a9cf5b619d251b609e09e5c833505ae', 'Java技术驿站', 'https://www.cmsblogs.com/', 2, '2021-12-29 17:06:41', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('867f5de38a6f5f79cdacb656a3e45a1c', 'NO视频官网', 'http://www.novipnoad.com/', 4, '2021-12-30 10:23:00', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('8c265d27abf6c7718417333ffa6e57bc', '片库', 'https://www.mypianku.net/', 3, '2021-12-31 08:46:00', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('8d82d262f9a3ad76bc19da521c4e9369', 'MSDN', 'https://msdn.itellyou.cn/', 1, '2021-12-30 10:32:12', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('97b30ae4258c9d82ced299b7de5db77a', '咪咕', 'https://www.miguvideo.com/mgs/website/prd/detail.html?cid=660316949', 3, '2021-12-31 08:47:06', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('9a8775ea465bdc97445a2c8aaab95e2d', '视频转换', 'https://convertio.co/zh/flv-mp4/', 3, '2021-12-29 17:04:46', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('9cf58f695f1b930d74a3cf2f64750e6d', '图标制作', 'https://shields.io/', 3, '2021-12-30 15:59:17', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('9e04740256816844b522ff749a638f15', '简捷工具', 'https://www.shulijp.com/index.html', 1, '2021-12-30 10:36:29', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('a0d9c170837a32540684cd6c39c5a313', '偷渡鱼', 'https://touduyu.com/', 1, '2021-12-30 10:37:49', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('a72de2899ea14b9866fd1229ed2f0f72', '阿里云maven仓库', 'https://developer.aliyun.com/mvn/guide', 1, '2021-12-29 16:54:01', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('a8f66a13e7e99c4a085875c2e6aee089', '图片压缩', 'https://docsmall.com/', 1, '2021-12-30 10:45:11', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('aa4f1847eda14e349524eb574d766966', '五分钟学算法', 'https://www.cxyxiaowu.com/', 2, '2021-12-29 17:08:59', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('b317edc9e489a699a547164770391ac0', '知轩藏书', 'http://www.zxcs.me/', 3, '2021-12-30 10:26:50', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('b7e18b3a11439af3b22a2faba3246e4d', 'Github', 'https://github.com/', 1, '2021-12-29 16:52:45', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('c27f9305183ab6e0a409b982aa05e09a', '动漫视频网', 'http://www.zzzfun.com/', 4, '2021-12-30 00:00:00', 'da6d966d37fcb819546064424a8bdd9f', '2021-12-30 14:52:21', 'da6d966d37fcb819546064424a8bdd9f');
INSERT INTO `blog_website` VALUES ('c4a44b3eac0bd4e57413269d156a72cb', '豆瓣Top250', 'https://movie.douban.com/top250', 3, '2021-12-31 08:48:55', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('c8edeb34f73da3644cdba78220992698', '高清图库', 'https://wallhaven.cc/toplist?page=2', 1, '2021-12-29 16:50:29', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('cbb7b121b7b41df57bf9493b82069cee', '云海电子图书馆', 'www.pdfbook.cn', 3, '2021-12-30 10:26:07', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('d1d98732f97587775e3d8d93a3687173', '必看网（人生必看的书籍）', 'https://www.biikan.com/', 3, '2021-12-30 10:28:14', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('d2ee046e00b3a38d757529431396ff01', 'unsplash', 'https://unsplash.com/', 3, '2021-12-30 10:43:24', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('d3a611cae955047004f9be37bac419cb', '翻墙安装', 'https://www.v2rayssr.com/trojan-go.html', 1, '2021-12-31 08:37:59', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('d4cdc8d8f4dfe01793416f8ceb0898a4', '电影天堂2', 'https://www.dy2018.com/html/gndy/jddy/', 3, '2021-12-31 08:47:21', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('d771a57f49752cfa81ea9d60349bf9a4', '程序员导航', 'http://cxy521.com/', 1, '2021-12-29 16:46:02', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('da8cbfd4ca54bf7d66ecde789f00a737', 'PDF转换', 'https://www.hipdf.cn/', 1, '2021-12-30 10:45:44', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('db01a47f2e90a0d1373f45222e213870', '********', 'https://mmdd6.com/index.html?channelCode=047&yy', 3, '2021-12-31 08:48:26', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e0d136fbce5491920aaa067165f64416', '小说阅读', 'https://www.xyyuedu.com/gdmz/sanyanerpai/', 3, '2021-12-29 17:09:20', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e5f7a8a265cd68749321f6a0650b2085', '工具导航', 'https://hao.logosc.cn/', 1, '2021-12-30 10:39:22', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e67f6bbcaf40c759583fdc718c07ce9b', '蓝光电影画质', 'http://www.languang.co/', 4, '2021-12-30 10:23:12', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e6ea38c9fd6008afe5ef089ce74ea559', '全历史', 'https://www.allhistory.com/', 2, '2021-12-30 10:28:42', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e7a1e2ae9d73a8642add8397502637d1', '图虫摄影', 'https://tuchong.com/', 4, '2021-12-30 16:34:58', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e8014681686d4a75d72c13ff35015523', 'kuangstudy', 'https://www.kuangstudy.com/bbs', 2, '2021-12-31 08:41:04', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e83538b3b2fdbf7e0643143e5320d6f7', '极像素', 'https://www.sigoo.com/', 3, '2021-12-30 10:44:16', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e98ee2df28ee79c7f213c7cac6a05fe6', '知乎', 'https://www.zhihu.com/', 1, '2021-12-29 16:52:33', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('e9d33367aa4cae7ac391652b21882407', 'oracle', 'https://www.oracle.com/cn/downloads/', 3, '2021-12-29 17:03:05', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('ecacd8ad8cc211b8dc053c1d6d6ef341', '阿里云图标', 'https://www.iconfont.cn/', 3, '2021-12-29 17:08:42', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('f0f9d81015e4494ed82e34c72024a18a', 'Java全栈知识体系', 'https://pdai.tech/', 2, '2021-12-31 08:51:21', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('fbe30be84824216a257aa94f9fcf7118', '精准云工具合集', 'https://jingzhunyun.com/', 1, '2021-12-30 10:40:09', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('fce28ef501a780c3cea7007fc63a2c23', '牛牛TV', 'http://www.ziliao6.com/tv/', 4, '2021-12-30 10:23:58', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);
INSERT INTO `blog_website` VALUES ('fd069ddb7489b0c9a7c18517281681b3', 'APP影院', 'https://app.movie', 4, '2021-12-30 10:22:01', 'da6d966d37fcb819546064424a8bdd9f', NULL, NULL);

commit;