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

    @Test
    public void addAProviderTest(){

        Provider provider = new Provider();
        provider.setProCode("IceCokeCola");
        provider.setProName("冰可乐");
        provider.setProDesc("卖可乐的啊");
        provider.setProContact("可乐王");
        provider.setProPhone("12312341234");
        provider.setProAddress("北京市海淀区");
        provider.setProFax("010-12345678");
        provider.setCreatedBy(1);
//        provider.setCreationDate();
//        provider.setCreatedBy();
//        provider.getModifyBy();

        ProviderService ps = (ProviderService) ApplicationContextUtil.getApplicationContext().getBean("providerService");
        Integer result = ps.addAProvider(provider);
        if (result.equals(0)){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功"+provider);
        }

    }

    @Test
    public void updateAProviderTest(){
        Provider provider = new Provider();
        provider.setId(20);
        provider.setProCode("IceSprits");
        provider.setProName("冰雪碧");
        provider.setProDesc("改行卖雪碧了");
        provider.setProContact("雪碧王");
        provider.setProPhone("12312341234");
        provider.setProAddress("北京市西城区");
        provider.setProFax("010-87654321");
        provider.setCreatedBy(2);

        ProviderService ps = (ProviderService) ApplicationContextUtil.getApplicationContext().getBean("providerService");
        Integer result = ps.updateAProvider(provider);
        if (result.equals(0)){
            System.out.println("更新失败");
        }else{
            System.out.println("更新成功"+provider);
        }


    }

    @Test
    public void deleteAProviderTest(){
        ProviderService ps = (ProviderService) ApplicationContextUtil.getApplicationContext().getBean("providerService");
        Integer id = 20;
        Integer result = ps.deleteAProvider(id);
        if (result.equals(0)){
            System.out.println("删除失败");
        }else{
            System.out.println("删除成功");
        }
    }
}
