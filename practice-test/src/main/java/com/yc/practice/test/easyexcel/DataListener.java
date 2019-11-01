package com.yc.practice.test.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataListener<DemoModel> extends AnalysisEventListener<DemoModel> {

    private final CountDownLatch latch;
    private final List<DemoModel> dataList = new ArrayList();

    public DataListener(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void invoke(DemoModel data, AnalysisContext context) {
        dataList.add(data);
//        System.out.println(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        latch.countDown();
    }

    public List<DemoModel> getDataList() {
        return dataList;
    }
}
