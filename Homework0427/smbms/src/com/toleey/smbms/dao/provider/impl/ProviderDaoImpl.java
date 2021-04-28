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
    public List<Provider> getProviderListByProCodeAndProName(Connection conn, String proCode, String proName,Integer fromLineNum,Integer toLineNum) {
        String sql = "SELECT id,proCode,proName,proContact,proPhone,proFax,creationDate FROM smbms_provider WHERE 1=1 ";
        if (proCode!=null && !proCode.equals("")){
            sql+=" AND proCode LIKE '%"+proCode+"%' ";
        }
        if (proName!=null && !proName.equals("")){
            sql+=" AND proName LIKE '%"+proName+"%' ";
        }
        sql+=" limit "+fromLineNum+","+toLineNum;

        ResultSet rst =  BaseDao.executeQuery(conn,sql);
        List<Provider> providerList = new ArrayList<Provider>();
            try {
                while (rst.next()){
                    Provider provider = new Provider();
                    provider.setId(rst.getInt("id"));
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

    @Override
    public Integer getProviderCountByProCodeAndProName(Connection conn, String proCode, String proName) {
        String sql = "SELECT count(*) as linenum FROM smbms_provider WHERE 1=1 ";
        if (proCode!=null && !proCode.equals("")){
            sql+=" AND proCode LIKE '%"+proCode+"%' ";
        }
        if (proName!=null && !proName.equals("")){
            sql+=" AND proName LIKE '%"+proName+"%' ";
        }

        ResultSet rst = BaseDao.executeQuery(conn,sql);
        int linenum = 0;
        try {
            if (rst.next()){
                linenum=rst.getInt("linenum");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return linenum;
    }

    @Override
    public Integer insertProvider(Connection conn, Provider provider) {
        String sql = "INSERT INTO `smbms`.`smbms_provider` " +
                "(`proCode`, `proName`, `proDesc`, `proContact`, `proPhone`, `proAddress`, `proFax`, `createdBy`, " +
                "`creationDate`,`companyLicPicPath`,`orgCodePicPath`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        Integer line = BaseDao.executeUpdate(conn,sql,provider.getProCode(),provider.getProName(), provider.getProDesc(),
                provider.getProContact(), provider.getProPhone(),provider.getProAddress(),provider.getProFax(),
                provider.getCreatedBy(), provider.getCreationDate(),provider.getCompanyLicPicPath(),provider.getOrgCodePicPath());

        return line;
    }

    @Override
    public Integer updateProviderById(Connection conn, Provider provider) {
        String sql = "UPDATE `smbms`.`smbms_provider` SET " +
                "`proName` = ? , `proDesc` = ? , `proContact` = ? , `proPhone` = ? , `proAddress` = ? , `proFax` = ? , " +
                "`modifyDate` = ? , `modifyBy` = ?  " +
                "WHERE `id` = ? ";
        Integer line = BaseDao.executeUpdate(conn,sql,provider.getProName(),provider.getProDesc(),provider.getProContact(),
                provider.getProPhone(),provider.getProAddress(),provider.getProFax(),
                provider.getModifyDate(),provider.getModifyBy(),provider.getId());
        return line;
    }

    @Override
    public Provider getProviderById(Connection conn, Integer id) {
        String sql = "SELECT id,proCode,proName,proContact,proAddress,proPhone,proFax,proDesc FROM smbms_provider WHERE id = ?";
        ResultSet rst = BaseDao.executeQuery(conn,sql,id);
        Provider provider = new Provider();
        try {
            if (rst.next()){
                provider.setId(rst.getInt("id"));
                provider.setProCode(rst.getString("proCode"));
                provider.setProName(rst.getString("proName"));
                provider.setProContact(rst.getString("proContact"));
                provider.setProAddress(rst.getString("proAddress"));
                provider.setProPhone(rst.getString("proPhone"));
                provider.setProFax(rst.getString("proFax"));
                provider.setProDesc(rst.getString("proDesc"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return provider;
    }

    @Override
    public Integer deleteProviderById(Connection conn, Integer id) {
        String sql = "DELETE FROM smbms_provider WHERE id = ?";
        Integer line = BaseDao.executeUpdate(conn,sql,id);
        return line;
    }

    @Override
    public Provider getViewProviderById(Connection conn, Integer id) {
        String sql = "SELECT proCode,proName,proContact,proPhone,proFax,proDesc FROM smbms_provider WHERE id = ?";
        ResultSet rst = BaseDao.executeQuery(conn,sql,id);
        Provider provider = new Provider();
        try {
            if (rst.next()){
                provider.setProCode(rst.getString("proCode"));
                provider.setProName(rst.getString("proName"));
                provider.setProContact(rst.getString("proContact"));
                provider.setProPhone(rst.getString("proPhone"));
                provider.setProFax(rst.getString("proFax"));
                provider.setProDesc(rst.getString("proDesc"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return provider;
    }

    @Override
    public List<Provider> getAllProviderIdAndProName(Connection conn) {
        String sql = "SELECT * FROM smbms_provider";
        ResultSet rst = BaseDao.executeQuery(conn,sql);
        List<Provider> providerList = new ArrayList<Provider>();
        try {
            while (rst.next()){
                Provider provider = new Provider();
                provider.setId(rst.getInt("id"));
                provider.setProName(rst.getString("proName"));
                providerList.add(provider);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return providerList;
    }

}
