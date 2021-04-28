package com.toleey.smbms.service.bill;

import com.toleey.smbms.entity.Bill;

import java.util.List;

public interface BillService {
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment, Integer fromLineNum, Integer toLineNum);
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment);
}
