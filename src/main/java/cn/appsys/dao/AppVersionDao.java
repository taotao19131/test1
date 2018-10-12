package cn.appsys.dao;

import cn.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppVersionDao {

    void insert(AppVersion appVersion);

    void delete(Integer id);

    void deleteByAppInfoId(Integer appInfoId);

    void update(AppVersion appVersion);

    AppVersion selectById(Integer id);

    Integer selectCountByAppId(Integer appId);

    AppVersion selectByVersionNoAndAppId(@Param("appId") Integer appId,
                                         @Param("versionNo") String versionNo);

    List<AppVersion> selectByAppId(@Param("appId") Integer appId,
                                   @Param("from") Integer currentPageNo,
                                   @Param("pageSize") Integer pageSize);

}
