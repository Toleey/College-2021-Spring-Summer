package com.toleey.appinfospringboot.dao.user;

import com.toleey.appinfospringboot.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    //根据devCode查询开发者账户
    public DevUser getDevUserByDevCode(@Param("devCode") String devCode);
}
