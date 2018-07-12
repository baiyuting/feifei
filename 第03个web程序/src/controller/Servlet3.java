package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建 Servlet ，路径是 /hello3
 * <p>
 * Servlet 怎么返回数据，方式 2
 * <p>
 * 1、携带数据 request.setAttribute()
 * 2、跳转到 hello3.jsp 页面 request.getRequestDispatcher("/hello3.jsp").forward()
 */
@WebServlet(name = "Servlet3", urlPatterns = {"/hello3"})
public class Servlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("key1", "用<%=%>显示数据");
        request.setAttribute("key2", "用 el 表达式显示数据");
        request.getRequestDispatcher("/hello3.jsp").forward(request, response);
    }
}
