package com.yc.practice.office.excel;

import java.util.List;

public class ResponsePageResult {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;
    private List<DeviceColumn> data;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<DeviceColumn> getData() {
        return data;
    }

    public void setData(List<DeviceColumn> data) {
        this.data = data;
    }
}
