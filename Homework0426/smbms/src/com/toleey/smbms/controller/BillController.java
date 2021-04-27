package com.toleey.smbms.controller;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.entity.Provider;
import com.toleey.smbms.service.bill.BillService;
import com.toleey.smbms.service.provider.ProviderService;
import com.toleey.smbms.util.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/bill")
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

    @RequestMapping("/billadd.html")
    public String addBill(@ModelAttribute("bill") Bill bill){
        return "billadd";
    }

    @RequestMapping("/doBillSave.do")
    public String doBillSave(){

        return "redirect:/bill/bill.do";
    }

}
