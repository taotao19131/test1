package cn.appsys.service.impl;


import cn.appsys.dao.DevelopUserDao;
import cn.appsys.pojo.DevelopUser;
import cn.appsys.service.DevLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevLoginServiceImpl implements DevLoginService {
    @Autowired
    private DevelopUserDao developUserDao;


    @Override
    public DevelopUser login(String devCode, String devPassword) {
        DevelopUser developUser = developUserDao.selectByDevCode(devCode);
        if (developUser == null) {
            return null;
        } else {
            if (developUser.getDevPassword().equals(devPassword)) {
                return developUser;
            }
        }
        return null;
    }
}
