
use coupon;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 用户状态表
DROP TABLE IF EXISTS `t_employee_status`;
CREATE TABLE `t_employee_status` (
  `id` int(11) unsigned NOT NULL,
  `code` char(16) DEFAULT NULL,
  `description` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_employee_status_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 岗位
DROP TABLE IF EXISTS `t_position`;
CREATE TABLE `t_position` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_position_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 小组
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(32) NOT NULL,
  `employee_id`  int(11) unsigned DEFAULT NULL COMMENT '组长id',
  `employee_name` char(32) DEFAULT NULL COMMENT '组长name',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户表
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `full_name` varchar(32) NOT NULL,
  `gender` char(1) NOT NULL,
	`mobile` char(16) NOT NULL,
	`password` char(128) NOT NULL,
	`group_id` int(16) unsigned DEFAULT NULL,
	`position_id` int(10) unsigned NOT NULL,
  `create_time` datetime NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_employee_mobile` (`mobile`),
  UNIQUE KEY `uk_employee_mobile` (`mobile`),
	CONSTRAINT `fk_employee_position` FOREIGN KEY (`position_id`) REFERENCES `t_position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_employee_group` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_status` FOREIGN KEY (`status_id`) REFERENCES `t_employee_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 权限
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` char(64) NOT NULL,
  `name` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`code`),
  UNIQUE KEY `uk_permission_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 岗位-权限表
DROP TABLE IF EXISTS `t_position_permission`;
CREATE TABLE `t_position_permission` (
  `position_id` int(10) unsigned NOT NULL,
  `permission_id` int(10) unsigned NOT NULL,
  UNIQUE KEY `uk_position_permission` (`position_id`,`permission_id`),
  KEY `idx_position_permission_position` (`position_id`),
  KEY `idx_position_permission_permission` (`permission_id`),
  CONSTRAINT `fk_position_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_position_permission_position` FOREIGN KEY (`position_id`) REFERENCES `t_position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在执行的审批表
DROP TABLE IF EXISTS `ru_task`;
CREATE TABLE `ru_task` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
	`employee_id` int(16) unsigned DEFAULT NULL COMMENT '审核人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 流程变量
DROP TABLE IF EXISTS `ru_variable`;
CREATE TABLE `ru_variable` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `text` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
	`task_id` int(16) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_ru_variable_name_task_id` (`name`,`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- 初始数据
INSERT INTO t_permission VALUES (1,'audit.wait.read','待审核'),(2,'audit.now.read','审核中'),(3,'audit.reject.read','驳回'),(4,'audit.refuse.read','拒绝'),(5,'audit.review.read','待复审'),(6,'store.generalize.read','推广中'),
(7,'date.about.end.read','即将结束'),(8,'date.end.read','结束'),(9,'payment.replace.read','代付款'),(10,'payment.now.read','付款中'),(11,'payment.end.read','已付款'),(12,'payment.refuse.read','拒绝付款'),(13,'finance.report.read','查看财务报表'),
(14,'store.submit','提交商品'),(15,'accounts.submit','提交结账'),(16,'employee.add','添加成员'),(17,'employee.upgrade','升级业务员');

INSERT INTO t_position VALUES (1,'管理员'),(2,'业务员'),(3,'组长'),(4,'审单员'),(5,'财务'),(6,'零');

INSERT INTO t_employee_status VALUES (1,'IN_POSITION','在职'),(2,'OUT_OF_POSITION','离职');


