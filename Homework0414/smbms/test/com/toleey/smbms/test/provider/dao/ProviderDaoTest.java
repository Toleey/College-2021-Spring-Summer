package com.toleey.smbms.test.provider.dao;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.dao.provider.impl.ProviderDaoImpl;
import com.toleey.smbms.entity.Provider;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class ProviderDaoTest {

    @Test
    public void testProviderTest(){
        Connection conn = BaseDao.getConnection();
        ProviderDao providerDao = new ProviderDaoImpl();
        List<Provider> providerList = providerDao.getProviderListByProCodeAndProName(conn,"G","司");
        for (Provider provider : providerList) {
			System.out.println(provider);
		}
        

    }

}
