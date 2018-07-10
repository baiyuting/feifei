package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过注解配置的 Servlet， 路径是 /hello， 名字是 Servlet
 * <p>
 * 本类的目的
 * 1、知道 Servlet 通过注解的创建办法
 * 2、response 如何返回信息
 */
@WebServlet(name = "Servlet", urlPatterns = {"/hello"})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);//可以这样处理，这样只用写一个方法
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");// 设置 返回的 内容类型，是 text 文本或者 html，采用的编码是 utf-8，这样中文不会产生乱码
        response.getWriter().print("这是Servlet，通过注解生成");
    }
}
