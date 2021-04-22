package com.toleey.smbms.service.provider;

import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderService {

    public List<Provider> findProviderListByProCodeAndProName(String proCode, String proName);
    //新增供应商
    public boolean addProvider(Provider provider);
    //修改供应商
    public boolean modifyProvider(Provider provider);
    //根据编号查找供应商
    public Provider findProviderById(Integer id);
    //删除供应商
    public boolean deleteProviderById(Integer id);
    //根据编号查找供应商 viewProvider
    public Provider findViewProviderById(Integer id);


}
