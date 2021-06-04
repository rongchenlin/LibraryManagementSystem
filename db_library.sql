/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50647
Source Host           : localhost:3306
Source Database       : db_library

Target Server Type    : MYSQL
Target Server Version : 50647
File Encoding         : 65001

Date: 2020-06-12 23:01:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `admin_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `admin_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `admin_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `admin_sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('666', '管理员', '666', '女');

-- ----------------------------
-- Table structure for tb_books
-- ----------------------------
DROP TABLE IF EXISTS `tb_books`;
CREATE TABLE `tb_books` (
  `book_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `book_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `book_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `book_num` int(11) DEFAULT NULL,
  `book_price` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_books
-- ----------------------------
INSERT INTO `tb_books` VALUES ('1', '容忍与自由', '文学', '50', '12.50');
INSERT INTO `tb_books` VALUES ('10', '软件工程导论', '软件工程', '48', '4.00');
INSERT INTO `tb_books` VALUES ('11', 'Java程序设计', '计算机', '49', '7.00');
INSERT INTO `tb_books` VALUES ('12', 'C++', '计算机', '50', '4.00');
INSERT INTO `tb_books` VALUES ('13', '野火集', '文学', '0', '10.00');
INSERT INTO `tb_books` VALUES ('14', '精神明亮的人', '文学', '48', '8.00');
INSERT INTO `tb_books` VALUES ('15', '软件工程导论', '计算机', '50', '7.00');
INSERT INTO `tb_books` VALUES ('16', '善良 丰富 高贵', '文学', '50', '12.00');
INSERT INTO `tb_books` VALUES ('17', 'Java程序设计', '计算机', '50', '12.50');
INSERT INTO `tb_books` VALUES ('18', '丑陋的中国人', '文学', '50', '17.00');
INSERT INTO `tb_books` VALUES ('3', '毛概', '政治', '49', '15.50');
INSERT INTO `tb_books` VALUES ('4', '复变函数', '数学', '50', '22.50');
INSERT INTO `tb_books` VALUES ('5', '计算机组成原理', '计算机', '50', '12.50');
INSERT INTO `tb_books` VALUES ('6', '计算机导论', '计算机', '50', '30.00');
INSERT INTO `tb_books` VALUES ('7', '离散数学', '计算机', '49', '4.00');
INSERT INTO `tb_books` VALUES ('8', '大学英语', '英语', '50', '3.00');
INSERT INTO `tb_books` VALUES ('9', '高等数学', '数学', '50', '9.00');

-- ----------------------------
-- Table structure for tb_borrowing
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrowing`;
CREATE TABLE `tb_borrowing` (
  `book_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `book_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reader_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `book_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `book_price` decimal(10,2) DEFAULT NULL,
  `borrow_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `need_return_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`book_id`,`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_borrowing
-- ----------------------------
INSERT INTO `tb_borrowing` VALUES ('10', '软件工程导论', '111', '软件工程', '4.00', '2020-06-11 ', '2020-07-02 ');
INSERT INTO `tb_borrowing` VALUES ('10', '软件工程导论', '222', '软件工程', '4.00', '2020-06-11 ', '2020-07-02 ');
INSERT INTO `tb_borrowing` VALUES ('11', 'Java程序设计', '111', '计算机', '7.00', '2020-06-10 ', '2020-07-01 ');
INSERT INTO `tb_borrowing` VALUES ('14', '精神明亮的人', '111', '文学', '8.00', '2020-06-11 ', '2020-07-02 ');
INSERT INTO `tb_borrowing` VALUES ('14', '精神明亮的人', '222', '文学', '8.00', '2020-06-11 ', '2020-07-02 ');
INSERT INTO `tb_borrowing` VALUES ('3', '毛概', '222', '政治', '15.50', '2020-06-11 ', '2020-07-02 ');
INSERT INTO `tb_borrowing` VALUES ('7', '离散数学', '222', '计算机', '4.00', '2020-06-11 ', '2020-07-02 ');

-- ----------------------------
-- Table structure for tb_reader
-- ----------------------------
DROP TABLE IF EXISTS `tb_reader`;
CREATE TABLE `tb_reader` (
  `reader_id` varchar(11) COLLATE utf8_bin NOT NULL,
  `reader_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reader_password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reader_sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_reader
-- ----------------------------
INSERT INTO `tb_reader` VALUES ('111', '读者1', '111', '男');
INSERT INTO `tb_reader` VALUES ('222', '读者2', '222', '女');
