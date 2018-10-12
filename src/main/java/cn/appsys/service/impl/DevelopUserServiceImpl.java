package cn.appsys.service.impl;

import cn.appsys.dao.DevelopUserDao;
import cn.appsys.pojo.DevelopUser;
import cn.appsys.service.DevelopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DevelopUserServiceImpl implements DevelopUserService {
    @Autowired
    private DevelopUserDao developUserDao;

    @Override
    public void add(DevelopUser developUser) {
        developUser.setCreatedBy(1);
        developUser.setCreationDate(new Date());
        developUserDao.insert(developUser);
    }

    @Override
    public void remove(Integer id) {
        developUserDao.delete(id);
    }

    @Override
    public void detail(DevelopUser developUser) {
        developUserDao.update(developUser);
    }

    @Override
    public DevelopUser findById(Integer id) {
        developUserDao.selectById(id);
        return null;
    }
}
