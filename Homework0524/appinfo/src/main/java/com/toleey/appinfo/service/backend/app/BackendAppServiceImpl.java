package com.toleey.appinfo.service.backend.app;

import com.toleey.appinfo.dao.backend.app.BackendAppMapper;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.AppVersion;
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
    public List<AppInfo> findAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(String softwareName, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer fromLineNum, Integer toLineNum) {
        return backendAppMapper.getAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(softwareName,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,fromLineNum,toLineNum);
    }

    @Override
    public Integer findCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(String softwareName, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3) {
        return backendAppMapper.getCountAllAppInfoBySoftwareNameAndFlatFormIdAndCategoryLevel1AndCategoryLevel2AndCategoryLevel3(softwareName,flatformId,categoryLevel1,categoryLevel2,categoryLevel3);
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
    public AppInfo findAnAppInfoById(Integer id) {
        return backendAppMapper.getAnAppInfoById(id);
    }

    @Override
    public AppVersion findAnAppVersionByAppId(Integer appId) {
        return backendAppMapper.getAnAppVersionByAppId(appId);
    }

    @Override
    public Integer updateAppInfoStatusSuccessById(Integer id) {
        return backendAppMapper.updateAppInfoStatusSuccessById(id);
    }

    @Override
    public Integer updateAppVersionPublishStatusSuccessByAppId(Integer appId) {
        return backendAppMapper.updateAppVersionPublishStatusSuccessByAppId(appId);
    }

    @Override
    public Integer updateAppInfoStatusFailById(Integer id) {
        return backendAppMapper.updateAppInfoStatusFailById(id);
    }

    @Override
    public Integer updateAppVersionPublishStatusFailByAppId(Integer appId) {
        return backendAppMapper.updateAppVersionPublishStatusFailByAppId(appId);
    }

}
