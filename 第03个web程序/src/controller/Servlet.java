package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建 Servlet，路径是 /hello
 * <p>
 * 本类的目的
 * 展示 如果在 servlet 中获取数据
 */
@WebServlet(name = "Servlet", urlPatterns = {"/hello"})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求参数编码为 utf-8，防止 出现中文乱码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("用户名：" + username);
        System.out.println("密码：" + password);
    }
}
