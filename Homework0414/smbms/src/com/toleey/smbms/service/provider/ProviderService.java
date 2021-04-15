package com.toleey.smbms.service.provider;

import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderService {

    public List<Provider> findProviderListByProCodeAndProName(String proCode, String proName);

}
