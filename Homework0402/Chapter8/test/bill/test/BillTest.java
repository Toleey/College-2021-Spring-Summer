package bill.test;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.service.bill.BillService;
import com.toleey.smbms.service.user.UserService;
import com.toleey.smbms.util.ApplicationContextUtil;
import org.junit.Test;

import java.util.List;

public class BillTest {

    @Test
    public void testBill(){
        BillService bs = (BillService) ApplicationContextUtil.getApplicationContext().getBean("billService");
        List<Bill> billList =  bs.selectBillByCondition("大",3);
        System.out.println(billList);

    }

    @Test
    public void addABillTest(){
        Bill bill = new Bill();
        bill.setBillCode("bili");
        bill.setProductName("哈哈");
        bill.setProductDesc("呵呵");
        bill.setProductUnit("乐");
        bill.setProductCount(100.00);
        bill.setTotalPrice(200.00);
        bill.setIsPayment(1);
        bill.setCreatedBy(1);
        //bill.setCreationDate();
        bill.setProviderId(2);



        BillService bs = (BillService) ApplicationContextUtil.getApplicationContext().getBean("billService");
        Integer result = bs.addABill(bill);
        if (result.equals(0)){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功:"+bill);
        }
    }

    @Test
    public void updateABillTest(){
        Bill bill = new Bill();
        bill.setId(20);
        bill.setBillCode("oh");
        bill.setProductName("嘻嘻");
        bill.setProductDesc("呼呼");
        bill.setProductUnit("好");
        bill.setProductCount(90.00);
        bill.setTotalPrice(20.00);
        bill.setIsPayment(2);
        bill.setCreatedBy(2);
        //bill.setCreationDate();
        bill.setProviderId(1);

        BillService bs = (BillService) ApplicationContextUtil.getApplicationContext().getBean("billService");
        Integer result = bs.updateABill(bill);
        if (result.equals(0)){
            System.out.println("添加失败");
        }else{
            System.out.println("添加成功");
        }

    }

    @Test
    public void deleteABillTest(){
        Integer id = 21;
        BillService bs = (BillService) ApplicationContextUtil.getApplicationContext().getBean("billService");
        Integer result = bs.deleteABill(id);
        if (result.equals(0)){
            System.out.println("删除失败");
        }else{
            System.out.println("删除成功");
        }
    }
}
