package cn.bw.springbootmybatisjsp.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;
import java.util.Date;

public class User {
    private Integer id;
    //@NotEmpty(message = "用户编号不能为空")
    private String userCode;
    private String userName;
    private String userPassword;
    private String ruserPassword;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //表单里的数据往对象里注入的时候
    private Date birthday;
    private Integer age;
    private String phone;
    private String address;
    private Integer userRole;
    private Integer createdBy;
    private Timestamp creationDate;
    private Integer modifyBy;
    private Timestamp modifyDate;
    public String idPicPath;
    public String workPicPath;

    //角色名称
    private String roleName;


    public User() {
    }

    public User(Integer id, String userCode, String userName, String userPassword, String ruserPassword, Integer gender, Date birthday, Integer age, String phone, String address, Integer userRole, Integer createdBy, Timestamp creationDate, Integer modifyBy, Timestamp modifyDate, String idPicPath, String workPicPath, String roleName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.ruserPassword = ruserPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.idPicPath = idPicPath;
        this.workPicPath = workPicPath;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRuserPassword() {
        return ruserPassword;
    }

    public void setRuserPassword(String ruserPassword) {
        this.ruserPassword = ruserPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIdPicPath() {
        return idPicPath;
    }

    public void setIdPicPath(String idPicPath) {
        this.idPicPath = idPicPath;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getWorkPicPath() {
        return workPicPath;
    }

    public void setWorkPicPath(String workPicPath) {
        this.workPicPath = workPicPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", ruserPassword='" + ruserPassword + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userRole=" + userRole +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", idPicPath='" + idPicPath + '\'' +
                ", workPicPath='" + workPicPath + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
