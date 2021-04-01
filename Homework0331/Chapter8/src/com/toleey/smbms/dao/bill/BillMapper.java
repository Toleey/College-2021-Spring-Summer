package com.toleey.smbms.dao.bill;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {

    public List<Bill> selectBill(
            @Param("productName") String productName,
            @Param("proId") Integer proId
    );
    public void addABill(Bill bill);
    public void updateABill(Bill bill);
    public void deleteABill(@Param("id") Integer id);


}
