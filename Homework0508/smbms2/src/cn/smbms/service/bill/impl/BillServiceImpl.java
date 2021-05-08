package cn.smbms.service.bill.impl;


import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
import cn.smbms.service.bill.BillService;
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
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(String productName, Integer providerId, Integer isPayment, Integer fromLineNum, Integer toLineNum) {
        return null;
    }

    @Override
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(String productName, Integer providerId, Integer isPayment) {
        return null;
    }
}
