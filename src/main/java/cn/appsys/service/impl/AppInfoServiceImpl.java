package cn.appsys.service.impl;

import cn.appsys.dao.AppInfoDao;
import cn.appsys.pojo.AppInfo;
import cn.appsys.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoDao appInfoDao;

    @Override
    public void add(Integer devId,AppInfo appInfo) {
        appInfo.setCreatedBy(devId);
        appInfo.setDevId(devId);
        appInfo.setCreationDate(new java.sql.Date(new Date().getTime()));
        appInfo.setStatus(1);
        appInfoDao.insert(appInfo);
    }

    @Override
    public void remove(Integer id) {
        appInfoDao.delete(id);
    }

    @Override
    public void detail(Integer devId,AppInfo appInfo) {
        appInfo.setModifyBy(devId);
        appInfo.setModifyDate(new Date());
        appInfoDao.update(appInfo);
    }

    @Override
    public void appShelves(Integer devId, AppInfo appInfo) {
        appInfo.setStatus(4);
        appInfo.setModifyDate(new Date());
        appInfo.setModifyBy(devId);
        appInfoDao.update(appInfo);
    }

    @Override
    public void appUnshelve(Integer devId, AppInfo appInfo) {
        appInfo.setStatus(5);
        appInfo.setModifyDate(new Date());
        appInfo.setModifyBy(devId);
        appInfoDao.update(appInfo);
    }

    @Override
    public AppInfo findById(Integer id) {
        return appInfoDao.selectById(id);
    }


    @Override
    public List<AppInfo> findAll(Integer currentPageNo,Integer pageSize) {
        Integer from = 0;
        if (currentPageNo>1){
            from = (currentPageNo - 1) * pageSize;
        }
        return appInfoDao.selectAll(from,pageSize);
    }

    @Override
    public Integer findCountByTerms(AppInfo appInfo) {
        return appInfoDao.selectCountByTerms(appInfo);
    }

    @Override
    public List<AppInfo> findByTerms(AppInfo appInfo, Integer currentPageNo, Integer pageSize) {
        Integer from = 0;
        if (currentPageNo>1){
            from = (currentPageNo - 1) * pageSize;
        }
        return appInfoDao.selectByTerms(appInfo,from,pageSize);
    }

    @Override
    public Integer findCountByApproval(AppInfo appInfo) {
        appInfo.setStatus(1);
        return appInfoDao.selectCountByTerms(appInfo);
    }

    @Override
    public List<AppInfo> findApproval(AppInfo appInfo, Integer currentPageNo, Integer pageSize) {
        Integer from = 0;
        if (currentPageNo>1){
            from = (currentPageNo - 1) * pageSize;
        }
        appInfo.setStatus(1);
        return appInfoDao.selectByTerms(appInfo,from,pageSize);
    }


}
