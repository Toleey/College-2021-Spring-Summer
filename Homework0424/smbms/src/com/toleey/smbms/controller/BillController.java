package com.toleey.smbms.controller;

import com.toleey.smbms.entity.Bill;
import com.toleey.smbms.service.bill.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Resource(name = "billService")
    private BillService billService;
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @RequestMapping("/bill.do")
    public String ds(){

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
