<%--
  Created by IntelliJ IDEA.
  User: 白玉廷
  Date: 2018/7/21
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/add" method="post" enctype="multipart/form-data">
    商品名称：<input type="text" name="name" id="name"><br>
    商品描述：<input type="text" name="description" id="description"><br>
    商品图片：<input type="file" id="image" name="image"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
