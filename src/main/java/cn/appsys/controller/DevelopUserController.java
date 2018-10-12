package cn.appsys.controller;

import cn.appsys.pojo.DevelopUser;
import cn.appsys.service.DevelopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dev")
public class DevelopUserController {
    @Autowired
    private DevelopUserService developUserService;

    @RequestMapping("/add")
    public ModelAndView add(DevelopUser developUser) {
        System.out.println(developUser.getDevName());
        System.out.println(developUser.getDevEmail());
        developUserService.add(developUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
