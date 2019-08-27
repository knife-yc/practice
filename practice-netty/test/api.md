# API 接口规范

## 目录

- API 接口规范
  - [基本](#%e5%9f%ba%e6%9c%ac)
  - [接口格式](#%e6%8e%a5%e5%8f%a3%e6%a0%bc%e5%bc%8f)
    - [请求格式](#%e8%af%b7%e6%b1%82%e6%a0%bc%e5%bc%8f)
    - [响应格式](#%e5%93%8d%e5%ba%94%e6%a0%bc%e5%bc%8f)
    - [通用错误码](#%e9%80%9a%e7%94%a8%e9%94%99%e8%af%af%e7%a0%81)
  - [通用数据结构](#%e9%80%9a%e7%94%a8%e6%95%b0%e6%8d%ae%e7%bb%93%e6%9e%84)
    - [工程](#%e5%b7%a5%e7%a8%8b)
    - [母图](#%e6%af%8d%e5%9b%be)
    - [子图](#%e5%ad%90%e5%9b%be)
  - [具体接口](#%e5%85%b7%e4%bd%93%e6%8e%a5%e5%8f%a3)
    - [创建工程](#%e5%88%9b%e5%bb%ba%e5%b7%a5%e7%a8%8b)
    - [查询工程状态](#%e6%9f%a5%e8%af%a2%e5%b7%a5%e7%a8%8b%e7%8a%b6%e6%80%81)
    - [修改工程](#%e4%bf%ae%e6%94%b9%e5%b7%a5%e7%a8%8b)
    - [删除工程](#%e5%88%a0%e9%99%a4%e5%b7%a5%e7%a8%8b)
    - [查询工程列表](#%e6%9f%a5%e8%af%a2%e5%b7%a5%e7%a8%8b%e5%88%97%e8%a1%a8)
    - [往工程里面添加图片同时进行转换（上传图片）](#%e5%be%80%e5%b7%a5%e7%a8%8b%e9%87%8c%e9%9d%a2%e6%b7%bb%e5%8a%a0%e5%9b%be%e7%89%87%e5%90%8c%e6%97%b6%e8%bf%9b%e8%a1%8c%e8%bd%ac%e6%8d%a2%ef%bc%88%e4%b8%8a%e4%bc%a0%e5%9b%be%e7%89%87%ef%bc%89)
    - [删除往工程图片](#%e5%88%a0%e9%99%a4%e5%be%80%e5%b7%a5%e7%a8%8b%e5%9b%be%e7%89%87)
    - [查询母图配置信息](#%e6%9f%a5%e8%af%a2%e6%af%8d%e5%9b%be%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
    - [创建母图配置](#%e5%88%9b%e5%bb%ba%e6%af%8d%e5%9b%be%e9%85%8d%e7%bd%ae)
    - [修改母图配置](#%e4%bf%ae%e6%94%b9%e6%af%8d%e5%9b%be%e9%85%8d%e7%bd%ae)
    - [删除母图配置](#%e5%88%a0%e9%99%a4%e6%af%8d%e5%9b%be%e9%85%8d%e7%bd%ae)
    - [查询子图配置信息](#%e6%9f%a5%e8%af%a2%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
    - [根据母图查询子图列表](#%E6%A0%B9%E6%8D%AE%E6%AF%8D%E5%9B%BE%E6%9F%A5%E8%AF%A2%E5%AD%90%E5%9B%BE%E5%88%97%E8%A1%A8)
    - [创建子图配置信息](#%e5%88%9b%e5%bb%ba%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
    - [修改子图配置信息](#%e4%bf%ae%e6%94%b9%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
    - [删除子图配置信息](#%e5%88%a0%e9%99%a4%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
    - [添加子图配置标签](#%e6%b7%bb%e5%8a%a0%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e6%a0%87%e7%ad%be)
    - [删除子图配置标签](#%e5%88%a0%e9%99%a4%e5%ad%90%e5%9b%be%e9%85%8d%e7%bd%ae%e6%a0%87%e7%ad%be)
	- [创建背景图](#%e5%88%9b%e5%bb%ba%e8%83%8c%e6%99%af%e5%9b%be)
	- [修改背景图](#%e4%bf%ae%e6%94%b9%e8%83%8c%e6%99%af%e5%9b%be)
	- [删除背景图](#%e5%88%a0%e9%99%a4%e8%83%8c%e6%99%af%e5%9b%be)
	- [获取背景图](#%e8%8e%b7%e5%8f%96%e8%83%8c%e6%99%af%e5%9b%be)
	- [查询背景图列表](#%e6%9f%a5%e8%af%a2%e8%83%8c%e6%99%af%e5%9b%be%e5%88%97%e8%a1%a8)
	- [添加背景图标签](#%e6%b7%bb%e5%8a%a0%e8%83%8c%e6%99%af%e5%9b%be%e6%a0%87%e7%ad%be)
	- [删除背景图标签](#%e5%88%a0%e9%99%a4%e8%83%8c%e6%99%af%e5%9b%be%e6%a0%87%e7%ad%be)
	- [获取背景色系列表](#%e8%8e%b7%e5%8f%96%e8%83%8c%e6%99%af%e8%89%b2%e7%b3%bb%e5%88%97%e8%a1%a8)
	- [获取背景标签列表](#%e8%8e%b7%e5%8f%96%e8%83%8c%e6%99%af%e6%a0%87%e7%ad%be%e5%88%97%e8%a1%a8)
	- [创建模板配置信息](#%e5%88%9b%e5%bb%ba%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
	- [修改模板配置信息](#%e4%bf%ae%e6%94%b9%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
	- [删除模板配置信息](#%e5%88%a0%e9%99%a4%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e4%bf%a1%e6%81%af)
	- [获取模板配置](#%e8%8e%b7%e5%8f%96%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae)
	- [查询模板配置列表](#%e6%9f%a5%e8%af%a2%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e5%88%97%e8%a1%a8)
	- [添加模板配置标签](#%e6%b7%bb%e5%8a%a0%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e6%a0%87%e7%ad%be)
	- [删除模板配置标签](#%e5%88%a0%e9%99%a4%e6%a8%a1%e6%9d%bf%e9%85%8d%e7%bd%ae%e6%a0%87%e7%ad%be)
	- [获取模板色系列表](#%e8%8e%b7%e5%8f%96%e6%a8%a1%e6%9d%bf%e8%89%b2%e7%b3%bb%e5%88%97%e8%a1%a8)
	- [获取模板标签列表](#%e8%8e%b7%e5%8f%96%e6%a8%a1%e6%9d%bf%e6%a0%87%e7%ad%be%e5%88%97%e8%a1%a8)
	- [添加字体](#%e6%b7%bb%e5%8a%a0%e5%ad%97%e4%bd%93)
	- [获取字体列表](#%e8%8e%b7%e5%8f%96%e5%ad%97%e4%bd%93%e5%88%97%e8%a1%a8)
	- [删除字体](#%e5%88%a0%e9%99%a4%e5%ad%97%e4%bd%93)
	- [检查字体名称是否存在]()
	
## 基本

1. 接口采用 HTTP restful 的方式调用，其 URL 格式为: https://server/cresset/api/${api-name}
2. 请求参数放在 URI 上
3. 所有的应答参数均采用 json 返回

## 接口格式

### 请求格式

请求均采用 restful 的方式，除文件上传外，其他的只要有数据的部分，均采用 JSON 格式。

### 响应格式

|   # | 父参数 | 参数名     | 用途       | 备注                           |
| --: | ------ | ---------- | ---------- | ------------------------------ |
|   1 | N/A    | code       | 错误码     | 0 表示正确，其他参见后面的定义 |
|   2 | N/A    | reason     | 错误提示语 | 可选                           |
|   3 | N/A    | \${object} | 数据       | 数据对象，由接口定义           |

举例：

```json
{
  "code": 0,
  "reason": "OK",
  "superimage": {
    "id": "1"
  }
}
```

### 通用错误码

## 通用数据结构

### 工程

|   # | 参数名 | 数据类型 | 用途       | 备注                                             |
| --: | ------ | -------- | ---------- | ------------------------------------------------ |
|   1 | id     | string   | 工程的 id  |                                                  |
|   2 | name   | string   | 工程的名称 |                                                  |
|   3 | type   | int      | 工程的类型 | 0 表示无效; 1 表示功能 1; 2 表示功能 2; 其他待定 |
|   4 | status | int      | 工程的状态 |                                                  |

### 母图

|   # | 参数名   | 数据类型 | 用途       | 备注 |
| --: | -------- | -------- | ---------- | ---- |
|   1 | id       | string   | 母图的 id  |      |
|   2 | name     | string   | 母图的名称 |      |
|   3 | width    | int      | 母图的宽度 |      |
|   4 | height   | int      | 母图的高度 |      |
|   5 | children | list     | 子图列表   |      |

### 子图

|   # | 参数名   | 数据类型 | 用途           | 备注 |
| --: | -------- | -------- | -------------- | ---- |
|   1 | id       | string   | 子图的 id      |      |
|   2 | super_id | string   | 母图的 id      |      |
|   3 | name     | string   | 子图的名称     |      |
|   4 | width    | int      | 子图的宽度     |      |
|   5 | height   | int      | 子图的高度     |      |
|   6 | tags     | list     | 子图的标签列表 |      |

## 具体接口

|   # | HTTP method | 接口名                   | 用途               | 备注       |
| --: | :---------- | ------------------------ | ------------------ | ---------- |
|   1 | POST        | project                  | 创建工程           |            |
|   2 | GET         | project                  | 查询工程           | 含任务状态 |
|   3 | POST        | project                  | 往工程里面添加图片 |            |
|   4 | POST        | convert                  | 添加图片转换任务   |            |
|   5 | GET         | config/superimage        | 查询母图配置信息   |            |
|   6 | PUT         | config/superimage        | 创建母图配置       |            |
|   7 | POST        | config/superimage        | 修改母图配置       |            |
|   8 | DELETE      | config/superimage        | 删除母图配置       |            |
|   9 | GET         | config/subimage          | 查询子图配置信息   |            |
|  10 | PUT         | config/subimage          | 创建子图配置信息   |            |
|  11 | POST        | config/subimage          | 修改子图配置信息   |            |
|  12 | DELETE      | config/subimage          | 删除子图配置信息   |            |
|  13 | PUT         | config/subimage/\${}/tag | 修改子图配置标签   |            |
|  14 | DELETE      | config/subimage/\${}/tag | 删除子图配置标签   |            |
|  15 | PUT         | background               | 创建背景图         |            |
|  16 | GET         | background               | 获取指定的背景图   |            |
|  17 | GET         | backgrounds              | 查询背景图         |            |
|  18 | POST        | background               | 修改背景图         |            |
|  19 | DELETE      | background               | 删除背景图         |            |
|  20 | PUT         | background/\${}/tag      | 修改背景图配置标签 |            |
|  21 | DELETE      | background/\${}/tag      | 删除背景图配置标签 |            |
|  22 | PUT         | config/template          | 创建模板           |            |
|  23 | GET         | config/template          | 获取指定的模板     |            |
|  24 | GET         | config/templates         | 查询模板           |            |
|  25 | POST        | config/template          | 修改模板           |            |
|  26 | DELETE      | config/template          | 删除模板           |            |
|  27 | PUT         | config/template/\${}/tag | 修改模板标签       |            |
|  28 | DELETE      | config/template/\${}/tag | 删除模板标签       |            |

---

### 创建工程

```HTTP
POST /cresset/api/project
```

- 参数

```json
{
  "name": "",
  "type": 2
}
```

- 接口特性

  - 创建一个工程，指定工程类型，返回工程 ID

- 返回值

```json
"project": {
    "id": "project-id",
    "name": "name-of-project",
    "type": type-of-project,
}
```

---

### 查询工程状态

```HTTP
GET /api/project/${projectId}
```

- 参数

  - project-id: 工程的 ID

- 接口特性

  - 查询工程的状态

- 返回值

```json
"project": {
    "id": "project-id",
    "name": "name-of-project",
    "type": type-of-project,
    "status": status-of-project,
    "images": [
        {
            "image_id": "id-of-image",
            "super_id": "id-of-super-image", // 没有该字段表示是母图,
            "originalUrl": "url-of-source-image",
            "convertedUrl": "url-of-converted-image",
            "width": "width-of-image",
            "height": "height-of-image",
            "originalFilesize": "byte-size-of-original-file",
            "filesize": "byte-size-of-convert-file"
        }
    ]
    "tasks": [
        {
            "task_id": "id-of-task",
            "image-id": "id-of-image",
            "status": status-of-the-task
        }
    ]
}
```
### 修改工程
```HTTP
PATCH /api/project
```

- 参数
```json
{
    "id": "project-id",
    "name": "name-of-project",
    "type": type-of-project,
    "status": status-of-project
}
```
  - projectId: 工程的 ID
  - name：工程名称
  - type：工程类型
  - status：工程状态
- 接口特性

  - 修改工程

- 返回值

```json
"data": "修改的工程数量"
```

---
### 删除工程
```HTTP
DELETE /api/project/{projectId}
```

- 参数

  - projectId: 工程的 ID
- 接口特性

  - 删除工程

- 返回值

```json
"data": "删除的工程数量"
```

---
### 查询工程列表
```HTTP
GET /api/project/projects
```

- 参数
 无
- 接口特性

  - 查询工程列表

- 返回值

```json
"data": "删除的工程数量"
```

---
### 往工程里面添加图片同时进行转换（上传图片）

```HTTP
POST /api/project/${projectId}?super=${superimage-id}&name=${name}&width=${width}&height=${height}&templateId=${templateId}&entityTypeEm=${entityTypeEm}&optim=${optim}&quality=${quality}&size=${size}&convert=${convert}&format=${format}
```

- 参数

  - superimage-id: 母图的 ID，空或者字段不存在表示上传的是母图
  - name: 图片名称，用于识别
  - width: 图片的宽度
  - height: 图片的高度
  - subId: 子图ID，如果上传的是子图，则需要传入子图id，如果上传的是母图，则可不传，默认为0
  - templateId：模板的 ID，空表示上传的不是根据模板生成的图片
  - entityTypeEm: 功能类型，1 表示根据模板生成图片，2 表示根据二维码转换，3 表示根据配置转换
  - optim：是否优化
  - quality：缩略图的质量
  - size：压缩文件大小为指定的大小
  - convert：是否进行转换
  - format：转换后的文件格式，默认jpg

- 接口特性

  - 该接口采用上传文件的方式

- 返回值

```json
"data": "图片ID或者转换任务ID"
-当需要进行转换图片时，返回的就是图片转换任务的ID，不需要转换时返回的就是图片的ID
```

---

### 删除工程图片

```HTTP
DELETE /api/project/deleteImage
```

- 参数
  
  - imageIds: 图片的 ID
```json
[1,2,3]
```

- 返回值

```json
"data": "3"
```

### 查询母图配置信息

- 接口形式

|   # | HTTP 请求                                                                    | 功能                     |
| --: | ---------------------------------------------------------------------------- | ------------------------ |
|   1 | GET /api/config/superimage/${母图配置ID}?detail=${detail}                    | 查询指定 ID 的母图配置   |
|   2 | GET /api/config/superimage/listByLimit?start=${母图配置ID}&limit=${limit}  | 列出母图配置             |
|   3 | GET /api/config/superimage/listByDetail?width=${width}&height=${height}&detail=\${detail} | 查询符合分辨率的母图配置 |

- 参数

  1. \${母图配置 ID}: 母图配置的 ID
  2. start: 起始母图 ID，即获取 >= `start` 的所有母图
  3. limit: 获取个数，默认 20 个
  4. width、height: 分辨率，母图的宽度和高度，必须同时出现
  5. detail: 是否需要获取子图的信息，默认为 0，即不获取

- 接口特性

  1. 按照第一种调用方式，仅返回 ID 完全相等的母图配置信息
  2. 按照第二种调用方式，返回从`start`开始的最多`limit`条母图配置信息，`start`不填表示从最小值开始取，`limit`不填默认 20 条
  3. 按照第三种调用方式，将列出所有符合分辨率的母图及附带的子图信息

- 返回值

  - 方式 1

```json
"superimage" : {
    "id": "superimage_id",
    "name": "name-of-super-image",
    "height": 1234,
    "width": 567,
    "children": [
        {...}
    ]
}
```

- 方式 2

```json
"superimages" : [
    {
        "id": "superimage_id",
        "name": "name-of-super-image",
        "height": 1234,
        "width": 567,
        "children": ["child-a", "child-b", "child-c"]
    },
]
```

- 方式 3

```json
"superimages" : [
    {
        "id": "superimage_id",
        "name": "name-of-super-image",
        "height": 1234,
        "width": 567,
        "children": [
            {
               "id": "child-a", // 子图详细信息
               "name": "name-of-sub-image",
               "height": 1234,
               "width": 567,
               "tags": ["tag-a", "tag-b", "tag-c"]
            },

        ]
    },
]
```

---

### 创建母图配置

- 接口形式

```HTTP
PUT /api/config/superimage
```

- 参数

```json
{
  "name": "name-of-super-image",
  "height": 1234,
  "width": 567
}
```

- 接口特性

  - 创建母图配置

- 返回值

```json
"superimage": {
    "id": "id-of-super-image",
    "name": "name-of-super-image",
    "height": 1234,
    "width": 567
}
```

---

### 修改母图配置

- 接口形式

```HTTP
POST /api/config/superimage/${super-image-id}
```

- 参数
  - super-image-id: 母图配置的 ID

{
"id": "id-of-super-image",
"name": "name-of-super-image",
"height": 1234,
"width": 567
}

- 接口特性

  - 删除母图配置

- 返回值

```json
"superimage": {
    "id": "id-of-super-image",
    "name": "name-of-super-image",
    "height": 1234,
    "width": 567
}
```

---

### 删除母图配置

- 接口形式

```HTTP
DELETE /api/config/superimage/${super-image-id}
```

- 参数

  - super-image-id: 母图配置的 ID

- 接口特性

  - 删除母图配置

- 返回值

```json
"superimage": {
    "id": "id-of-super-image",
    "name": "name-of-super-image",
    "height": 1234,
    "width": 567
}
```

---

### 查询子图配置信息

- 接口形式

```HTTP
GET /api/config/subimage/${子图配置ID}
```

- 参数

  - \${子图配置 ID}: 子图配置的 ID

- 接口特性

  - 查询子图配置信息

- 返回值

```json
"subimage" : {
    "id": "subimage_id",
    "name": "name-of-sub-image",
    "super_id": "id-of-superimage",
    "height": 1234,
    "width": 567,
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 根据母图查询子图列表

- 接口形式

```HTTP
GET /api/config/subimage?superImageId=${母图配置ID}
```

- 参数

  - \${母图配置 ID}: 母图配置的 ID

- 接口特性

  - 根据母图查询子图列表

- 返回值

```json
[
 {
    "id": "subimage_id",
    "name": "name-of-sub-image",
    "super_id": "id-of-superimage",
    "height": 1234,
    "width": 567,
    "tags": ["tag-a", "tag-b", "tag-c"]
},
]
```



---

### 创建子图配置信息

- 接口形式

```HTTP
PUT /api/config/subimage
```

- 参数

```json
{
  "name": "name-of-sub-image",
  "super_id": "id-of-superimage",
  "identifier": "province-of-subimage",
  "height": 1234,
  "width": 567
}
```

- 接口特性

  - 创建子图配置信息

- 返回值

```json
"subimage" : {
    "subImageId": "subimage_id",
    "name": "name-of-sub-image",
    "superImageId": "id-of-superimage",
    "identifier": "province-of-subimage",
    "height": 1234,
    "width": 567
}
```

---
### 修改子图配置信息

- 接口形式

```HTTP
POST /api/config/subimage/${子图配置ID}
```

- 参数

  - \${子图配置 ID}: 子图配置的 ID

- 接口特性

  - 查询子图配置信息

```HTTP
 {
    "id": "subimage_id",
    "name": "name-of-sub-image",
    "super_id": "id-of-superimage",
    "height": 1234,
    "width": 567,
}
```

- 返回值

```json
"subimage" : {
    "id": "subimage_id",
    "name": "name-of-sub-image",
    "super_id": "id-of-superimage",
    "height": 1234,
    "width": 567,
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 删除子图配置信息

- 接口形式

```HTTP
DELETE /api/config/subimage/${子图配置ID}
```

- 参数

  - \${子图配置 ID}: 子图配置的 ID

- 接口特性

  - 查询子图配置信息

- 返回值

```json
"subimage" : {
    "id": "subimage_id",
    "name": "name-of-sub-image",
    "super_id": "id-of-superimage",
    "height": 1234,
    "width": 567,
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 添加子图配置标签

- 接口形式

```HTTP
PUT /api/config/subimage/${子图配置ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 查询子图配置信息

- 返回值

```json
"tags": ["tag-a", "tag-b", "tag-c"]
```

---

### 删除子图配置标签

- 接口形式

```HTTP
DELETE /api/config/subimage/${子图配置ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 查询子图配置信息

- 返回值

```json
"tags": ["tag-a", "tag-b", "tag-c"]
```

---

### 创建背景图

- 接口形式

```HTTP
PUT /api/background
```

- 参数

```json
{
  "name": "name-of-background",
  "height": 1234,
  "width": 567,
  "colorSystem": "333333"
}
```

- 接口特性

  - 创建背景图，上传文件的格式
参数：
    - file: 图片文件
    - name: 背景名称,
    - height: 背景高度,
    - width: 背景宽度,
    - colorSystem: 色系，
    - memo: 备注

- 返回值
```json
{
    "id": "id-of-background",
    "name": "name-of-background",
    "height": 1234,
    "width": 567,
    "colorSystem": "333333",
    "memo": "remarks",
    "path": "path-of-image"
}
```

---

### 修改背景图

- 接口形式

```HTTP
POST /api/background/update
```

- 参数

  - \${背景图 ID}: 背景图 ID

- 接口特性

  - 上传文件的格式，文件可不传入，不传入时不对背景图片进行替换

```HTTP
 - file：图片文件
 - id：背景ID
 - name: 背景名称,
 - height: 背景高度,
 - width: 背景宽度,
 - colorSystem: 色系，
 - memo: 备注
```

- 返回值

```json
"background" : {
    "id": "id-of-background",
    "name": "name-of-background",
    "height": 1234,
    "width": 567,
    "colorSystem": "333333",
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 删除背景图

- 接口形式

```HTTP
DELETE /api/background/${背景图ID}
```

- 参数

  - \${背景图ID}: 背景图 ID

- 接口特性

  - 删除背景图信息

- 返回值

```json
"background" : {
    "id": "id-of-background",
    "name": "name-of-background",
    "height": 1234,
    "width": 567,
    "colorSystem": "333333",
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 获取背景图

- 接口形式

```HTTP
GET /api/background/${背景图ID}
```

- 参数

  - \${背景图ID}: 背景图 ID

- 接口特性

  - 查询背景图信息

- 返回值

```json
"background" : {
    "id": "id-of-background",
    "name": "name-of-background",
    "height": 1234,
    "width": 567,
    "colorSystem": "333333",
    "tags": ["tag-a", "tag-b", "tag-c"]
}
```

---

### 查询背景图列表

- 接口形式

```HTTP
GET /api/backgrounds?tag=${tag}&colorSystem=${colorSystem}
```

- 参数

  - \${tag}: 标签
  - \${colorSystem}: 色系

- 接口特性

  - 查询背景图信息

- 返回值

```json
"backgrounds" : [
  {
    "id": "id-of-background",
    "name": "name-of-background",
    "height": 1234,
    "width": 567,
    "colorSystem": "333333",
    "tags": ["tag-a", "tag-b", "tag-c"]
  },{
    ....
  }
]
```

---

### 添加背景图标签

- 接口形式

```HTTP
PUT /api/background/${背景图ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 查询背景图标签

- 返回值

```json
"tags": ["tag-a", "tag-b", "tag-c"]
```

---

### 删除背景图标签

- 接口形式

```HTTP
DELETE /api/background/${背景图ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 查询背景图标签

- 返回值

```json
"tags": ["tag-a", "tag-b", "tag-c"]
```

---

### 获取背景色系列表

- 接口形式

```HTTP
GET /api/background/colorSystems
```

- 参数

无

- 接口特性

  - 查询背景色系列表

- 返回值

```json
"tags": ["333", "ADEF"]
```

---

### 获取背景标签列表

- 接口形式

```HTTP
GET /api/background/tags
```

- 参数

无

- 接口特性

  - 查询背景标签列表

- 返回值

```json
"tags": ["tag-a", "tag-b"]
```


---

### 创建模板配置信息

- 接口形式

```HTTP
POST /api/config/template
```

- 参数

```json
{
  "name": "name-of-template",
  "height": 1234,
  "width": 567,
  "html": "<p>adfsd</p>",
  "css": "font{}",
  "colorSystem": "333333"
}
```

- 接口特性

  - 创建模板配置信息

- 返回值

```json
"template" : {
  "id": "id-of-template",
  "name": "name-of-template",
  "height": 1234,
  "width": 567,
  "html": "<p>adfsd</p>",
  "css": "font{}",
  "colorSystem": "333333"
}
```

---

### 修改模板配置信息

- 接口形式

```HTTP
PATCH /api/template
```

- 参数

  ```json
  {
    "templateId": "id-of-template",//必填参数
    "name": "name-of-template",//选填参数
    "height": 1234,//选填参数
    "width": 567,//选填
    "html": "<p>adfsd</p>",//选填
    "css": "font{}",//选填
    "colorSystem": "333333"//选填
  }
  ```

- 接口特性

  - 修改模板配置信息

- 返回值

```json
"template" : {
  "id": "id-of-template",
  "name": "name-of-template",
  "height": 1234,
  "width": 567,
  "html": "<p>adfsd</p>",
  "css": "font{}",
  "colorSystem": "333333"
}
```

---

### 删除模板配置信息

- 接口形式

```HTTP
DELETE /api/config/template/${模板ID}
```

- 参数

  - \${模板ID}: 模板ID

- 接口特性

  - 查询模板配置信息

- 返回值

```json
"data" : 1
```

---

### 获取模板配置

- 接口形式

```HTTP
GET /api/config/template/${模板ID}
```

- 参数

  - \${模板ID}: 模板ID

- 接口特性

  - 查询模板详情信息，包括模板的标签信息

- 返回值

```json
"template" : {
  "id": "id-of-template",
  "name": "name-of-template",
  "height": 1234,
  "width": 567,
  "html": "<p>adfsd</p>",
  "css": "font{}",
  "colorSystem": "333333",
  "templateTagList": [{
      "id":1,
      "templateId":1,
      "tag": "tag-a",
      "status": 0,
      "timeCreated": "2019-08-15 11:13:43",
      "timeModified": "2019-08-15 11:18:43"
  },
  {...}]
}
```

---

### 查询模板配置列表

- 接口形式

```HTTP
GET /api/config/templates?tag=${tag}&colorSystem=${colorSystem}
```

- 参数

  - \${tag}: 标签
  - \${colorSystem}: 色系

- 接口特性

  - 查询模板配置

- 返回值

```json
"templates" : [
  {
    "id": "id-of-template",
    "name": "name-of-template",
    "height": 1234,
    "width": 567,
    "html": "<p>adfsd</p>",
    "css": "font{}",
    "colorSystem": "333333",
    "tags": [{
                   "id":1,
                   "templateId":1,
                   "tag": "tag-a",
                   "status": 0,
                   "timeCreated": "2019-08-15 11:13:43",
                   "timeModified": "2019-08-15 11:18:43"
               },
               {...}]
  },{
    ....
  }
]
```

---

### 添加模板配置标签

- 接口形式

```HTTP
PUT /api/config/template/${模板ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 查询模板配置标签

- 返回值

```json
"tags": ["tag-a", "tag-b", "tag-c"]
```

---

### 删除模板配置标签

- 接口形式

```HTTP
DELETE /api/config/template/${模板ID}/tag
```

- 参数

```json
["tag-a", "tag-b"]
```

- 接口特性

  - 删除模板配置标签

- 返回值

```json
"data": ["tag-a", "tag-b", "tag-c"]
```
### 获取模板色系列表
- 接口形式

```HTTP
GET /api/config/template/colorSystems
```

- 参数

无
```

- 接口特性

  - 获取所有模板色系，去重

- 返回值

​```json
"data": ["333", "233", "231"]
```
### 获取模板标签列表
- 接口形式

```HTTP
GET /api/config/template/tags
```

- 参数

无
```

- 接口特性

  - 获取所有模板标签，去重

- 返回值

​```json
 "data":["tag-a", "tag-b", "tag-c"]
```

### 添加字体
- 接口形式

```HTTP
POST /api/config/font
```

- 参数

- image ：字体的图片
- activeImage：选中的图片
- hoverImage：鼠标悬停的图片
- fontFile：字体文件
- family：字体名称
- stretch：字体拉伸,0: normal;-1: semi-condensed;-2: condensed; -3: ultra-condensed;-4: extra-condensed;1: semi-expanded;2: expanded;3: extra-expanded;4: ultra-expanded;
- style：字体风格,0: normal; 1: italic; 2: oblique
- weight：字体粗细程度，可选范围是：100, 200, 300, 400, 500, 600, 700, 800, 900,其中正常状态是400，该字段为0代表默认状态400
```

- 接口特性

  - 添加字体

- 返回值

​```json
data:{
    "family":"宋体"，
    "stretch":1,
    "style":2,
    "weight":400,
    "path":"http://qewrqw/asd/adfa",
    "image":"http://qewrqw/asd/daw/dfa",
    "activeImage":"http://qewrqw/asfw/adf/ad",
    "hoverImage":"http://qewrqw/dwe/adsfaf",
    "status":1
}
```

### 获取字体列表
- 接口形式

```HTTP
GET /api/config/fonts
```

- 参数
无
```

- 接口特性

  - 添加字体

- 返回值

​```json
data:[{
    "family":"宋体"，
    "stretch":1,
    "style":2,
    "weight":400,
    "path":"http://qewrqw/asd/adfa",
    "image":"http://qewrqw/asd/daw/dfa",
    "activeImage":"http://qewrqw/asfw/adf/ad",
    "hoverImage":"http://qewrqw/dwe/adsfaf",
    "status":1
},
{
...
}]
```
### 删除字体
- 接口形式

```HTTP
DELETE /api/config/font/{fontId}
```

- 参数
-fontId：字体ID
```

- 接口特性

  - 删除字体

- 返回值

​```json
"data":number-of-deleted
```
### 检查字体名称是否存在
- 接口形式

```HTTP
GET /api/config/font/exits?fontFamily=宋体
```

- 参数
    - fontFamily：字体名称
```

- 接口特性

  - 校验字体名称是否存在

- 返回值

​```json
"data":true/false --true表示字体名称存在，false表示不存在
```