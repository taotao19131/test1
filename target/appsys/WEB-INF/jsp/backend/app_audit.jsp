<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/16
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentellela Alela! | </title>

    <!-- Bootstrap -->
    <link href="/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="/statics/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="/statics/css/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="/statics/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="/statics/css/jqvmap.min.css" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="/statics/css/custom.min.css" rel="stylesheet">
</head>
<body>
<div class="container body">
    <div class="main_container">
        <div class="x_panel">
            <div class="x_title">
                <h2 class="ttt">查看并审核APP信息</h2>
                <div class="clearfix"></div>
            </div>
        </div>

        <%--app详情--%>
        <div class="x_panel">
                <div class="x_title">
                    <h2 class="ttt">APP基础信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">

                    <br>
                            <input type="hidden" id="appInfoId" value="${appInfo.id}"/>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control" value="${appInfo.softwareName}" readonly="readonly">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APK名称<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control" readonly="readonly" value="${appInfo.apkName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">支持ROM<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.supportROM}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">界面语言<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.interfaceLanguage}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件大小<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.softwareSize}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">下载次数<span class="required">*</span> </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.downloads}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.flatformName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属分类<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.categoryLevel1Name}-->${appInfo.categoryLevel2Name}-->${appInfo.categoryLevel3Name}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态<span class="required">*</span></label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <input type="text" class="form-control"  readonly="readonly" value="${appInfo.statusName}">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
                                </label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <textarea id="textarea" readonly="readonly" required="required" class="form-control col-md-7 col-xs-12">${appInfo.appInfo}</textarea>
                                </div>
                            </div>

                            <div class="item form-group" id="logoDetail">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">LOGO图片<span class="required">*</span> </label>
                                <img src="${appInfo.logoPicPath}" id="logo" height="100px" width="100px">
                            </div>


                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                    <button type="button" class="btn btn-primary returnPre" id="returnPre">返回</button>
                                    <button type="button" class="btn btn-success audit" id="auditSuccess">审核通过</button>
                                    <button type="button" class="btn btn-danger audit" id="auditFailed">审核不通过</button>
                                </div>
                            </div>

                </div>
            </div>









<%--历史版本--%>
        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>最新版本列表</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <!-- start project list -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">版本号<span class="required">*</span> </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <input type="text" class="form-control" readonly="readonly" value="${appVersion.versionNo}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">版本大小<span class="required">*</span> </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <input type="text" class="form-control" readonly="readonly" value="${appVersion.versionSize}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">发布状态<span class="required">*</span></label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <p>预发布</p>
                            </div>
                        </div>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">版本简介 <span class="required">*</span>
                            </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <textarea class="form-control col-md-7 col-xs-12" readonly="readonly">${appVersion.versionInfo}</textarea>
                            </div>
                        </div>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">APK文件<span class="required">*</span> </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <p>${appVersion.apkFileName}<a href="">下载</a></p>
                            </div>
                        </div>


                        <div class="ln_solid"></div>
                        <!-- end project list -->

                    </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

</body>
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script>
    $("#datatable-buttons_next").click(function (){
        var currentPageNo = $("#current-page-no").html();
        var totalPageNo = $("#lastPageValue").html();
        if (currentPageNo < totalPageNo) {
            currentPageNo++;
            $("#current-page-no").html(currentPageNo);
            $("#current-page-no1").val(currentPageNo);
            window.location.assign("/dev/app/version/toDetailVersion?currentPageNo="+currentPageNo);
        }

    });

    $("#datatable-buttons_pre").click(function () {
        var currentPageNo = $("#current-page-no").html();
        if (currentPageNo > 1) {
            currentPageNo--;
            $("#current-page-no").html(currentPageNo);

            $("#current-page-no1").val(currentPageNo);
            window.location.assign("/dev/app/version/toDetailVersion?currentPageNo="+currentPageNo);
        }

    });


    $("#datatable-buttons_last").click(function () {
        var lastPageNo = $("#lastPageValue").html();
        var currentPageNo = $("#current-page-no").html();
        if(lastPageNo > currentPageNo){
            $("#current-page-no1").val(lastPageNo);
            window.location.assign("/dev/app/version/toDetailVersion?currentPageNo="+lastPageNo);
        }
    });


    $("#deleteApk").click(function () {
        $(this).parents("p").hide();
        $("#f_upload").show();

    });


    /*返回上一页*/
    $("#returnPre").click(function () {
        window.history.back(-1);
    });
    var appInfoId = $("#appInfoId").val();
    $("#auditSuccess").click(function () {
        window.location.assign("/backend/appManage/auditSuccess?appInfoId="+appInfoId);
    });
    $("#auditFailed").click(function () {
        window.location.assign("/backend/appManage/auditSuccess?appInfoId="+appInfoId);
    })
</script>
</html>
