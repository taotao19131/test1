package cn.appsys.controller;

import cn.appsys.pojo.*;
import cn.appsys.service.AppCategoryService;
import cn.appsys.service.AppInfoService;
import cn.appsys.service.AppVersionService;
import cn.appsys.service.DataDictionaryService;
import cn.appsys.tools.FileNameUtils;
import cn.appsys.tools.FinalDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dev/app")
public class DevelopManageAppController {

    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppVersionService appVersionService;

    @RequestMapping("appList")
    public ModelAndView appList(@ModelAttribute("appInfoes") AppInfo appInfoes,
                                HttpServletRequest request,
                                @RequestParam(value = "currentPageNo",required = false) Integer currentPageNo){
        ModelAndView modelAndView = new ModelAndView();
        if(currentPageNo==null){
            currentPageNo=1;
        }
        if ("".equalsIgnoreCase(appInfoes.getSoftwareName())) {
            appInfoes.setSoftwareName(null);
        }
        //取得当前的用户id
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();
        appInfoes.setDevId(devId);
        List<AppInfo> appInfoList = appInfoService.findByTerms(appInfoes, currentPageNo, FinalDataUtils.pageSize);


        Integer totalInfo = appInfoService.findCountByTerms(appInfoes);
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
        modelAndView.addObject("appInfo", appInfoes);
        modelAndView.setViewName("/developer/app_manage");
        modelAndView.addObject("appInfoList", appInfoList);
        return modelAndView;
    }

    @RequestMapping("toAddInfo")
    public ModelAndView toAddInfo(){
        ModelAndView modelAndView = new ModelAndView();
        List<AppCategory> appCategories = appCategoryService.findByParentId(null);
        List<DataDictionary> flatforms = dataDictionaryService.findByTypeCode("APP_FLATFORM");
        modelAndView.addObject("appCategories", appCategories);
        modelAndView.addObject("flatforms", flatforms);
        modelAndView.setViewName("/developer/add_appInfo");
        return modelAndView;
    }


    /*@RequestMapping("addAppInfo")
    public String addAppInfo(HttpServletRequest request,AppInfo appInfo) {
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();
        appInfoService.add(devId,appInfo);
        return "redirect:appList";
    }*/

    @RequestMapping("appCategories")
    @ResponseBody
    public List<AppCategory> appCategories(Integer parentId){
        return appCategoryService.findByParentId(parentId);
    }


    @RequestMapping("addApp")
    public ModelAndView addApp(HttpServletRequest request,
                               @ModelAttribute("appInfoes") AppInfo appInfoes,
                               @RequestParam("logo") MultipartFile attach) {
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();

        ModelAndView modelAndView = new ModelAndView();
        //取得上传文件的文件全名
        String logoFileName = attach.getOriginalFilename();
        //取得后缀名
        String suffix = FileNameUtils.getFileSuffix(logoFileName);
        //判断后缀名是否为jpg,jpeg,png中的一种,如果不符合要求则不能成功添加信息
        if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
            //文件大小不能超过50kb
            if (attach.getSize() < 50*1024) {
                //获得上传目标文件夹路径
                String path = request.getSession().getServletContext().getRealPath(FinalDataUtils.uploadFile);
                String newFileName = FileNameUtils.getNewFileName(logoFileName);

                File targetFile = new File(path, newFileName);
                //如果目录不存在,则创建目录
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                //将文件保存到upload文件夹中
                try {
                    attach.transferTo(targetFile);
                    //文件能成功上传,执行添加appInfo
                    String logoLocPath = path + File.separator + newFileName;
                    String logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+newFileName;
                    appInfoes.setLogoPicPath(logoPicPath);
                    appInfoes.setLogoLocPath(logoLocPath);
                    appInfoService.add(devId,appInfoes);
                    //添加成功后返回app列表页
                    modelAndView.setViewName("redirect:appList");
                } catch (IOException e) {
                    modelAndView.addObject("addMSG","文件上传失败,请重新操作");
                    modelAndView.setViewName("redirect:toAddInfo");
                    e.printStackTrace();
                }
            } else {
                modelAndView.addObject("addMSG","请上传不超过50kb的文件");
                modelAndView.setViewName("redirect:toAddInfo");
            }

        } else {
            modelAndView.addObject("addMSG","文件后缀名不满足要求(支持jpg,jpeg,png三种格式)");
            modelAndView.setViewName("redirect:toAddInfo");

        }

