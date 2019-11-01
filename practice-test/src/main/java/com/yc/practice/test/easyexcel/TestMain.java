package com.yc.practice.test.easyexcel;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {
//        write();
        read();
    }

    public static void read() {
        String fileName = "text.xlsx";
        List<DemoModel> list = EasyExcelUtil.read(fileName);
        System.out.println(list.size());
    }

    private static void write() {
        String fileName = "text.xlsx";
        String sheetName = "test";
        EasyExcelUtil.write(fileName, sheetName);
    }
}
