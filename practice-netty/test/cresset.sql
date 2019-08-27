/*
Created: 2019-07-24
Modified: 2019-08-26
Project: cresset
Model: MySQL 5.5
Database: MySQL 5.5
*/


-- Create tables section -------------------------------------------------

-- Table cresset_projects

CREATE TABLE `cresset_projects`
(
  `project_id` Int NOT NULL AUTO_INCREMENT,
  `project_type` Int NOT NULL
 COMMENT '工程类型：0 - 无效; 1 - 模板; 2 - 尺寸延展; 3 - 普通图片处理',
  `name` Varchar(20) NOT NULL
 COMMENT '工程名称',
  `status` Int NOT NULL
 COMMENT '状态',
  `creator` Varchar(80)
 COMMENT '创建者',
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`project_id`)
)
 COMMENT = '工程表'
;

-- Table cresset_super_images

CREATE TABLE `cresset_super_images`
(
  `super_image_id` Int NOT NULL AUTO_INCREMENT,
  `name` Varchar(128) NOT NULL
 COMMENT '名称',
  `width` Int NOT NULL
 COMMENT '宽',
  `height` Int NOT NULL
 COMMENT '高',
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`super_image_id`)
)
 COMMENT = '母图配置表'
;

-- Table cresset_sub_images

CREATE TABLE `cresset_sub_images`
(
  `sub_image_id` Int NOT NULL AUTO_INCREMENT,
  `super_image_id` Int
 COMMENT '母图ID',
  `name` Varchar(20) NOT NULL
 COMMENT '名称',
  `identifier` Varchar(30)
 COMMENT '标识',
  `width` Int NOT NULL
 COMMENT '宽',
  `height` Int NOT NULL
 COMMENT '高',
  `optim` Int NOT NULL DEFAULT 0
 COMMENT '是否优化',
  `quality` Int NOT NULL DEFAULT 0
 COMMENT '图片质量',
  `size` Int NOT NULL DEFAULT 0
 COMMENT '最大文件大小',
  `status` Int NOT NULL DEFAULT 0
 COMMENT '状态',
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`sub_image_id`)
)
 COMMENT = '子图配置表'
;

CREATE INDEX `idx_sub_images` ON `cresset_sub_images` (`super_image_id`)
;

-- Table cresset_project_super_images

CREATE TABLE `cresset_project_super_images`
(
  `entity_id` Int NOT NULL,
  `super_image_id` Int NOT NULL,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`entity_id`)
)
;

CREATE INDEX `idx_project_super_images` ON `cresset_project_super_images` (`super_image_id`)
;

-- Table cresset_sub_image_tags

CREATE TABLE `cresset_sub_image_tags`
(
  `id` Int NOT NULL AUTO_INCREMENT,
  `sub_image_id` Int NOT NULL,
  `tag` Varchar(20) NOT NULL,
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL,
  `time_modified` Datetime,
  PRIMARY KEY (`id`)
)
;

CREATE UNIQUE INDEX `uk_sub_images_tags` ON `cresset_sub_image_tags` (`tag`,`sub_image_id`)
;

-- Table cresset_project_images

CREATE TABLE `cresset_project_images`
(
  `image_id` Int NOT NULL AUTO_INCREMENT,
  `entity_id` Int,
  `sub_id` Int NOT NULL DEFAULT 0
 COMMENT '子图ID，仅当工程为子母图时、并且上传的是子图的时候有效，其他填0
',
  `filename` Varchar(20) NOT NULL
 COMMENT '文件名',
  `filename_pattern` Varchar(80),
  `width` Int NOT NULL
 COMMENT '图像的目标宽度',
  `height` Int NOT NULL
 COMMENT '图像的目标高度',
  `optim` Int NOT NULL DEFAULT 0
 COMMENT '是否优化',
  `quality` Int NOT NULL DEFAULT 0
 COMMENT '图片质量，0~100',
  `size` Int NOT NULL DEFAULT 0
 COMMENT '最大文件大小',
  `original_uri` Varchar(1024)
 COMMENT '原图的 URI，由后台处理模块上传完毕之后填入',
  `original_filesize` Int NOT NULL DEFAULT 0
 COMMENT '原始文件大小，0表示无效',
  `uri` Varchar(1024)
 COMMENT '处理过的图片的URI，由后台处理模块上传完毕之后填入',
  `filesize` Int NOT NULL DEFAULT 0
 COMMENT '处理后文件大小，0表示无效',
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`image_id`)
)
;

CREATE INDEX `idx_project_images` ON `cresset_project_images` (`entity_id`)
;

-- Table cresset_project_entities

CREATE TABLE `cresset_project_entities`
(
  `entity_id` Int NOT NULL AUTO_INCREMENT,
  `project_id` Int NOT NULL,
  `entity_type` Int NOT NULL,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`entity_id`)
)
;

CREATE INDEX `idx_project_entities` ON `cresset_project_entities` (`project_id`)
;

-- Table cresset_templates

