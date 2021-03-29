package com.toleey.smbms.service.provider;

import com.toleey.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {
    public List<Provider> findProviderList(
            @Param("proName") String proName
    );
}
