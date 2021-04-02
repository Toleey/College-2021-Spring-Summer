package com.toleey.smbms.dao.user;

import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> getUserListByCondition(User user);

    public void addAUser(User user);
    public void updateAUser(User user);
    public void deleteAUser(@Param("id") Integer id);
}
