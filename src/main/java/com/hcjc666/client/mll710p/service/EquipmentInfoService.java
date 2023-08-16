package com.hcjc666.client.mll710p.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcjc666.client.mll710p.entity.EquipmentInfo;
import com.hcjc666.client.mll710p.mapper.EquipmentInfoMapper;

@Service
public class EquipmentInfoService {
    @Autowired
    private EquipmentInfoMapper equipmentInfoMapper;

    public List<EquipmentInfo> getList(EquipmentInfo equipmentInfo) {
        return equipmentInfoMapper.getList(equipmentInfo);
    }
    public EquipmentInfo query(EquipmentInfo equipmentInfo) {
        return equipmentInfoMapper.query(equipmentInfo);
    }
}