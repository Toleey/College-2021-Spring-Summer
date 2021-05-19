package com.toleey.appinfo.service.backend.app;

import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendAppService {
    //查询App信息 根据一些条件
    public List<AppInfo> findAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(
            String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,
            Integer categoryLevel3,Integer fromLineNum, Integer toLineNum
    );
    //查询App信息 根据一些条件 统计数量Count 分页用
    public Integer findCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(
            String softwareName,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,
            Integer categoryLevel3
    );
    //查询全部平台
    public List<DataDictionary> findAllFlatForm();
    //查询全部一级分类
    public List<AppCategory> findCategoryLevel1();
    //查询二级分类 By 一级分类Id
    public List<AppCategory> findCategoryLevel2ByCategoryLevel1(@Param("parentId") Integer parentId);
    //查询三级分类 By 二级分类Id
    public List<AppCategory> findCategoryLevel3ByCategoryLevel2(@Param("parentId") Integer parentId);

}
