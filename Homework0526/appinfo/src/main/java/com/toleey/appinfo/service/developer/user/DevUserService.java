package com.toleey.appinfo.service.developer.user;

import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevUserService {
    //根据devCode查询开发者用户
    public DevUser findDevUserByDevCode(String devCode);

}
