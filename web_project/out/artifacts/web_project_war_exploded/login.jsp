<%--
  Created by IntelliJ IDEA.
  User: baiyu
  Date: 2018/6/28
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="check.jsp" method="post">
    <table>
      <tr>
        <td>用户名 </td>
        <td><input type="text" name="mid" id="mid"/></td>
      </tr>
      <tr>
        <td>密码 </td>
        <td><input type="password" name="password"  id="password"/></td>
      </tr>
      <tr>
        <td><input type="submit" value="提交" /></td>
        <td><input type="reset" value="重置" /></td>
      </tr>
    </table>
  </form>
  </body>
</html>
