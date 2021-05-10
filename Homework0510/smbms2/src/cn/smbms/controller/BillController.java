package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/bill")
public class BillController {

    @Resource(name = "billService")
    private BillService billService;
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @Resource(name = "providerService")
    private ProviderService providerService;
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping(value = "/bill.do",method = RequestMethod.GET)
    public String billList(
            @RequestParam(value = "queryProductName",required = false) String queryProductName,
            @RequestParam(value = "queryProviderId",required = false) String queryProviderId,
            @RequestParam(value = "queryIsPayment",required = false) String queryIsPayment,
            @RequestParam(value = "pageIndex",required = false) String currentPageNum,
            Model model
    ){
        //当前页
        int currentpage = 1;
        if (currentPageNum!=null && !"".equals(currentPageNum)){
            currentpage = Integer.parseInt(currentPageNum);
        }
        model.addAttribute("pageCount",currentpage);
        //总条数
        Integer providerId = null;
        if (queryProviderId!=null && !"".equals(queryProviderId) && !"0".equals(queryIsPayment)){//传0进去数据库查不到
            providerId = Integer.parseInt(queryProviderId);
        }
        Integer isPayment = null;
        if (queryIsPayment!=null && !"".equals(queryIsPayment) && !"0".equals(queryIsPayment)){ //传0进去数据库查不到
            isPayment = Integer.parseInt(queryIsPayment);
        }
        int rowCount = billService.findCountBillListByProductNameAndProviderAndIsPayment(queryProductName,providerId,isPayment);
        model.addAttribute("totalCount",rowCount);
        //每页显示条数
        int rowPerPage = 5;
        //总页数
        Pager pager = new Pager(rowCount,rowPerPage,currentpage);
        int pageCount = pager.getPageCount();
        model.addAttribute("totalPageCount",pageCount);
        model.addAttribute("currentPageNo",currentpage);

        model.addAttribute("queryUserName",queryProductName);
        model.addAttribute("provider.id",providerId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        //获得分页记录
        int fromLineNum=(currentpage-1)*rowPerPage;

        List<Provider> providerList = providerService.findAllProviderIdAndProName();
        model.addAttribute("providerList",providerList);
        List<Bill> billList = billService.findBillListByProductNameAndProviderAndIsPayment(
                queryProductName,providerId,isPayment,fromLineNum,rowPerPage);
        model.addAttribute("billList",billList);
        return "billlist";
    }
    //新增订单
    @RequestMapping("/billadd.html")
    public String addBill(@ModelAttribute("bill") Bill bill){
        return "billadd";
    }
    //新增订单提交
    @RequestMapping("/doBillSave.do")
    public String doBillSave(){

        return "redirect:/sys/bill/bill.do";
    }
    //修改订单
    @RequestMapping(value = "/modifyBill.html",method = RequestMethod.GET)
    public String modifyBill(@RequestParam("billid") Integer billid,Model model){
        Bill bill = billService.findViewBillById(billid);
        model.addAttribute("bill",bill);
        return "billmodify";
    }
    //修改订单提交
    @RequestMapping(value = "modifyBillSave.do",method = RequestMethod.POST)
    public String modifyBillSave(){
        return "redirect:/sys/bill/bill.do";
    }
    //删除订单
    @RequestMapping(value = "/deleteBill.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteBill(@Param("billid") Integer billid){

        String dataStr = "";

        return dataStr;
    }

    //查看订单
    @RequestMapping(value = "/viewBill.do",method = RequestMethod.GET)
    public String viewProvider(@RequestParam("billid") Integer billid,Model model){
        Bill bill = billService.findViewBillById(billid);
        model.addAttribute("bill",bill);
        return "billview";
    }

    //AJAX查看订单
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    public Object viewAjaxBill(@RequestParam("id") String id){

        String dataStr = "";
        return dataStr;
    }

}
