package com.hcjc666.client.mll710p.config;

 
 
import lombok.Getter;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
 
/**
 * @className: ServerConfig
 * @author: wang
 * @date: 2021/12/7
 **/
@Getter
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private Integer port;
 
    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
    }
}
