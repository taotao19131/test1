package cn.appsys.service.impl;

import cn.appsys.dao.AppCategoryDao;
import cn.appsys.pojo.AppCategory;
import cn.appsys.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Autowired
    private AppCategoryDao appCategoryDao;

    @Override
    public void add(AppCategory appCategory) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public void detail(AppCategory appCategory) {

    }

    @Override
    public AppCategory findById(Integer id) {
        return appCategoryDao.selectById(id);
    }

    @Override
    public List<AppCategory> findByParentId(Integer parentId) {
        return appCategoryDao.selectByParentId(parentId);
    }
}
