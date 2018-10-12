package cn.appsys.service;

import cn.appsys.pojo.DevelopUser;

public interface DevelopUserService {

    void add(DevelopUser developUser);

    void remove(Integer id);

    void detail(DevelopUser developUser);

    DevelopUser findById(Integer id);


}
