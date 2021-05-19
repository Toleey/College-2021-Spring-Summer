import com.toleey.appinfo.dao.backend.app.BackendAppMapper;
import com.toleey.appinfo.pojo.AppCategory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestGetUserCode {
    @Autowired
    private BackendAppMapper backendAppMapper;

    @Test
    public void getUserCode(){
        List<AppCategory> appCategoryList = backendAppMapper.getCategoryLevel1();
        System.out.println(appCategoryList);
    }
}
