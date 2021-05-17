package com.toleey.appinfo.service.backend.user;

import com.toleey.appinfo.pojo.BackendUser;

public interface BackUserService {
    //查询用户密码
    public BackendUser findBackendUser (String userCode);
}
