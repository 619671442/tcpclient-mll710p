package com.hcjc666.client.mll710p.mapper;

import org.springframework.stereotype.Repository;

import com.hcjc666.client.mll710p.entity.Mll710pCurrent;
import com.hcjc666.client.mll710p.entity.Mll710pHistory;


@Repository
public interface Mll710pMapper {

    public void saveCurrent(Mll710pCurrent current);

    public void saveHistory(Mll710pHistory history);
}
