package com.toleey.appinfo.service.developer.app;

import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

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
    //查询全部一级分类
    public List<AppCategory> findCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> findCategoryLevel2ByCategoryLevel1(Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> findCategoryLevel3ByCategoryLevel2(Integer parentId);

    //查询APKName是否存在
    public AppInfo findAppInfoByAPKName(String APKName);
}
