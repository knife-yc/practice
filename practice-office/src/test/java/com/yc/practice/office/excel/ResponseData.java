package com.yc.practice.office.excel;

public class ResponseData {
    private String message;
    private Integer status;
    private Long timestamp;
    private ResponsePageResult result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public ResponsePageResult getResult() {
        return result;
    }

    public void setResult(ResponsePageResult result) {
        this.result = result;
    }
}
