package com.toleey.smbms.dao.bill;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.entity.Provider;

import java.sql.Connection;
import java.util.List;

public interface BillDao {
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(Connection conn, String billCode, String proName);
}
