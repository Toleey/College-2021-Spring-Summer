package com.toleey.smbms.service.bill.impl;

import com.toleey.smbms.dao.bill.BillDao;
import com.toleey.smbms.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    @Qualifier("billDao")
    private BillDao billDao;
    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

}
