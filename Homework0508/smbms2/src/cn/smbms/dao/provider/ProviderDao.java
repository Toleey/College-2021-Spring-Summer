package cn.smbms.dao.provider;


import cn.smbms.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {

    public List<Provider> getProviderListByProCodeAndProName(String proCode, String proName,
                                                             Integer fromLineNum, Integer toLineNum);
    //查找供应商的总记录数
    public Integer getProviderCountByProCodeAndProName(String proCode,String proName);

    //新增供应商
    public Integer insertProvider(Provider provider);
    //修改供应商
    public Integer updateProviderById(Provider provider);
    //根据编号查找供应商
    public Provider getProviderById(Integer id);
    //删除供应商
    public Integer deleteProviderById(Integer id);
    //根据编号查找供应商 viewProvider
    public Provider getViewProviderById(Integer id);
    //无条件查询所有供应商
    public List<Provider> getAllProviderIdAndProName();


}
