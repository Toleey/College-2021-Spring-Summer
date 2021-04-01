package com.toleey.smbms.dao.provider.impl;

import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class ProviderDaoImpl implements ProviderDao {

    @Override
    public List<Provider> getAllProviderList(@Param("proName") String proName) {
        SqlSessionTemplate sqlSession = (SqlSessionTemplate) ApplicationContextUtil.getApplicationContext()
                .getBean("sqlSessionTemplate");

        List<Provider> providerList = sqlSession.selectList
                ("getAllProviderList");

        return providerList;
    }
}
