package com.toleey.smbms.dao.provider;

import com.toleey.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    public List<Provider> getAllProviderList(@Param("proName") String proName);
    public void addAProvider(Provider provider);
    public void updateAProvider(Provider provider);
    public void deleteAProvider(@Param("id") Integer id);
}
