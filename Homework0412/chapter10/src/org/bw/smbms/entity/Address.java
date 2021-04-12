package org.bw.smbms.entity;


import java.sql.Timestamp;

public class Address {

  private long id;
  private String contact;
  private String addressDesc;
  private String postCode;
  private String tel;
  private long createdBy;
  private Timestamp creationDate;
  private long modifyBy;
  private Timestamp modifyDate;
  private long userId;

  public Address() {
  }

  public Address(long id, String contact, String addressDesc, String postCode, String tel, long createdBy, Timestamp creationDate, long modifyBy, Timestamp modifyDate, long userId) {
    this.id = id;
    this.contact = contact;
    this.addressDesc = addressDesc;
    this.postCode = postCode;
    this.tel = tel;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
    this.userId = userId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }


  public String getAddressDesc() {
    return addressDesc;
  }

  public void setAddressDesc(String addressDesc) {
    this.addressDesc = addressDesc;
  }


  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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


  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }


  public Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Address{" +
            "id=" + id +
            ", contact='" + contact + '\'' +
            ", addressDesc='" + addressDesc + '\'' +
            ", postCode='" + postCode + '\'' +
            ", tel='" + tel + '\'' +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            ", userId=" + userId +
            '}';
  }
}
