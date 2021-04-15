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
}
