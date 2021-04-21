package com.toleey.smbms.dao.bill.impl;

import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.entity.Bill;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository("billDao")
public class BillDaoImpl implements BillDao {
    @Override
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(Connection conn, String billCode, String proName) {
        return null;
    }
}
