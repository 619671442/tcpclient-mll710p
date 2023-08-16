package com.hcjc666.client.mll710p.entity;

import lombok.Data;

@Data
public class TcpReadData {

    private String data;

    private Long time;

    private String dtuImei;

    private String equipmentName;

    private String modbusAddr;

}
