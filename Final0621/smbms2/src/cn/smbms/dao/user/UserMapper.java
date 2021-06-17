package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import com.mysql.jdbc.Connection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //查找某个编码的用户
    public User getUserByUserCode(@Param("userCode") String userCode);
    // 根据多条件(用户名模糊查询，角色)查询用户列表，并且分页
    public List<User> getUserListByNameAndRole(
            @Param("userName") String userName,@Param("roleId") Integer roleId,
            @Param("fromLineNum") Integer fromLineNum,@Param("toLineNum") Integer toLineNum
    );
    //查找用户的总记录数
    public Integer getUserCountByNameAndRole(@Param("userName") String userName, @Param("roleId") Integer roleId);
    //新增用户
    public Integer insertUser(User user);
    //根据用户编号查询用户信息
    public User getUserById(@Param("id") Integer id);
    //修改用户
    public Integer updateUserById(User user);
    //删除用户
    public Integer deleteUserById(@Param("id") Integer id);
    //根据编号查找用户 viewUser
    public User getVewUserById(@Param("id") Integer id);
    //查询用户密码
    public String getUserPasswordById(@Param("id") Integer id);
    //修改用户密码
    public Integer updateUserPasswordById(@Param("id") Integer id,@Param("password") String password);
}
