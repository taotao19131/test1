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

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>历史版本列表</h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <!-- start project list -->
                        <table class="table table-striped projects">
                            <thead style="font-size: 14px">
                            <tr>
                                <th>软件名称</th>
                                <th>版本号</th>
                                <th>版本大小(单位:M)</th>
                                <th>发布状态</th>
                                <th>APK文件下载</th>
                                <th>更新时间</th>
                            </tr>
                            </thead>
                            <tbody style="font-size: 12px">
                            <c:forEach items="${appVersions}" var="appVersion">
                                <tr>
                                    <td>${appVersion.softwareName}</td>
                                    <td>${appVersion.versionNo}</td>
                                    <td>${appVersion.versionSize}</td>
                                    <td>${appVersion.publishStatusName}</td>
                                    <td>${appVersion.apkFileName}</td>
                                    <td>
                                        <fmt:formatDate value="${appVersion.modifyDate}" pattern="yyyy-MM-dd"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- end project list -->

                        <p>共${totalInfo}条记录&nbsp;<span id="current-page-no">${currentPageNo}</span>/<span id="lastPageValue">${totalPage}</span>页</p>

                        <div class="dataTables_paginate paging_simple_numbers" id="datatable-responsive_paginate">
                            <ul class="pagination">
                                <li class="paginate_button next" id="datatable-buttons_pre">
                                    <a href="javascript:void(0);" aria-controls="datatable-buttons" data-dt-idx="" tabindex="0">上一页</a>
                                </li>
                                <li class="paginate_button next" id="datatable-buttons_next">
                                    <a href="javascript:void(0);" aria-controls="datatable-buttons" data-dt-idx="" tabindex="0">下一页</a>
                                </li>
                                <li class="paginate_button next" id="datatable-buttons_last">
                                    <a href="javascript:void(0);" aria-controls="datatable-buttons" data-dt-idx="" tabindex="0">最后一页</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>


<%--************************************************--%>
        <div class="x_panel">
                <div class="x_title">
                    <h2 class="ttt">修改APP版本信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <br>
                    <form class="form-horizontal form-label-left" enctype="multipart/form-data"
                            method="post" action="/dev/app/version/detailVersion">
                        <input type="hidden" name="id" value="${oldAppVersion.id}" />
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">版本号<span class="required">*</span> </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <input type="text" class="form-control" readonly="readonly" value="${oldAppVersion.versionNo}" placeholder="请输入版本号">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">版本大小<span class="required">*</span> </label>
                            <div class="col-md-9 col-sm-9 col-xs-12">
                                <input type="text" class="form-control" name="versionSize" value="${oldAppVersion.versionSize}" placeholder="请输入版本大小,单位为Mb">
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
                                <textarea id="textarea" required="required" name="versionInfo"  placeholder="请输入本版本的相关信息,本信息作为版本的详细信息进行版本的介绍" class="form-control col-md-7 col-xs-12">${oldAppVersion.versionInfo}</textarea>
                            </div>
                        </div>

                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">APK文件<span class="required">*</span> </label>
                            <input id="f_upload" type="file" class="file" name="apk" style="display: none" />
                            <p>
                                ${oldAppVersion.apkFileName}
                                <a href="javascript:void (0);" id="deleteApk">删除</a>
                            </p>

                        </div>


                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                                <button type="submit" id="returnPre" class="btn btn-primary">返回</button>
                                <button type="submit" class="btn btn-success">保存</button>
                            </div>
                        </div>

                    </form>
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
    })
</script>
</html>
