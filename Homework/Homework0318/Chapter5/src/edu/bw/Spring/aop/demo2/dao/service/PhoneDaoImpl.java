package edu.bw.Spring.aop.demo2.dao.service;

import edu.bw.Spring.aop.demo2.dao.PhoneDao;
import edu.bw.Spring.aop.demo2.entity.Phone;

public class PhoneDaoImpl implements PhoneDao {
    @Override
    public void addPhone(Phone phone) {
        System.out.println("编号："+phone.getId()+" 品牌："+phone.getName()+" 价格："+phone.getPrice()+"元，……买吗？？？");
    }
}
