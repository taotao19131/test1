package cn.appsys.service;

import cn.appsys.pojo.AppVersion;

import java.util.List;

public interface AppVersionService {

    void add(Integer devId,AppVersion appVersion);

    void remove(Integer id);

    void removeByAppInfoId(Integer appInfoId);

    void detail(Integer devId,AppVersion appVersion);

    AppVersion findById(Integer id);

    List<AppVersion> findByAppId(Integer appId,Integer currentPageNo,Integer pageSize);

    Integer findCountByAppId(Integer appId);

    AppVersion findByVersionNoAndAppId(Integer appId,String versionNo);

    AppVersion findByNewestAndAppId(Integer appId);
}
