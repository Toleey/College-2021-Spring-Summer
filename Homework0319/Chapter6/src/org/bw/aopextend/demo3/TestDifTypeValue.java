package org.bw.aopextend.demo3;

import org.bw.aopextend.demo1.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestDifTypeValue {
    //内部bean
    private User innerUser;
    //数组
    private String[] arrCountry;
    //list属性
    private List<String> listCyty;
    //map属性
    private Map<String,String> mapCountry;
    //Properties集合作为属性
    private Properties dbProperties;
    //注入null值
    private String nullValue;
    //注入空字符串的属性:""
    private String blankValue;



    public User getInnerUser() {
        return innerUser;
    }

    public void setInnerUser(User innerUser) {
        this.innerUser = innerUser;
    }

    public String[] getArrCountry() {
        return arrCountry;
    }

    public void setArrCountry(String[] arrCountry) {
        this.arrCountry = arrCountry;
    }

    public List<String> getListCyty() {
        return listCyty;
    }

    public void setListCyty(List<String> listCyty) {
        this.listCyty = listCyty;
    }

    public Map<String, String> getMapCountry() {
        return mapCountry;
    }

    public void setMapCountry(Map<String, String> mapCountry) {
        this.mapCountry = mapCountry;
    }

    public Properties getDbProperties() {
        return dbProperties;
    }

    public void setDbProperties(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public String getBlankValue() {
        return blankValue;
    }

    public void setBlankValue(String blankValue) {
        this.blankValue = blankValue;
    }

    public String getNullValue() {
        return nullValue;
    }

    public void setNullValue(String nullValue) {
        this.nullValue = nullValue;
    }

    @Override
    public String toString() {
        return "TestDifTypeValue{" +
                "innerUser=" + innerUser +
                ", arrCountry=" + Arrays.toString(arrCountry) +
                ", listCyty=" + listCyty +
                ", mapCountry=" + mapCountry +
                ", dbProperties=" + dbProperties +
                ", nullValue='" + nullValue + '\'' +
                ", blankValue='" + blankValue + '\'' +
                '}';
    }
}
