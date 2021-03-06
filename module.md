# 视图服务前后端交互模型定义

## 入参
 ```json
{
  "startTime": "2020-08-19 12:32:32,起始时间",
  "endTime": "2020-09-19 12:32:32,结束时间",
  "pageNum": 1,//页码
  "pageSize": 20,//页大小
  "orders": [
    {
      "field":  "排序字段名称",
      "type": "排序类型，asc/desc",
      "priority": 1 --优先级
    }
  ],
  "permission": ["暂时先不考虑"],
  "area": {
    "areaType": "查询区域的类型，1：全国，2：大区，3：省，4：市，5：区,市和区作为预备字段保留，第一期不使用",
    "areaIds": [2123,1234,13451]//需要查询的对应类型的ID列表，查全国时不填，填了也不管
  },
  "conditions": [{
    "key": "条件的字段名",
    "value": "条件的值",
    "operate": "条件比较的方式，<、>等"
  }]
}
```

## 出参
```json
{
  "code": "状态码，正常为200，其他错误需要根据异常码列表而定,int类型",
  "message": "异常原因，正常时这个值为空",
  "visible": false,错误信息是否可对外暴露
  "page": {
    "pageNum": 1,第几页
    "pageSize": 20,页大小
    "total": 200 总数量
  },
  "info": {
    "title": "报表标题",
    "description": "报表描述"
  },
  "data": {
    "header": {
      "dimensions": [{
        "identifier": "字段标识符",
        "expression": "字段抽象算法",
        "fieldName": "字段名称",
        "dataType": "字段数据类型，类型包含：date 时间，integer 整数,double 小数，小数点后强制保留两位,percent 百分比，小数点后强制保留两位，duration 时长",
        "group": "组标识，无实际意义，用于前端字段展示的分区分块，ignore 不展示,正常分块分类，group_xx 相同group的字段会展示在一个框内",
        "unit": "单位"
      }],
      "indicators": [{
          "identifier": "字段标识符",
          "expression": "字段抽象算法",
          "fieldName": "字段名称",
          "dataType": "字段数据类型，类型包含：date 时间，integer 整数,double 小数，小数点后强制保留两位,percent 百分比，小数点后强制保留两位，duration 时长",
          "group": "组标识，无实际意义，用于前端字段展示的分区分块，ignore 不展示,正常分块分类，group_xx 相同group的字段会展示在一个框内",
          "unit": "单位"
       }],
      "tooltips": [{
          "identifier": "字段标识符",
          "expression": "字段抽象算法",
          "fieldName": "字段名称",
          "dataType": "字段数据类型，类型包含：date 时间，integer 整数,double 小数，小数点后强制保留两位,percent 百分比，小数点后强制保留两位，duration 时长",
          "group": "组标识，无实际意义，用于前端字段展示的分区分块，ignore 不展示,正常分块分类，group_xx 相同group的字段会展示在一个框内",
          "unit": "单位"
      }],
      "rows": [{
        "im_whole_overview__stat_at": "2018-01-02 00:00:00,原始值",
        "im_whole_overview__stat_at_format": "1月2日，格式化之后的值"
      }]    
    }
  } 
}
```