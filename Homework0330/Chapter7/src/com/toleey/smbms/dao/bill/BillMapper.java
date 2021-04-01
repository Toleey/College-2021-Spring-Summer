package com.toleey.smbms.dao.bill;

import com.toleey.smbms.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {

    public List<Bill> selectBill(
            @Param("productName") String productName,
            @Param("proId") Integer proId
    );



}
