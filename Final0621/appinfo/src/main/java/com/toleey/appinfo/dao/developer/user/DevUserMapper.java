package com.toleey.appinfo.dao.developer.user;

import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevUserMapper {
    //根据devCode查询开发者账户
    public DevUser getDevUserByDevCode(@Param("devCode") String devCode);

}
