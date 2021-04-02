package com.toleey.smbms.service.bill;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {
    public List<Bill> selectBillByCondition(
            @Param("productName") String productName,
            @Param("proId") Integer proId
    );

    public Integer addABill(Bill bill);

    public Integer updateABill(Bill bill);

    public Integer deleteABill(Integer id);
}
