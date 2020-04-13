/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : databasedemo

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 13/04/2020 15:12:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for output
-- ----------------------------
DROP TABLE IF EXISTS `output`;
CREATE TABLE `output`  (
  `OPcontext` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OPmoney` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OPtelephone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OPtitle` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OPNO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isdelete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`OPNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of output
-- ----------------------------
INSERT INTO `output` VALUES ('that is very big', '34', '45678901234', 'apple', 'admin', '212992d144bc40d590d4dc1b2ede7a43', '1');
INSERT INTO `output` VALUES ('car', '99999', '56789012345', 'bus', 'admin', '855cb23566664c0488eae055b4873494', '1');
INSERT INTO `output` VALUES ('it is new', '10000000', '11111111111', 'gy', 'admin', '8e4ac0deace74589a1dcd938a3427452', '0');
INSERT INTO `output` VALUES ('1111', '23', '24123456789', '123', 'ad1234', 'd88412cbb96f48768226e0accdcc5abe', '0');
INSERT INTO `output` VALUES ('222', '123', '12345678901', '', 'ad1234', 'fb3df904dd8d455daa0f171bb786f008', '1');
INSERT INTO `output` VALUES ('big very big', '45', '09876543210', 'orange', 'admin', 'fdde5af393a94ffe85290816414dcad3', '1');

-- ----------------------------
-- Table structure for shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcar`;
CREATE TABLE `shoppingcar`  (
  `SCimageid` int(11) NULL DEFAULT NULL,
  `SCname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `SCmoney` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `SCNO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isdelete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcar
-- ----------------------------
INSERT INTO `shoppingcar` VALUES (2131230857, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '2b3579a3846a45ac8bdec967c34704bd', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230859, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '31cb72d4b61d4efbaa4a8122988bf227', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230859, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '49796e21b0404f028f565a5fc5abbfad', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230859, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '5b04e72f35d24420a8eb9e210ac0671c', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230857, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '5c5a75a4633943c8a69e3d076679dd7b', '0', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230887, '木质u盘 16g/32g/64g', '¥34.0', '83c16e129ae54093b154f18dc8d0ac58', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230859, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', '8aa4c71d8cba47eaa1ef7b744f0db996', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230834, '头戴式蓝牙耳机', '¥328.0', 'ac3a0aec6d644216b07267350e2dd836', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230879, '腾达 双千兆无线路由器 无线wifi', '¥119.0', 'ada131bcbd004a4bb589525f594d4b03', '0', 'ad1234');
INSERT INTO `shoppingcar` VALUES (2131230859, '罗及G313 有线游戏机械手感键盘鼠标', '¥279.0', 'ae08caebaffa4aa7bf1a0c35aeff735e', '1', 'ad1234');
INSERT INTO `shoppingcar` VALUES (2131230834, '头戴式蓝牙耳机', '¥328.0', 'bfadc279ff9b42f3937ae54eb2257cc5', '1', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230879, '腾达 双千兆无线路由器 无线wifi', '¥119.0', 'e285a6cd511f44beaeebd08c6eedb4fb', '0', 'admin');
INSERT INTO `shoppingcar` VALUES (2131230883, '智能手环 运动计步', '¥99.0', 'ed706b1bc36540a788c0394b3a93fb7c', '0', 'admin');

-- ----------------------------
-- Table structure for typebottom
-- ----------------------------
DROP TABLE IF EXISTS `typebottom`;
CREATE TABLE `typebottom`  (
  `TBname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TBtitle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TBcontext` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `TBprice` decimal(10, 2) NULL DEFAULT NULL,
  `TBNO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`TBNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of typebottom
-- ----------------------------
INSERT INTO `typebottom` VALUES ('keyboard', '罗及G313 有线游戏机械手感键盘鼠标', '有线|USB|支持人体工学|-0001\r\n', 279.00, '01');
INSERT INTO `typebottom` VALUES ('router', '腾达 双千兆无线路由器 无线wifi', '有线传输|百兆端口|1200Mbps\r\n', 119.00, '02');
INSERT INTO `typebottom` VALUES ('youpan', '木质u盘 16g/32g/64g', '内存容量：32GB|USB 3.0|防水\r\n', 34.00, '03');
INSERT INTO `typebottom` VALUES ('shouhuan', '智能手环 运动计步', '心率检测|智能提醒|睡眠检测\r\n', 99.00, '04');
INSERT INTO `typebottom` VALUES ('earphone', '头戴式蓝牙耳机', '无线耳机|蓝牙：4.0版本', 328.00, '05');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userpsd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '-272874517023827102959669599430568608046907545193', '01');
INSERT INTO `user` VALUES ('1234', '-534506718816875235077686917400765719896834376563', '17c0ef7b45f74b7d89817212a6731242');
INSERT INTO `user` VALUES ('ad1234', '-534506718816875235077686917400765719896834376563', 'a6f82ccdf9db4ec68e170ea5aa015e92');

SET FOREIGN_KEY_CHECKS = 1;
