package cn.appsys.service;

import cn.appsys.pojo.DevelopUser;

public interface DevLoginService {

    DevelopUser login(String devCode, String devPassword);

}
