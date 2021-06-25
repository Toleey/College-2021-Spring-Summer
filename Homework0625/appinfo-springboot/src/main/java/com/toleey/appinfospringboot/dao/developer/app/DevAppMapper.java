package com.toleey.appinfospringboot.dao.developer.app;

import com.toleey.appinfospringboot.pojo.AppCategory;
import com.toleey.appinfospringboot.pojo.AppInfo;
import com.toleey.appinfospringboot.pojo.AppVersion;
import com.toleey.appinfospringboot.pojo.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
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
    //查询全部分类
    public List<AppCategory> getAllCategoryLevel();
    //查询全部一级分类
    public List<AppCategory> getCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> getCategoryLevel2ByCategoryLevel1(@Param("parentId") Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> getCategoryLevel3ByCategoryLevel2(@Param("parentId") Integer parentId);
    //查询APKName是否存在
    public AppInfo getAppInfoByAPKName(@Param("APKName") String APKName);

    //新增AppInfo
    public Integer insertAnAppInfo(AppInfo appInfo);
    //修改AppInfo
    public Integer updateAnAppInfo(AppInfo appInfo);
    //查看AppInfo
    public AppInfo getAnAppInfoById(@Param("id") Integer id);
    //删除AppInfo
    public Integer deleteAnAppInfoById(@Param("id") Integer id);
    //查看AppInfo修改用
    public AppInfo getAnAppInfoByIdToUpdate(@Param("id") Integer id);


    //新增AppVersion
    public Integer insertAnAppVersion(AppVersion appVersion);
    //修改AppVersion
    public Integer updateAnAppVersion(AppVersion appVersion);
    //查看AppVersion
    public List<AppVersion> getAppVersionByAppId(@Param("appId") Integer appId );
    //删除AppVersion
    public Integer deleteAnAppVersionById(@Param("id")Integer id);
    //查找AppVersion 修改用
    public AppVersion getAnAppVersionById(@Param("id")Integer id);

    //删除APK文件
    public Integer updateAnAPKFileToNull(@Param("id") Integer id);
    //删除照片
    public Integer updateALogoPictureToNull(@Param("id") Integer id);

    //App上架
    public Integer updateAppInfoStatusLaunch(@Param("id") Integer id);
    //App下架
    public Integer updateAppInfoStatusRemoval(@Param("id") Integer id);

}
