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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tx_order` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `tx_order`;

/*Table structure for table `tx_order` */

DROP TABLE IF EXISTS `tx_order`;

CREATE TABLE `tx_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT '0' COMMENT '用户账号ID',
  `product_id` int(10) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `product_count` int(4) DEFAULT '0' COMMENT '购买数量',
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态：0=已拍下，1=拍下失败，2=未支付，3=支付成功，4=支付失败',
  `total_money` decimal(10,0) DEFAULT '0' COMMENT '总价',
  `coupon_receive_id` int(10) DEFAULT NULL COMMENT '优惠券id',
  `district_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `payment_money` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `decrease_score` int(10) DEFAULT '0' COMMENT '扣除积分',
  `largess_score` int(10) DEFAULT '0' COMMENT '赠送积分',
  `deleted` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='订单表 https://open.taobao.com/api.htm?docId=54&docType=2';

/*Data for the table `tx_order` */



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
