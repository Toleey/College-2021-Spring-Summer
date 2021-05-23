package com.toleey.appinfo.dao.developer.app;

import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.List;

public interface DevAppMapper {
    //根据开发者Id查询上传的App
    public List<AppInfo> getAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(
            @Param("id") Integer id,@Param("softwareName") String softwareName, @Param("status") Integer status,
            @Param("flatformId") Integer flatformId, @Param("categoryLevel1") Integer categoryLevel1,
            @Param("categoryLevel2") Integer categoryLevel2, @Param("categoryLevel3") Integer categoryLevel3,
            @Param("fromLineNum") Integer fromLineNum, @Param("toLineNum") Integer toLineNum
    );
    //根据开发者Id查询上传的App 统计数量 分页用
    public Integer getCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(
            @Param("id") Integer id,@Param("softwareName") String softwareName, @Param("status") Integer status,
            @Param("flatformId") Integer flatformId, @Param("categoryLevel1") Integer categoryLevel1,
            @Param("categoryLevel2") Integer categoryLevel2, @Param("categoryLevel3") Integer categoryLevel3
    );
    //查询全部App状态
    public List<DataDictionary> getAppStatus();
    //查询全部平台
    public List<DataDictionary> getFlatForm();
    //查询全部一级分类
    public List<AppCategory> getCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> getCategoryLevel2ByCategoryLevel1(@Param("parentId") Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> getCategoryLevel3ByCategoryLevel2(@Param("parentId") Integer parentId);

    //查询APKName是否存在
    public AppInfo getAppInfoByAPKName(@Param("APKName") String APKName);

}
