package cn.appsys.controller;

import cn.appsys.pojo.DevelopUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SelfController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/test")
    public String test(){
        return "/developer/dev_index";
    }

    @RequestMapping("/logout")
    public String main(HttpServletRequest request) {
        System.out.println("1111");
        System.out.println(request.getSession().getAttribute("USER"));
        request.getSession().removeAttribute("USER");
        request.getSession().removeAttribute("DEVUSER");
        System.out.println(request.getSession().getAttribute("USER"));
        return "redirect:index";
    }


    @RequestMapping("/success")
    public String success(){
        return "success";
    }
}
