package cn.bw.springbootmybatisjsp.service;

import cn.bw.springbootmybatisjsp.pojo.User;

import java.util.List;

public interface UserService {

    public int findUserCount();
    public List<User> findAllUser();

}
