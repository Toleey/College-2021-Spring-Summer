package com.toleey.smbms.dao.bill;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface BillDao {
    //查找订单
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(
            Connection conn, String productName,Integer providerId,Integer isPayment,
            Integer fromLineNum, Integer toLineNum);
    //特定条件查找订单总数
    public Integer getCountBillListByProductNameAndProviderAndIsPayment(
            Connection conn, String productName,Integer providerId,Integer isPayment
    );

}
