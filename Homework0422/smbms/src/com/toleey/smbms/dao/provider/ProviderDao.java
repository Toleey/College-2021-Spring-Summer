package com.toleey.smbms.dao.provider;

import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    public List<Provider> getProviderListByProCodeAndProName(Connection conn,String proCode,String proName);
    //新增供应商
    public Integer insertProvider(Connection conn,Provider provider);
    //修改供应商
    public Integer updateProviderById(Connection conn,Provider provider);
    //根据编号查找供应商
    public Provider getProviderById(Connection conn,Integer id);
    //删除供应商
    public Integer deleteProviderById(Connection conn,Integer id);
    //根据编号查找供应商 viewProvider
    public Provider getViewProviderById(Connection conn,Integer id);


}
