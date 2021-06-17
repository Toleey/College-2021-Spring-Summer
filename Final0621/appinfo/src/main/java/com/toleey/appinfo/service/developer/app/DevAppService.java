package com.toleey.appinfo.service.developer.app;

import com.toleey.appinfo.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DevAppService {
    //根据开发者Id查询上传的App
    public List<AppInfo> findAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(
            Integer id,String softwareName,Integer status,Integer flatformId,
            Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3,
            Integer fromLineNum,Integer toLineNum
    );
    //根据开发者Id查询上传的App 统计数量 分页用
    public Integer findCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(
            Integer id,String softwareName,Integer status,Integer flatformId,
            Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3
    );
    //查询全部App状态
    public List<DataDictionary> findAppStatus();
    //查询全部平台
    public List<DataDictionary> findFlatForm();
    //查询全部分类
    public List<AppCategory> findAllCategoryLevel();
    //查询全部一级分类
    public List<AppCategory> findCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> findCategoryLevel2ByCategoryLevel1(Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> findCategoryLevel3ByCategoryLevel2(Integer parentId);
    //查询APKName是否存在
    public AppInfo findAppInfoByAPKName(String APKName);

    //新增AppInfo
    public boolean addAnAppInfo(AppInfo appInfo);
    //修改AppInfo
    public boolean updateAnAppInfo(AppInfo appInfo);
    //查看AppInfo
    public AppInfo findAnAppInfoById(Integer id);
    //删除AppInfo
    public boolean deleteAnAppInfoById(Integer id);
    //查看AppInfo修改用
    public AppInfo findAnAppInfoByIdToUpdate(Integer id);

    //新增AppVersion
    public boolean addAnAppVersion(AppVersion appVersion);
    //修改AppVersion
    public boolean updateAnAppVersion(AppVersion appVersion);
    //查看AppVersion
    public List<AppVersion> findAppVersionByAppId(Integer appId);
    //删除AppVersion
    public boolean deleteAnAppVersionById(Integer id);
    //查找AppVersion 修改用
    public AppVersion findAnAppVersionById(Integer id);

    //删除APK文件
    public boolean updateAnAPKFileToNull(Integer id);
    //删除照片
    public boolean updateALogoPictureToNull(Integer id);

    //App上架
    public boolean updateAppInfoStatusLaunch(Integer id);
    //App下架
    public boolean updateAppInfoStatusRemoval(Integer id);

}
