package cn.appsys.dao;

import cn.appsys.pojo.DevelopUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevelopUserDao {
    void insert(DevelopUser developUser);

    void delete(Integer id);

    void update(DevelopUser developUser);

    DevelopUser selectById(Integer id);

    DevelopUser selectByDevCode(String devCode);

    List<DevelopUser> selectAll();
}
