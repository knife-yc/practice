package com.yc.practice.office.excel;


import com.yc.pratice.office.excel.ExcelWriterByPoi;
import com.yc.pratice.office.excel.FileUtil;
import com.yc.pratice.office.excel.JSONUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriteByPoiTest {

    @Test
    public void writeExcel() {
        File sourceFile = new File("D:\\myself\\practice\\practice-office\\src\\test\\resources\\response.json");
        StringBuffer stringBuffer = FileUtil.readFileByLine(sourceFile, "utf-8");
        try {
//            File file = ExcelWriterByPoi.write(fileName, sheetName, clazz, rows);
//            System.out.println(file.getAbsolutePath());
            ResponseData responseData = JSONUtil.parse(stringBuffer.toString(), ResponseData.class);
            List<DeviceColumn> rows = responseData.getResult().getData();
            File file = ExcelWriterByPoi.write("862290057826574", "862290057826574", DeviceColumn.class, rows);
            System.out.println(file.getAbsolutePath());
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readExcel() {
        String fileName = "testRead";
        String sheetName = "test";
        Class clazz = ExcelModel4Test.class;
        List<ExcelModel4Test> rows = new ArrayList<>();
        ExcelModel4Test row = new ExcelModel4Test();
        row.setAge(1);
        row.setId("124");
        row.setAsserts(12.3f);
        row.setName("张三");
        row.setLiveTimeMs(10000l);
        row.setSex(true);
        rows.add(row);

        try {
            File file = ExcelWriterByPoi.write(fileName, sheetName, clazz, rows);
            System.out.println(file.getAbsolutePath());
            List<Object> list = ExcelWriterByPoi.readExcel(clazz, file.getName());
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
