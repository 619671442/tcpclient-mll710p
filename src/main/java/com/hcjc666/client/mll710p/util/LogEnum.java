package com.hcjc666.client.mll710p.util;

/**
 * 本地日志枚举
 * 
 * @author Administrator
 *
 */
public enum LogEnum {


    PLATFORM("platform"),
    DATASOURCE("source"),
    DATARESULT("result"),
    EXCEPTION("exception"),
    ;

    private String category;

    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}