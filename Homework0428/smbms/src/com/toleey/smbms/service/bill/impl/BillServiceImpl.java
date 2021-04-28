package com.toleey.smbms.service.bill.impl;

import com.toleey.smbms.dao.BaseDao;
import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    @Qualifier("billDao")
    private BillDao billDao;
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    @Override
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(
            String productName, Integer providerId, Integer isPayment, Integer fromLineNum, Integer toLineNum) {
        Connection conn = BaseDao.getConnection();
        List<Bill> billList = billDao.getBillListByProductNameAndProviderAndIsPayment(conn,productName,providerId,isPayment,fromLineNum,toLineNum);
        BaseDao.close(conn,null,null);
        return billList;
    }

    @Override
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(String productName, Integer providerId, Integer isPayment) {
        Connection conn = BaseDao.getConnection();
        Integer lines = billDao.getCountBillListByProductNameAndProviderAndIsPayment(conn,productName,providerId,isPayment);
        BaseDao.close(conn,null,null);
        return lines;
    }
}
