package com.hcjc666.client.mll710p.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地日志参考类
 * 
 * @author Administrator
 *
 */
public class LogUtils {

   
    

    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
    }

 

    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }


    public static Logger getSourceLogger() {
        return LoggerFactory.getLogger(LogEnum.DATASOURCE.getCategory());
    }

    public static Logger getResultLogger() {
        return LoggerFactory.getLogger(LogEnum.DATARESULT.getCategory());
    }
}