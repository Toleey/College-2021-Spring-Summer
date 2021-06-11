package cn.bw.springbootthymeleaf.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String name;
    private String describe;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    public User(Integer id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public User() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
