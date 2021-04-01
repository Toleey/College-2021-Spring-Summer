package com.toleey.smbms.service.provider.impl;

import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.dao.provider.ProviderMapper;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer addAProvider(Provider provider) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            providerMapper.addAProvider(provider);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return t;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer updateAProvider(Provider provider) {
        Integer t = 0; //0 失败 ｜ 1 通过
        try {
            providerMapper.updateAProvider(provider);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return t;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer deleteAProvider(Integer id) {
        Integer t = 0; //0 失败 ｜ 1 通过
        try {
            providerMapper.deleteAProvider(id);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
        return t;
    }
}
