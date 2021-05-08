package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {
    //查找订单
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment,
            Integer fromLineNum, Integer toLineNum);
    //特定条件查找订单总数
    public Integer getCountBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment
    );

}
