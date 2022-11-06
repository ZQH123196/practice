DROP DATABASE IF EXISTS `rbac0withresource`;
CREATE DATABASE `rbac0withresource` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
SET NAMES utf8mb4;

USE `rbac0withresource`;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `role`      varchar(50) NOT NULL,
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`role`)
) engine = innodb comment = '角色表';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `username`  varchar(50)  NOT NULL,
    `password`  varchar(500) NOT NULL,
    create_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (username)
) engine = innodb comment = '用户表';


DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT COMMENT '编号',
    `role`     varchar(50) NOT NULL,
    `username` varchar(50) NOT NULL,
    UNIQUE INDEX `idx_role_user` (`role` ASC, `username` ASC) USING BTREE,
    PRIMARY KEY (`id`)
) engine = innodb comment = '角色用户关联表';


DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `role`          varchar(50) NOT NULL,
    `permission_id` int(11)     NOT NULL,
    UNIQUE INDEX `idx_role_permission` (`role` ASC, `permission_id` ASC) USING BTREE,
    PRIMARY KEY (`role`)
) engine = innodb comment = '角色权限关联表';


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`                  int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `expression`          varchar(4)   DEFAULT '' COMMENT 'CRUD，表明可以对资源做什么操作，比如 RU 就说明可读可改',
    `resource_type`       varchar(100) DEFAULT NULL COMMENT '资源的类型，permel、menu、page 等，其中类型为 permel',
    `resource_id`         int(11)      DEFAULT NULL COMMENT '资源 id',
    `resource_table`      int(11)      DEFAULT NULL COMMENT '资源 id',
    `resource_handler_id` int(11)      DEFAULT NULL COMMENT '使用的 handler 需要被注册到 resource_handler 表中',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  auto_increment = 1000 comment = '权限表';


DROP TABLE IF EXISTS `resource_handler`;
CREATE TABLE `resource_handler`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `handler_ref` varchar(50)  DEFAULT NULL COMMENT '填写 handler 类的全限定名',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    UNIQUE INDEX `idx_register_handler` (`handler_ref` ASC) USING BTREE,
    PRIMARY KEY (`id`)
) engine = innodb comment = '可用的资源处理器';




DROP TABLE IF EXISTS `resource_permel`;
CREATE TABLE `resource_permel`
(
    `id`            int(11)     NOT NULL AUTO_INCREMENT COMMENT '编号',
    `permission_el` varchar(50) NOT NULL COMMENT '纯粹的权限表达式，比如 模块:资源:权限 system:role:create',
    `description`   varchar(500) DEFAULT NULL COMMENT '资源描述',
    PRIMARY KEY (`id`)
)
    engine = innodb comment = '常规资源表（即不需要额外处理的资源,该资源只用于表达权限，使用起来相当于写死权限，将与代码深度耦合）';


DROP TABLE IF EXISTS `resource_menu`;
CREATE TABLE `resource_menu`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `parent_id`   int(11)      DEFAULT NULL COMMENT '父级 id，为 null 则为 顶级',
    `order`       int(4)       DEFAULT 500 COMMENT '显示的排列顺序，递增数值表自上而下的显示',
    `router_path` varchar(50)  DEFAULT NULL COMMENT '路由匹配的地址',
    `description` varchar(500) DEFAULT NULL COMMENT '资源描述',
    PRIMARY KEY (`id`)
) engine = innodb comment = '菜单资源表';


DROP TABLE IF EXISTS `resource_page`;
CREATE TABLE `resource_page`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `menu_id`        int(11)      DEFAULT NULL COMMENT '归属菜单的 id，为 null 则为 顶级',
    `order`          int(4)       DEFAULT 500 COMMENT '显示的排列顺序，递增数值表自上而下的显示',
    `router_path`    varchar(50)  DEFAULT NULL COMMENT '路由匹配的地址',
    `component_path` varchar(50)  DEFAULT NULL COMMENT '前端组件的渲染位置',
    `description`    varchar(500) DEFAULT NULL COMMENT '资源描述',
    PRIMARY KEY (`id`)
) engine = innodb comment = '页面资源表';


DROP TABLE IF EXISTS `resource_file`;
CREATE TABLE `resource_file`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `dirPath`     varchar(500) DEFAULT NULL COMMENT '文件存放的根路径，只能由数据库访问者修改，系统完全信任此路径不会有校验，不应当被暴露到前端，暴露可能造成安全隐患（路径遍历）',
    `fileName`    varchar(50)  DEFAULT NULL COMMENT '文件名，对应的 handler 会进行文件名的安全校验',
    `description` varchar(500) DEFAULT NULL COMMENT '资源描述',
    UNIQUE INDEX `idx_resource_file` (`dirPath` ASC, `fileName` ASC) USING BTREE,
    PRIMARY KEY (`id`)
) engine = innodb comment = '文件资源表';










