package cn.appsys.controller;

import cn.appsys.pojo.BackendUser;
import cn.appsys.pojo.DevelopUser;
import cn.appsys.service.BackendLoginService;
import cn.appsys.service.DevLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private DevLoginService devLoginService;
    @Autowired
    private BackendLoginService backendLoginService;


//  开发者登录
    @RequestMapping("/toDevLogin")
    public String toDevLogin(){
        return "devLogin";
    }
    @RequestMapping("/devLogin")
    public ModelAndView devLogin
            (@RequestParam(value = "devCode",required = false) String devCode,
             @RequestParam(value = "devPassword",required = false) String devPassword,
             HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if (devCode == null || "".equals(devCode) || devPassword == null || "".equals(devPassword)) {
            modelAndView.setViewName("devLogin");
        }else{
            DevelopUser developUser = devLoginService.login(devCode, devPassword);
            if (developUser == null) {
                modelAndView.setViewName("devLogin");
            } else {
                request.getSession().setAttribute("DEVUSER",developUser);
                modelAndView.setViewName("/developer/dev_index");
            }
        }
        return modelAndView;
    }

//  后台登录
    @RequestMapping("/toBackendLogin")
    public String tobackendLogin(){
        return "backendLogin";
    }
    @RequestMapping("/backendLogin")
    public ModelAndView backendLogin
            (@RequestParam(value = "userCode",required = false) String userCode,
             @RequestParam(value = "userPassword",required = false) String userPassword,
             HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if (userCode == null || "".equals(userCode) || userPassword == null || "".equals(userPassword)) {
            modelAndView.setViewName("backendLogin");
        }else{
            BackendUser backendUser = backendLoginService.login(userCode, userPassword);
            if (backendUser == null) {
                modelAndView.setViewName("backendLogin");
            } else {
                request.getSession().setAttribute("USER",backendUser);
                modelAndView.setViewName("/backend/backend_index");
            }
        }
        return modelAndView;
    }

}
