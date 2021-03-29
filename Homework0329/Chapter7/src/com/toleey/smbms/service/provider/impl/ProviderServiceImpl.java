package com.toleey.smbms.service.provider.impl;

import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.dao.provider.ProviderMapper;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    @Qualifier("providerMapper")
    private ProviderMapper providerMapper;

    public void setProviderDao(ProviderDao providerDao) {
        this.providerMapper = providerMapper;
    }

    @Override
    public List<Provider> findProviderList(String proName) {
        List<Provider> providerList = providerMapper.getAllProviderList(proName) ;
        return providerList;
    }
}
