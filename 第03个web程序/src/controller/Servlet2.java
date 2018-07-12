package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建 Servlet2 ，路径是 /hello2
 * <p>
 * 本类的目的：
 * Servlet 怎么返回数据，方式1
 * <p>
 * 只返回数据
 */
@WebServlet(name = "Servlet2", urlPatterns = {"/hello2"})
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("这是 Servlet 2");
    }
}
