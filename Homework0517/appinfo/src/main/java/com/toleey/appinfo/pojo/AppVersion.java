package com.toleey.appinfo.pojo;


public class AppVersion {

  private long id;
  private long appId;
  private String versionNo;
  private String versionInfo;
  private long publishStatus;
  private String downloadLink;
  private double versionSize;
  private long createdBy;
  private java.sql.Timestamp creationDate;
  private long modifyBy;
  private java.sql.Timestamp modifyDate;
  private String apkLocPath;
  private String apkFileName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getAppId() {
    return appId;
  }

  public void setAppId(long appId) {
    this.appId = appId;
  }


  public String getVersionNo() {
    return versionNo;
  }

  public void setVersionNo(String versionNo) {
    this.versionNo = versionNo;
  }


  public String getVersionInfo() {
    return versionInfo;
  }

  public void setVersionInfo(String versionInfo) {
    this.versionInfo = versionInfo;
  }


  public long getPublishStatus() {
    return publishStatus;
  }

  public void setPublishStatus(long publishStatus) {
    this.publishStatus = publishStatus;
  }


  public String getDownloadLink() {
    return downloadLink;
  }

  public void setDownloadLink(String downloadLink) {
    this.downloadLink = downloadLink;
  }


  public double getVersionSize() {
    return versionSize;
  }

  public void setVersionSize(double versionSize) {
    this.versionSize = versionSize;
  }


  public long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(long createdBy) {
    this.createdBy = createdBy;
  }


  public java.sql.Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(java.sql.Timestamp creationDate) {
    this.creationDate = creationDate;
  }


  public long getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(long modifyBy) {
    this.modifyBy = modifyBy;
  }


  public java.sql.Timestamp getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }


  public String getApkLocPath() {
    return apkLocPath;
  }

  public void setApkLocPath(String apkLocPath) {
    this.apkLocPath = apkLocPath;
  }


  public String getApkFileName() {
    return apkFileName;
  }

  public void setApkFileName(String apkFileName) {
    this.apkFileName = apkFileName;
  }

  @Override
  public String toString() {
    return "AppVersion{" +
            "id=" + id +
            ", appId=" + appId +
            ", versionNo='" + versionNo + '\'' +
            ", versionInfo='" + versionInfo + '\'' +
            ", publishStatus=" + publishStatus +
            ", downloadLink='" + downloadLink + '\'' +
            ", versionSize=" + versionSize +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            ", apkLocPath='" + apkLocPath + '\'' +
            ", apkFileName='" + apkFileName + '\'' +
            '}';
  }
}
