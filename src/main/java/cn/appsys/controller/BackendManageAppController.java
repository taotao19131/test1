package cn.appsys.controller;

import cn.appsys.pojo.*;
import cn.appsys.service.AppCategoryService;
import cn.appsys.service.AppInfoService;
import cn.appsys.service.AppVersionService;
import cn.appsys.service.DataDictionaryService;
import cn.appsys.tools.FinalDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/backend/appManage")
public class BackendManageAppController {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppVersionService appVersionService;


    @RequestMapping("appList")
    public ModelAndView appList(@ModelAttribute("appInfoe") AppInfo appInfoe,
                                HttpServletRequest request,
                                @RequestParam(value = "currentPageNo",required = false) Integer currentPageNo){
        ModelAndView modelAndView = new ModelAndView();
        if(currentPageNo==null){
            currentPageNo=1;
        }
        if ("".equalsIgnoreCase(appInfoe.getSoftwareName())) {
            appInfoe.setSoftwareName(null);
        }
        //得到所有待审核的app
        List<AppInfo> appInfoList = appInfoService.findApproval(appInfoe, currentPageNo, FinalDataUtils.pageSize);
        Integer totalInfo = appInfoService.findCountByApproval(appInfoe);
        Integer totalPage = totalInfo % FinalDataUtils.pageSize == 0 ? totalInfo / FinalDataUtils.pageSize : totalInfo / FinalDataUtils.pageSize + 1 ;
        List<AppCategory> appCategories = appCategoryService.findByParentId(null);
        List<DataDictionary> flatforms = dataDictionaryService.findByTypeCode("APP_FLATFORM");
        List<DataDictionary> app_status = dataDictionaryService.findByTypeCode("APP_STATUS");
        modelAndView.addObject("app_status", app_status);
        modelAndView.addObject("appCategories", appCategories);
        modelAndView.addObject("flatforms", flatforms);
        modelAndView.addObject("totalPage",totalPage);
        modelAndView.addObject("totalInfo",totalInfo);
        modelAndView.addObject("currentPageNo", currentPageNo);
        modelAndView.addObject("appInfo", appInfoe);
        modelAndView.setViewName("/backend/app_manage");
        modelAndView.addObject("appInfoList", appInfoList);
        return modelAndView;
    }


    @RequestMapping("toAppAudit")
    public ModelAndView toAppAudit(Integer appInfoId){
        ModelAndView modelAndView = new ModelAndView();
        //得到appInfo对象
        AppInfo appInfo = appInfoService.findById(appInfoId);
        //得到该app的最新版本信息
        List<AppVersion> appVersions =appVersionService.findByAppId(appInfoId,1,1);
        if (appVersions.size() == 0) {
            modelAndView.setViewName("redirect:appList");
            modelAndView.addObject("msg", "该APP没有上传最新版本,不能进行审核操作");
            return modelAndView;
        }
        AppVersion appVersion = appVersions.get(0);

        modelAndView.addObject("appVersion", appVersion);
        modelAndView.addObject("appInfo", appInfo);
        modelAndView.setViewName("/backend/app_audit");
        return modelAndView;
    }

    //审核通过
    @RequestMapping("auditSuccess")
    public ModelAndView auditSuccess(HttpServletRequest request,Integer appInfoId){
        BackendUser user = (BackendUser) request.getSession().getAttribute("USER");
        Integer userId = user.getId();
        AppInfo appInfo = new AppInfo();
        appInfo.setStatus(2);
        appInfo.setId(appInfoId);
        appInfoService.detail(userId,appInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:appList");
        return  modelAndView;
    }

    //审核未通过
    @RequestMapping("auditFailed")
    public ModelAndView auditFailed(HttpServletRequest request,Integer appInfoId){
        BackendUser user = (BackendUser) request.getSession().getAttribute("USER");
        Integer userId = user.getId();
        AppInfo appInfo = new AppInfo();
        appInfo.setStatus(3);
        appInfo.setId(appInfoId);
        appInfoService.detail(userId,appInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:appList");
        return  modelAndView;
    }

}
