package cn.smbms.service.user.impl;


import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByUserCode(String userCode) {
        User user = null;
        try {
            user = userMapper.getUserByUserCode(userCode);

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findUserListByNameAndRole(String userName, Integer roleId, Integer fromLineNum, Integer toLineNum) {
        List<User> userList = userMapper.getUserListByNameAndRole(userName,roleId,fromLineNum,toLineNum);
        return userList;
    }

    @Override
    public Integer findUserCountByNameAndRole(String userName, Integer roleId) {
        Integer lines = userMapper.getUserCountByNameAndRole(userName,roleId);
        return lines;
    }

    @Override
    public boolean addUser(User user) {
        Integer line = userMapper.insertUser(user);
        return line>=1;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public boolean modifyUser(User user) {
        Integer line = userMapper.updateUserById(user);
        return line>=1;
    }

    @Override
    public boolean deleteUser(Integer id) {
        Integer line = userMapper.deleteUserById(id);
        return line>=1;
    }

    @Override
    public User findViewUserById(Integer id) {
        User user = userMapper.getVewUserById(id);
        return user;
    }

    @Override
    public String findUserPasswordById(Integer id) {
        String userPassword = userMapper.getUserPasswordById(id);
        return userPassword;
    }

    @Override
    public boolean modifyUserPasswordById(Integer id, String password) {
        Integer line = userMapper.updateUserPasswordById(id,password);
        return line>=1;
    }
}
