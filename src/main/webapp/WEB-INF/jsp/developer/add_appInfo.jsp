<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
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

<div class="x_panel">
        <div class="x_title">
            <h2 class="ttt">新增APP基础信息</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br>
            <form class="form-horizontal form-label-left" enctype="multipart/form-data"
                    method="post" name="appInfoForm" id="appInfoForm" action="/dev/app/addApp">
                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="softwareName" placeholder="请输入软件名称">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">APK名称<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="apkName" placeholder="请输入APK名称">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">支持ROM<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="supportROM" placeholder="请输入支持的ROM">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">界面语言<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="interfaceLanguage" placeholder="请输入软件支持的界面语言">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">软件大小<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="softwareSize" placeholder="请输入软件大小,单位为Mb">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">下载次数<span class="required">*</span> </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" name="downloads" placeholder="请输入下载次数">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台<span class="required">*</span></label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <select class="form-control" name="flatformId">
                            <option>--请选择--</option>
                            <c:forEach items="${flatforms}" var="flatform">
                                <option value="${flatform.valueId}">${flatform.valueName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类<span class="required">*</span></label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <select class="form-control" id="appCategoryLevel1" name="categoryLevel1">
                            <option>--请选择--</option>
                            <c:forEach items="${appCategories}" var="appCategoryLevel1">
                                <option value="${appCategoryLevel1.id}">${appCategoryLevel1.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类<span class="required">*</span></label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <select class="form-control" id="appCategoryLevel2" name="categoryLevel2">
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类<span class="required">*</span></label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <select class="form-control" id="appCategoryLevel3" name="categoryLevel3">
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态<span class="required">*</span></label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <input type="text" class="form-control" readonly="readonly" placeholder="待审核">
                    </div>
                </div>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
                    </label>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                        <textarea id="textarea" required="required" name="appInfo" placeholder="请输入本软件的相关信息,本信息作为软件的详细信息进行软件的介绍" class="form-control col-md-7 col-xs-12"></textarea>
                    </div>
                </div>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">LOGO图片<span class="required">*</span> </label>
                    <input id="f_upload" type="file" class="file" name="logo" />
                </div>


                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary" id="returnPre">返回</button>
                        <button type="submit" class="btn btn-success">提交</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</body>
<script type="text/javascript" src="/statics/js/jquery.min.js"></script>
<script>
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



    /*返回上一页*/
    $("#returnPre").click(function () {
        window.history.back(-1);
    })
</script>
</html>
