package com.hcjc666.client.mll710p.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcjc666.client.mll710p.entity.EquipmentInfo;

@Repository
public interface EquipmentInfoMapper {
   public List<EquipmentInfo> getList(EquipmentInfo equipmentInfo);

   public EquipmentInfo query(EquipmentInfo equipmentInfo);
}
