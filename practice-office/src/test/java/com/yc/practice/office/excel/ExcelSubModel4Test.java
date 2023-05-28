package com.yc.practice.office.excel;

import com.yc.pratice.office.excel.ExportHeaderAnnotation;

public class ExcelSubModel4Test {
    @ExportHeaderAnnotation(headerName = "学校名称", index = 0)
    private String education;
    @ExportHeaderAnnotation(headerName = "开始时间", index = 1)
    private String eduStartDate;
    @ExportHeaderAnnotation(headerName = "结束时间", index = 2)
    private String eduEndDate;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEduStartDate() {
        return eduStartDate;
    }

    public void setEduStartDate(String eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public String getEduEndDate() {
        return eduEndDate;
    }

    public void setEduEndDate(String eduEndDate) {
        this.eduEndDate = eduEndDate;
    }
}
