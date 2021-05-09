package cn.smbms.service.bill;



import cn.smbms.pojo.Bill;

import java.util.List;

public interface BillService {
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment, Integer fromLineNum, Integer toLineNum);
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment);
}
