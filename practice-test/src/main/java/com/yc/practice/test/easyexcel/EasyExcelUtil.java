package com.yc.practice.test.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

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

    public static void writeWithStyle(String fileName, String sheetName){
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)20);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)20);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoModel.class).registerWriteHandler(horizontalCellStyleStrategy).sheet(sheetName)
                .doWrite(data());
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
