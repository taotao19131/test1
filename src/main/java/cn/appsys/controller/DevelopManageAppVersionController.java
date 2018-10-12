package cn.appsys.controller;


import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DevelopUser;
import cn.appsys.service.AppInfoService;
import cn.appsys.service.AppVersionService;
import cn.appsys.tools.FileNameUtils;
import cn.appsys.tools.FinalDataUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dev/app/version")
public class DevelopManageAppVersionController {

    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private AppInfoService appInfoService;

    @RequestMapping("toAddAppVersion")
    public ModelAndView toAddAppVersion(Integer appInfoId,
                                        @RequestParam(value = "currentPageNo",required = false) Integer currentPageNo){
        ModelAndView modelAndView = new ModelAndView();
        if (currentPageNo == null) {
            currentPageNo = 1;
        }
        /*取得对应该app的所有版本信息*/
        List<AppVersion> appVersions = appVersionService.findByAppId(appInfoId,currentPageNo, FinalDataUtils.pageSize);
        //取得版本信息的数量
        Integer totalInfo = appVersionService.findCountByAppId(appInfoId);
        Integer totalPage = totalInfo % FinalDataUtils.pageSize == 0 ? totalInfo / FinalDataUtils.pageSize : totalInfo / FinalDataUtils.pageSize + 1 ;
        modelAndView.addObject("appVersions", appVersions);
        modelAndView.addObject("appInfoId", appInfoId);
        modelAndView.setViewName("/developer/add_appVersion");
        modelAndView.addObject("currentPageNo", currentPageNo);
        modelAndView.addObject("totalInfo", totalInfo);
        modelAndView.addObject("totalPage", totalPage);
        return modelAndView;
    }



