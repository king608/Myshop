/*
Navicat MariaDB Data Transfer

Source Server         : localhost_3306
Source Server Version : 100408
Source Host           : localhost:3306
Source Database       : myshop

Target Server Type    : MariaDB
Target Server Version : 100408
File Encoding         : 65001

Date: 2021-06-18 11:01:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for myshop_cart
-- ----------------------------
DROP TABLE IF EXISTS `myshop_cart`;
CREATE TABLE `myshop_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_p_filename` varchar(128) DEFAULT NULL,
  `cart_p_name` varchar(64) DEFAULT NULL,
  `cart_p_price` decimal(10,2) DEFAULT NULL,
  `cart_quantity` int(11) DEFAULT NULL,
  `cart_p_stock` int(11) DEFAULT NULL,
  `cart_p_id` int(11) DEFAULT NULL,
  `cart_u_id` varchar(64) DEFAULT NULL,
  `cart_valid` int(11) DEFAULT NULL,
  `cart_mc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `cart_merchant` (`cart_mc`),
  CONSTRAINT `cart_merchant` FOREIGN KEY (`cart_mc`) REFERENCES `myshop_merchant` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshop_cart
-- ----------------------------
INSERT INTO `myshop_cart` VALUES ('14', '821.266.big.jpg', 'PHP编程实战', '69.00', '3', '99', '24', '13969696', '1', '1111');
INSERT INTO `myshop_cart` VALUES ('16', '22012926869.jpg', '瑞士卷面包', '59.00', '2', '654', '27', '13969696', '1', '1111');
INSERT INTO `myshop_cart` VALUES ('17', 'O1CN0.jpg', '零食大礼包小吃休闲食品', '98.00', '2', '1200', '26', '13969696', '1', '1111');
INSERT INTO `myshop_cart` VALUES ('21', '821.266.big.jpg', 'PHP编程实战', '69.00', '4', '99', '24', '1905030112', '2', '1111');
INSERT INTO `myshop_cart` VALUES ('22', 'iphone12.jpg', 'iphone 12', '6999.00', '1', '99', '35', '1905030112', '2', '1112');
INSERT INTO `myshop_cart` VALUES ('23', '078643-01.jpg', 'PHP从入门到精通', '89.00', '1', '100', '11', '1905030112', '4', '1111');
INSERT INTO `myshop_cart` VALUES ('24', 'topee.jpg', 'Lenovo/联想游戏本笔记本', '9299.00', '1', '99', '36', 'userName2', '4', 'merchant04');
INSERT INTO `myshop_cart` VALUES ('25', '078643-01.jpg', 'PHP从入门到精通', '89.00', '1', '100', '11', 'userName2', '2', '1111');
INSERT INTO `myshop_cart` VALUES ('26', 'iphone12.jpg', 'iphone 12', '6999.00', '1', '99', '35', '1905030112', '2', '1112');
INSERT INTO `myshop_cart` VALUES ('27', 'topee.jpg', 'Lenovo/联想游戏本笔记本', '9299.00', '3', '99', '36', '1905030112', '2', 'merchant04');
INSERT INTO `myshop_cart` VALUES ('28', '078643-01.jpg', 'PHP从入门到精通', '89.00', '1', '100', '11', '1905030112', '2', '1111');
INSERT INTO `myshop_cart` VALUES ('29', '821.266.big.jpg', 'PHP编程实战', '69.00', '1', '99', '24', '1905030112', '1', '1111');

-- ----------------------------
-- Table structure for myshop_category
-- ----------------------------
DROP TABLE IF EXISTS `myshop_category`;
CREATE TABLE `myshop_category` (
  `cate_id` int(10) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(20) NOT NULL,
  `cate_parent_id` decimal(10,0) NOT NULL,
  PRIMARY KEY (`cate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshop_category
-- ----------------------------
INSERT INTO `myshop_category` VALUES ('1', '图书', '0');
INSERT INTO `myshop_category` VALUES ('2', 'Python图书', '1');
INSERT INTO `myshop_category` VALUES ('3', 'PHP图书', '1');
INSERT INTO `myshop_category` VALUES ('4', 'Go语言图书', '1');
INSERT INTO `myshop_category` VALUES ('5', 'Linux图书', '1');
INSERT INTO `myshop_category` VALUES ('6', 'JavaSE图书', '1');
INSERT INTO `myshop_category` VALUES ('7', 'JAVAEE图书', '1');
INSERT INTO `myshop_category` VALUES ('8', 'JavaEE框架', '1');
INSERT INTO `myshop_category` VALUES ('9', 'Python基础', '2');
INSERT INTO `myshop_category` VALUES ('10', 'Python人工智能', '2');
INSERT INTO `myshop_category` VALUES ('13', 'Linux系统', '5');
INSERT INTO `myshop_category` VALUES ('14', 'Linux网络服务', '5');
INSERT INTO `myshop_category` VALUES ('15', 'Linux运维', '5');
INSERT INTO `myshop_category` VALUES ('16', 'java web', '2');
INSERT INTO `myshop_category` VALUES ('17', '美食', '0');
INSERT INTO `myshop_category` VALUES ('18', '零食', '17');
INSERT INTO `myshop_category` VALUES ('19', '家居', '0');
INSERT INTO `myshop_category` VALUES ('27', 'python入门', '2');
INSERT INTO `myshop_category` VALUES ('28', '服装', '0');
INSERT INTO `myshop_category` VALUES ('30', '童装', '28');
INSERT INTO `myshop_category` VALUES ('31', '夏季运动装', '28');
INSERT INTO `myshop_category` VALUES ('33', '电子产品', '0');
INSERT INTO `myshop_category` VALUES ('34', '手机', '33');
INSERT INTO `myshop_category` VALUES ('35', '电脑', '33');
INSERT INTO `myshop_category` VALUES ('36', '桌子', '19');
INSERT INTO `myshop_category` VALUES ('37', '床', '19');
INSERT INTO `myshop_category` VALUES ('38', '柜子', '19');
INSERT INTO `myshop_category` VALUES ('39', '耳机', '33');
INSERT INTO `myshop_category` VALUES ('40', '数码相机', '33');
INSERT INTO `myshop_category` VALUES ('41', '方便面', '17');
INSERT INTO `myshop_category` VALUES ('42', '饼干', '17');
INSERT INTO `myshop_category` VALUES ('43', '酸奶', '17');
INSERT INTO `myshop_category` VALUES ('44', '糖果', '17');
INSERT INTO `myshop_category` VALUES ('45', '薯片', '17');
INSERT INTO `myshop_category` VALUES ('46', '男装', '28');
INSERT INTO `myshop_category` VALUES ('47', '女装', '28');
INSERT INTO `myshop_category` VALUES ('48', '沙发', '19');
INSERT INTO `myshop_category` VALUES ('49', '床垫', '19');
INSERT INTO `myshop_category` VALUES ('50', '花洒', '19');
INSERT INTO `myshop_category` VALUES ('51', '休闲装', '28');
INSERT INTO `myshop_category` VALUES ('52', '羽绒服', '28');
INSERT INTO `myshop_category` VALUES ('53', '智能手表', '33');
INSERT INTO `myshop_category` VALUES ('54', '平板电脑', '33');
INSERT INTO `myshop_category` VALUES ('55', '路由器', '33');

-- ----------------------------
-- Table structure for myshop_merchant
-- ----------------------------
DROP TABLE IF EXISTS `myshop_merchant`;
CREATE TABLE `myshop_merchant` (
  `merchant_id` varchar(20) NOT NULL,
  `merchant_name` varchar(20) NOT NULL,
  `merchant_password` varchar(20) NOT NULL,
  `merchant_status` int(2) NOT NULL,
  PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshop_merchant
-- ----------------------------
INSERT INTO `myshop_merchant` VALUES ('0111', '白', '2323', '1');
INSERT INTO `myshop_merchant` VALUES ('1111', '小小', '22', '1');
INSERT INTO `myshop_merchant` VALUES ('1112', 'FLY', '55', '1');
INSERT INTO `myshop_merchant` VALUES ('1113', 'GYT', '0', '0');
INSERT INTO `myshop_merchant` VALUES ('merchant01', '虹猫', '123', '0');
INSERT INTO `myshop_merchant` VALUES ('merchant02', '小白', '111', '0');
INSERT INTO `myshop_merchant` VALUES ('merchant03', '蓝兔', '159', '0');
INSERT INTO `myshop_merchant` VALUES ('merchant04', '少侠', '159', '1');
INSERT INTO `myshop_merchant` VALUES ('merchant05', '吕景波02', '123', '1');

-- ----------------------------
-- Table structure for myshop_product
-- ----------------------------
DROP TABLE IF EXISTS `myshop_product`;
CREATE TABLE `myshop_product` (
  `PRODUCT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(128) NOT NULL,
  `PRODUCT_DESCRIPTION` varchar(512) DEFAULT NULL,
  `PRODUCT_PRICE` decimal(10,2) NOT NULL,
  `PRODUCT_STOCK` decimal(10,0) DEFAULT NULL,
  `PRODUCT_FID` decimal(10,0) DEFAULT NULL,
  `PRODUCT_CID` decimal(10,0) DEFAULT NULL,
  `PRODUCT_FILENAME` varchar(200) DEFAULT NULL,
  `PRODUCT_MC` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `Product_Merchant` (`PRODUCT_MC`),
  CONSTRAINT `Product_Merchant` FOREIGN KEY (`PRODUCT_MC`) REFERENCES `myshop_merchant` (`merchant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshop_product
-- ----------------------------
INSERT INTO `myshop_product` VALUES ('11', 'PHP从入门到精通', '本书从初学者的角度出发，通过通俗易懂的语言、丰富多彩的实例，详细介绍了使用PHP进行网络开发应该掌握的各方面技术。 ', '89.00', '100', '1', '3', '078643-01.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('24', 'PHP编程实战', 'PHP是最为流行的Web开发技术之一，它糅合了C++、Perl、Java等语言的语法，功能强大，可以快速地构建动态网页，因此深受开发者喜爱', '69.00', '99', '1', '3', '821.266.big.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('26', '零食大礼包小吃休闲食品', '零食大礼包小吃休闲食品小整箱送女友网红猪饲料儿童礼盒爆款解馋', '98.00', '1200', '17', '18', 'O1CN0.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('27', '瑞士卷面包', '零食大礼包小吃休闲食品小整箱送女友网红猪饲料儿童礼盒爆款解馋', '59.00', '654', '17', '18', '22012926869.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('35', 'iphone 12', '证书编号：2019011606222162证书状态：有效产品名称：TD-LTE 数字移动电话机3C规格型号：A2223(电源适配器可选：A1443 输出：5.0VDC 1A)产品名称：Apple/苹果 iPhone 12Apple型号: iPhone 12机身颜色: 红色 白色 黑色 蓝色 绿色 紫色运行内存RAM: 4GB存储容量: 64GB 128GB 256GB', '6999.00', '99', '33', '34', 'iphone12.jpg', '1112');
INSERT INTO `myshop_product` VALUES ('36', 'Lenovo/联想游戏本笔记本', '【24期免息】Lenovo/联想 拯救者R9000P 16英寸游戏本笔记本电脑 新锐龙 8核 R7-5800H/RTX30系显卡', '9299.00', '99', '33', '35', 'topee.jpg', 'merchant04');
INSERT INTO `myshop_product` VALUES ('37', 'Huawei/华为 nova 7', 'Huawei/华为 nova 7 5G手机官方新款全网通旗舰全新nova7se Pro', '3841.00', '1436', '33', '34', 'huaweinona.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('38', '华为p40pro', '正品华为p40pro Huawei/华为 P40 5G手机直降官方旗舰p40pro+华为', '5899.00', '100', '33', '34', 'p40.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('39', '小米 11青春版', '抢券可减390元】xiaomi/小米 11青春版5G手机官方旗舰店正品11青春版小米手机红米官网全网通10青春新品', '2599.00', '100', '33', '34', 'xiaomi01.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('40', '红米Redmi K30S 至尊纪念版', '【当天发货赠小米耳机】红米Redmi K30S 至尊纪念版5G手机骁龙865新品小米官方旗舰店官网正品红米k30s至尊版', '2459.00', '654', '33', '34', 'REdmi.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('41', 'xiaomi/小米11青春版', '【支持88VIP消费券】xiaomi/小米11青春版手机骁龙780G处理器轻薄 AMOLED屏幕 骁龙780G处理器', '2599.00', '635', '33', '34', 'xiaomi02.jpg', '1111');
INSERT INTO `myshop_product` VALUES ('42', '零食大礼包', '零食大礼包小吃休闲食品小整箱送女友网红猪饲料儿童礼盒爆款解馋', '69.00', '100', '17', '18', 'lsi.jpg', 'merchant05');
INSERT INTO `myshop_product` VALUES ('43', '手机', '【24期免息】Lenovo/联想 拯救者R9000P 16英寸游戏本笔记本电脑 新锐龙 8核 R7-5800H/RTX30系显卡', '9855.00', '99', '33', '34', 'xiaomi01.jpg', 'merchant05');

-- ----------------------------
-- Table structure for myshop_user
-- ----------------------------
DROP TABLE IF EXISTS `myshop_user`;
CREATE TABLE `myshop_user` (
  `User_ID` varchar(20) NOT NULL,
  `User_name` varchar(20) NOT NULL,
  `User_password` varchar(20) NOT NULL,
  `User_sex` varchar(2) NOT NULL,
  `User_birthday` datetime DEFAULT NULL,
  `User_IDENITY_code` varchar(50) DEFAULT NULL,
  `User_email` varchar(20) DEFAULT NULL,
  `User_mobile` varchar(11) DEFAULT NULL,
  `User_address` varchar(100) NOT NULL,
  `User_status` decimal(6,0) NOT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myshop_user
-- ----------------------------
INSERT INTO `myshop_user` VALUES ('1105030112', '潘家俊', '456789', 'T', '2001-06-05 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('13969696', '潘家俊', '11', 'T', '2020-06-07 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('144969696', '潘家俊FLY', '222', 'T', '2020-06-28 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1480726217', '李续', '78945789', 'T', '2001-02-23 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1705030122', '潘家俊eeeee', '564dwdfe', 'T', '2001-02-23 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1705030232', '潘家俊', '564dwdfe', 'T', '2020-06-07 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1705030422', '潘家俊', '564dwdfe', 'T', '2020-06-07 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('170503055', '潘家俊', '78945789', 'T', '2001-06-05 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1805030112', '潘家俊eeeee', 'asderfvgygbhybh', 'T', '2000-05-09 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1805030118', '潘家俊', '564dwdfe', 'T', '2001-03-02 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030108', '谭盛', 'sddfggggg', 'T', '2001-06-08 00:00:00', null, '12395566@qq.com', '159786489', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030112', '吕景波', '123', 'T', '2001-02-23 00:00:00', null, '1480726216@qq.com', '17674611715', '湖南科技大学', '2');
INSERT INTO `myshop_user` VALUES ('1905030113', '李续', '6354789', 'T', '2000-06-05 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030120', '王化', '467899', 'T', '2000-06-05 00:00:00', null, '56555@qq.com', '1659783697', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030127', '李续', 'sdfghjkuh', 'T', '2001-03-02 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030131', '胡志伟', 'sssssfffgg', 'T', '2000-05-09 00:00:00', null, '456789@qq.com', '156897434', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030136', '吕景波', 'fffghjkk', 'T', '2000-06-05 00:00:00', null, '1480726216@qq.com', '17674611715', '湖南科技大学', '2');
INSERT INTO `myshop_user` VALUES ('1905030144', '王化', '6354789', 'T', '2001-03-02 00:00:00', null, '56555@qq.com', '1659783697', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030212', '小白', '456789', 'T', '2001-02-23 00:00:00', null, '122345668@qq.com', '15956791569', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030322', '廖卓汶', 'werfghjkkgjytr', 'T', '2000-09-07 00:00:00', null, '1367890633@qq.com', '1257864315', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1905030329', '潘家俊', 'sfggghhhh', 'T', '2000-05-09 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1907030122', '潘家俊FLY', 'wertyuio', 'T', '2003-06-09 00:00:00', null, '159159555@qq.com', '1264895678', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('1980726216', '白小纯', '564dwdfe', 'T', '2020-06-07 00:00:00', null, '22132132Q', '136853333', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('admin3', 'admin', '123', 'T', '2019-10-08 00:00:00', null, 'aaa@bbb.com', '222222', '4323323232323232', '2');
INSERT INTO `myshop_user` VALUES ('admin4', 'admin', '123', 'T', '2019-10-08 00:00:00', null, 'aaa@bbb.com', '222222', '4323323232323232', '2');
INSERT INTO `myshop_user` VALUES ('admin5', 'admin', '123', 'T', '2019-10-08 00:00:00', null, 'aaa@bbb.com', '222222', '4323323232323232', '2');
INSERT INTO `myshop_user` VALUES ('admin6', 'admin', '123', 'T', '2019-10-08 00:00:00', null, 'aaa@bbb.com', '222222', '4323323232323232', '2');
INSERT INTO `myshop_user` VALUES ('admin7', 'admin', '123', 'T', '2019-10-08 00:00:00', null, 'aaa@bbb.com', '222222', '4323323232323232', '2');
INSERT INTO `myshop_user` VALUES ('hello123', 'hello', '111', 'T', '2019-10-15 00:00:00', null, 'aaa@lmonkey.com', '121212121', 'aaaaaaaaaaaaaaaaa', '1');
INSERT INTO `myshop_user` VALUES ('hello2', 'hello', '111', 'F', '2019-10-08 00:00:00', null, '88888', '88888', '8888888888', '1');
INSERT INTO `myshop_user` VALUES ('hello3', 'rewreww', 'www', 'T', '2019-10-08 00:00:00', null, 'retrewtrew', 'trewtrewt', 'rewtrewtew', '1');
INSERT INTO `myshop_user` VALUES ('lisi2', '李四lisi2', '111', 'T', '2019-11-05 00:00:00', null, 'lisi@lmonkey.com', '15801684888', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('lisi4', '李四', '111', 'T', '2019-11-05 00:00:00', null, 'lisi@lmonkey.com', '15801684888', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('lisi6', '李四', '111', 'T', '2019-11-05 00:00:00', null, 'lisi@lmonkey.com', '15801684888', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('lisi67', '李四', '111', 'T', '2019-11-05 00:00:00', null, 'lisi@lmonkey.com', '15801684888', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('userName2', 'Fly2', '123', 'F', '1999-03-06 00:00:00', null, '12395566@qq.com', '159786489', '湖南科技大学', '1');
INSERT INTO `myshop_user` VALUES ('wangwu', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu1', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu2', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu3', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu5', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu6', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('wangwu67', '王五', '111', 'T', '2019-11-01 00:00:00', null, 'wangwu@lmonkey.com', '18810090000', '中关村软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan', '张三', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan1', '张三', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan3', '张三', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan4', '张三', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan5', '坤哥', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
INSERT INTO `myshop_user` VALUES ('zhangsan6', '三FLY', '111', 'T', '2019-11-08 00:00:00', null, 'zhangsan@lmonkey.com', '1192121221', '北京西二期软件园', '1');
