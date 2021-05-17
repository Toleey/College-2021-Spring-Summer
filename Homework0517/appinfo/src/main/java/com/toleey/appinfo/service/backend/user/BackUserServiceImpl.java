package com.toleey.appinfo.service.backend.user;

import com.toleey.appinfo.dao.backend.user.BackUserMapper;
import com.toleey.appinfo.pojo.BackendUser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("backUserService")
public class BackUserServiceImpl implements BackUserService{

    @Resource(name = "backUserMapper")
    private BackUserMapper backUserMapper;

    public void setBackUserMapper(BackUserMapper backUserMapper) {
        this.backUserMapper = backUserMapper;
    }

    @Override
    public BackendUser findBackendUser(String userCode) {
        return backUserMapper.getBackendUser(userCode);
    }
}
