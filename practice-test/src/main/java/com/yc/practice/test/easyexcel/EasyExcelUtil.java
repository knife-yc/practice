package com.yc.practice.test.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class EasyExcelUtil {

    public static void write(String fileName, String sheetName) {
        long timeBeginMS = System.currentTimeMillis();
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoModel.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        excelWriter.write(data(), writeSheet);
        excelWriter.finish();
        long timeEndMS = System.currentTimeMillis();
        System.out.println("cost:" + (timeEndMS - timeBeginMS));
    }

    private static List<DemoModel> data() {
        List<DemoModel> list = new ArrayList<>();
        int size = 3000;

        for (int index = 0; index < size; index++) {
            DemoModel dm = new DemoModel();
            dm.setTestId(index);
            dm.setTestName("test-" + index);
            dm.setTestWriter("knife-" + index);
            dm.setCreateTime(new Date());
            list.add(dm);
        }
        return list;
    }

    public static List<DemoModel> read(String fileName) {
        final CountDownLatch latch = new CountDownLatch(1);
        DataListener listener = new DataListener(latch);
        long timeBeginMS = System.currentTimeMillis();
        ExcelReader excelReader = EasyExcel.read(fileName, DemoModel.class, listener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
        try {
            latch.await(180, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        long timeEndMS = System.currentTimeMillis();
        System.out.println("cost:" + (timeEndMS - timeBeginMS));
        return listener.getDataList();
    }
}