    @RequestMapping("addAppVersion")
    public ModelAndView addAppVersion(HttpServletRequest request,
                                      @ModelAttribute("appVersion") AppVersion appVersion,
                                      @RequestParam(value = "apk") MultipartFile attach){
        ModelAndView modelAndView = new ModelAndView();
        //获取用户id,
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();


        /*对文件上传项的操作*/
        //1.判断是否上传了文件
        if (attach.isEmpty()) {
            //未上传文件,返回到添加页面
            modelAndView.setViewName("/toAddAppVersion");
            modelAndView.addObject("msg", "请上传APK文件!");
            return modelAndView;
        } else {
            //2.已经上传文件,对文件进行操作
            //2.1 取得上传文件的全名和大小
            String fileName = attach.getOriginalFilename();
            //2.2 获得文件后缀名
            String suffix = FileNameUtils.getFileSuffix(fileName);
            //2.3 判断后缀名是否是APK,文件大小是否小于500M
            if (!suffix.equalsIgnoreCase("APK")) {
                //上传的文件后缀名不是apk
                //返回到添加页面,并提示
                modelAndView.addObject("msg", "上传文件格式不正确,请上传APK文件");
                modelAndView.setViewName("/toAddAppVersion");
                return modelAndView;
            } else if (attach.getSize() > FinalDataUtils.uploadFileSize) {
                //上传文件大于500m
                //返回到添加页面,并提示
                modelAndView.addObject("msg", "上传文件大小超过500M");
                modelAndView.setViewName("/toAddAppVersion");
                return modelAndView;
            } else {
                //2.4获取上传文件夹的路径
                String uploadPath = request.getSession().getServletContext().getRealPath(FinalDataUtils.uploadFile);
                //2.5创建上传的文件文件对象
                File targetFile = new File(uploadPath,fileName);
                //2.6上传文件到指定的文件夹
                //2.6.1如果目录不存在,则创建目录
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //2.6.2保存文件到指定文件夹
                try {
                    attach.transferTo(targetFile);
                    //2.6.3如果文件上传成功,则执行版本信息的添加操作
                    //2.6.3.1添加文件名到appVersion中
                    appVersion.setApkFileName(fileName);
                    //2.6.3.2添加文件的本地路径到appVersion
                    String apkLocPath = uploadPath + File.separator + fileName;
                    appVersion.setApkLocPath(apkLocPath);
                    //2.6.3.3添加文件的下载连接到appVersion
                    String downloadLink = request.getContextPath()+FinalDataUtils.uploadFile+fileName;
                    appVersion.setDownloadLink(downloadLink);
                    //添加版本信息
                    appVersionService.add(devId,appVersion);

                    //更新AppInfo中的最新版本号
                    //1.得到添加的版本的id
                    AppVersion appVersion1 =appVersionService.findByVersionNoAndAppId(appVersion.getAppId(), appVersion.getVersionNo());
                    AppInfo appInfo = new AppInfo();
                    appInfo.setId(appVersion.getAppId());
                    appInfo.setVersionId(appVersion1.getId());
                    appInfoService.detail(devId,appInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        modelAndView.setViewName("/developer/app_manage");
        return modelAndView;
    }



    @RequestMapping("toDetailVersion")
    public ModelAndView toDetailVersion(Integer appInfoId,Integer currentPageNo){
        if (currentPageNo == null) {
            currentPageNo = 1;
        }
        ModelAndView modelAndView = new ModelAndView();
        List<AppVersion> appVersions = appVersionService.findByAppId(appInfoId,currentPageNo,FinalDataUtils.pageSize);
        //取得版本信息的数量
        Integer totalInfo = appVersionService.findCountByAppId(appInfoId);
        Integer totalPage = totalInfo % FinalDataUtils.pageSize == 0 ? totalInfo / FinalDataUtils.pageSize : totalInfo / FinalDataUtils.pageSize + 1 ;

        //获得当前版本信息
        AppVersion appVersion = appVersions.get(0);

        modelAndView.addObject("oldAppVersion", appVersion);
        modelAndView.addObject("appVersions", appVersions);
        modelAndView.addObject("appInfoId", appInfoId);
        modelAndView.addObject("currentPageNo", currentPageNo);
        modelAndView.setViewName("/developer/detail_appVersion");
        modelAndView.addObject("totalInfo", totalInfo);
        modelAndView.addObject("totalPage", totalPage);
        return modelAndView;
    }


    @RequestMapping("detailVersion")
    public ModelAndView detailVersion(HttpServletRequest request,
                                      @ModelAttribute("appVersion") AppVersion appVersion,
                                      @RequestParam(value = "apk",required = false) MultipartFile attach){
        ModelAndView modelAndView = new ModelAndView();
        //获取用户id,
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();


        /*对文件上传项的操作*/
        //1.判断是否上传了文件
        if (attach == null ||attach.isEmpty()) {
            //未上传文件,直接修改信息
            appVersionService.detail(devId,appVersion);
            modelAndView.setViewName("redirect:/dev/app/appList");
            return modelAndView;
        } else {
            //2.已经上传文件,对文件进行操作
            //2.1 取得上传文件的全名和大小
            String fileName = attach.getOriginalFilename();
            //2.2 获得文件后缀名
            String suffix = FileNameUtils.getFileSuffix(fileName);
            //2.3 判断后缀名是否是APK,文件大小是否小于500M
            if (!suffix.equalsIgnoreCase("APK")) {
                //上传的文件后缀名不是apk
                //返回到添加页面,并提示
                modelAndView.addObject("msg", "上传文件格式不正确,请上传APK文件");
                modelAndView.setViewName("/toAddAppVersion");
                return modelAndView;
            } else if (attach.getSize() > FinalDataUtils.uploadFileSize) {
                //上传文件大于500m
                //返回到添加页面,并提示
                modelAndView.addObject("msg", "上传文件大小超过500M");
                modelAndView.setViewName("/toAddAppVersion");
                return modelAndView;
            } else {
                //2.4获取上传文件夹的路径
                String uploadPath = request.getSession().getServletContext().getRealPath(FinalDataUtils.uploadFile);
                //2.5创建上传的文件文件对象
                File targetFile = new File(uploadPath,fileName);
                //2.6上传文件到指定的文件夹
                //2.6.1如果目录不存在,则创建目录
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //2.6.2保存文件到指定文件夹
                try {
                    String oldApkLocPath = appVersionService.findById(appVersion.getId()).getApkLocPath();
                    attach.transferTo(targetFile);
                    //2.6.3如果文件上传成功,则执行版本信息的添加操作
                    //2.6.3.1添加文件名到appVersion中
                    appVersion.setApkFileName(fileName);
                    //2.6.3.2添加文件的本地路径到appVersion
                    String apkLocPath = uploadPath + File.separator + fileName;
                    appVersion.setApkLocPath(apkLocPath);
                    //2.6.3.3添加文件的下载连接到appVersion
                    String downloadLink = request.getContextPath()+FinalDataUtils.uploadFile+fileName;
                    appVersion.setDownloadLink(downloadLink);
                    //修改版本信息
                    appVersionService.detail(devId,appVersion);
                    //修改成功后删除原apk文件
                    File fileForRemove = new File(oldApkLocPath);
                    if (fileForRemove.exists()) {
                        fileForRemove.delete();
                        System.out.println("原APK删除成功");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        modelAndView.setViewName("redirect:/dev/app/appList");
        return modelAndView;
    }

}
