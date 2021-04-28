package com.toleey.smbms.dao.bill.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.entity.Bill;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("billDao")
public class BillDaoImpl implements BillDao {

    @Override
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(
            Connection conn, String productName, Integer providerId, Integer isPayment,
            Integer fromLineNum, Integer toLineNum) {
        String sql = "SELECT " +
                "b.billCode,b.productName,p.proName,b.totalPrice,b.isPayment,b.creationDate " +
                "FROM smbms_bill b INNER JOIN smbms_provider p ON b.providerId = p.id WHERE 1=1 ";
        if (productName!=null && !productName.equals("")){
            sql+="AND productName LIKE '%"+productName+"%' ";
        }
        if (providerId!=null){
            sql+=" AND providerId = "+providerId;
        }
        if(isPayment!=null){
            sql+=" AND isPayment ="+isPayment;
        }
            sql+=" limit "+fromLineNum+","+toLineNum;

        ResultSet rst = BaseDao.executeQuery(conn,sql);
        List<Bill> billList = new ArrayList<Bill>();

        try {
            while (rst.next()){
                Bill bill = new Bill();
                bill.setBillCode(rst.getString("billCode"));
                bill.setProductName(rst.getString("productName"));
                bill.setProName(rst.getString("proName"));
                bill.setTotalPrice(rst.getDouble("totalPrice"));
                bill.setIsPayment(rst.getLong("isPayment"));
                bill.setCreationDate(rst.getTimestamp("creationDate"));
                billList.add(bill);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return billList;
    }

    @Override
    public Integer getCountBillListByProductNameAndProviderAndIsPayment(Connection conn, String productName, Integer providerId, Integer isPayment) {
        String sql = "SELECT count(*) as linenum FROM smbms_bill WHERE 1=1 ";
        if (productName!=null && !productName.equals("")){
            sql+="AND productName LIKE '%"+productName+"%' ";
        }
        if (providerId!=null){
            sql+=" AND providerId = "+providerId;
        }
        if(isPayment!=null){
            sql+=" AND isPayment ="+isPayment;
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
}
