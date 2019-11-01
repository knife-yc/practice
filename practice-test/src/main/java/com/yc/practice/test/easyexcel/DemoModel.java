package com.yc.practice.test.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DemoModel{

    @ColumnWidth(20)
    @ExcelProperty(value = "图书ID",index = 0)
    private Integer testId;
    @ColumnWidth(20)
    @ExcelProperty(value = "图书名称",index = 1)
    private String testName;
    @ColumnWidth(20)
    @ExcelProperty(value = "图书作者",index = 2)
    private String testWriter;
    @ColumnWidth(20)
    @ExcelProperty(value = "写作时间",index = 3)
    private Date createTime;

    @Override
    public String toString() {
        return "DemoModel{" +
                "testId=" + testId +
                ", testName='" + testName + '\'' +
                ", testWriter='" + testWriter + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
