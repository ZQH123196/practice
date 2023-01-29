

DROP DATABASE IF EXISTS `Rbac0WithGroupHierarchy`;
CREATE DATABASE  `Rbac0WithGroupHierarchy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
SET NAMES utf8mb4;

USE `Rbac0WithGroupHierarchy`;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `role`      varchar(50) NOT NULL,
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    UNIQUE INDEX `idx_user_role` (`role` ASC) USING BTREE,
    PRIMARY KEY (`id`)
) engine=innodb comment = '角色表';


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `username`  varchar(50)  NOT NULL,
    `password`  varchar(500) NOT NULL,
    create_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    UNIQUE INDEX `idx_user_role` (`username` ASC) USING BTREE,
    PRIMARY KEY (id)
) engine=innodb comment = '用户表';


DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
    `role`      varchar(50) NOT NULL,
    `username`  varchar(50)  NOT NULL,
    PRIMARY KEY (`role`, `username`)
) engine=innodb comment = '角色用户关联表';


DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `expression` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限表达式,模块名称:资源名称:操作(crud)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB auto_increment=1000 comment = '权限表';


DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
    `role`      varchar(50) NOT NULL,
    `permission_id`  int(11)  NOT NULL,
    PRIMARY KEY (`role`, `permission_id`)
)engine=innodb comment = '角色权限关联表';


DROP TABLE IF EXISTS `user_usergroup`;
CREATE TABLE `user_usergroup` (
   `user_group_id`  varchar(50)  NOT NULL,
   `user_id`  varchar(50)  NOT NULL,
   UNIQUE INDEX `idx_user_role` (`user_group_id` ASC, `user_id` ASC) USING BTREE
)engine=innodb comment = '用户用户组关联表';


DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `text` varchar(100)  NOT NULL COMMENT '用于前端显示的文本',
    UNIQUE INDEX `idx_user_role` (`text` ASC) USING BTREE,
    PRIMARY KEY (`id`)
)engine=innodb auto_increment=1000 comment = '用户组表';


DROP TABLE IF EXISTS `user_group_hierarchy`;
CREATE TABLE `user_group_hierarchy` (
    `user_group_id`  int(11) NOT NULL,
    `node_name`  varchar(50)  NOT NULL,
    `path_enum`  varchar(50)  NOT NULL,
    PRIMARY KEY (`node_name`)
)engine=innodb comment = '用户组继承树';



