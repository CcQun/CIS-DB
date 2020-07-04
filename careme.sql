drop database IF EXISTS cis;

create database cis;

use cis;

--
-- Table structure for table `oldperson_info`
--

DROP TABLE IF EXISTS `oldperson_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oldperson_info`
(
    `ID`                          int(11) NOT NULL AUTO_INCREMENT,
    `ORG_ID`                      int(11)      DEFAULT NULL,
    `CLIENT_ID`                   int(11)      DEFAULT NULL,
    `username`                    varchar(50)  DEFAULT NULL COMMENT '用户名',
    `gender`                      char(5)      DEFAULT NULL COMMENT '性别',
    `phone`                       varchar(50)  DEFAULT NULL,
    `id_card`                     varchar(50)  DEFAULT NULL,
    `birthday`                    datetime     DEFAULT NULL,
    `checkin_date`                datetime     DEFAULT NULL,
    `checkout_date`               datetime     DEFAULT NULL,
    `imgset_dir`                  varchar(200) DEFAULT NULL,
    `profile_photo`               varchar(200) DEFAULT NULL,
    `room_number`                 varchar(50)  DEFAULT NULL,
    `firstguardian_name`          varchar(50)  DEFAULT NULL,
    `firstguardian_relationship`  varchar(50)  DEFAULT NULL,
    `firstguardian_phone`         varchar(50)  DEFAULT NULL,
    `firstguardian_wechat`        varchar(50)  DEFAULT NULL,
    `secondguardian_name`         varchar(50)  DEFAULT NULL,
    `secondguardian_relationship` varchar(50)  DEFAULT NULL,
    `secondguardian_phone`        varchar(50)  DEFAULT NULL,
    `secondguardian_wechat`       varchar(50)  DEFAULT NULL,
    `health_state`                varchar(50)  DEFAULT NULL,
    `DESCRIPTION`                 varchar(200) DEFAULT NULL,
    `ISACTIVE`                    char(10)     DEFAULT NULL,
    `CREATED`                     datetime     DEFAULT NULL,
    `CREATEBY`                    int(11)      DEFAULT NULL,
    `UPDATED`                     datetime     DEFAULT NULL,
    `UPDATEBY`                    int(11)      DEFAULT NULL,
    `REMOVE`                      char(1)      DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `employee_info`
--

DROP TABLE IF EXISTS `employee_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_info`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `ORG_ID`        int(11)      DEFAULT NULL,
    `CLIENT_ID`     int(11)      DEFAULT NULL,
    `username`      varchar(50)  DEFAULT NULL COMMENT '用户名',
    `gender`        char(5)      DEFAULT NULL COMMENT '性别',
    `phone`         varchar(50)  DEFAULT NULL,
    `id_card`       varchar(50)  DEFAULT NULL,
    `birthday`      datetime     DEFAULT NULL,
    `hire_date`     datetime     DEFAULT NULL,
    `resign_date`   datetime     DEFAULT NULL,
    `imgset_dir`    varchar(200) DEFAULT NULL,
    `profile_photo` varchar(200) DEFAULT NULL,
    `DESCRIPTION`   varchar(200) DEFAULT NULL,
    `ISACTIVE`      char(10)     DEFAULT NULL,
    `CREATED`       datetime     DEFAULT NULL,
    `CREATEBY`      int(11)      DEFAULT NULL,
    `UPDATED`       datetime     DEFAULT NULL,
    `UPDATEBY`      int(11)      DEFAULT NULL,
    `REMOVE`        char(1)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 60
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `volunteer_info`
--

DROP TABLE IF EXISTS ` volunteer_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volunteer_info`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `ORG_ID`        int(11)      DEFAULT NULL,
    `CLIENT_ID`     int(11)      DEFAULT NULL,
    `name`          varchar(50)  DEFAULT NULL COMMENT '姓名',
    `gender`        char(5)      DEFAULT NULL COMMENT '性别',
    `phone`         varchar(50)  DEFAULT NULL,
    `id_card`       varchar(50)  DEFAULT NULL,
    `birthday`      datetime     DEFAULT NULL,
    `checkin_date`  datetime     DEFAULT NULL,
    `checkout_date` datetime     DEFAULT NULL,
    `imgset_dir`    varchar(200) DEFAULT NULL,
    `profile_photo` varchar(200) DEFAULT NULL,
    `DESCRIPTION`   varchar(200) DEFAULT NULL,
    `ISACTIVE`      char(10)     DEFAULT NULL,
    `CREATED`       datetime     DEFAULT NULL,
    `CREATEBY`      int(11)      DEFAULT NULL,
    `UPDATED`       datetime     DEFAULT NULL,
    `UPDATEBY`      int(11)      DEFAULT NULL,
    `REMOVE`        char(1)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 60
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event_info`
--

DROP TABLE IF EXISTS `event_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_info`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `event_type`     int(11)      DEFAULT NULL COMMENT '事件类型',
    `event_date`     datetime     DEFAULT NULL,
    `event_location` varchar(200) DEFAULT NULL,
    `event_desc`     varchar(200) DEFAULT NULL,
    `oldperson_id`   int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user`
(
    `ID`          int(11)     NOT NULL AUTO_INCREMENT,
    `ORG_ID`      int(11)       DEFAULT NULL,
    `CLIENT_ID`   int(11)       DEFAULT NULL,
    `UserName`    varchar(50) NOT NULL COMMENT '用户名',
    `Password`    varchar(50)   DEFAULT NULL COMMENT '密码',
    `REAL_NAME`   varchar(50)   DEFAULT NULL,
    `SEX`         varchar(20)   DEFAULT NULL,
    `EMAIL`       varchar(50)   DEFAULT NULL,
    `PHONE`       varchar(50)   DEFAULT NULL,
    `MOBILE`      varchar(50)   DEFAULT NULL,
    `DESCRIPTION` varchar(200)  DEFAULT NULL,
    `ISACTIVE`    char(10)      DEFAULT NULL,
    `CREATED`     datetime      DEFAULT NULL,
    `CREATEBY`    int(11)       DEFAULT NULL,
    `UPDATED`     datetime      DEFAULT NULL,
    `UPDATEBY`    int(11)       DEFAULT NULL,
    `REMOVE`      char(1)       DEFAULT NULL,
    `DATAFILTER`  varchar(200)  DEFAULT NULL,
    `theme`       varchar(45)   DEFAULT NULL,
    `defaultpage` varchar(45)   DEFAULT NULL COMMENT '登录成功页面',
    `logoimage`   varchar(45)   DEFAULT NULL COMMENT '显示不同logo',
    `qqopenid`    varchar(100)  DEFAULT NULL COMMENT '第三方登录的凭证',
    `appversion`  char(10)      DEFAULT NULL COMMENT '检测app的版本号',
    `jsonauth`    varchar(1000) DEFAULT NULL COMMENT ' app权限控制',
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 62
  DEFAULT CHARSET = utf8;
INSERT INTO `sys_user`
VALUES (1, NULL, NULL, 'zsm', '3B6302C1ED258F38542D468F7111C5FC', '周诗梦', '女', '7868@163.com', '123', '12345', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user`
VALUES (2, NULL, NULL, 'wyy', 'E10ADC3949BA59ABBE56E057F20F883E', '吴瑛瑛', '女', '121@163.com', '123', '12345', NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `volunteer_info`
VALUES (1, NULL, NULL, '符永乐', '男', '123', '460031199912166111', '1999-12-16 00:00:00', '2020-07-02 10:23:00', NULL,
        NULL, NULL, '优秀义工', '不知道', '2020-07-03 13:58:37', 2, NULL, NULL, '0');
INSERT INTO `employee_info`
VALUES (1, NULL, NULL, '崔超群', '男', '123', '370126199907284210', '1999-07-28 00:00:00', '2020-07-04 14:57:00', NULL,
        NULL, NULL, '优秀工作人员', '不知道', '2020-07-02 10:38:45', 1, NULL, NULL, '0');
INSERT INTO `oldperson_info`
VALUES (1, NULL, NULL, '李程遥', '男', '123', '220203199904264810', '1999-04-26 00:00:00', '2020-07-20 00:00:00', NULL,
        NULL, NULL, '12', 'aaa', '儿子', '11111111111', 'aaaaaa', 'aaa', '儿子', '11111111111', 'aaaaaa', '好', '好得很', '不知道',
        '2020-07-02 21:34:59', 1, NULL, NULL, '0');


/*!40101 SET character_set_client = @saved_cs_client */;