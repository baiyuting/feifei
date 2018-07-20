package controller;

import service.Service;
import service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 老哥写了几个 Servlet ，发现总要写一些重复代码，所以老哥写一个 抽象类，BaseServlet，里面将一些共有操作先处理了
 */
@WebServlet(name = "BaseServlet")
public abstract class BaseServlet extends HttpServlet {

    protected Service service = new ServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//统一设置编码格式
        response.setContentType("text/html;charset=utf-8");//统一设置编码格式
        handle(request, response);
    }

    /**
     * 子类 统一在这个 方法中处理 get 和 post 方法
     *
     * @param request  请求
     * @param response 相应
     */
    protected abstract void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
