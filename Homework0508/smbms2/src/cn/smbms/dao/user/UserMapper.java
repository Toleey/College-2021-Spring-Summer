package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import com.mysql.jdbc.Connection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //查找某个编码的用户
    public User getUserByUserCode(@Param("userCode") String userCode);
    // 根据多条件(用户名模糊查询，角色)查询用户列表，并且分页
    public List<User> getUserListByNameAndRole(String userName,Integer roleId,Integer fromLineNum,Integer toLineNum);
    //查找用户的总记录数
    public Integer getUserCountByNameAndRole(String userName, Integer roleId);
    //新增用户
    public Integer insertUser(User user);
    //根据用户编号查询用户信息
    public User getUserById(Integer id);
    //修改用户
    public Integer updateUserById(User user);
    //删除用户
    public Integer deleteUserById(Integer id);
    //根据编号查找用户 viewUser
    public User getVewUserById(Integer id);
    //查询用户密码
    public String getUserPasswordById(Integer id);
    //修改用户密码
    public Integer updateUserPasswordById(Integer id,String password);
}
