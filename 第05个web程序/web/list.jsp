<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 白玉廷
  Date: 2018/7/16
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${page['list']}" var="item">
    ${item.id} -> ${item.name} -> ${item.status} <br>
</c:forEach>
<c:if test="${page['pageNo']-1>=1}">
    <a href="/list?pageNo=${page['pageNo']-1}&pageSize=${page['pageSize']}">上一页</a>
</c:if>
<c:if test="${page['pageNo']+1<=(page['count']-1)/page['pageSize']+1}">
    <a href="/list?pageNo=${page['pageNo']+1}&pageSize=${page['pageSize']}">下一页</a>
</c:if>
</body>
</html>
