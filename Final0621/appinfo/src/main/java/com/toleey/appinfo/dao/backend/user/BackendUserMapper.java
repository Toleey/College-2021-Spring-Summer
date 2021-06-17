package com.toleey.appinfo.dao.backend.user;

import com.toleey.appinfo.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

public interface BackendUserMapper {
    public BackendUser getBackendUser(@Param("userCode") String userCode);
}
