package com.toleey.smbms.dao.user;

import com.toleey.smbms.entity.User;

import java.util.List;

public interface UserMapper {

    public List<User> getUserListByCondition(User user);
}
