package com.toleey.appinfospringboot.service.user;

import com.toleey.appinfospringboot.dao.user.DevUserMapper;
import com.toleey.appinfospringboot.pojo.DevUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
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
