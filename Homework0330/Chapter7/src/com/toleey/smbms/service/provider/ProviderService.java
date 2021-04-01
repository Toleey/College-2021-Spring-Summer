package com.toleey.smbms.service.provider;

import com.toleey.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {
    public List<Provider> findProviderList(
            @Param("proName") String proName
    );

    public Integer addAProvider(Provider provider);

    public Integer updateAProvider(Provider provider);

    public Integer deleteAProvider(Integer id);
}
