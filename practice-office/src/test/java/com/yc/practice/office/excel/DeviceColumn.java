package com.yc.practice.office.excel;

import com.yc.pratice.office.excel.ExportHeaderAnnotation;

import java.util.Date;

public class DeviceColumn {

//    "id" : "95d8df257ae8d4b2b8a51b6b099ea389",
//            "deviceId" : "862290057826574",
//            "property" : "BusVoltage",
//            "propertyName" : "输入电压",
//            "type" : "int",
//            "unit" : "V",
//            "numberValue" : 376,
//            "value" : 376,
//            "formatValue" : "376V",
//            "createTime" : 1685281801767,
//            "timestamp" : 1685281801718

    private String id;
    private String deviceId;
    private String property;
    private String propertyName;
    private String type;
    private String unit;
    @ExportHeaderAnnotation(headerName = "属性名称", index = 0)
    private Integer numberValue;
    private Integer value;
    private String formatValue;
    private Long createTime;
    @ExportHeaderAnnotation(headerName = "产生时间", index = 1, dataType = Date.class)
    private Long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Integer numberValue) {
        this.numberValue = numberValue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(String formatValue) {
        this.formatValue = formatValue;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
