import com.toleey.appinfo.dao.backend.app.BackendAppMapper;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.tools.PageSupport;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

public class TestGetUserCode {


    @Test
    public void getUserCode(){
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(1);//当前页码为1
        pageSupport.setTotalCount(10);
        pageSupport.setPageSize(5);//页面容量 一页显示5条
        pageSupport.setTotalPageCountByRs();//计算总页数

        System.out.println(pageSupport);
    }
}
