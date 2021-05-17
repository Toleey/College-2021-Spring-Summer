package com.toleey.appinfo.pojo;


public class AppInfo {

  private long id;
  private String softwareName;
  private String apkName;
  private String supportRom;
  private String interfaceLanguage;
  private double softwareSize;
  private java.sql.Date updateDate;
  private long devId;
  private String appInfo;
  private long status;
  private java.sql.Timestamp onSaleDate;
  private java.sql.Timestamp offSaleDate;
  private long flatformId;
  private long categoryLevel3;
  private long downloads;
  private long createdBy;
  private java.sql.Timestamp creationDate;
  private long modifyBy;
  private java.sql.Timestamp modifyDate;
  private long categoryLevel1;
  private long categoryLevel2;
  private String logoPicPath;
  private String logoLocPath;
  private long versionId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getSoftwareName() {
    return softwareName;
  }

  public void setSoftwareName(String softwareName) {
    this.softwareName = softwareName;
  }


  public String getApkName() {
    return apkName;
  }

  public void setApkName(String apkName) {
    this.apkName = apkName;
  }


  public String getSupportRom() {
    return supportRom;
  }

  public void setSupportRom(String supportRom) {
    this.supportRom = supportRom;
  }


  public String getInterfaceLanguage() {
    return interfaceLanguage;
  }

  public void setInterfaceLanguage(String interfaceLanguage) {
    this.interfaceLanguage = interfaceLanguage;
  }


  public double getSoftwareSize() {
    return softwareSize;
  }

  public void setSoftwareSize(double softwareSize) {
    this.softwareSize = softwareSize;
  }


  public java.sql.Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(java.sql.Date updateDate) {
    this.updateDate = updateDate;
  }


  public long getDevId() {
    return devId;
  }

  public void setDevId(long devId) {
    this.devId = devId;
  }


  public String getAppInfo() {
    return appInfo;
  }

  public void setAppInfo(String appInfo) {
    this.appInfo = appInfo;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getOnSaleDate() {
    return onSaleDate;
  }

  public void setOnSaleDate(java.sql.Timestamp onSaleDate) {
    this.onSaleDate = onSaleDate;
  }


  public java.sql.Timestamp getOffSaleDate() {
    return offSaleDate;
  }

  public void setOffSaleDate(java.sql.Timestamp offSaleDate) {
    this.offSaleDate = offSaleDate;
  }


  public long getFlatformId() {
    return flatformId;
  }

  public void setFlatformId(long flatformId) {
    this.flatformId = flatformId;
  }


  public long getCategoryLevel3() {
    return categoryLevel3;
  }

  public void setCategoryLevel3(long categoryLevel3) {
    this.categoryLevel3 = categoryLevel3;
  }


  public long getDownloads() {
    return downloads;
  }

  public void setDownloads(long downloads) {
    this.downloads = downloads;
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


  public long getCategoryLevel1() {
    return categoryLevel1;
  }

  public void setCategoryLevel1(long categoryLevel1) {
    this.categoryLevel1 = categoryLevel1;
  }


  public long getCategoryLevel2() {
    return categoryLevel2;
  }

  public void setCategoryLevel2(long categoryLevel2) {
    this.categoryLevel2 = categoryLevel2;
  }


  public String getLogoPicPath() {
    return logoPicPath;
  }

  public void setLogoPicPath(String logoPicPath) {
    this.logoPicPath = logoPicPath;
  }


  public String getLogoLocPath() {
    return logoLocPath;
  }

  public void setLogoLocPath(String logoLocPath) {
    this.logoLocPath = logoLocPath;
  }


  public long getVersionId() {
    return versionId;
  }

  public void setVersionId(long versionId) {
    this.versionId = versionId;
  }

  @Override
  public String toString() {
    return "AppInfo{" +
            "id=" + id +
            ", softwareName='" + softwareName + '\'' +
            ", apkName='" + apkName + '\'' +
            ", supportRom='" + supportRom + '\'' +
            ", interfaceLanguage='" + interfaceLanguage + '\'' +
            ", softwareSize=" + softwareSize +
            ", updateDate=" + updateDate +
            ", devId=" + devId +
            ", appInfo='" + appInfo + '\'' +
            ", status=" + status +
            ", onSaleDate=" + onSaleDate +
            ", offSaleDate=" + offSaleDate +
            ", flatformId=" + flatformId +
            ", categoryLevel3=" + categoryLevel3 +
            ", downloads=" + downloads +
            ", createdBy=" + createdBy +
            ", creationDate=" + creationDate +
            ", modifyBy=" + modifyBy +
            ", modifyDate=" + modifyDate +
            ", categoryLevel1=" + categoryLevel1 +
            ", categoryLevel2=" + categoryLevel2 +
            ", logoPicPath='" + logoPicPath + '\'' +
            ", logoLocPath='" + logoLocPath + '\'' +
            ", versionId=" + versionId +
            '}';
  }
}
