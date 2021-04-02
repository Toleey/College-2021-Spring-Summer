package user.test;
import com.toleey.smbms.dao.user.UserMapper;
import com.toleey.smbms.entity.User;
import com.toleey.smbms.service.user.UserService;
import com.toleey.smbms.service.user.impl.UserServiceImpl;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "jndiServlet",value = "/jndiServlet")
public class TestJNDJDataSource extends HttpServlet {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    @Qualifier("userService")
    private UserService userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        UserService userService = (UserService) ApplicationContextUtil.getApplicationContext().getBean("userService");
        //生成一个HTML页面输出对象
        PrintWriter out = resp.getWriter();
        User user = new User();
        user.setUserName("孙");
        user.setUserRole(3);
        List<User> userList =  userService.findUserListByCondition(user);
        out.println(userList);
        out.flush();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
