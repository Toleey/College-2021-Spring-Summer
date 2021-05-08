package cn.smbms.pojo;

import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

public class Provider {
    private long id;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",message = "手机号格式不正确" )
    private String proPhone;
    private String proAddress;
    private String proFax;
    private long createdBy;
    private Timestamp creationDate;
    private Timestamp modifyDate;
    private long modifyBy;
    private String companyLicPicPath;
    private String orgCodePicPath;

    public Provider() {
    }

    public Provider(long id, String proCode, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, long createdBy, Timestamp creationDate, Timestamp modifyDate, long modifyBy, String companyLicPicPath, String orgCodePicPath) {
        this.id = id;
        this.proCode = proCode;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.modifyBy = modifyBy;
        this.companyLicPicPath = companyLicPicPath;
        this.orgCodePicPath = orgCodePicPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getCompanyLicPicPath() {
        return companyLicPicPath;
    }

    public void setCompanyLicPicPath(String companyLicPicPath) {
        this.companyLicPicPath = companyLicPicPath;
    }

    public String getOrgCodePicPath() {
        return orgCodePicPath;
    }

    public void setOrgCodePicPath(String orgCodePicPath) {
        this.orgCodePicPath = orgCodePicPath;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", proCode='" + proCode + '\'' +
                ", proName='" + proName + '\'' +
                ", proDesc='" + proDesc + '\'' +
                ", proContact='" + proContact + '\'' +
                ", proPhone='" + proPhone + '\'' +
                ", proAddress='" + proAddress + '\'' +
                ", proFax='" + proFax + '\'' +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyDate=" + modifyDate +
                ", modifyBy=" + modifyBy +
                ", companyLicPicPath='" + companyLicPicPath + '\'' +
                ", orgCodePicPath='" + orgCodePicPath + '\'' +
                '}';
    }
}
