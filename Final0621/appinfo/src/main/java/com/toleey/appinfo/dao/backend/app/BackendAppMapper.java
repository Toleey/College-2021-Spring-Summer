package com.toleey.appinfo.dao.backend.app;

import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.AppVersion;
import com.toleey.appinfo.pojo.DataDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("backendAppMapper")
public interface BackendAppMapper {
    //查询App信息 根据一些条件
    public List<AppInfo> getAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(
            @Param("softwareName") String softwareName, @Param("flatformId") Integer flatformId,
            @Param("categoryLevel1") Integer categoryLevel1, @Param("categoryLevel2") Integer categoryLevel2,
            @Param("categoryLevel3") Integer categoryLevel3, @Param("fromLineNum") Integer fromLineNum,
            @Param("toLineNum") Integer toLineNum
    );
    //查询App信息 根据一些条件 统计数量Count 分页用
    public Integer getCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(
            @Param("softwareName") String softwareName, @Param("flatformId") Integer flatformId,
            @Param("categoryLevel1") Integer categoryLevel1, @Param("categoryLevel2") Integer categoryLevel2,
            @Param("categoryLevel3") Integer categoryLevel3
    );
    //查询全部平台
    public List<DataDictionary> getAllFlatForm();
    //查询全部一级分类
    public List<AppCategory> getCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> getCategoryLevel2ByCategoryLevel1(@Param("parentId") Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> getCategoryLevel3ByCategoryLevel2(@Param("parentId") Integer parentId);

    //查看App信息 用于审核
    public AppInfo getAnAppInfoById(@Param("id") Integer id);
    //查询App版本 用于审核
    public AppVersion getAnAppVersionByAppId(@Param("appId") Integer appId);

    //App审核状态 通过 AppInfo表
    public Integer updateAppInfoStatusSuccessById(@Param("id") Integer id);
    //App审核状态 通过 AppVersion表
    public Integer updateAppVersionPublishStatusSuccessByAppId(@Param("appId") Integer appId);

    //App审核状态 不通过 AppInfo表
    public Integer updateAppInfoStatusFailById(@Param("id") Integer id);
    //App审核状态 不通过 AppVersion表
    public Integer updateAppVersionPublishStatusFailByAppId(@Param("appId") Integer appId);


}
