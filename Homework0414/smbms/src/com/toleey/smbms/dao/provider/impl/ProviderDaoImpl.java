package com.toleey.smbms.dao.provider.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.provider.ProviderDao;
import com.toleey.smbms.entity.Provider;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("providerDao")
public class ProviderDaoImpl implements ProviderDao {
    @Override
    public List<Provider> getProviderListByProCodeAndProName(Connection conn, String proCode, String proName) {
        String sql = "SELECT proCode,proName,proContact,proPhone,proFax,creationDate FROM smbms_provider WHERE 1=1 ";
        if (proCode!=null && !proCode.equals("")){
            sql+=" AND proCode LIKE '%"+proCode+"%' ";
        }
        if (proName!=null && !proName.equals("")){
            sql+=" AND proName LIKE '%"+proName+"%' ";
        }
        System.out.println(sql);
        ResultSet rst =  BaseDao.executeQuery(conn,sql);
        List<Provider> providerList = new ArrayList<Provider>();
            try {
                while (rst.next()){
                    Provider provider = new Provider();
                    provider.setProCode(rst.getString("proCode"));
                    provider.setProName(rst.getString("proName"));
                    provider.setProContact(rst.getString("proContact"));
                    provider.setProPhone(rst.getString("proPhone"));
                    provider.setProFax(rst.getString("proFax"));
                    provider.setCreationDate(rst.getTimestamp("creationDate"));
                    providerList.add(provider);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }



        return providerList;
    }

}
