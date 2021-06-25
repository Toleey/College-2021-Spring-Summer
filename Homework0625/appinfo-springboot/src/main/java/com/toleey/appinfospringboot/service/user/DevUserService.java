package com.toleey.appinfospringboot.service.user;

import com.toleey.appinfospringboot.pojo.DevUser;

public interface DevUserService {
    //根据devCode查询开发者用户
    public DevUser findDevUserByDevCode(String devCode);
}
