package cn.appsys.service;

import cn.appsys.pojo.AppInfo;

import java.util.List;

public interface AppInfoService {

    void add(Integer devId,AppInfo appInfo);

    void remove(Integer id);

    void detail(Integer devId,AppInfo appInfo);
    //APP上架
    void appShelves(Integer devId, AppInfo appInfo);

    //APP下架
    void appUnshelve(Integer devId, AppInfo appInfo);

    AppInfo findById(Integer id);

    List<AppInfo> findAll(Integer currentPageNo,Integer pageSize);

    Integer findCountByTerms(AppInfo appInfo);

    List<AppInfo> findByTerms(AppInfo appInfo,Integer currentPageNo,Integer pageSize);

    //查询所有待审核的app数量
    Integer findCountByApproval(AppInfo appInfo);

    //查询所有待审核的app信息
    List<AppInfo> findApproval(AppInfo appInfo,Integer currentPageNo,Integer pageSize);
}
