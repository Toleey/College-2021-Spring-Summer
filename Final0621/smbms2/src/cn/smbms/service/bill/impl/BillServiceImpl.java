package cn.smbms.service.bill.impl;


import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.service.bill.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

   @Resource(name = "billMapper")
   private BillMapper billMapper;

    public void setBillMapper(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> findBillListByProductNameAndProviderAndIsPayment(String productName, Integer providerId, Integer isPayment, Integer fromLineNum, Integer toLineNum) {
        List<Bill> billList = billMapper.getBillListByProductNameAndProviderAndIsPayment(productName,providerId,isPayment,fromLineNum,toLineNum);
        return billList;
    }

    @Override
    public Integer findCountBillListByProductNameAndProviderAndIsPayment(String productName, Integer providerId, Integer isPayment) {
        Integer line = billMapper.getCountBillListByProductNameAndProviderAndIsPayment(productName,providerId,isPayment);
        return line;
    }

    @Override
    public Bill findViewBillById(Integer id) {
        Bill bill = billMapper.getViewBillById(id);
        return bill;
    }

    @Override
    public boolean addBill(Bill bill) {
        Integer line = billMapper.insertBill(bill);
        return line>=1;
    }

    @Override
    public boolean updateBill(Bill bill) {
        Integer line = billMapper.updateBillById(bill);
        return line>=1;
    }

    @Override
    public boolean deleteBill(Integer id) {
        Integer line = billMapper.deleteBillById(id);
        return line>=1;
    }

    @Override
    public Bill findABillById(Integer id) {
        return billMapper.getABillById(id);
    }
}
