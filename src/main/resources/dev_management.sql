/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : dev_management

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 07/05/2021 23:15:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bug
-- ----------------------------
DROP TABLE IF EXISTS `bug`;
CREATE TABLE `bug`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `relation_task_id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联开发单',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  `detauk` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'bug描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `file_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES ('3f184d31-1924-4f8f-a0a2-42760bcd146a', 'da8f1dc3-3212-4e6e-8ef4-1b5bd08d53da', '【发送短信/短信模板】增加字数实时计算', '【用户故事（User Story）】\r\n作为：客户\r\n希望：编辑短信时，能够实时计算出短信字数（包含签名+后缀），并计算短信条数\r\n以便：快速知道字数如果超标，则可实时减少字数', '/graduation/91514b39-22ae-438c-a5c0-97a670c08bf0-tapd_47672491_base64_1597991152_3.png,/graduation/966b3c15-e0fa-4023-b0e8-bbe9a2d46915-tapd_47672491_base64_1597991168_62.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:05:37', '2021-05-02 19:05:37');
INSERT INTO `demand` VALUES ('4b509021-806a-4534-8d15-cd73b77d49e8', 'd3734bfa-a1d9-4a3d-8394-9daf88095784', '【接口】对外接口文档维护', '【用户故事（User Story）】\r\n作为：\r\n希望：\r\n以便：\r\n\r\n【方案】\r\n1、\r\n\r\n【对其他产品的依赖】\r\n1、\r\n\r\n【影响到哪些第三方产品】\r\n1、\r\n\r\n【验收标准】\r\n1、所有对外接口都需提供完整的接口文档，实时更新维护\r\n\r\n\r\n', '/graduation/2d373028-dc88-4c98-9c14-32c2c50fcd5c-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:07:07', '2021-05-02 19:07:07');
INSERT INTO `demand` VALUES ('4ce0ae66-c6f5-4a02-a182-32be95668119', '6dbce00c-aca7-4137-b612-183e71968e8c', '【消息中心-发送短信】批量发送，支持下载模板、下载错误原因', '【用户故事（User Story）】\r\n作为：\r\n希望：可以查看发送失败的原因\r\n以便：检查内容哪里有错好更改，重新发送\r\n', '/graduation/4718a301-5003-4d3a-af9b-0bc496c76de3~-er图.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:12:31', '2021-05-02 19:12:31');
INSERT INTO `demand` VALUES ('4d063b76-b9b0-413a-aaf9-ef7397a71d4a', '270b3978-7067-4890-ae0c-ca13b9fb4362', '【数据】统计打开短链接的具体用户', '需求背景\r\n营销自动化发送短信时，短信内容含有短链接（短链接时内容中心提供的），营销自动化知道具体是谁打开了这个短链接\r\n\r\n方案\r\n\r\n记录以下数据\r\n链接：URL\r\n打开链接的用户：需考虑小程序、h5场景\r\n打开的时间：精确到秒\r\n短信发送批次号\r\n实现方案：\r\n1.营销自动化发送短信的时候，在跳转链接中拼接上用户的手机号，调用内容中心的接口生成跳转短链接\r\n2.用户点击短连接进行跳转的时候，从链接中提取到用户手机号，并记录用户打开时间和手机号信息', '/graduation/08fae718-00d1-49a6-b3cd-b2b4d5d21217-草稿.docx', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:07:58', '2021-05-02 19:07:58');
INSERT INTO `demand` VALUES ('70106680-7576-4a77-a9d9-3447c3ef3d4b', '84f407e1-db08-4a68-9570-bbff83411ac0', '营销短信模板案例展示', '【用户故事（User Story）】\r\n作为：客户\r\n希望：营销短信模板案例可以参考\r\n以便：快速编辑可规避敏感字的模板\r\n', '/graduation/aa895949-01bf-4d28-871f-b8b81a7e6f32-tapd_47672491_base64_1597991152_3.png,/graduation/cfd54c67-1ad5-4277-bc16-1104e06825d0-tapd_47672491_base64_1597991168_62.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:05:12', '2021-05-02 19:05:12');
INSERT INTO `demand` VALUES ('792c8680-3d6e-4c4f-af0b-eb35b31f5f25', 'cd2a5d69-d6d9-4562-b32d-d836c628ed11', '【短信平台-通道管理】列表展示短信类型', '【用户故事（User Story）】\r\n作为：运营\r\n希望：在模板新建时，模板类型和通道关联，比如营销类模板类型，通道拉取支持营销类型的模板\r\n以便：快速校验模板类型，选择的通道是正确的\r\n\r\n【方案】\r\n1、短信平台-通道管理：短信类型展示在列表', '/graduation/cfe8339b-5933-4a82-ade8-4be74ecf88f6~-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:14:48', '2021-05-02 19:14:48');
INSERT INTO `demand` VALUES ('88b8816e-e2a6-4fd5-9970-2dee6470e443', '88b8816e-e2a6-4fd5-9970-2dee6470e444', '22', '22', '22', '9a635afe-cc2f-4920-bea9-4f66740bece9', '2021-05-15 20:28:56', '2021-05-28 20:28:59');
INSERT INTO `demand` VALUES ('a51011e1-f0a1-4ca4-acd6-cf8d8eb66858', '49e103db-e832-49dd-bcce-f6425c4dd32c', '【短信平台】控制营销短信发送频率', '【用户故事（User Story）】\r\n作为：消息中心\r\n希望：在发送营销短信的时候控制发送频率\r\n以便：集中发送几万条短信的时候不出现发送失败的问题\r\n\r\n【方案】\r\n1、发送短信记录调用通道发送短信的TPS，如果超过限制的TPS即等待一段时间后再继续发送，以便减少供应商因发送频率限制导致的发送失败的问题', '/graduation/aee70087-f8e2-4eea-a120-486103cc5ec0-tapd_47672491_base64_1597991152_3.png,/graduation/ecda5727-a8f5-4e7a-aed6-892b3733b041-tapd_47672491_base64_1597991168_62.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:04:44', '2021-05-02 19:04:44');
INSERT INTO `demand` VALUES ('a7150931-d309-4d85-8426-af89b9c6e817', 'cf94821c-4424-44ee-87e6-879170ca8cfc', '接手微信小程序（接手云店）', '需求背景：来访登记希望。。\r\n\r\n增加模板来源、模板类型、模板状态筛选项\r\n模板来源：必填，选项：全部、租户模板、公共模板，默认租户模板。租户模板是租户创建的模板；公共模板是业务在短信平台创建的模板\r\n模板类型：必填，选项：全部、普通业务短信、验证码短信、交互短信、营销短信，默认全部\r\n模板状态：必填，选项：全部、待审核、已通过、未通过，默认全部\r\n', '/graduation/46cb7aee-e435-45f5-b470-e2b82ebf6266-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:08:39', '2021-05-02 19:08:39');
INSERT INTO `demand` VALUES ('b80b80dd-30b8-46cd-9555-a8909e8a3fd4', '090d2a89-b32e-4159-b468-8edf848439d7', '【接口】短信模板接口优化', '【接口】短信模板接口优化', '/graduation/1eac2f88-c920-4a21-8b57-3bac02e22f97-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:07:34', '2021-05-02 19:07:34');
INSERT INTO `demand` VALUES ('c9d1b51e-9c2b-46a9-afc6-e83e6a7f0643', 'ff02099a-d96d-4923-8c77-e47518830539', '【接口】短信白名单创建/删除接口', '【方案】\r\n一、白名单创建接口\r\n批量添加白名单手机号上限--需技术查询代码后确认\r\n白名单创建接口维护在消息中心，消息中心再调用短信平台的接口。因为对外提供接口统一在消息中心维护', '/graduation/75367995-ecc8-45a4-80b5-dd99796684ea~-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:16:18', '2021-05-02 19:16:18');
INSERT INTO `demand` VALUES ('d8662646-10c1-4253-8114-700ddede0c75', '11b48922-3ac7-466e-a60d-2701134af1c0', '【消息中心】页面查询性能优化', '【用户故事（User Story）】\r\n作为：消息中心中台方\r\n希望：在查询短信模板，各个消息记录，统计数据的页面提高相应时间\r\n以便：提高用户体验\r\n\r\n【方案】\r\n1、权限点相关数据存储在redis中，失效时间为5分钟\r\n2、登录信息数据缓存在客户端，失效时间为2分钟\r\n\r\n【对其他产品的依赖】\r\n1\r\n\r\n【影响到哪些第三方产品】\r\n1、\r\n\r\n【验收标准】\r\n1、点击页面查询模板列表响应时间小于200ms\r\n2、点击各个消息查询记录响应时间小于200ms\r\n3、点击首页响应时间小于200ms\r\n4、刷新页面响应时间小于 2s\r\n5、通过F12查看各个接口的具体调用时间进行检查', '/graduation/97e1f252-c174-4769-8472-8e2305686930~-tapd_47672491_base64_1597991152_3.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:13:10', '2021-05-02 19:13:10');
INSERT INTO `demand` VALUES ('e0ee9fa1-0858-4b40-8215-dc704c91b8d0', 'dfc8bf8d-db88-4b59-b447-96b5195d651a', '【接口】包消息订阅功能的接口输出给外部', '需求背景\r\n微信打算下架公众号模板消息的功能，公众号消息做成和小程序一样。以防止后面微信官方下架公众号模板消息，公共组做了消息订阅的功能\r\n\r\n【方案】\r\n消息中心包公共组开发的消息订阅功能的接口输出给外部', '/graduation/695bf385-f3cc-47f6-9a89-ea50f1273ce3-tapd_47672491_base64_1597991152_3.png,/graduation/c43546d0-c2a6-459d-b0ed-4c2e27832c35-tapd_47672491_base64_1597991168_62.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:06:08', '2021-05-02 19:06:08');
INSERT INTO `demand` VALUES ('e394de49-42a3-47a4-8f5d-c9b1456b6532', '18405b90-6fc2-417e-9078-03354fcc8fbc', '【Bug转需求】初始密码短信。发送记录、数据库存储，密码都是明文', '【Bug转需求】初始密码短信。发送记录、数据库存储，密码都是明文', '/graduation/f016746d-8022-442d-a2ab-11d2824261bd-tapd_47672491_base64_1597991152_3.png,/graduation/39406005-1c1e-4a8e-95ea-16d92d17dc49-tapd_47672491_base64_1597991168_62.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:04:07', '2021-05-02 19:04:07');
INSERT INTO `demand` VALUES ('e7533dae-e0f6-4d2d-8b1d-a5f1e6ed14d1', '10df8dd1-2523-47f2-b08b-7d720a9142de', '微信公众号消息订阅的功能', '需求背景\r\n微信打算下架公众号模板消息的功能，公众号消息做成和小程序一样。以防止后面微信官方下架公众号模板消息，做了消息订阅的功能\r\nhttps://developers.weixin.qq.com/community/minihome/doc/000a4e1df800d82acb9b7fb5e5b001\r\n\r\n方案\r\n接手公共组已开发', '/graduation/e01925a8-2e32-44a5-b28b-22c28dd6ede6-er图.png', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:06:34', '2021-05-02 19:06:34');
INSERT INTO `demand` VALUES ('ef27cea2-fe30-4d62-8503-202ee74d9b22', 'd809c055-3c84-4756-87f5-108cc88b0742', '【消息中心/短信平台】短信模板做成云客级、租户级', '【需求背景】\r\n短信服务商的短信模板是云客级的，且相同模板类型下的模板内容不能重复。但是消息中心的模板是租户级别，模型无法适配。会碰到的问题如：租户B创建了模板内容为X，租户A也模板内容为X时就会报错“模板重复，不能创建”，租户A会很迷茫，为什么自己没有建过这样的模板，系统却说自己重复了。\r\n\r\n【用户故事（User Story）】\r\n作为：租户\r\n希望：可以按自己的意愿创建模板内容（不触发服务商关键词、运营商敏感词的前提下）\r\n以便：保证模板适配更多业务场景\r\n\r\n【方案】\r\n一、增加云客模板库、租户模板库\r\n租户模板库：租户新建的模板\r\n云客模板库：不重复的模板\r\n新建模板流程说明：判断模板内容是否存在“云客模板库”，是则直接使用云客模板，点击“保存”不提交接口审核；\r\n编辑模板流程说明：判断模板类型、模板内容是否变更，是则按新建模板流程判断\r\n5、消息中心-新建模板：模板内容可重复----04月09日会议确定并更改\r\n6、租户模板库和云客模板库共用一个code----04月09日会议确定并更改\r\n7、删除：删除租户模板时，对应云客模板关联租户模板数=0，则删除云客模板----04月09日会议确定并更改', '/graduation/9df853b3-a279-4500-9c70-710464809fb3~-dev_management.sql', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 19:14:02', '2021-05-02 19:14:02');

-- ----------------------------
-- Table structure for directory
-- ----------------------------
DROP TABLE IF EXISTS `directory`;
CREATE TABLE `directory`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int(11) NOT NULL COMMENT '1. 需求文档 2. 规范文档 3.开发文档 4.其它',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of directory
-- ----------------------------
INSERT INTO `directory` VALUES ('15263b6f-0110-4772-9753-3597a482842f', 1, 'V1.7', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '2021-03-24 21:27:20', '2021-03-24 21:17:44');
INSERT INTO `directory` VALUES ('1fbb08ae-005c-4db8-9735-1f2f122ebe82', 3, 'V1.8', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '2021-03-24 21:27:21', '2021-03-24 21:25:55');
INSERT INTO `directory` VALUES ('1fbb08ae-005c-4db8-9735-1f2f122ebe84', 2, 'V1.8', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '2021-03-24 21:27:21', '2021-03-24 21:25:55');
INSERT INTO `directory` VALUES ('1fbb08ae-005c-4db8-9735-1f2f122ebe85', 1, 'V1.8', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '2021-03-24 21:27:21', '2021-03-24 21:25:55');
INSERT INTO `directory` VALUES ('614b74b2-cddd-4931-91b6-3ff90c6e6063', 2, 'V1.7', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '2021-04-04 23:10:09', '2021-03-24 21:25:55');

-- ----------------------------
-- Table structure for document
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1. 需求文档 2. 规范文档 3.开发文档 4.其它',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `path` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `directory_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES ('0c2fd1d3-1699-40c0-a574-b22bf3c3e64f', NULL, '李景根-毕业论文.doc', '/graduation/b1dc6bc6-0c27-4b09-a409-00011a12ddf5~-李景根-毕业论文.doc', '', '', '2021-05-07 22:42:04', '2021-05-07 22:42:04');
INSERT INTO `document` VALUES ('2e55a8fa-1763-4f52-8134-d4402dd9288d', NULL, '毕业论文撰写规范.doc', '/graduation/552fa152-9c1e-4764-a525-ade00ba88bca~-毕业论文撰写规范.doc', '', '', '2021-05-07 22:41:40', '2021-05-07 22:41:40');
INSERT INTO `document` VALUES ('45836ee8-44b5-4ea5-8de4-9fa9add44def', 1, 'test_file2', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '15263b6f-0110-4772-9753-3597a482842f', '2021-03-29 21:40:30', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bc5f5aa2-7330-46c0-ad7d-a828a38b11db', 1, 'test_file', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', NULL, '2021-03-29 21:40:30', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bc5f5aa2-7330-46c0-ad7d-a828a38b11de', 2, 'test_file', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', NULL, '2021-03-29 21:40:30', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bc5f5aa2-7330-46c0-ad7d-a828a38b11dq', 3, 'test_file', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '1fbb08ae-005c-4db8-9735-1f2f122ebe82', '2021-05-06 20:27:11', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bc5f5aa2-7330-46c0-ad7d-a828a38b11dw', 2, 'test_file', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '1fbb08ae-005c-4db8-9735-1f2f122ebe84', '2021-05-06 20:27:11', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bc5f5aa2-7330-46c0-ad7d-a828a38b22dq', 3, 'test_file', 'localhost', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '', '2021-05-06 20:27:11', '2021-03-29 21:40:33');
INSERT INTO `document` VALUES ('bcb1269b-344f-44ad-9fb6-a6e91fd86403', NULL, '17级毕业初审（计算机科学与工程学院）202004261.zip', '/graduation/3c2c20ac-7afe-429e-8349-4a67c4cd4e2d~-17级毕业初审（计算机科学与工程学院）202004261.zip', '', '', '2021-05-07 22:41:22', '2021-05-07 22:41:22');
INSERT INTO `document` VALUES ('c788ecb5-cdc3-49bf-9872-0dea3fb27b90', 1, '毕业论文撰写规范.doc', '/graduation/2d09de20-3042-4601-87eb-89d9dfceae78~-毕业论文撰写规范.doc', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '', '2021-05-07 21:14:47', '2021-05-07 21:14:47');
INSERT INTO `document` VALUES ('ebbbaa41-6acb-4b89-9411-2d1b7a4f6676', 1, '草稿.docx', '/graduation/6cbb3cf7-db7f-4e96-b569-c17fbfd075f7~-草稿.docx', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', '1fbb08ae-005c-4db8-9735-1f2f122ebe85', '2021-05-07 23:15:17', '2021-05-07 23:15:17');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `project` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `from_user` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `to_user` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_read` int(255) NOT NULL,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  `to_user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('0181692a-006e-4b76-a1a0-470116a9cb2f', '支付中心', '李明', '张三', 0, 'wwww', '2021-05-01 20:10:05', '2021-05-01 20:10:05', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('0ee4f50f-265c-457a-b5af-419edef296a1', '支付中心', '李明', '李大聪明', 0, '123131', '2021-05-01 20:10:40', '2021-05-01 20:10:40', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('1a2c6479-bdec-4a5c-aad9-8781621d6af1', '支付中心', '李大聪明', '李三', 0, '【测试】【发送短信/短信模板】增加字数实时计算', '2021-05-07 21:04:32', '2021-05-07 21:04:32', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('1a5274a6-fcab-4b12-b55b-76091c1f8516', '支付中心', '李明', '张三', 0, 'tttt', '2021-05-01 17:11:04', '2021-05-01 17:11:04', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('1af1dfee-23db-48e8-9684-efd0434fdd66', '支付中心', '李大聪明', '李三', 0, '【测试】支付中心', '2021-05-04 16:23:31', '2021-05-04 16:23:31', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('210a7de9-5ae1-41c7-bb32-9a470d5b8c2e', '支付中心', '李明', '李大聪明', 0, 'tttttttttttttttt', '2021-05-01 13:36:36', '2021-05-01 13:36:36', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('29ae571c-3701-4162-b4d3-52eecb5ad366', '支付中心', '李明', '姚大聪明', 0, '【接口】对外接口文档维护', '2021-05-02 19:07:07', '2021-05-02 19:07:07', 'e1a8f7b5-49f0-4815-bf7f-001880d478db');
INSERT INTO `message` VALUES ('2cc43a71-c7e5-4ee9-998d-9be1de47df54', '支付中心', '李大聪明', '张四', 0, '【测试】支付中心', '2021-05-04 16:34:47', '2021-05-04 16:34:47', '804b7fbb-d6a1-4329-8cbd-36bd7c740738');
INSERT INTO `message` VALUES ('3d9679c3-44ad-4fdf-829e-7f9adbc5d59b', '支付中心', '李明', '张三', 0, '123', '2021-05-01 16:11:56', '2021-05-01 16:11:56', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('452d4d93-04b0-49f4-a647-c843a76827a7', '支付中心', '李大聪明', '李三', 0, '【测试】【发送短信/短信模板】增加字数实时计算', '2021-05-07 21:06:54', '2021-05-07 21:06:54', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('45844ae6-8ca7-4dd5-92fb-da6996331348', '支付中心', '李明', '张三', 0, 'NMSLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL', '2021-05-01 19:26:08', '2021-05-01 19:26:08', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('4b76f548-5ffd-4c94-aad5-35fdd1808d8d', '房源中心', '李明', '李大聪明', 0, '【消息中心-发送短信】批量发送，支持下载模板、下载错误原因', '2021-05-02 19:12:31', '2021-05-02 19:12:31', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('51572f41-b9c0-4c46-8177-cbf0f4d7026e', '支付中心', '李景根1', '姚德春', 1, '1231313', '2021-04-28 22:09:18', '2021-04-28 22:09:18', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('57307b45-05b8-4c34-a201-02c4d9205e75', '支付中心', '李明', '李大聪明', 0, '【发送短信/短信模板】增加字数实时计算', '2021-05-02 19:05:37', '2021-05-02 19:05:37', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('5eb73e74-ff6b-408b-8800-4c72af81a4c9', '支付中心', '李大聪明', '张四', 0, '【测试】支付中心', '2021-05-04 16:35:13', '2021-05-04 16:35:13', '804b7fbb-d6a1-4329-8cbd-36bd7c740738');
INSERT INTO `message` VALUES ('5ef9c053-9162-4355-81e4-867b8c414cd5', '支付中心', '李大聪明', '李三', 0, '【测试】支付中心', '2021-05-04 16:24:28', '2021-05-04 16:24:28', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('6276f5b7-36fc-48ac-a7e1-b3dcf132ca91', '支付中心', '李大聪明', '张四', 0, '【测试】支付中心', '2021-05-04 16:42:04', '2021-05-04 16:42:04', '804b7fbb-d6a1-4329-8cbd-36bd7c740738');
INSERT INTO `message` VALUES ('631a3ad7-9aa6-418c-9d26-718656ec03f9', '支付中心', '李大聪明', '李三', 0, '【测试】【发送短信/短信模板】增加字数实时计算', '2021-05-07 21:06:02', '2021-05-07 21:06:02', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('659bf0d0-a7e0-483d-af89-85d283350bfe', '支付中心', '李明', '张三', 0, '213', '2021-05-01 16:11:29', '2021-05-01 16:11:29', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('67e192cd-356e-474b-a086-68cd9079c9db', '支付中心', '李明', '王五', 0, '接手微信小程序（接手云店）', '2021-05-02 19:08:39', '2021-05-02 19:08:39', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e');
INSERT INTO `message` VALUES ('776c94a3-0508-4889-9238-8bb1f00150ef', '房源中心', '李明', '李大聪明', 0, '【消息中心】页面查询性能优化', '2021-05-02 19:13:10', '2021-05-02 19:13:10', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('77fd5bf8-f3ec-4e6d-8171-efd9b32affd5', '支付中心', '李明', '张三', 0, '312', '2021-05-01 16:12:55', '2021-05-01 16:12:55', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('85b10822-30ec-4a43-8895-3ea1ba9bd658', '支付中心', '李大聪明', '李三', 0, '【测试】【发送短信/短信模板】增加字数实时计算', '2021-05-07 21:04:16', '2021-05-07 21:04:16', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('8c0251bc-ecd7-47be-9158-640c2f0a2beb', '支付中心', '李明', '李大聪明', 0, '【接口】包消息订阅功能的接口输出给外部', '2021-05-02 19:06:08', '2021-05-02 19:06:08', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('8fee83b0-466d-424d-b538-9d02b1125483', '支付中心', '李明', '张三', 0, '【数据】统计打开短链接的具体用户', '2021-05-02 19:07:58', '2021-05-02 19:07:58', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('997c8bf8-5e7a-4336-a7ef-dcc27bebc0ee', '支付中心', '李明', '张三', 0, '【接口】短信平台接口文档定义优化', '2021-04-30 11:36:33', '2021-04-30 11:36:33', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('a214a04d-f339-44cc-956e-3235926bc84a', '支付中心', '李景根1', '姚德春', 0, '测试添加', '2021-04-28 22:09:37', '2021-04-28 22:09:37', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('a6fd39d3-5d7a-4c81-9bc9-e8b400af9235', '房源中心', '李明', '张四', 0, '【消息中心/短信平台】短信模板做成云客级、租户级', '2021-05-02 19:14:02', '2021-05-02 19:14:02', '804b7fbb-d6a1-4329-8cbd-36bd7c740738');
INSERT INTO `message` VALUES ('b23d2798-2c89-4433-8280-adfa1da89ad4', '支付中心', '李大聪明', '李三', 0, '【测试】【发送短信/短信模板】增加字数实时计算', '2021-05-07 21:03:51', '2021-05-07 21:03:51', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('bdf5633d-4bab-4b43-8762-c117381a1e63', '支付中心', '李明', '莉莉', 0, '【短信平台】控制营销短信发送频率', '2021-05-02 19:04:44', '2021-05-02 19:04:44', '76fbefa3-3433-44e5-b4af-86ed72c41593');
INSERT INTO `message` VALUES ('c7c7505c-d6ba-402f-b4c6-0c632e18a529', '支付中心', '李大聪明', '李三', 0, '【测试】支付中心', '2021-05-04 16:42:38', '2021-05-04 16:42:38', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('c988e949-2ee3-4f72-b73b-eac32df874ed', '支付中心', '李大聪明', '李三', 0, '【测试】支付中心', '2021-05-04 16:44:06', '2021-05-04 16:44:06', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f');
INSERT INTO `message` VALUES ('cb2bab07-74a6-4b85-a94f-9a68c552e600', '支付中心', '李明', '王五', 0, '营销短信模板案例展示', '2021-05-02 19:05:12', '2021-05-02 19:05:12', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e');
INSERT INTO `message` VALUES ('d16e4e3e-1be9-4f50-87d0-e47a6dd48b34', '支付中心', '李明', '张三', 0, 'tttt', '2021-05-01 16:09:54', '2021-05-01 16:09:54', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('d2edfcb3-665b-446e-aeac-cc1e04996698', '移动销售', '李明', '王五', 0, '【接口】短信白名单创建/删除接口', '2021-05-02 19:16:18', '2021-05-02 19:16:18', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e');
INSERT INTO `message` VALUES ('d96b0e85-cf71-4617-90dd-183720152333', '支付中心', '李明', '张三', 0, '123', '2021-05-01 16:13:50', '2021-05-01 16:13:50', '806cf5fc-f827-4b92-b797-b366f13d16bc');
INSERT INTO `message` VALUES ('e13cf7da-b247-4cd3-8764-3f9700b58e4f', '支付中心', '李明', '王五', 0, '【Bug转需求】初始密码短信。发送记录、数据库存储，密码都是明文', '2021-05-02 19:04:07', '2021-05-02 19:04:07', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e');
INSERT INTO `message` VALUES ('f00498d1-46b6-4a40-b4c6-62c3d82c664e', '活动中台', '李明', '李大聪明', 0, '【短信平台-通道管理】列表展示短信类型', '2021-05-02 19:14:48', '2021-05-02 19:14:48', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('f86f107b-e591-4622-813e-5c05a82f7a6d', '支付中心', '李明', '姚大聪明', 0, '微信公众号消息订阅的功能', '2021-05-02 19:06:34', '2021-05-02 19:06:34', 'e1a8f7b5-49f0-4815-bf7f-001880d478db');
INSERT INTO `message` VALUES ('fccba964-d202-4663-98bc-7a5daaca643a', '支付中心', '李景根1', '姚德春', 1, '132313', '2021-04-28 22:08:45', '2021-04-28 22:08:45', 'af641119-abf4-4f72-b3ef-3724fedca776');
INSERT INTO `message` VALUES ('fd52ef9c-efe8-4e81-b57b-d76f7901e053', '支付中心', '李明', '赵四', 0, '【接口】短信模板接口优化', '2021-05-02 19:07:34', '2021-05-02 19:07:34', '9a635afe-cc2f-4920-bea9-4f66740bece9');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名字',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('0e9f1d0f-ea37-425c-bc52-71c79796aa90', '房源中心', '2021-04-30 21:36:20', '2021-04-30 21:36:20');
INSERT INTO `project` VALUES ('1c7f9173-422a-416a-93cb-57b5c99901b0', '活动中台', '2021-04-30 21:37:12', '2021-04-30 21:37:12');
INSERT INTO `project` VALUES ('28a6e232-6af5-4d88-861c-380a19bf699b', '支付中心', '2021-04-22 21:46:51', '2021-04-22 21:46:51');
INSERT INTO `project` VALUES ('859ede7e-250f-422e-ad7a-09b61d90077c', '移动销售', '2021-04-04 21:30:33', '2021-04-04 21:30:35');
INSERT INTO `project` VALUES ('97b7d957-61f8-4f56-b2d8-845d56dbd2ee', '管理平台', '2021-04-22 21:47:27', '2021-04-22 21:47:27');
INSERT INTO `project` VALUES ('9ae9ba69-a8cd-4890-9815-4fa4a8f73d7c', '组件中心', '2021-04-30 21:36:28', '2021-04-30 21:36:28');
INSERT INTO `project` VALUES ('b88d19b6-59c7-4989-949a-1d70bfabc1cc', '内容中心', '2021-03-23 21:14:35', '2021-03-23 21:14:37');

-- ----------------------------
-- Table structure for project_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `project_user_relation`;
CREATE TABLE `project_user_relation`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关系表id',
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `project_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目id',
  `role` int(11) NULL DEFAULT 0 COMMENT '角色',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_project_idx`(`user_id`, `project_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_user_relation
-- ----------------------------
INSERT INTO `project_user_relation` VALUES ('00fa3d23-997a-49d6-a907-73b11f474dd6', 'af641119-abf4-4f72-b3ef-3724fedca776', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-05-02 19:01:46', '2021-05-02 19:01:46');
INSERT INTO `project_user_relation` VALUES ('09288a93-0b20-44d1-878b-d9b2728cadd5', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 3, '2021-05-02 19:13:28', '2021-05-02 19:13:28');
INSERT INTO `project_user_relation` VALUES ('129c29cb-9d73-49b1-b287-2a0b2cad83a8', '76fbefa3-3433-44e5-b4af-86ed72c41593', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-05-02 19:01:42', '2021-05-02 19:01:42');
INSERT INTO `project_user_relation` VALUES ('18509dbc-aa09-4ce2-9ab3-5a581f3ded15', '76fbefa3-3433-44e5-b4af-86ed72c41593', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 2, '2021-05-02 19:13:34', '2021-05-02 19:13:34');
INSERT INTO `project_user_relation` VALUES ('2a034511-e97c-4e92-9d8a-a40582620cdf', '9a635afe-cc2f-4920-bea9-4f66740bece9', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-05-02 19:01:49', '2021-05-02 19:01:49');
INSERT INTO `project_user_relation` VALUES ('2b99e983-ce11-419b-b26d-a9150f7e0f08', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-05-02 19:01:53', '2021-05-02 19:01:53');
INSERT INTO `project_user_relation` VALUES ('33552592-4367-4381-aa57-10ab2c470d07', '804b7fbb-d6a1-4329-8cbd-36bd7c740738', '28a6e232-6af5-4d88-861c-380a19bf699b', 3, '2021-04-30 11:35:29', '2021-04-30 11:35:29');
INSERT INTO `project_user_relation` VALUES ('35386b42-d5fa-40c7-96ac-9c643f199c6f', 'af641119-abf4-4f72-b3ef-3724fedca776', '859ede7e-250f-422e-ad7a-09b61d90077c', 3, '2021-05-02 19:15:44', '2021-05-02 19:15:44');
INSERT INTO `project_user_relation` VALUES ('3802c0f8-01b0-4c0e-af99-fc1dd256b9a7', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '28a6e232-6af5-4d88-861c-380a19bf699b', 5, '2021-04-22 21:46:51', '2021-04-22 21:46:51');
INSERT INTO `project_user_relation` VALUES ('3af30dd8-aac8-4e0f-9a6a-f64b5c1ecaf0', 'e1a8f7b5-49f0-4815-bf7f-001880d478db', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 2, '2021-05-02 19:13:24', '2021-05-02 19:13:24');
INSERT INTO `project_user_relation` VALUES ('3ca3f767-da6b-47c2-b9ac-333f78d92105', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', 5, '2021-04-20 21:55:31', '2021-03-23 21:15:21');
INSERT INTO `project_user_relation` VALUES ('3fff57df-f8b6-4726-8a4b-5de5b194d21e', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '859ede7e-250f-422e-ad7a-09b61d90077c', 2, '2021-05-02 19:15:40', '2021-05-02 19:15:40');
INSERT INTO `project_user_relation` VALUES ('4400bc15-6e28-4f3a-9acd-58868d757df5', '806cf5fc-f827-4b92-b797-b366f13d16bc', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 3, '2021-05-02 19:13:21', '2021-05-02 19:13:21');
INSERT INTO `project_user_relation` VALUES ('680d31e2-8e77-4699-a819-670589692f41', 'af641119-abf4-4f72-b3ef-3724fedca776', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 2, '2021-04-30 21:37:52', '2021-04-30 21:37:52');
INSERT INTO `project_user_relation` VALUES ('7bc8c62a-9376-4a40-8526-c8622f476069', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '1c7f9173-422a-416a-93cb-57b5c99901b0', 5, '2021-04-30 21:37:12', '2021-04-30 21:37:12');
INSERT INTO `project_user_relation` VALUES ('9a9e0ac9-b22c-4d63-a736-0fbd33969b20', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '859ede7e-250f-422e-ad7a-09b61d90077c', 5, '2021-04-22 21:42:56', '2021-04-22 21:42:20');
INSERT INTO `project_user_relation` VALUES ('9f73bcd2-65d7-4062-b280-0f1bea57e37f', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '97b7d957-61f8-4f56-b2d8-845d56dbd2ee', 5, '2021-04-22 21:47:27', '2021-04-22 21:47:27');
INSERT INTO `project_user_relation` VALUES ('b7674c4b-f820-4895-9e44-8e47e76506f8', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '9ae9ba69-a8cd-4890-9815-4fa4a8f73d7c', 5, '2021-04-30 21:36:28', '2021-04-30 21:36:28');
INSERT INTO `project_user_relation` VALUES ('bacb7668-bdf1-4a23-b483-98b4084b4499', '806cf5fc-f827-4b92-b797-b366f13d16bc', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-04-30 11:35:18', '2021-04-30 11:35:18');
INSERT INTO `project_user_relation` VALUES ('c0c48f4a-d74b-486c-af26-95a0260baeb9', 'af641119-abf4-4f72-b3ef-3724fedca776', 'b88d19b6-59c7-4989-949a-1d70bfabc1cc', 2, '2021-04-30 21:07:33', '2021-04-30 21:07:33');
INSERT INTO `project_user_relation` VALUES ('c38dbe44-9f70-4219-a6ef-b9a4de26ab03', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 5, '2021-04-30 21:36:20', '2021-04-30 21:36:20');
INSERT INTO `project_user_relation` VALUES ('c6e633dc-0835-45d5-bf3d-de4e4c4890d3', 'af641119-abf4-4f72-b3ef-3724fedca776', '1c7f9173-422a-416a-93cb-57b5c99901b0', 2, '2021-04-30 21:37:56', '2021-04-30 21:37:56');
INSERT INTO `project_user_relation` VALUES ('d263a746-2319-4b5d-b52f-4fa670b9d556', 'e1a8f7b5-49f0-4815-bf7f-001880d478db', '28a6e232-6af5-4d88-861c-380a19bf699b', 2, '2021-05-02 19:01:57', '2021-05-02 19:01:57');
INSERT INTO `project_user_relation` VALUES ('df8d7a12-ea2a-496f-a899-13f5b158d95b', '804b7fbb-d6a1-4329-8cbd-36bd7c740738', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', 2, '2021-05-02 19:13:32', '2021-05-02 19:13:32');
INSERT INTO `project_user_relation` VALUES ('e457d46c-1362-464d-9f38-8a0a0f130975', 'af641119-abf4-4f72-b3ef-3724fedca776', '9ae9ba69-a8cd-4890-9815-4fa4a8f73d7c', 2, '2021-04-30 21:37:46', '2021-04-30 21:37:46');
INSERT INTO `project_user_relation` VALUES ('ff099b37-1b99-4a5d-8712-2bac0a93b260', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f', '28a6e232-6af5-4d88-861c-380a19bf699b', 3, '2021-04-30 11:35:12', '2021-04-30 11:35:12');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `batch_no` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `project_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL COMMENT '1. 需求 2.测试 3. bug',
  `status` int(11) NULL DEFAULT NULL COMMENT '1. 进行中 2.已完成 3. 已上线 4. 已关闭 5.开发修复中 6. 待上线',
  `priority` int(11) NULL DEFAULT NULL COMMENT '1. low 2. middle 3. high',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '任务预期开始时间',
  `create_user` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '任务预期完成时间',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('090d2a89-b32e-4159-b468-8edf848439d7', '88b8816e-e2a6-4fd5-9970-2dee6470e59e', '9a635afe-cc2f-4920-bea9-4f66740bece9', '28a6e232-6af5-4d88-861c-380a19bf699b', '【接口】短信模板接口优化', 1, 2, 1, '2021-05-07 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-22 00:00:00', '2021-05-02 19:10:19', '2021-05-02 19:07:34');
INSERT INTO `task` VALUES ('10df8dd1-2523-47f2-b08b-7d720a9142de', '1d6df9b4-17ca-4586-bc10-af91a7b39505', 'e1a8f7b5-49f0-4815-bf7f-001880d478db', '28a6e232-6af5-4d88-861c-380a19bf699b', '微信公众号消息订阅的功能', 1, 2, 3, '2021-05-16 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-22 00:00:00', '2021-05-02 19:18:30', '2021-05-02 19:06:34');
INSERT INTO `task` VALUES ('11b48922-3ac7-466e-a60d-2701134af1c0', 'cfc8fe44-b320-4634-912f-0f3ac48333ab', 'af641119-abf4-4f72-b3ef-3724fedca776', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', '【消息中心】页面查询性能优化', 1, 1, 2, '2021-05-01 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 00:00:00', '2021-05-02 19:13:10', '2021-05-02 19:13:10');
INSERT INTO `task` VALUES ('18405b90-6fc2-417e-9078-03354fcc8fbc', 'ef0475e9-4b41-47b7-8231-bf81f927304e', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '28a6e232-6af5-4d88-861c-380a19bf699b', '【Bug转需求】初始密码短信。发送记录、数据库存储，密码都是明文', 1, 2, 1, '2021-05-23 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-31 00:00:00', '2021-05-02 19:18:32', '2021-05-02 19:04:07');
INSERT INTO `task` VALUES ('270b3978-7067-4890-ae0c-ca13b9fb4362', 'e7f7111e-2a21-40e8-9376-683bfd47066f', '806cf5fc-f827-4b92-b797-b366f13d16bc', '28a6e232-6af5-4d88-861c-380a19bf699b', '【数据】统计打开短链接的具体用户', 1, 2, 3, '2021-05-08 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-14 00:00:00', '2021-05-02 19:07:58', '2021-05-02 19:07:58');
INSERT INTO `task` VALUES ('3c7b4049-25ed-478a-b627-a80b31798d6b', '38aaef5a-3ea7-4902-8286-d303364d065c', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f', '28a6e232-6af5-4d88-861c-380a19bf699b', '【测试】【发送短信/短信模板】增加字数实时计算', 2, 1, 3, '2021-05-07 00:00:00', 'af641119-abf4-4f72-b3ef-3724fedca776', '2021-05-29 00:00:00', '2021-05-07 21:06:54', '2021-05-07 21:06:54');
INSERT INTO `task` VALUES ('49e103db-e832-49dd-bcce-f6425c4dd32c', '2f169638-8dd8-497b-a5de-60aecd77ccef', '76fbefa3-3433-44e5-b4af-86ed72c41593', '28a6e232-6af5-4d88-861c-380a19bf699b', '【短信平台】控制营销短信发送频率', 1, 1, 1, '2021-05-23 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-29 00:00:00', '2021-05-02 19:04:44', '2021-05-02 19:04:44');
INSERT INTO `task` VALUES ('6dbce00c-aca7-4137-b612-183e71968e8c', '2040a130-e475-45bf-875e-ce4b1cdd781b', 'af641119-abf4-4f72-b3ef-3724fedca776', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', '【消息中心-发送短信】批量发送，支持下载模板、下载错误原因', 1, 1, 1, '2021-05-23 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-30 00:00:00', '2021-05-02 19:12:31', '2021-05-02 19:12:31');
INSERT INTO `task` VALUES ('84f407e1-db08-4a68-9570-bbff83411ac0', '8887d1af-8ce9-4bfc-a5c3-2b972f06f4d3', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '28a6e232-6af5-4d88-861c-380a19bf699b', '营销短信模板案例展示', 1, 2, 2, '2021-05-02 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-09 00:00:00', '2021-05-02 19:18:34', '2021-05-02 19:05:12');
INSERT INTO `task` VALUES ('cd2a5d69-d6d9-4562-b32d-d836c628ed11', 'c2a82734-35f6-40d2-8a50-e05fdfc7e541', 'af641119-abf4-4f72-b3ef-3724fedca776', '1c7f9173-422a-416a-93cb-57b5c99901b0', '【短信平台-通道管理】列表展示短信类型', 1, 1, 2, '2021-05-16 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-29 00:00:00', '2021-05-02 19:14:48', '2021-05-02 19:14:48');
INSERT INTO `task` VALUES ('cf94821c-4424-44ee-87e6-879170ca8cfc', 'baf6de3f-4c72-4977-8c4f-a40281a464df', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '28a6e232-6af5-4d88-861c-380a19bf699b', '接手微信小程序（接手云店）', 1, 2, 2, '2021-04-09 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-01 00:00:00', '2021-05-02 19:08:39', '2021-05-02 19:08:39');
INSERT INTO `task` VALUES ('d3734bfa-a1d9-4a3d-8394-9daf88095784', '6779fc71-9b62-49a1-bf08-f19ea11f4ddd', 'e1a8f7b5-49f0-4815-bf7f-001880d478db', '28a6e232-6af5-4d88-861c-380a19bf699b', '【接口】对外接口文档维护', 1, 2, 3, '2021-05-09 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-14 00:00:00', '2021-05-02 19:18:36', '2021-05-02 19:07:07');
INSERT INTO `task` VALUES ('d809c055-3c84-4756-87f5-108cc88b0742', 'b613d432-ffba-4950-8350-025dc492a4b1', '804b7fbb-d6a1-4329-8cbd-36bd7c740738', '0e9f1d0f-ea37-425c-bc52-71c79796aa90', '【消息中心/短信平台】短信模板做成云客级、租户级', 1, 2, 1, '2021-05-16 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-29 00:00:00', '2021-05-02 19:18:37', '2021-05-02 19:14:02');
INSERT INTO `task` VALUES ('d9eaff19-77c6-4171-b3aa-4346b09032b4', '08c0007a-42f2-489c-a8f4-54ebc7f33132', '217bd46b-aaf6-4bb6-ae95-9e1dce302b6f', '28a6e232-6af5-4d88-861c-380a19bf699b', '【测试】【数据】统计打开短链接的具体用户', 2, 1, 1, '2021-05-04 00:00:00', 'af641119-abf4-4f72-b3ef-3724fedca776', '2021-05-06 00:00:00', '2021-05-04 16:44:06', '2021-05-04 16:44:06');
INSERT INTO `task` VALUES ('da8f1dc3-3212-4e6e-8ef4-1b5bd08d53da', '86ce9d49-acaf-46f8-aeea-f24da6e6938a', 'af641119-abf4-4f72-b3ef-3724fedca776', '28a6e232-6af5-4d88-861c-380a19bf699b', '【发送短信/短信模板】增加字数实时计算', 1, 2, 2, '2021-05-02 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-08 00:00:00', '2021-05-02 20:33:09', '2021-05-02 19:05:37');
INSERT INTO `task` VALUES ('dfc8bf8d-db88-4b59-b447-96b5195d651a', '53fe54ae-d1bb-4ef0-a453-6760bec362ed', 'af641119-abf4-4f72-b3ef-3724fedca776', '28a6e232-6af5-4d88-861c-380a19bf699b', '【接口】包消息订阅功能的接口输出给外部', 1, 2, 1, '2021-05-09 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-09 00:00:00', '2021-05-02 19:06:08', '2021-05-02 19:06:08');
INSERT INTO `task` VALUES ('ff02099a-d96d-4923-8c77-e47518830539', 'abce23e6-5127-49dd-a9f6-cbf97f85d595', '3d04102e-dbc5-4b8a-b5b2-6171f84d987e', '859ede7e-250f-422e-ad7a-09b61d90077c', '【接口】短信白名单创建/删除接口', 1, 1, 3, '2021-05-01 00:00:00', 'f0e94adc-d80d-4c18-b576-acd1e3f6ba03', '2021-05-02 00:00:00', '2021-05-02 19:16:18', '2021-05-02 19:16:18');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `relation_task_id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联开发单',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('ef315810-0712-433a-b9c5-ca4ffb862dc7', '1221', 'd9eaff19-77c6-4171-b3aa-4346b09032b4', '270b3978-7067-4890-ae0c-ca13b9fb4362', '2021-05-04 17:29:09', '2021-05-04 16:44:06');
INSERT INTO `test` VALUES ('fb73fd3c-77e4-4324-91a7-be3c5e954a74', '测试测试测试', '3c7b4049-25ed-478a-b627-a80b31798d6b', 'da8f1dc3-3212-4e6e-8ef4-1b5bd08d53da', '2021-05-07 21:06:54', '2021-05-07 21:06:54');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户账号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_account`(`user_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('217bd46b-aaf6-4bb6-ae95-9e1dce302b6f', 'lijg05', '李三', '123456', '2021-04-30 11:34:06', '2021-04-10 00:55:04');
INSERT INTO `user` VALUES ('368b11c9-6330-419c-8b87-f8da0812b992', 'lili04', '丽丽', '123456', '2021-05-04 16:15:12', '2021-05-04 16:15:12');
INSERT INTO `user` VALUES ('3d04102e-dbc5-4b8a-b5b2-6171f84d987e', 'lijg02', '王五', '123456', '2021-04-30 11:34:13', '2021-04-09 14:44:08');
INSERT INTO `user` VALUES ('76fbefa3-3433-44e5-b4af-86ed72c41593', 'lili', '莉莉', '123456', '2021-05-01 20:17:29', '2021-05-01 20:17:04');
INSERT INTO `user` VALUES ('804b7fbb-d6a1-4329-8cbd-36bd7c740738', 'lijg01', '张四', '123456', '2021-04-30 11:34:19', '2021-04-09 14:42:47');
INSERT INTO `user` VALUES ('806cf5fc-f827-4b92-b797-b366f13d16bc', 'lijg04', '张三', '123456', '2021-04-30 11:34:24', '2021-04-10 00:55:04');
INSERT INTO `user` VALUES ('9a635afe-cc2f-4920-bea9-4f66740bece9', 'zhaosi01', '赵四', '123456', '2021-05-01 20:24:59', '2021-05-01 20:23:32');
INSERT INTO `user` VALUES ('af641119-abf4-4f72-b3ef-3724fedca776', 'yaodc01', '李大聪明', '123456', '2021-04-30 11:34:40', '2021-04-09 14:43:02');
INSERT INTO `user` VALUES ('e1a8f7b5-49f0-4815-bf7f-001880d478db', 'lijg03', '姚大聪明', '123456', '2021-04-30 11:34:44', '2021-04-10 00:55:04');
INSERT INTO `user` VALUES ('f0e94adc-d80d-4c18-b576-acd1e3f6ba03', 'admin', '李明', '123456', '2021-04-30 11:34:56', '2021-03-15 20:38:33');

SET FOREIGN_KEY_CHECKS = 1;
