<%--
  Created by IntelliJ IDEA.
  User: 白玉廷
  Date: 2018/7/14
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript">
        window.onload = function (ev) {//页面初次加载，执行获取验证码方法
            verifyCode();
        }

        function verifyCode() {
            var xmlhttp;
            if (window.XMLHttpRequest) //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                xmlhttp = new XMLHttpRequest();
            else // IE6, IE5 浏览器执行代码
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    document.getElementById("imageCode").src = xmlhttp.responseText;
            }
            xmlhttp.open("GET", "/verifyCode", true);
            xmlhttp.send();
        }
    </script>
</head>
<body>
<form action="/login" method="post">
    用户名:<input type="text" id="username" name="username"><br>
    密码：<input type="text" id="password" name="password"><br>
    验证码：<input type="text" id="verifyCode" name="verifyCode"><br>
    <img src="#" id="imageCode" name="imageCode">
    <button onclick="verifyCode()">看不清，换一张</button>
    <br>
    <input type="submit" value="提交">
</form>
</body>
</html>
