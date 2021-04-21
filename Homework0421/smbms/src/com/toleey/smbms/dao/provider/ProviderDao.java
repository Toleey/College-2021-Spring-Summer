package com.toleey.smbms.dao.provider;

import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    public List<Provider> getProviderListByProCodeAndProName(Connection conn,String proCode,String proName);
    //新增供应商
    public Integer insertProvider(Connection conn,Provider provider);


}
