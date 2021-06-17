package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Pager;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
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
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId",queryProviderId);
        model.addAttribute("queryIsPayment",queryIsPayment);
        //当前页
        int currentpage = 1;
        if (currentPageNum!=null && !"".equals(currentPageNum)){
            currentpage = Integer.parseInt(currentPageNum);
        }
        model.addAttribute("pageCount",currentpage);
        //总条数
        Integer providerId = null;
        if (queryProviderId!=null && !"".equals(queryProviderId) && !"0".equals(queryProviderId)){//传0进去数据库查不到
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
    //新增订单 获取供应商列表 AJAX
    @RequestMapping("/getProviderList.do")
    @ResponseBody
    public Object getProviderList(){
        List<Provider> providerList = providerService.findAllProviderIdAndProName();
        return JSON.toJSONString(providerList);
    }
    //新增订单提交
    @RequestMapping(value = "/doBillSave.do",method = RequestMethod.POST)
    public String doBillSave(Bill bill, HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        bill.setModifyBy(loginUser.getId());
        bill.setCreationDate(new Timestamp(new Date().getTime()));
        billService.addBill(bill);
        return "redirect:/sys/bill/bill.do";
    }
    //修改订单
    @RequestMapping(value = "/modifyBill.html",method = RequestMethod.GET)
    public String modifyBill(@RequestParam("billid") Integer billid,@ModelAttribute("bill") Bill bill,Model model){
        Bill bill2 = billService.findABillById(billid);
        model.addAttribute("bill",bill2);
        return "billmodify";
    }
    //修改订单提交
    @RequestMapping(value = "/modifyBillSave.do",method = RequestMethod.POST)
    public String modifyBillSave(Bill bill,HttpSession session){
        User loginUser = (User) session.getAttribute("userSession");
        bill.setModifyBy(loginUser.getId());
        bill.setModifyDate(new Timestamp(new Date().getTime()));
        billService.updateBill(bill);
        return "redirect:/sys/bill/bill.do";
    }
    //删除订单
    @RequestMapping(value = "/deleteBillSave.do",method = RequestMethod.GET)
    @ResponseBody
    public Object deleteBill(@Param("billid") Integer billid){
        boolean result = billService.deleteBill(billid);
        String data = null;
        if (result){
            data = "true";
        }else {
            data = "false";
        }
        return JSON.toJSONString(data);
    }

    //查看订单
    @RequestMapping(value = "/viewBill.do",method = RequestMethod.GET)
    public String viewProvider(@RequestParam("billid") Integer billid,Model model){
        Bill bill = billService.findViewBillById(billid);
        model.addAttribute("bill",bill);
        return "billview";
    }

    //AJAX查看订单
    @RequestMapping(value = "/viewBillAjax.do",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public Object viewBillAjax(@RequestParam("id") String id){
        Bill bill = new Bill();
        if (id == null || "".equals(id)){
            return JSON.toJSONString("nodata");
        }else {
            try {
                Integer billid = Integer.parseInt(id);
                bill = billService.findViewBillById(billid);
            }catch (Exception e){ //数据用可能传过来不是数字
                e.printStackTrace();
                return JSON.toJSONString("failed");
            }
        }
        return JSON.toJSONString(bill);
    }

}
