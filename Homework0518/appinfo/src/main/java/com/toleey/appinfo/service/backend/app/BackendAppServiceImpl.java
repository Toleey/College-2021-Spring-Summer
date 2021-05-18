package com.toleey.appinfo.service.backend.app;

import com.toleey.appinfo.dao.backend.app.BackendAppMapper;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("backendAppService")
public class BackendAppServiceImpl implements BackendAppService {

    @Resource(name = "backendAppMapper")
    private BackendAppMapper backendAppMapper;

    public void setBackendAppMapper(BackendAppMapper backendAppMapper) {
        this.backendAppMapper = backendAppMapper;
    }

    @Override
    public List<AppInfo> findAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(String SoftwareName, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer fromLineNum, Integer toLineNum) {
        return backendAppMapper.getAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(SoftwareName,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,fromLineNum,toLineNum);
    }

    @Override
    public Integer findCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3() {
        return backendAppMapper.getCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3();
    }

    @Override
    public List<DataDictionary> findAllFlatForm() {
        return backendAppMapper.getAllFlatForm();
    }

    @Override
    public List<AppCategory> findCategoryLevel1() {
        return backendAppMapper.getCategoryLevel1();
    }

    @Override
    public List<AppCategory> findCategoryLevel2ByCategoryLevel1(Integer parentId) {
        return backendAppMapper.getCategoryLevel2ByCategoryLevel1(parentId);
    }

    @Override
    public List<AppCategory> findCategoryLevel3ByCategoryLevel2(Integer parentId) {
        return backendAppMapper.getCategoryLevel3ByCategoryLevel2(parentId);
    }

    @Override
    public List<AppInfo> findAllAppInfo() {
        return backendAppMapper.getAllAppInfo();
    }
}
