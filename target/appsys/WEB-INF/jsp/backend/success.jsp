<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/14
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>
        欢迎你,
        <c:if test="${USER!=null}">${USER.userName}</c:if>
        <c:if test="${DEVUSER!=null}">${DEVUSER.devName}</c:if>
        <span>|</span>
        角色:
        <c:if test="${DEVUSER!=null}">开发者账户</c:if>
        <c:if test="${USER!=null}">管理员账户</c:if>
        
    </h2>
</body>
</html>
