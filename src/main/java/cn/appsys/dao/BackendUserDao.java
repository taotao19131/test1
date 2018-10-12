package cn.appsys.dao;

import cn.appsys.pojo.BackendUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackendUserDao {
    void insert(BackendUser backendUser);

    void delete(Integer id);

    void update(BackendUser backendUser);

    BackendUser selectById(Integer id);

    BackendUser selectByDevCode(String userCode);

    List<BackendUser> selectAll();
}
