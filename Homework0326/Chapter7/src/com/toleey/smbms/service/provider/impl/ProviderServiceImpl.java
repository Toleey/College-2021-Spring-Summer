package com.toleey.smbms.service.provider.impl;

import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;

import java.util.List;

public class ProviderServiceImpl implements ProviderService {

    private ProviderDao providerDao;

    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Override
    public List<Provider> findProviderList() {
        List<Provider> providerList = providerDao.getAllProviderList() ;
        return providerList;
    }
}
