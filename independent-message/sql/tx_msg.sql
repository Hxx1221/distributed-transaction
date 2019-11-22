/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.22 : Database - tx_order
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tx_msg` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tx_msg`;

/*Table structure for table `tx_order` */

DROP TABLE IF EXISTS `tx_msg`;

CREATE TABLE `tx_msg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(50) NOT NULL DEFAULT '' COMMENT '消息ID,例如交易订单号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息状态 0=待发送 1=已发送 ',
  `app_name` varchar(50) NOT NULL DEFAULT '' COMMENT '回调服务名',
  `routing_key` varchar(50) NOT NULL DEFAULT '' COMMENT '消息路由key',
  `json_msg` varchar(1000) NOT NULL DEFAULT '' COMMENT '消息内容',
  `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='消息表';

