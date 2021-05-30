package com.toleey.appinfo.service.backend.user;

import com.toleey.appinfo.dao.backend.user.BackendUserMapper;
import com.toleey.appinfo.pojo.BackendUser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {

    @Resource(name = "backendUserMapper")
    private BackendUserMapper backendUserMapper;

    public void setBackendUserMapper(BackendUserMapper backendUserMapper) {
        this.backendUserMapper = backendUserMapper;
    }

    @Override
    public BackendUser findBackendUser(String userCode) {
        return backendUserMapper.getBackendUser(userCode);
    }


}
