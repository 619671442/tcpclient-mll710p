package com.hcjc666.client.mll710p.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcjc666.client.mll710p.entity.Mll710pCurrent;
import com.hcjc666.client.mll710p.entity.Mll710pHistory;
import com.hcjc666.client.mll710p.mapper.Mll710pMapper;


@Service
public class Mll710pService {
    @Autowired
    private Mll710pMapper mll710pMapper;

    
    public void saveCurrent(Mll710pCurrent current) {
        mll710pMapper.saveCurrent(current);
    }
public void saveHistory(Mll710pHistory history) {
        mll710pMapper.saveHistory(history);
    }
 
}