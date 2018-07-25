<%--
  Created by IntelliJ IDEA.
  User: 白玉廷
  Date: 2018/7/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/list">返回列表</a><br>
id : ${goods.id}<br>
名称 : ${goods.name} <br>
描述：${goods.description}<br>
图片：<img src="${goods.image}"/><br>
上架状态：${goods.status}
</body>
</html>
