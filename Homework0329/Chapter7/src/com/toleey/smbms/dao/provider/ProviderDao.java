package com.toleey.smbms.dao.provider;

import com.toleey.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderDao {
    public List<Provider> getAllProviderList(
            @Param("proName") String proName
    );
}
