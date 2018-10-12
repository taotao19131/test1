package cn.appsys.service.impl;

import cn.appsys.dao.AppInfoDao;
import cn.appsys.dao.AppVersionDao;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppVersionServiceImpl  implements AppVersionService {
    @Autowired
    private AppVersionDao appVersionDao;
    @Autowired
    private AppInfoDao appInfoDao;

    @Override
    public void add(Integer devId,AppVersion appVersion) {
        //将appVersion数据插入到数据库
        appVersion.setPublishStatus(3);
        appVersion.setCreatedBy(devId);
        appVersion.setCreationDate(new Date());
        appVersionDao.insert(appVersion);

    }

    @Override
    public void remove(Integer id) {
        appVersionDao.delete(id);
    }

    @Override
    public void removeByAppInfoId(Integer appInfoId) {
        appVersionDao.deleteByAppInfoId(appInfoId);
    }

    @Override
    public void detail(Integer devId,AppVersion appVersion) {
        appVersion.setModifyBy(devId);
        appVersion.setModifyDate(new Date());
        appVersionDao.update(appVersion);
    }

    @Override
    public AppVersion findById(Integer id) {
        return appVersionDao.selectById(id);
    }

    @Override
    public List<AppVersion> findByAppId(Integer appId,Integer currentPageNo,Integer pageSize) {
        Integer from = 0;
        if (currentPageNo>1){
            from = (currentPageNo - 1) * pageSize;
        }
        return appVersionDao.selectByAppId(appId,from,pageSize);
    }

    @Override
    public Integer findCountByAppId(Integer appId) {
        return appVersionDao.selectCountByAppId(appId);
    }

    @Override
    public AppVersion findByVersionNoAndAppId(Integer appId, String versionNo) {
        return appVersionDao.selectByVersionNoAndAppId(appId,versionNo);
    }

    @Override
    public AppVersion findByNewestAndAppId(Integer appId) {
        return null;
    }


}
