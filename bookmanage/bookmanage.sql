/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - bookmanage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookmanage` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `bookmanage`;

/*Table structure for table `p_book_info` */

DROP TABLE IF EXISTS `p_book_info`;

CREATE TABLE `p_book_info` (
  `Book_ID` int(11) NOT NULL AUTO_INCREMENT,
  `book_Type` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '类别',
  `book_name` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '书名',
  `Publisher` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '出版社',
  `Publish_year` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '出版年份',
  `Author` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `Price` double(8,2) DEFAULT NULL COMMENT '价格',
  `code` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`Book_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `p_book_info` */

insert  into `p_book_info`(`Book_ID`,`book_Type`,`book_name`,`Publisher`,`Publish_year`,`Author`,`Price`,`code`,`status`) values (1,'1','书名','1','2018','1',1.00,'1','1'),(2,'1','1','1','2019','1',1.00,'2','0'),(3,'计算机','计算机应用','','2018','',12.20,'123','1');

/*Table structure for table `p_borrow_record` */

DROP TABLE IF EXISTS `p_borrow_record`;

CREATE TABLE `p_borrow_record` (
  `Book_ID` int(16) DEFAULT '0' COMMENT '图书编号',
  `card_ID` int(16) DEFAULT NULL COMMENT '借阅证件号',
  `data` date DEFAULT NULL,
  `admin_ID` int(16) DEFAULT NULL COMMENT '经办人',
  `record_type` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '办理类别\n0:借阅\n1:返回'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `p_borrow_record` */

insert  into `p_borrow_record`(`Book_ID`,`card_ID`,`data`,`admin_ID`,`record_type`) values (1,2,'2018-07-24',16,'1'),(2,2,'2018-07-24',16,'0'),(2,3,'2018-07-27',NULL,'0'),(2,4,'2018-07-27',16,'0'),(1,4,'2018-07-27',16,'0'),(1,2,'2018-07-27',16,'0'),(1,2,'2018-07-27',NULL,'0'),(1,2,'2018-07-27',NULL,'0'),(NULL,NULL,'2018-07-27',NULL,'0'),(NULL,NULL,'2018-07-27',NULL,'0'),(NULL,NULL,'2018-07-27',NULL,'0'),(2,5,'2018-07-27',16,'1'),(1,5,'2018-07-27',NULL,'1');

/*Table structure for table `p_library_card` */

DROP TABLE IF EXISTS `p_library_card`;

CREATE TABLE `p_library_card` (
  `card_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(8) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `gender` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别\n0：女，1男',
  `Company` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '单位',
  `phone` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `rank` int(2) DEFAULT NULL COMMENT '级别\n借阅数量=级别*2',
  PRIMARY KEY (`card_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `p_library_card` */

insert  into `p_library_card`(`card_ID`,`Username`,`gender`,`Company`,`phone`,`email`,`rank`) values (2,'22222','1','22','222222','2@qq.com',2),(3,'3','0','3','3','3',3),(4,'4','4','4','4','4',4),(5,'213','1','','123','',5),(6,'242','1','123','123','',5),(7,'7','1','7','7','7@qq.com',5),(8,'12','1','12','123','',5),(9,'123','1','123123','123','',5),(10,'21','1','11','111','',5),(11,'111','1','11','1111111','',5),(12,'132123','1','21','1221','',5),(14,'12','1','','','',5),(15,'','1','fsa','','',5),(16,'22222','1','123123','123','234',5),(17,'','1','','','we',5),(18,'12','1','','15737328611','',5);

/*Table structure for table `s_admin_inof` */

DROP TABLE IF EXISTS `s_admin_inof`;

CREATE TABLE `s_admin_inof` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '登录名称',
  `PASSWORD` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `EFFECT_TIME` datetime DEFAULT NULL COMMENT '生效时间',
  `FAIL_TIME` datetime DEFAULT NULL COMMENT '失效时间',
  `IS_VALID` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0：失效，1：生效',
  `gender` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '0：女，1男',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `department` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `s_admin_inof` */

insert  into `s_admin_inof`(`admin_id`,`NAME`,`PASSWORD`,`EFFECT_TIME`,`FAIL_TIME`,`IS_VALID`,`gender`,`phone`,`email`,`department`) values (4,'1223','asdfghjkl','2018-01-01 00:00:00','2020-07-14 21:12:24','1','0','15737328611','12@11','12'),(11,'3221','','2018-07-13 21:52:36','2018-07-13 21:52:37','0','0','15737328611','s@qq',''),(12,'SD',NULL,'2018-07-14 09:39:48','2018-07-14 09:39:50','1',NULL,'1231',NULL,NULL),(13,'23232',NULL,'2018-07-14 09:51:14','2018-07-14 09:51:15','1','1','18813008179','',''),(14,'撒旦法',NULL,'2018-07-03 09:52:15','2018-07-14 09:52:20','1','1','爱抚撒',NULL,NULL),(15,'1231','213','2018-07-03 09:53:54','2018-07-14 09:53:54','1','1','123','123@qq.com',NULL),(16,'admin','21232f297a57a5a743894a0e4a801fc3','2018-07-22 12:42:34','2021-07-22 12:42:34','1','0','15737328611','',''),(17,'123123','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,'1','1','15737328611','1@11',NULL),(18,'12332','f5bb0c8de146c67b44babbf4e6584cc0','2018-07-25 21:01:57','2018-07-28 21:02:18','1','1','15737328611','123@11',NULL),(19,'23131','f5bb0c8de146c67b44babbf4e6584cc0','2018-07-28 21:17:45','2018-07-31 21:17:53','1','1','15737328611','132@1',NULL),(20,'131231','f5bb0c8de146c67b44babbf4e6584cc0',NULL,NULL,'1','1','15737328611','123@11',NULL),(21,'123123','f5bb0c8de146c67b44babbf4e6584cc0','2018-07-28 22:45:04','2019-07-28 22:45:04','1','1','15737328611',NULL,NULL),(23,NULL,'c20ad4d76fe97759aa27a0c99bff6710',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,NULL,'25d55ad283aa400af464c76d713c07ad',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
