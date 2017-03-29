use coupon;


-- 用户状态表
CREATE TABLE `t_employee_status` (
  `id` int(11) unsigned NOT NULL,
  `code` char(16) DEFAULT NULL,
  `description` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_employee_status_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 岗位
CREATE TABLE `t_position` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(64) NOT NULL,
  `name` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_position_code` (`code`),
  UNIQUE KEY `uk_position_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 小组
CREATE TABLE `t_group` (
  `id` char(16) NOT NULL,
  `code` char(64) NOT NULL,
  `name` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_code` (`code`),
  UNIQUE KEY `uk_group_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户表
CREATE TABLE `t_employee` (
  `id` char(16) NOT NULL,
  `full_name` varchar(32) NOT NULL,
  `gender` char(1) NOT NULL,
	`mobile` char(16) NOT NULL,
	`password` char(128) NOT NULL,
	`group_id` char(16) DEFAULT NULL,
	`position_id` int(10) unsigned NOT NULL,
  `create_time` datetime NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_employee_status` (`status_id`),
	CONSTRAINT `fk_employee_position` FOREIGN KEY (`position_id`) REFERENCES `t_position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_employee_group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_status` FOREIGN KEY (`status_id`) REFERENCES `t_employee_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 权限
CREATE TABLE `t_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(64) NOT NULL,
  `name` char(32) NOT NULL,
  `description` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`code`),
  UNIQUE KEY `uk_permission_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 岗位-权限表
CREATE TABLE `t_position_permission` (
  `position_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  UNIQUE KEY `uk_position_permission` (`position_id`,`permission_id`),
  KEY `idx_position_permission_position` (`position_id`),
  KEY `idx_position_permission_permission` (`permission_id`),
  CONSTRAINT `fk_position_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_position_permission_position` FOREIGN KEY (`position_id`) REFERENCES `t_position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



