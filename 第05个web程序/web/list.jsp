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
    <script type="text/javascript">
        function changeStatus(id, itemStatus) {//改变商品 id 的状态
            var element = document.getElementById("status_" + id + "_" + itemStatus);//获取 对应 select
            var status = element.options[element.selectedIndex].value;//获得选中的状态
            var xmlhttp;
            if (window.XMLHttpRequest) //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                xmlhttp = new XMLHttpRequest();
            else // IE6, IE5 浏览器执行代码
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            xmlhttp.open("post", "/update", true);//post请求
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("id=" + id + "&status=" + status);
        }

        window.onload = function () {//页面加载的时候需要 显示 select 的 上架状态，通过 option.select=true/false 的方式设置
            var selects = document.getElementsByName("status");//获取所有的 select
            for (var i = 0; i < selects.length; i++) {
                var options = selects[i].options;
                for (var j = 0; j < options.length; j++) {
                    var selectId = selects[i].getAttribute("id");
                    var status = selectId.substring(selectId.lastIndexOf("_") + 1, selectId.length);// 获取 该 select 对应的 商品的 上架状态
                    options[j].selected = options[j].value == status;//设置是否选中该 option
                }
            }
        }
    </script>
</head>
<body>
<a href="add.jsp">添加商品</a><br>
<c:forEach items="${page['list']}" var="item">
    ${item.id} -> ${item.name} ->
    <select onchange="changeStatus(${item.id},${item.status})" id="status_${item.id}_${item.status}" name="status">
        <option value="0">下架</option>
        <option value="1">上架</option>
    </select>
    <br>
</c:forEach>
<c:if test="${page['pageNo']-1>=1}">
    <a href="/list?pageNo=${page['pageNo']-1}&pageSize=${page['pageSize']}">上一页</a>
</c:if>
<c:if test="${page['pageNo']+1<=(page['count']-1)/page['pageSize']+1}">
    <a href="/list?pageNo=${page['pageNo']+1}&pageSize=${page['pageSize']}">下一页</a>
</c:if>
</body>
</html>
