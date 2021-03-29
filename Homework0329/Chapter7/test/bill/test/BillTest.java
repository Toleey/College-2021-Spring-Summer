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
}
