/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.22 : Database - tx_score
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tx_score` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tx_score`;

/*Table structure for table `tx_score_account` */

DROP TABLE IF EXISTS `tx_score_account`;

CREATE TABLE `tx_score_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `total_score` int(10) NOT NULL COMMENT '积分总数',
  `lock_score` int(10) NOT NULL COMMENT '消耗积分（锁定积分）',
  `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分账号';

/*Data for the table `tx_score_account` */

insert  into `tx_score_account`(`id`,`user_id`,`total_score`,`lock_score`,`deleted`,`create_time`,`update_time`) values (1,1,10000,0,0,'2019-06-03 10:13:37','2019-06-13 08:21:33');

/*Table structure for table `tx_score_detail` */

DROP TABLE IF EXISTS `tx_score_detail`;

CREATE TABLE `tx_score_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `tx_status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '事务状态 1=try，2=confirm,3=cancel',
  `user_id` int(10) DEFAULT '0' COMMENT '用户账号ID',
  `score` int(10) DEFAULT '0' COMMENT '积分',
  `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='积分明细信息';

/*Data for the table `tx_score_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- 高级篇新增了一个积分类型自动
ALTER TABLE `tx_score`.`tx_score_detail`
  ADD COLUMN `type` TINYINT(4) DEFAULT 0  NOT NULL  COMMENT '积分类型 0=消耗，1=新增' AFTER `score`;
