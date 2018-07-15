<%--
  Created by IntelliJ IDEA.
  User: baiyu
  Date: 2018/6/28
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page  pageEncoding="UTF-8"  contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<html>
  <head>
    <title>check</title>
  </head>
  <body>
  check。。。

  <%
    final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    final String URL = "jdbc:oracle:thin:@localhost:1521:mldn";
    final String USER = "scott";
    final String PASSWORD = "tiger";

    boolean flag=false;
    Connection conn=null;
    PreparedStatement pstm=null;
    ResultSet rs=null;
    String sql = "SELECT mid FROM member WHERE mid= ? AND password= ?";
%>
  <%
    try {
      Class.forName(DRIVER);
      conn = DriverManager.getConnection(URL, USER, PASSWORD);
      pstm = conn.prepareStatement(sql);
      pstm.setString(1,request.getParameter("mid"));
      pstm.setString(2, request.getParameter("password"));
      rs = pstm.executeQuery();
      if (rs.next()) {
          flag=true;
      }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      if(flag){
  %>
  <jsp:forward page="welcome.jsp"/>
  <%
        }else{
  %>
  <jsp:forward page="index.jsp"/>
  <%
        }
    }
  %>
  </body>
</html>
