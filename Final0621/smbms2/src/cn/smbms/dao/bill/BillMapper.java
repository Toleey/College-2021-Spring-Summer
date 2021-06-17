package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.util.List;

public interface BillMapper {
    //查找订单
    public List<Bill> getBillListByProductNameAndProviderAndIsPayment(
            @Param("productName") String productName, @Param("providerId") Integer providerId,
            @Param("isPayment") Integer isPayment, @Param("fromLineNum") Integer fromLineNum,
            @Param("toLineNum") Integer toLineNum);
    //特定条件查找订单总数
    public Integer getCountBillListByProductNameAndProviderAndIsPayment(
            @Param("productName") String productName,@Param("providerId") Integer providerId,
            @Param("isPayment") Integer isPayment
    );
    //根据ID查订单信息
    public Bill getViewBillById(@Param("id") Integer id);
    //新增订单
    public Integer insertBill(Bill bill);
    //删除订单
    public Integer deleteBillById(@Param("id") Integer id);
    //查询订单 修改用
    public Bill getABillById(@Param("id") Integer id);
    //修改订单
    public Integer updateBillById(Bill bill);

}
