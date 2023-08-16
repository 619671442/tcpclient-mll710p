package com.hcjc666.client.mll710p.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "client")
public class ClientConfig {
    /**
     * 查询数据接口地址
     */
    private String queryUrl;
    /**
     * 查询间隔
     */
    private Integer intervalSeconds;
    /**
     * 客户端名称
     */
    private String name;
}
