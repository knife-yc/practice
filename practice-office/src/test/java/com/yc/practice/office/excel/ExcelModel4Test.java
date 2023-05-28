package com.yc.practice.office.excel;

import com.yc.pratice.office.excel.ExportHeaderAnnotation;
import com.yc.pratice.office.excel.SubTableAnnotation;

import java.util.List;

public class ExcelModel4Test {

    @ExportHeaderAnnotation(headerName = "编号", index = 0)
    private String id;
    @ExportHeaderAnnotation(headerName = "年龄", index = 1)
    private Integer age;
    @ExportHeaderAnnotation(headerName = "姓名", index = 2)
    private String name;
    @ExportHeaderAnnotation(headerName = "性别", index = 3)
    private Boolean sex;
    @ExportHeaderAnnotation(headerName = "生存时长", index = 4)
    private Long liveTimeMs;
    @ExportHeaderAnnotation(headerName = "资产", index = 5)
    private Float asserts;

    @SubTableAnnotation(startColumn = 2, subModule = ExcelSubModel4Test.class)
    private List<ExcelSubModel4Test> eduList;

    public List<ExcelSubModel4Test> getEduList() {
        return eduList;
    }

    public void setEduList(List<ExcelSubModel4Test> eduList) {
        this.eduList = eduList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Long getLiveTimeMs() {
        return liveTimeMs;
    }

    public void setLiveTimeMs(Long liveTimeMs) {
        this.liveTimeMs = liveTimeMs;
    }

    public Float getAsserts() {
        return asserts;
    }

    public void setAsserts(Float asserts) {
        this.asserts = asserts;
    }
}
