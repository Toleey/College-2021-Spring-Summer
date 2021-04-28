package com.toleey.smbms.test.provider.service;

import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import com.toleey.smbms.service.provider.impl.ProviderServiceImpl;
import org.junit.Test;

import java.util.List;

public class ProviderServiceTest {
    @Test
    public void testProviderService(){
        ProviderService providerService = new ProviderServiceImpl();
        //List<Provider> providerList = providerService.findProviderListByProCodeAndProName("","");
//        for (Provider provider : providerList) {
//			System.out.println(provider);
//		}
        
    }
}
