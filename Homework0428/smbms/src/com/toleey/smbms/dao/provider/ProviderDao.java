package com.toleey.smbms.dao.provider;

import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    public List<Provider> getProviderListByProCodeAndProName(Connection conn,String proCode,String proName,
                                                             Integer fromLineNum,Integer toLineNum);
    //查找供应商的总记录数
    public Integer getProviderCountByProCodeAndProName(Connection conn,String proCode,String proName);

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
    //无条件查询所有供应商
    public List<Provider> getAllProviderIdAndProName(Connection conn);


}
