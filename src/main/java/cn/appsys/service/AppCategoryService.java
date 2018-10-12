package cn.appsys.service;

import cn.appsys.pojo.AppCategory;

import java.util.List;

public interface AppCategoryService {

    void add(AppCategory appCategory);

    void remove(Integer id);

    void detail(AppCategory appCategory);

    AppCategory findById(Integer id);

    List<AppCategory> findByParentId(Integer parentId);
}
