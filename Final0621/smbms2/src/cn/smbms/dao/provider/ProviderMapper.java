package cn.smbms.dao.provider;


import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface ProviderMapper {

    //查找供应商根据proCode和proName
    public List<Provider> getProviderListByProCodeAndProName(
            @Param("proCode") String proCode, @Param("proName") String proName,
            @Param("fromLineNum") Integer fromLineNum, @Param("toLineNum") Integer toLineNum
    );
    //查找供应商的总记录数
    public Integer getProviderCountByProCodeAndProName(
            @Param("proCode") String proCode,@Param("proName") String proName
    );
    //新增供应商
    public Integer insertProvider(Provider provider);
    //修改供应商
    public Integer updateProviderById(Provider provider);
    //根据编号查找供应商
    public Provider getProviderById(@Param("id") Integer id);
    //删除供应商
    public Integer deleteProviderById(@Param("id") Integer id);
    //根据编号查找供应商 viewProvider
    public Provider getViewProviderById(@Param("id") Integer id);
    //无条件查询所有供应商
    public List<Provider> getAllProviderIdAndProName();


}
