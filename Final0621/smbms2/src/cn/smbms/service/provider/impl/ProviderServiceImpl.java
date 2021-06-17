package cn.smbms.service.provider.impl;


import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Resource(name = "providerMapper")
    private ProviderMapper providerMapper;

    public void setProviderMapper(ProviderMapper providerMapper) {
        this.providerMapper = providerMapper;
    }

    @Override
    public List<Provider> findProviderListByProCodeAndProName(String proCode, String proName, Integer fromLineNum, Integer toLineNum) {
        List<Provider> providerList = providerMapper.getProviderListByProCodeAndProName(proCode,proName,fromLineNum,toLineNum);
        return providerList;
    }

    @Override
    public Integer findProviderCountByProCodeAndProName(String proCode, String proName) {
        Integer lines = providerMapper.getProviderCountByProCodeAndProName(proCode,proName);
        return lines;
    }

    @Override
    public boolean addProvider(Provider provider) {
        int line = providerMapper.insertProvider(provider);
        return line>=1;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        Integer line =  providerMapper.updateProviderById(provider);
        return line>=1;
    }

    @Override
    public Provider findProviderById(Integer id) {
        Provider provider = providerMapper.getProviderById(id);
        return provider;
    }

    @Override
    public boolean deleteProviderById(Integer id) {
        Integer line = providerMapper.deleteProviderById(id);
        return line>=1;
    }

    @Override
    public Provider findViewProviderById(Integer id) {
        Provider provider = providerMapper.getViewProviderById(id);
        return provider;
    }

    @Override
    public List<Provider> findAllProviderIdAndProName() {
        List<Provider> providerList = providerMapper.getAllProviderIdAndProName();
        return providerList;
    }
}
