package com.toleey.smbms.service.provider.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    @Qualifier("providerDao")
    private ProviderDao providerDao;
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Override
    public List<Provider> findProviderListByProCodeAndProName(String proCode, String proName) {
        Connection conn = BaseDao.getConnection();
        List<Provider> providerList = providerDao.getProviderListByProCodeAndProName(conn,proCode,proName);
        BaseDao.close(conn,null,null);
        return providerList;
    }

    @Override
    public boolean addProvider(Provider provider) {
        Connection conn = BaseDao.getConnection();
        int line = providerDao.insertProvider(conn,provider);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        Connection conn = BaseDao.getConnection();
        Integer line =  providerDao.updateProviderById(conn,provider);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public Provider findProviderById(Integer id) {
        Connection conn = BaseDao.getConnection();
        Provider provider = providerDao.getProviderById(conn,id);
        BaseDao.close(conn,null,null);
        return provider;
    }

    @Override
    public boolean deleteProviderById(Integer id) {
        Connection conn = BaseDao.getConnection();
        Integer line = providerDao.deleteProviderById(conn,id);
        BaseDao.close(conn,null,null);
        return line>=1;
    }

    @Override
    public Provider findViewProviderById(Integer id) {
        Connection conn = BaseDao.getConnection();
        Provider provider = providerDao.getViewProviderById(conn,id);
        BaseDao.close(conn,null,null);
        return provider;
    }


}