CREATE TABLE `cresset_templates`
(
  `template_id` Int NOT NULL AUTO_INCREMENT,
  `name` Varchar(20) NOT NULL
 COMMENT '名称',
  `width` Int NOT NULL
 COMMENT '模板的宽度',
  `height` Int NOT NULL
 COMMENT '模板的高度',
  `html` Text NOT NULL
 COMMENT '模板的html内容',
  `css` Text
 COMMENT '模板的css内容，可选',
  `color_system` Char(8)
 COMMENT '色系',
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Date,
  PRIMARY KEY (`template_id`)
)
 COMMENT = '模板表'
;

-- Table cresset_project_templates

CREATE TABLE `cresset_project_templates`
(
  `entity_id` Int NOT NULL,
  `template_id` Int,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`entity_id`)
)
;

CREATE INDEX `idx_project_templates` ON `cresset_project_templates` (`template_id`)
;

-- Table cresset_image_conversions

CREATE TABLE `cresset_image_conversions`
(
  `id` Int NOT NULL AUTO_INCREMENT,
  `image_id` Int,
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`id`)
)
;

CREATE INDEX `idx_image_conversions` ON `cresset_image_conversions` (`image_id`)
;

-- Table cresset_fonts

CREATE TABLE `cresset_fonts`
(
  `font_id` Int NOT NULL AUTO_INCREMENT,
  `family` Varchar(64) NOT NULL
 COMMENT '字体名称，即font-family',
  `stretch` Int NOT NULL DEFAULT 0
 COMMENT '字体拉伸，
0: normal; 
-1: semi-condensed;
-2: condensed;
-3: ultra-condensed;
-4: extra-condensed;
1: semi-expanded;
2: expanded;
3: extra-expanded;
4: ultra-expanded;',
  `style` Int NOT NULL DEFAULT 0
 COMMENT '字体风格；0: normal; 1: italic; 2: oblique',
  `weight` Int NOT NULL DEFAULT 400
 COMMENT '字体粗细程度；可选范围是：100, 200, 300, 400, 500, 600, 700, 800, 900
其中正常状态是400，该字段为0代表默认状态400；
名称对应关系如下：normal: 400; bold: 700; ',
  `uri` Varchar(1024) NOT NULL,
  `image` Varchar(256)
 COMMENT '字体的图片文件',
  `active_image` Varchar(256)
 COMMENT '字体的选中状态图片',
  `hover_image` Varchar(256)
 COMMENT '字体的hover状态图片',
  `status` Int NOT NULL DEFAULT 0
 COMMENT '状态',
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`font_id`)
)
 COMMENT = '字体表'
;

ALTER TABLE `cresset_fonts` ADD UNIQUE `uk_cresset_fonts` (`family`)
;

-- Table cresset_backgrounds

CREATE TABLE `cresset_backgrounds`
(
  `background_id` Int NOT NULL AUTO_INCREMENT,
  `name` Varchar(128) NOT NULL
 COMMENT '背景名称',
  `width` Int NOT NULL
 COMMENT '宽',
  `height` Int NOT NULL
 COMMENT '高',
  `uri` Varchar(1024) NOT NULL
 COMMENT '路径',
  `color_system` Char(8)
 COMMENT '色系',
  `memo` Varchar(2048)
 COMMENT '备注',
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`background_id`)
)
 COMMENT = '背景图表'
;

-- Table cresset_assets

CREATE TABLE `cresset_assets`
(
  `asset_id` Int NOT NULL AUTO_INCREMENT,
  `name` Varchar(128) NOT NULL
 COMMENT '名称',
  `asset_type` Int NOT NULL
 COMMENT '类型：0 无效，1. 图片',
  `uri` Varchar(1024) DEFAULT NULL
 COMMENT '路径',
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`asset_id`)
)
 COMMENT = '附件资源表'
;

-- Table cresset_background_tags

CREATE TABLE `cresset_background_tags`
(
  `id` Int NOT NULL AUTO_INCREMENT,
  `background_id` Int NOT NULL,
  `tag` Varchar(20) NOT NULL,
  `status` Int NOT NULL,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`id`)
)
;

CREATE UNIQUE INDEX `uk_background_tags` ON `cresset_background_tags` (`tag`,`background_id`)
;

-- Table cresset_asset_tags

CREATE TABLE `cresset_asset_tags`
(
  `id` Int NOT NULL AUTO_INCREMENT,
  `asset_id` Int NOT NULL,
  `tag` Varchar(20) NOT NULL,
  `status` Int NOT NULL,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`id`)
)
;

CREATE UNIQUE INDEX `uk_asset_tags` ON `cresset_asset_tags` (`tag`,`asset_id`)
;

-- Table cresset_template_tags

CREATE TABLE `cresset_template_tags`
(
  `id` Int NOT NULL AUTO_INCREMENT,
  `template_id` Int NOT NULL,
  `tag` Varchar(20) NOT NULL,
  `status` Int NOT NULL DEFAULT 0,
  `time_created` Datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_modified` Datetime,
  PRIMARY KEY (`id`)
)
;

CREATE UNIQUE INDEX `uk_template_tags` ON `cresset_template_tags` (`tag`,`template_id`)
;

