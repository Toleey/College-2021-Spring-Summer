package Provider.test;

import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.dao.provider.impl.ProviderDaoImpl;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.provider.ProviderService;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.junit.Test;

import java.util.List;

public class ProviderTest {
    @Test
    public void ProviderTest(){

        //ProviderDao pd = new ProviderDaoImpl();
        //List<Provider> providerList = pd.getAllProviderList();

        ProviderService ps = (ProviderService) ApplicationContextUtil.getApplicationContext().getBean("providerService");
        List<Provider> providerList = ps.findProviderList("公");
        System.out.println(providerList);
    }
}
