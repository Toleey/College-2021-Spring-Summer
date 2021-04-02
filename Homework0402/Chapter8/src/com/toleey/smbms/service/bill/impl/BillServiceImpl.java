package com.toleey.smbms.service.bill.impl;

import com.toleey.smbms.dao.bill.BillMapper;
import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Scope(value = "prototype")
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

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public List<Bill> selectBillByCondition(String productName, Integer proId) {
        List<Bill> billList = billMapper.selectBill(productName,proId);
        return billList;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer addABill(Bill bill) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            billMapper.addABill(bill);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer updateABill(Bill bill) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            billMapper.updateABill(bill);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }

    @Transactional(propagation =  Propagation.SUPPORTS)
    @Override
    public Integer deleteABill(Integer id) {
        Integer t = 0; //0 失败 ｜ 1 通过

        try {
            billMapper.deleteABill(id);
            t = 1;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }

        return t;
    }
}
