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
        return null;
    }

    @Override
    public Integer findUserCountByNameAndRole(String userName, Integer roleId) {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public boolean modifyUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return false;
    }

    @Override
    public User findViewUserById(Integer id) {
        return null;
    }

    @Override
    public String findUserPasswordById(Integer id) {
        return null;
    }

    @Override
    public boolean modifyUserPasswordById(Integer id, String password) {
        return false;
    }
}
