<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/13
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/dev/add" method="post">
    开发者账号:<input name="devCode" type="text"/>
    <br/>
    开发者名称:<input type="text" name="devName"/>
    <br/>
    开发者密码:<input type="password" name="devPassword"/>
    <br/>
    电子邮件:<input type="email" name="devEmail"/>
    <br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
