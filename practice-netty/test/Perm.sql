--工程接口权限
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project','创建工程', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project/{projectId}','删除工程', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project','修改工程', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project/projects','查询工程列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project/{projectId}','查询工程详情', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project/{projectId}','上传工程图片', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/project/deleteImage','删除工程图片', 'cresset');
--模板接口权限
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template','创建模板', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template','修改模板', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/templates','查询模板列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template/{templateId}','查询模板详情', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template','删除模板', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template/{templateId}/tag','添加模板标签', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template/{templateId}/tag','删除模板标签', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template/colorSystems','获取模板色系列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/template/tags','获取模板标签列表', 'cresset');
--背景图接口权限
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background','创建背景图', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/update','修改背景图', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/{backgroundId}','删除背景图', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/{backgroundId}','查询背景图', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/backgrounds','查询背景图列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/{backgroundId}/tag','添加背景图标签', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/{backgroundId}/tag','删除背景图标签', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/colorSystems','查询背景图色系列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/background/tags','查询背景标签列表', 'cresset');
--字库权限接口
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/font','添加字体', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/fonts','查询字体列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/font/{fontId}','删除字体', 'cresset');
--母图接口权限
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage','查询指定 ID 的母图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage/listByLimit','列出母图配置列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage/listByDetail','查询符合分辨率的母图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage','创建母图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage','修改母图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/superimage','删除母图配置', 'cresset');
--子图接口权限
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage','查询子图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage','创建子图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage','修改子图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage','删除子图配置', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage','根据母图查询子图列表', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage/tag','添加子图配置标签', 'cresset');
INSERT INTO `perm`(PERM_RESOURCE,PERM_NAME,P_APP_NAME) VALUES ('/api/config/subimage/tag','删除子图配置标签', 'cresset');
