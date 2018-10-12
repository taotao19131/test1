package cn.appsys.service;

import cn.appsys.pojo.BackendUser;

public interface BackendLoginService {

    BackendUser login(String userCode,String userPassword);

}