        return modelAndView;
    }

    @RequestMapping("toAppDetail")
    public ModelAndView toDetail(@RequestParam("appInfoId") Integer appInfoId){
        ModelAndView modelAndView = new ModelAndView();
        AppInfo appInfo = appInfoService.findById(appInfoId);
        if (appInfo != null && appInfo.getStatus() == 1 || appInfo.getStatus() == 3) {
            List<AppCategory> appLevel1Categories = appCategoryService.findByParentId(null);
            List<AppCategory> appLevel2Categories = appCategoryService.findByParentId(appInfo.getCategoryLevel1());
            List<AppCategory> appLevel3Categories = appCategoryService.findByParentId(appInfo.getCategoryLevel2());
            List<DataDictionary> flatforms = dataDictionaryService.findByTypeCode("APP_FLATFORM");
            modelAndView.addObject("oldAppInfo", appInfo);
            modelAndView.setViewName("/developer/detail_appInfo");
            modelAndView.addObject("appLevel1Categories", appLevel1Categories);
            modelAndView.addObject("appLevel2Categories", appLevel2Categories);
            modelAndView.addObject("appLevel3Categories", appLevel3Categories);
            modelAndView.addObject("flatforms", flatforms);
        }else{
            modelAndView.setViewName("redirect:appList");
        }
        return  modelAndView;
    }


    @RequestMapping("appDetail")
    public ModelAndView appDetail(HttpServletRequest request,
                                  @ModelAttribute("appInfoes") AppInfo appInfoes,
                                  @RequestParam(value = "logo",required = false) MultipartFile attach,
                                  @RequestParam(value = "reCheck", required = false) String reCheck){
        ModelAndView modelAndView = new ModelAndView();
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");

        if (!attach.isEmpty()) {//修改了logo图片,上传了新文件
            //取得上传文件的文件全名
            String logoFileName = attach.getOriginalFilename();
            //取得后缀名
            String suffix = FileNameUtils.getFileSuffix(logoFileName);
            //判断后缀名是否为jpg,jpeg,png中的一种,如果不符合要求则不能成功添加信息
            if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
                //文件大小不能超过50kb
                if (attach.getSize() < FinalDataUtils.uploadFileSize) {
                    //获得上传目标文件夹路径
                    String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
                    String newFileName = FileNameUtils.getNewFileName(logoFileName);

                    File targetFile = new File(path, newFileName);
                    //如果目录不存在,则创建目录
                    if (!targetFile.exists()) {
                        targetFile.mkdirs();
                    }
                    //将文件保存到upload文件夹中
                    try {
                        String oldLogoLocPath = appInfoService.findById(appInfoes.getId()).getLogoLocPath();
                        String oldLogoPicPath = appInfoService.findById(appInfoes.getId()).getLogoPicPath();
                        attach.transferTo(targetFile);
                        //文件能成功上传,执行修改appInfo
                        String logoLocPath = path + File.separator + newFileName;
                        String logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+newFileName;
                        appInfoes.setLogoPicPath(logoPicPath);
                        appInfoes.setLogoLocPath(logoLocPath);

                        //修改成功后,删除原logo
                        File fileForRemove = new File(oldLogoLocPath);
                        if (fileForRemove.exists()) {
                            fileForRemove.delete();
                            System.out.println("原logo删除成功");
                        }
                    } catch (IOException e) {
                        modelAndView.addObject("addMSG","文件上传失败,请重新操作");
                        modelAndView.setViewName("redirect:toAppDetail");
                        e.printStackTrace();
                    }
                } else {
                    modelAndView.addObject("addMSG","请上传不超过50kb的文件");
                    modelAndView.setViewName("redirect:toAppDetail");
                }

            } else {
                modelAndView.addObject("addMSG","文件后缀名不满足要求(支持jpg,jpeg,png三种格式)");
                modelAndView.setViewName("redirect:toAppDetail");

            }
        }

        if (reCheck != null && reCheck.equals("true")) {
            appInfoes.setStatus(1);
        }
        appInfoService.detail(developUser.getId(),appInfoes);
        modelAndView.setViewName("redirect:appList");
        return modelAndView;
    }



    //查看app信息
    @RequestMapping("/showAppInfo")
    public ModelAndView showAppInfo(Integer appInfoId,
                                    @RequestParam(value = "currentPageNo",required = false) Integer currentPageNo){
        if (currentPageNo == null) {
            currentPageNo =  1;
        }
        ModelAndView modelAndView = new ModelAndView();
        //得到appInfo对象
        AppInfo appInfo = appInfoService.findById(appInfoId);
        //得到该app的版本对象集合
        List<AppVersion> appVersions = appVersionService.findByAppId(appInfoId,currentPageNo,FinalDataUtils.pageSize);
        //得到版本信息的总条数
        Integer totalInfo = appVersionService.findCountByAppId(appInfoId);
        //将得到的数据添加到modelAndView
        modelAndView.addObject("appVersions",appVersions);
        modelAndView.addObject("appInfo",appInfo);
        modelAndView.addObject("currentPageNo", currentPageNo);
        modelAndView.addObject("totalInfo",totalInfo);
        Integer totalPage = totalInfo % FinalDataUtils.pageSize == 0 ? totalInfo / FinalDataUtils.pageSize : totalInfo / FinalDataUtils.pageSize + 1 ;
        modelAndView.addObject("totalPage", totalPage);
        modelAndView.setViewName("/developer/show_appInfo");
        return modelAndView;
    }


    //删除app信息和所有版本
    @RequestMapping("removeAppInfo")
    public ModelAndView removeAppInfo(Integer appInfoId) {
        appInfoService.remove(appInfoId);
        appVersionService.removeByAppInfoId(appInfoId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:appList");
        return  modelAndView;
    }

    //上架APP
    @RequestMapping("appShelves")
    public ModelAndView appShelves(Integer appInfoId,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();
        AppInfo appInfo = new AppInfo();
        appInfo.setId(appInfoId);
        appInfoService.appShelves(devId,appInfo);
        modelAndView.setViewName("redirect:appList");
        return modelAndView;
    }


    //下架APP
    @RequestMapping("appUnshelve")
    public ModelAndView appUnshelve(Integer appInfoId,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        DevelopUser developUser = (DevelopUser) request.getSession().getAttribute("DEVUSER");
        Integer devId = developUser.getId();
        AppInfo appInfo = new AppInfo();
        appInfo.setId(appInfoId);
        appInfoService.appUnshelve(devId,appInfo);
        modelAndView.setViewName("redirect:appList");
        return modelAndView;
    }

}
