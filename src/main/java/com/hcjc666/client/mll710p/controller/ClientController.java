package com.hcjc666.client.mll710p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSON;
import com.hcjc666.client.mll710p.entity.TcpReadData;
import com.hcjc666.client.mll710p.service.Mll710pService;

@RestController
public class ClientController {
    ;
    @Autowired
    private Mll710pService mll710pService;

    @GetMapping(value = "/")
    public String index() {
        return " mll710pService is running ";
    }

    /**
     * 清零接口
     * 
     * @return
     */
    @GetMapping(value = "/clear")
    public String clear() {

        return "success";
    }

    /**
     * 解析数据接口
     * 接受tcp服务发送过来的json数据，进行解析保存
     * 
     * @return
     */
    @PostMapping(value = "/received")
    public String received(@RequestBody TcpReadData data) {
        System.out.println("收到数据："+JSON.toJSONString(data));

        return "success";
    }
}