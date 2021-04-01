package com.toleey.smbms.service.bill.impl;

import com.toleey.smbms.dao.bill.BillMapper;
import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

    @Autowired
    @Qualifier("billMapper")
    private BillMapper billMapper;

    public BillMapper getBillMapper() {
        return billMapper;
    }

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> selectBillByCondition(String productName, Integer proId) {
        List<Bill> billList = billMapper.selectBill(productName,proId);
        return billList;
    }
}
