package com.toleey.appinfo.service.developer.user;

import com.toleey.appinfo.pojo.DevUser;

public interface DevUserService {
    //根据devCode查询开发者用户
    public DevUser findDevUserByDevCode(String devCode);
}
