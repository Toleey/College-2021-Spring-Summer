package com.toleey.appinfo.service.developer.app;

import com.toleey.appinfo.dao.developer.app.DevAppMapper;
import com.toleey.appinfo.dao.developer.user.DevUserMapper;
import com.toleey.appinfo.pojo.AppCategory;
import com.toleey.appinfo.pojo.AppInfo;
import com.toleey.appinfo.pojo.DataDictionary;
import com.toleey.appinfo.pojo.DevUser;
import com.toleey.appinfo.service.developer.user.DevUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("devAppService")
public class DevAppServiceImpl implements DevAppService {

    @Resource(name = "devAppMapper")
    private DevAppMapper devAppMapper;

    public void setDevAppMapper(DevAppMapper devAppMapper) {
        this.devAppMapper = devAppMapper;
    }


    @Override
    public List<AppInfo> findAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(Integer id, String softwareName, Integer status, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer fromLineNum, Integer toLineNum) {
        return devAppMapper.getAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(id,softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,fromLineNum,toLineNum);
    }

    @Override
    public Integer findCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(Integer id, String softwareName, Integer status, Integer flatformId, Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3) {
        return devAppMapper.getCountAllAppInfoByDevIdAndsoftwareNameAndStatusAndFlatFormIdAndcategoryLevel1AndcategoryLevel2AndcategoryLevel3(id,softwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3);
    }

    @Override
    public List<DataDictionary> findAppStatus() {
        return devAppMapper.getAppStatus();
    }

    @Override
    public List<DataDictionary> findFlatForm() {
        return devAppMapper.getFlatForm();
    }

    @Override
    public List<AppCategory> findCategoryLevel1() {
        return devAppMapper.getCategoryLevel1();
    }

    @Override
    public List<AppCategory> findCategoryLevel2ByCategoryLevel1(Integer parentId) {
        return devAppMapper.getCategoryLevel2ByCategoryLevel1(parentId);
    }

    @Override
    public List<AppCategory> findCategoryLevel3ByCategoryLevel2(Integer parentId) {
        return devAppMapper.getCategoryLevel3ByCategoryLevel2(parentId);
    }

    @Override
    public AppInfo findAppInfoByAPKName(String APKName) {
        return devAppMapper.getAppInfoByAPKName(APKName);
    }
}
