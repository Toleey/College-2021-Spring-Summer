package com.toleey.smbms.test.billdao;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.dao.bill.impl.BillDaoImpl;
import org.junit.Test;

import java.sql.Connection;

public class BillDaoTest {

    @Test
    public void testBillDao(){
        Connection conn = BaseDao.getConnection();
        BillDao billDao = new BillDaoImpl();
    }

}
