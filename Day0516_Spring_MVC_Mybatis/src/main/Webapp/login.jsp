<%--
  Created by IntelliJ IDEA.
  User: ZhuJinhong
  Date: 2024/5/16
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
<form action="loginCL.jsp" method="post">
    <label for="username">用户名：</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">密码：</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="登录">
</form>
</body>
</html>

