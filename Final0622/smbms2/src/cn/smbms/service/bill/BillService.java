package cn.smbms.service.bill;



import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment, Integer fromLineNum, Integer toLineNum);
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(
            String productName,Integer providerId,Integer isPayment);
    public Bill findViewBillById(Integer id);
    public boolean addBill(Bill bill);
    public boolean updateBill(Bill bill);
    public boolean deleteBill(Integer id);
    public Bill findABillById(Integer id);    //查询订单 修改用
}
