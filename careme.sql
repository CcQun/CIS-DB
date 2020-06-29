drop database IF EXISTS cis;

create database cis;

use cis;

--
-- Table structure for table `oldperson_info`
--

DROP TABLE IF EXISTS `oldperson_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oldperson_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORG_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `gender` char(5) DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) DEFAULT NULL,
  `id_card` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `checkin_date` datetime DEFAULT NULL,
  `checkout_date` datetime DEFAULT NULL,
  `imgset_dir` varchar(200) DEFAULT NULL,
  `profile_photo` varchar(200) DEFAULT NULL,
  `room_number` varchar(50) DEFAULT NULL,
  `firstguardian_name` varchar(50) DEFAULT NULL,
  `firstguardian_relationship` varchar(50) DEFAULT NULL,
  `firstguardian_phone` varchar(50) DEFAULT NULL,
  `firstguardian_wechat` varchar(50) DEFAULT NULL,
  `secondguardian_name` varchar(50) DEFAULT NULL,
  `secondguardian_relationship` varchar(50) DEFAULT NULL,
  `secondguardian_phone` varchar(50) DEFAULT NULL,
  `secondguardian_wechat` varchar(50) DEFAULT NULL,
  `health_state` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ISACTIVE` char(10) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATEBY` int(11) DEFAULT NULL,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATEBY` int(11) DEFAULT NULL,
  `REMOVE` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `employee_info`
--

DROP TABLE IF EXISTS `employee_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ORG_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `gender` char(5) DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) DEFAULT NULL,
  `id_card` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `hire_date` datetime DEFAULT NULL,
  `resign_date` datetime DEFAULT NULL,
  `imgset_dir` varchar(200) DEFAULT NULL,
  `profile_photo` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ISACTIVE` char(10) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATEBY` int(11) DEFAULT NULL,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATEBY` int(11) DEFAULT NULL,
  `REMOVE` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `volunteer_info`
--

DROP TABLE IF EXISTS ` volunteer_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunteer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ORG_ID` int(11) DEFAULT NULL,
  `CLIENT_ID` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `gender` char(5) DEFAULT NULL COMMENT '性别',
  `phone` varchar(50) DEFAULT NULL,
  `id_card` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `checkin_date` datetime DEFAULT NULL,
  `checkout_date` datetime DEFAULT NULL,
  `imgset_dir` varchar(200) DEFAULT NULL,
  `profile_photo` varchar(200) DEFAULT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  `ISACTIVE` char(10) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL,
  `CREATEBY` int(11) DEFAULT NULL,
  `UPDATED` datetime DEFAULT NULL,
  `UPDATEBY` int(11) DEFAULT NULL,
  `REMOVE` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `event_info`
--

DROP TABLE IF EXISTS `event_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` int(11) DEFAULT NULL COMMENT '事件类型',
  `event_date` datetime DEFAULT NULL,
  `event_location` varchar(200) DEFAULT NULL,
  `event_desc` varchar(200) DEFAULT NULL,
  `oldperson_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;