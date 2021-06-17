package com.toleey.appinfo.service.backend.user;

import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.BackendUser;

public interface BackendUserService {
    //查询用户密码
    public BackendUser findBackendUser (String userCode);

}
