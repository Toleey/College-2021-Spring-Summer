package com.toleey.smbms.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
        /**
         * 使用@InitBinder解决SpringMVC日期类型无法绑定的问题
         * @param dataBinder
         */
        @InitBinder
        public void initBinder(WebDataBinder dataBinder){
            System.out.println("initBinder=======================");

            dataBinder.registerCustomEditor(Date.class,
                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

        }
    }
