package cn.appsys.service.impl;

import cn.appsys.dao.BackendUserDao;
import cn.appsys.pojo.BackendUser;
import cn.appsys.service.BackendLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackendLoginServiceImpl implements BackendLoginService {
    @Autowired
    private BackendUserDao backendUserDao;

    @Override
    public BackendUser login(String userCode, String userPassword) {
        BackendUser backendUser = backendUserDao.selectByDevCode(userCode);
        if (backendUser == null) {
            return null;
        } else {
            if (backendUser.getUserPassword().equals(userPassword)) {
                return backendUser;
            }
        }
        return null;
    }
}
