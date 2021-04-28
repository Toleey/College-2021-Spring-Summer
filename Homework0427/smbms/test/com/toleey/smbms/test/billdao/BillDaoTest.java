package com.toleey.smbms.test.billdao;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.dao.bill.impl.BillDaoImpl;
import com.toleey.smbms.entity.Bill;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class BillDaoTest {

    @Test
    public void testBillDao(){
        Connection conn = BaseDao.getConnection();
        BillDao billDao = new BillDaoImpl();
        List<Bill> billList = billDao.getBillListByProductNameAndProviderAndIsPayment(conn,"油",6,2,0,5);
        for (Bill bill : billList) {
			System.out.println(bill);
		}
        
    }

}
