package com.toleey.appinfo.service.developer.user;

import com.toleey.appinfo.dao.developer.user.DevUserMapper;
import com.toleey.appinfo.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService{

    @Resource(name = "devUserMapper")
    private DevUserMapper devUserMapper;

    public void setDevUserMapper(DevUserMapper devUserMapper) {
        this.devUserMapper = devUserMapper;
    }

    @Override
    public DevUser findDevUserByDevCode(String devCode) {
         return devUserMapper.getDevUserByDevCode(devCode);
    }
}