use coupon;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- 商品活动类型表
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
	`id` INT (11) UNSIGNED NOT NULL,
	`code` CHAR (32) DEFAULT NULL,
	`description` CHAR (32) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `uk_activity_code` (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 佣金类型表
DROP TABLE IF EXISTS `t_hire_type`;
CREATE TABLE `t_hire_type` (
	`id` INT (11) UNSIGNED NOT NULL,
	`code` CHAR (32) DEFAULT NULL,
	`description` CHAR (32) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `uk_hire_type_code` (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 商品状态表
DROP TABLE IF EXISTS `t_product_status`;
CREATE TABLE `t_product_status` (
	`id` INT (11) UNSIGNED NOT NULL,
	`code` CHAR (32) DEFAULT NULL,
	`description` CHAR (32) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `uk_product_status_code` (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 商品类型表
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
	`id` INT (11) UNSIGNED NOT NULL,
	`code` CHAR (32) DEFAULT NULL,
	`description` CHAR (32) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `uk_product_type_code` (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 店铺类型表
DROP TABLE IF EXISTS `t_store_type`;
CREATE TABLE `t_store_type` (
	`id` INT (11) UNSIGNED NOT NULL,
	`code` CHAR (32) DEFAULT NULL,
	`description` CHAR (32) DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `uk_store_type_code` (`code`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;

-- 店铺
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `store_id` char(32) DEFAULT NULL,
  `qq` char(32) DEFAULT NULL,
  `description_score` decimal(18,0) DEFAULT NULL,
  `service_score` decimal(18,0) DEFAULT NULL,
  `speed_score` decimal(18,0) DEFAULT NULL,
  `type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `t_store_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_store_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `taoKe_id` char(32) DEFAULT NULL,
  `product_id` char(32) NOT NULL,
  `name` char(255) NOT NULL,
  `picture_url` varchar(255) DEFAULT NULL COMMENT '商品主图',
  `supplement_picture_url` varchar(255) DEFAULT NULL COMMENT '补充主图',
  `reserve_price` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '正常价格',
  `sales` int(10) unsigned NOT NULL COMMENT '月销量',
  `url` varchar(255) NOT NULL,
  `activity_time` datetime NOT NULL,
  `immediately` char(1) NOT NULL COMMENT '是否拍立减',
  `discount_price` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '卷后金额',
  `coupon_amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '优惠卷金额',
  `coupon_url` varchar(255) NOT NULL,
  `coupon_begin_time` datetime NOT NULL,
  `coupon_end_time` datetime NOT NULL,
  `coupon_receive_number` int(10) unsigned NOT NULL COMMENT '领取数量',
  `coupon_surplus_number` int(10) unsigned NOT NULL COMMENT '剩余数量',
  `condition` char(32) NOT NULL COMMENT '使用条件',
  `features` text COMMENT '特色',
  `description` text,
  `charge_price` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '收费单价',
  `create_time` datetime NOT NULL,
  `update_status_time` datetime DEFAULT NULL,
  `ratio` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '佣金比例',
  `plan_url` char(255) DEFAULT NULL COMMENT '计划链接',
  `hire_type_id` int(10) unsigned NOT NULL,
  `type_id` int(10) unsigned NOT NULL,
  `activity_id` int(10) unsigned NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  `store_id` int(10) unsigned NOT NULL,
  `employee_id` int(10) unsigned NOT NULL,
  `task_id` int(16) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `hire_type_id` (`hire_type_id`),
  KEY `type_id` (`type_id`),
  KEY `activity_id` (`activity_id`),
  KEY `t_product_ibfk_4` (`status_id`),
  KEY `employee_id` (`employee_id`),
  KEY `t_product_ibfk_5` (`store_id`),
  KEY `t_product_ibfk_6` (`task_id`),
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`hire_type_id`) REFERENCES `t_hire_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `t_product_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_3` FOREIGN KEY (`activity_id`) REFERENCES `t_activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_4` FOREIGN KEY (`status_id`) REFERENCES `t_product_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_5` FOREIGN KEY (`store_id`) REFERENCES `t_store` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_6` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_product_ibfk_7` FOREIGN KEY (`task_id`) REFERENCES `ru_task` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;



-- 商品图片
DROP TABLE IF EXISTS `t_product_picture`;
CREATE TABLE `t_product_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `product_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_product_picture_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品凭证
DROP TABLE IF EXISTS `t_product_voucher`;
CREATE TABLE `t_product_voucher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `receive_number` int(10) unsigned NOT NULL,
  `use_number` int(10) unsigned NOT NULL,
  `pay_amount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `should_charge_amount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `actual_charge_amount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `create_time` datetime NOT NULL,
  `conversion_rate` decimal(18,2) NOT NULL DEFAULT '0.00',
  `without_url` varchar(255) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `product_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `t_product_voucher_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 商品凭证图片
DROP TABLE IF EXISTS `t_voucher_picture`;
CREATE TABLE `t_voucher_picture` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `voucher_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `voucher_id` (`voucher_id`),
  CONSTRAINT `t_voucher_picture_ibfk_1` FOREIGN KEY (`voucher_id`) REFERENCES `t_product_voucher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 财务
DROP TABLE IF EXISTS `t_finance`;
CREATE TABLE `t_finance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `submit_number` int(10) unsigned NOT NULL,
  `average_daily` int(10) unsigned NOT NULL,
  `refuse_rate` int(10) unsigned NOT NULL,
  `refuse_number` int(10) unsigned NOT NULL,
  `two_audit_number` int(10) unsigned NOT NULL,
  `promote_number` int(10) unsigned NOT NULL,
  `end_approach_number` int(10) unsigned NOT NULL,
  `end_number` int(10) unsigned NOT NULL,
  `pay_wait_number` int(10) unsigned NOT NULL,
  `pay_run_number` int(10) unsigned NOT NULL,
  `pay_trailer_number` int(10) unsigned NOT NULL,
  `pay_end_number` int(10) unsigned NOT NULL,
  `settlement_number` int(10) unsigned NOT NULL,
  `guest_unit_price` decimal(18,2) NOT NULL DEFAULT '0.00',
  `actual_charge_amount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `should_charge_amount` decimal(18,2) NOT NULL DEFAULT '0.00',
  `create_time` datetime NOT NULL,
  `employee_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `t_finance_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

INSERT INTO `t_activity` VALUES ( '1', 'ORDINARY', '普通活动' ), ('2', 'ROB', '淘抢购'), ('3', 'BARGAIN', '聚划算'), ( '4', 'TRAILER', '预告商品' );

INSERT INTO `t_hire_type` VALUES ('1', 'DIRECTIONAL', '定向'), ('2', 'GENERAL', '通用'), ('3', 'MAGPIE', '鹊桥');

INSERT INTO `t_product_status` VALUES ( '1', 'AUDIT_WAIT', '待审核' ), ( '2', 'AUDIT_RUN', '审核中' ), ('3', 'REJECTED', '驳回'), ('4', 'TRAILER', '拒绝'), ( '5', 'TWO_AUDIT', '待二审' ), ('6', 'PROMOTE', '推广中'), ( '7', 'END_APPROACH', '即将结束' ), ('8', 'END', '已结束'), ('9', 'PAY_WAIT', '代付款'), ('10', 'PAY_RUN', '付款中'), ( '11', 'PAY_TRAILER', '拒绝付款' ), ('12', 'PAY_END', '已付款'),('13', 'SETTLEMENT', '已结算');

INSERT INTO `t_store_type` VALUES ('1', 'TAOBAO', '淘宝'), ('2', 'TMALL', '天猫');

INSERT INTO `t_product_type` VALUES ('1', 'WOMAN', '女裝'), ('2', 'MAN', '男裝'), ('3', 'UNDERWEAR', '內衣'), ('4', 'MATERNAL', '母婴'), ( '5', 'COSMETICS', 'COSMETICS' ), ('6', 'OCCUPY', '居家'), ('7', 'SHOES', '鞋包配饰'), ('8', 'FOOD', '美食'), ('9', 'CAR', '文体车品'), ( '10', 'APPLIANCE', '数码家电' );
INSERT INTO `t_employee` VALUES (1, 'admin', 'M', 'admin', '21232F297A57A5A743894A0E4A801FC3', NULL, 1, '2017-4-10 23:50:36', 1);
