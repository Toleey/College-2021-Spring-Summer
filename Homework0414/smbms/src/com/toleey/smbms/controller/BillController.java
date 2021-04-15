package com.toleey.smbms.controller;

import com.toleey.smbms.service.bill.BillService;
import org.springframework.stereotype.Controller;
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

}
