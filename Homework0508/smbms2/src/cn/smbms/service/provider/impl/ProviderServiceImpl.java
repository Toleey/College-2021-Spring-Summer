package cn.smbms.service.provider.impl;


import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
import cn.smbms.service.provider.ProviderService;
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
    public List<Provider> findProviderListByProCodeAndProName(String proCode, String proName, Integer fromLineNum, Integer toLineNum) {
        return null;
    }

    @Override
    public Integer findProviderCountByProCodeAndProName(String proCode, String proName) {
        return null;
    }

    @Override
    public boolean addProvider(Provider provider) {
        return false;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        return false;
    }

    @Override
    public Provider findProviderById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteProviderById(Integer id) {
        return false;
    }

    @Override
    public Provider findViewProviderById(Integer id) {
        return null;
    }

    @Override
    public List<Provider> findAllProviderIdAndProName() {
        return null;
    }
}
