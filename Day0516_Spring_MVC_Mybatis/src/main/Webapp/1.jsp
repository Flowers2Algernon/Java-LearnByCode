<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
</head>
<body>
<h2>注册页面</h2>
<form action="register" method="post" enctype="multipart/form-data">
    <label for="username">用户名:</label><br>
    <input type="text" id="username" name="username" required><br>
    <label for="password">密码:</label><br>
    <input type="password" id="password" name="password" required><br>
    <label for="avatar">头像:</label><br>
    <input type="file" id="avatar" name="avatar" accept="image/*"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
