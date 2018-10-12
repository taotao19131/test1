<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/14
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
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
                <h2>APP审核列表 <small>${USER.userName},你可以通过搜索或者其他的筛选项对APP信息进行修改,删除等管理操作</small></h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form id="demo-form2" name="appInfoes" method="get" data-parsley-validate="" class="form-horizontal form-label-left" action="/backend/appManage/appList" novalidate="">
                    <div class="form-group col-md-4 col-sm-4 col-xs-12">
                        <label class="control-label col-md-4 col-sm-4 col-xs-4">软件名称</label>
                        <div class="col-md-6 col-sm-6 col-xs-8">
                            <input type="text" name="softwareName" class="form-control col-md-7 col-xs-12" value="${requestScope.appInfo.softwareName}">
                        </div>
                    </div>

                    <div class="form-group col-md-4 col-sm-4 col-xs-12">
                        <label class="control-label col-md-4 col-sm-4 col-xs-12">所属平台</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select class="form-control" name="flatformId">
                                <option value="">--请选择--</option>
                                <c:forEach items="${flatforms}" var="flatform">
                                    <c:if test="${flatform.valueId == appInfo.flatformId}">
                                        <option selected="selected" value="${flatform.valueId}">${flatform.valueName}</option>
                                    </c:if>
                                    <c:if test="${flatform.valueId != appInfo.flatformId}">
                                        <option value="${flatform.valueId}">${flatform.valueName}</option>
                                    </c:if>

                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-12">
                        <label class="control-label col-md-4 col-sm-4 col-xs-12">一级分类</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select class="form-control" id="appCategoryLevel1" name="categoryLevel1">
                                <option value="">--请选择--</option>
                                <c:forEach items="${appCategories}" var="appCategoryLevel1">
                                    <c:if test="${appCategoryLevel1.id == appInfo.categoryLevel1}">
                                        <option selected="selected" value="${appCategoryLevel1.id}">${appCategoryLevel1.categoryName}</option>
                                    </c:if>
                                    <c:if test="${appCategoryLevel1.id != appInfo.categoryLevel1}">
                                        <option value="${appCategoryLevel1.id}">${appCategoryLevel1.categoryName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-12">
                        <label class="control-label col-md-4 col-sm-4 col-xs-12">二级分类</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select class="form-control" id="appCategoryLevel2" name="CategoryLevel2">

                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-4 col-sm-4 col-xs-12">
                        <label class="control-label col-md-4 col-sm-4 col-xs-12">三级分类</label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select class="form-control" id="appCategoryLevel3" name="CategoryLevel3">

                            </select>
                        </div>
                    </div>

                    <input type="hidden" name="currentPageNo" id="current-page-no1" value=""/>

                    <div class="form-group col-md-2 col-sm-2 col-xs-12">
                        <div class="col-md-4 col-sm-4 col-xs-12 col-md-offset-3">
                            <button type="submit" class="btn btn-success">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <%--app列表--%>
        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">
                    <div class="x_content">

                        <!-- start project list -->
                        <table class="table table-striped projects">
                            <thead style="font-size: 14px">
                                <tr>
                                    <th>软件名称</th>
                                    <th>APK名称</th>
                                    <th>软件大小(单位:M)</th>
                                    <th>所属平台</th>
                                    <th>所属分类(一级分类,二级分类,三级分类)</th>
                                    <th>状态</th>
                                    <th>下载次数</th>
                                    <th>最新版本号</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody style="font-size: 12px">
                                <c:forEach items="${appInfoList}" var="appInfo">
                                    <tr>
                                        <td>${appInfo.softwareName}</td>
                                        <td>${appInfo.apkName}</td>
                                        <td>${appInfo.softwareSize}</td>
                                        <td>${appInfo.flatformName}</td>
                                        <td>${appInfo.categoryLevel1Name}->${appInfo.categoryLevel2Name}->${appInfo.categoryLevel3Name}</td>
                                        <td>
                                            <c:if test="${appInfo.status==1}">
                                                <button type="button" class="btn btn-warning btn-xs" value="${appInfo.status}">${appInfo.statusName}</button>
                                            </c:if>
                                            <c:if test="${appInfo.status==2}">
                                                <button type="button" class="btn btn-success btn-xs" value="${appInfo.status}">${appInfo.statusName}</button>
                                            </c:if>
                                            <c:if test="${appInfo.status==3}">
                                                <button type="button" class="btn btn-danger btn-xs" value="${appInfo.status}">${appInfo.statusName}</button>
                                            </c:if>
                                            <c:if test="${appInfo.status==4}">
                                                <button type="button" class="btn btn-success btn-xs" value="${appInfo.status}">${appInfo.statusName}</button>
                                            </c:if>
                                            <c:if test="${appInfo.status==5}">
                                                <button type="button" class="btn btn-dark btn-xs" value="${appInfo.status}">${appInfo.statusName}</button>
                                            </c:if>
                                        </td>
                                        <td>${appInfo.downloads}</td>
                                        <td>${appInfo.versionName}</td>
                                        <td>
                                            <input  value="${appInfo.id}" type="hidden" />
                                            <button type="button" class="btn btn-success audit">审核</button>
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
            /*window.location.assign("/dev/app/appList?currentPageNo="+currentPageNo);*/
            $("#demo-form2").submit();
        }

    });

    $("#datatable-buttons_pre").click(function () {
        var currentPageNo = $("#current-page-no").html();
        if (currentPageNo > 1) {
            currentPageNo--;
            $("#current-page-no").html(currentPageNo);
            /*window.location.assign("/dev/app/appList?currentPageNo="+currentPageNo);*/
            $("#current-page-no1").val(currentPageNo);
            $("#demo-form2").submit();
        }

    });


    $("#datatable-buttons_last").click(function () {
        var lastPageNo = $("#lastPageValue").html();
        $("#current-page-no1").val(lastPageNo);
        $("#demo-form2").submit();

    });


    $("#addAppInfo").click(function () {
        window.location.assign("/dev/app/toAddInfo");
    });

    $("#appCategoryLevel1").change(function () {
        var parentId = $(this).val();
        $.ajax({
            type: "GET",
            url: "/dev/app/appCategories",
            data: "parentId="+parentId,
            dataType:"json",
            success:function (data) {
                var options = "<option value=\"\">--请选择--</option>";
                for(var i = 0 ; i < data.length ; i++) {
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
                $("#appCategoryLevel2").html(options);
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                alert("加载二级分类列表失败！");
            }


        })
    });


    $("#appCategoryLevel2").change(function () {
        var parentId = $(this).val();
        $.ajax({
            type: "GET",
            url: "/dev/app/appCategories",
            data: "parentId="+parentId,
            dataType:"json",
            success:function (data) {
                var options = "<option value=\"\">--请选择--</option>";
                for(var i = 0 ; i < data.length ; i++) {
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
                $("#appCategoryLevel3").html(options);
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                alert("加载三级分类列表失败！");
            }
        })
    });


    /*审核按钮*/
    $(".audit").click(function () {
        var appInfoId = $(this).siblings("input").val();
        var appVersionName = $(this).parents("tr").find("td:eq(7)").text();
        if (appVersionName == null || appVersionName == "") {
            alert("该APP没有上传最新版本,不能进行审核操作");
            return;
        }

        /*$.ajax({
            type:"GET",
            url:"/backend/appManage/toAppAudit",
            data:"appInfoId="+appInfoId,
            dataType:"json",
            success:function (data) {
                if (data.get("msg") == null) {
                    window.location.assign("/backend/appManage/toAppAudit?appInfoId="+appInfoId);
                }else {
                    alert(data.get("msg"));
                }
            }
        });*/
        window.location.assign("/backend/appManage/toAppAudit?appInfoId="+appInfoId);
    });

</script>
</html>
