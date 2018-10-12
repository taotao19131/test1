package cn.appsys.dao;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppCategoryDao {

    void insert(AppCategory appCategory);

    void delete(Integer id);

    void update(AppCategory appCategory);

    AppCategory selectById(Integer id);

    List<AppCategory> selectByParentId(@Param("parentId") Integer parentId);

    List<AppCategory> selectAll();

}
