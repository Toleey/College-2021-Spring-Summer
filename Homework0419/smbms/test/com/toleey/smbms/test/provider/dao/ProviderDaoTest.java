package com.toleey.smbms.test.provider.dao;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.dao.provider.impl.ProviderDaoImpl;
import com.toleey.smbms.entity.Provider;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
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

    @Test
    public void testaddProviderTest(){
        Connection conn = BaseDao.getConnection();
        ProviderDao providerDao = new ProviderDaoImpl();
        Provider provider = new Provider();
        provider.setProCode("ALph");
        provider.setProName("google");
        provider.setProDesc("搜索");
        provider.setProContact("Larry");
        provider.setProPhone("1234");
        provider.setProAddress("硅谷市");
        provider.setProFax("010123");
        provider.setCreatedBy(1);
        provider.setCreationDate(new Timestamp(new Date().getTime()));
        int line = providerDao.insertProvider(conn,provider);
        System.out.println(line);
    }


}
