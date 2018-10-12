package cn.appsys.dao;

import cn.appsys.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppInfoDao {

    void insert(AppInfo appInfo);

    void delete(Integer id);

    void update(AppInfo appInfo);

    AppInfo selectById(Integer id);

    List<AppInfo> selectAll(@Param("from") Integer currentPageNo,
                            @Param("pageSize") Integer pageSize);

    Integer selectCountByTerms(AppInfo appInfo);

    List<AppInfo> selectByTerms(@Param("appInfo") AppInfo appInfo,
                                @Param("from") Integer currentPageNo,
                                @Param("pageSize") Integer pageSize);

}
