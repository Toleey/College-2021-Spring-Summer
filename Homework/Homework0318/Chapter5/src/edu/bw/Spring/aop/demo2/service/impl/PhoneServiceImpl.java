package edu.bw.Spring.aop.demo2.service.impl;

import edu.bw.Spring.aop.demo2.dao.PhoneDao;
import edu.bw.Spring.aop.demo2.entity.Phone;
import edu.bw.Spring.aop.demo2.service.PhoneService;

public class PhoneServiceImpl implements PhoneService {
    private PhoneDao phoneDao;

    public PhoneDao getPhoneDao() {
        return phoneDao;
    }

    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public void addNewPhone(Phone phone) {
        phoneDao.addPhone(phone);
    }
}
